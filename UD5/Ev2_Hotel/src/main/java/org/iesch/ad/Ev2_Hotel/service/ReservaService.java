package org.iesch.ad.Ev2_Hotel.service;

import org.iesch.ad.Ev2_Hotel.dto.*;
import org.iesch.ad.Ev2_Hotel.modelo.Cliente;
import org.iesch.ad.Ev2_Hotel.modelo.Reserva;
import org.iesch.ad.Ev2_Hotel.modelo.Suplemento;
import org.iesch.ad.Ev2_Hotel.modelo.enums.*;
import org.iesch.ad.Ev2_Hotel.repository.ClienteRepositorio;
import org.iesch.ad.Ev2_Hotel.repository.HabitacionRepositorio;
import org.iesch.ad.Ev2_Hotel.repository.RepositorioReservas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.iesch.hotel.modelo.Habitacion;
import org.springframework.web.bind.annotation.PutMapping;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    RepositorioReservas repositorioReservas;
    @Autowired
    HabitacionRepositorio habitacionRepositorio;
    @Autowired
    ClienteRepositorio clienteRepositorio;

    public CrearReservaResponse crearReserva(CrearReservaRequest request) {
        Cliente cliente = clienteRepositorio.findByDni(request.getDniCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        LocalDateTime ahora = LocalDateTime.now();
        if (request.getFechaEntrada().isBefore(ahora.plusHours(24))) {
            throw new RuntimeException("No se permiten reservas con menos de 24h");
        }

        if (!request.getFechaSalida().isBefore(request.getFechaEntrada())) {
            throw new RuntimeException("Fecha salida posterior a fecha entrada");
        }

        long numeroNoches = request.getFechaEntrada().toLocalDate()
                .until(request.getFechaSalida().toLocalDate()).getDays();

        TipoHabitacion tipoSolicitado = request.getTipoHabitacion();
        TipoHabitacion tipoAsignado = tipoSolicitado;

        if (cliente.getEsVIP() && cliente.getTotalReservas() > 5) {
            tipoAsignado = aplicarUpgradeVip(tipoSolicitado);
        }

        List<Habitacion> habitacionesDiponibles = habitacionRepositorio.findByTipoHabitacionAndEstado(tipoAsignado, EstadoHabitacion.DISPONIBLE);
        if (habitacionesDiponibles.isEmpty()) {
            throw new RuntimeException("No hay habitaciones disponibles del tipo");
        }

        Habitacion habitacionAsignada = null;
        for (Habitacion hab : habitacionesDiponibles) {
            if (!tieneReservasEnFechas(hab.getNumero(), request.getFechaEntrada(), request.getFechaSalida())) {
                habitacionAsignada = hab;
                break;
            }
        }
        if (habitacionAsignada == null) {
            throw new RuntimeException("No hay habitaciones disponibles en esa fecha");
        }

        Temporada temporada = determinarTemporada(request.getFechaEntrada());

        double precioBaseNoche = habitacionAsignada.getPrecioBaseNoche();
        double precioBase = precioBaseNoche * numeroNoches;

        double multiplicadorTemporada = switch (temporada) {
            case ALTA -> 1.30;
            case MEDIA -> 1.0;
            case BAJA -> 0.8;
        };

        precioBase *= multiplicadorTemporada;

        double costeTotalRegimen = calcularCosteRegimen(request.getRegimen(), (int) numeroNoches);

        double descuento = 0.0;
        if (numeroNoches > 7) {
            descuento = precioBase*0.15;
        }

        double precioTotal = precioBase - descuento;

        String codigo = generarCodigoReserva();

        Reserva reserva = new Reserva();
        reserva.setCodigo(codigo);
        reserva.setDniCliente(request.getDniCliente());
        reserva.setNumeroHabitacion(habitacionAsignada.getNumero());
        reserva.setTipoHabitacion(tipoAsignado);
        reserva.setFechaEntrada(request.getFechaEntrada());
        reserva.setFechaSalida(request.getFechaSalida());
        reserva.setNumeroNoches((int) numeroNoches);
        reserva.setNumeroPersonas(request.getNumeroPersonas());
        reserva.setRegimen(request.getRegimen());
        reserva.setTemporada(temporada);
        reserva.setEstado(EstadoReserva.PENDIENTE_CONFIRMACION);
        reserva.setPrecioBase(precioBase);
        reserva.setDescuentoAplicado(descuento);
        reserva.setPrecioTotal(precioTotal);
        reserva.setFechaCreacion(LocalDateTime.now());
        reserva.setSuplementos(new ArrayList<>());
        reserva.setServiciosAdicionales(new ArrayList<>());

        if (cliente.getEsVIP() && tipoAsignado != tipoSolicitado) {
            reserva.setObservaciones("Upgrade automatico del tipo solicitado");
        }

        repositorioReservas.save(reserva);

        CrearReservaResponse response = new CrearReservaResponse();
        response.setCodigo(codigo);
        response.setEstado(EstadoReserva.PENDIENTE_CONFIRMACION.toString());
        response.setPrecioTotal(precioTotal);
        response.setDescuentoAplicado(descuento);
        response.setNumeroNoches((int) numeroNoches);
        response.setHabitacionAsignada(habitacionAsignada.getNumero());

        return response;
    }

    private String generarCodigoReserva() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String fecha = LocalDateTime.now().format(formatter);
        String aleatorio = String.format("%04d", (int) (Math.random() * 1000));

        return "HTL-" + fecha + "-" + aleatorio;
    }

    private double calcularCosteRegimen(Regimen regimen, int numeroNoches) {
        double costePorDia = switch (regimen) {
            case SOLO_ALOJAMIENTO -> 0.0;
            case DESAYUNO -> 15.0;
            case MEDIA_PENSION -> 35.0;
            case PENSION_COMPLETA -> 55.0;
        };
        return costePorDia*numeroNoches;
    }

    private Temporada determinarTemporada(LocalDateTime fechaentrada) {
        int mes = fechaentrada.getMonthValue();

        if (mes >= 3 && mes <=8 ) {
            return Temporada.ALTA;
        }


    }

    private boolean tieneReservasEnFechas(String numero, LocalDateTime fechaEntrada, LocalDateTime fechaSalida) {
        List<EstadoReserva> estadoActivos = Arrays.asList(
                EstadoReserva.CONFIRMADA,
                EstadoReserva.EN_CURSO
        );
        List<Reserva> reservasHabitacion = repositorioReservas.findByNumeroHabitacionAndEstadoIn(numero, estadoActivos);

        for (Reserva r : reservasHabitacion) {
            boolean solapamiento = !(fechaSalida.isBefore(r.getFechaEntrada())
            || fechaEntrada.isAfter(r.getFechaEntrada()));

            if (solapamiento) return true;
        }
        return false;
    }

    private TipoHabitacion aplicarUpgradeVip(TipoHabitacion tipoSolicitado) {
        return switch (tipoSolicitado) {
            case INDIVIDUAL -> TipoHabitacion.DOBLE;
            case DOBLE -> TipoHabitacion.SUITE;
            case SUITE -> TipoHabitacion.SUITE;
        };
    }

    // PUT
    public CheckInResponse realizarCheckIn(String codigo, CheckInRequest request) {

        Reserva reserva = repositorioReservas.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        if (reserva.getEstado() != EstadoReserva.CONFIRMADA) {
            throw new RuntimeException("La reserva debe estar en estado confirmado");
        }

        LocalDateTime ahora = LocalDateTime.now();

        if (!reserva.getFechaEntrada().toLocalDate().equals(ahora.toLocalDate())) {
            throw new RuntimeException("CheckIn solo el dia de la reserva");
        }

        Cliente cliente = clienteRepositorio.findByDni(reserva.getDniCliente())
                .orElseThrow(() -> new RuntimeException("El cliente tiene deudas pendientes "));

        double suplementoEarlyCheckIn = 0.0;
        LocalTime horaActual = ahora.toLocalTime();
        LocalTime limitEarly = LocalTime.of(14, 00);

        if (horaActual.isBefore(limitEarly)) {
            suplementoEarlyCheckIn = 20.0;
            Suplemento suplemento = new Suplemento();
            suplemento.setTipo("EARLY_CHECK_IN");
            suplemento.setImporte(suplementoEarlyCheckIn);
            reserva.getSuplementos().add(suplemento);
        }

        Habitacion habitacion = habitacionRepositorio.findByNumero(reserva.getNumeroHabitacion())
                .orElseThrow(() -> new RuntimeException("Habitacion no encontrada"));

        habitacion.setEstado(EstadoHabitacion.OCUPADA);
        habitacionRepositorio.save(habitacion);

        reserva.setEstado(EstadoReserva.EN_CURSO);
        reserva.setHoraEntradaReal(ahora);
        reserva.setMetodoPago(request.getMetodoPago());
        reserva.setPrecioTotal(reserva.getPrecioTotal() + suplementoEarlyCheckIn);
        reserva.setFechaActualizacion(ahora);

        if (request.getObservaciones() != null && !request.getObservaciones().isEmpty()) {
            String obsActuales = reserva.getObservaciones();
            reserva.setObservaciones(obsActuales + " " + reserva.getObservaciones());
        }

        repositorioReservas.save(reserva);

        CheckInResponse response = new CheckInResponse();
        response.setCodigo(codigo);
        response.setEstado(EstadoReserva.EN_CURSO.toString());
        response.setHoraEntrada(ahora);
        response.setSuplementoEarlyCheckIn(suplementoEarlyCheckIn);
        response.setPrecioFinal(response.getPrecioFinal());

        return response;

    }

    public CheckOutResponse realizarCheckOut(String codigo) {
        Reserva reserva = repositorioReservas.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Reserva erronea"));

        if (reserva.getEstado() != EstadoReserva.EN_CURSO) {
            throw  new RuntimeException("Reserva debe estar en curso");
        }

        LocalDateTime ahora = LocalDateTime.now();

        double suplementoLateCheckOut = 0.0;
        LocalTime horaActual = ahora.toLocalTime();
        LocalTime limit = LocalTime.of(12, 00);

        if (horaActual.isAfter(limit)) {
            suplementoLateCheckOut = 25.0;
            Suplemento suplemento = new Suplemento();
            suplemento.setTipo("LATE_CHECK_OUT");
            suplemento.setImporte(suplementoLateCheckOut);
            reserva.getSuplementos().add(suplemento);
        }

        double totalServicios = reserva.getServiciosAdicionales().stream()
                .mapToDouble(Suplemento::getImporte).sum();
    }
}
