package org.iesch.dgt.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.iesch.dgt.dto.TransferenciaRequest;
import org.iesch.dgt.modelo.HistorialTitulares;
import org.iesch.dgt.modelo.SituacionAdmistrativa;
import org.iesch.dgt.modelo.Vehiculos;
import org.iesch.dgt.modelo.enums.EstadoVehiculo;
import org.iesch.dgt.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    public Vehiculos crearVehiculo(Vehiculos vehiculos) throws Exception {
        if (vehicleRepository.existsByMatricula(vehiculos.getMatricula())) {
            throw new Exception("Matricula existente");
        }
        if (vehicleRepository.existsByBastidor(vehiculos.getBastidor())) {
            throw new Exception("Bastidor existente");
        }
        return (Vehiculos) vehicleRepository.save(vehiculos);
    }

    public Vehiculos darBajaDefinitiva(String matricula, String motivo, String certificadoDesguace) {
        Vehiculos vehiculos = buscarPorMatricula(matricula);

        SituacionAdmistrativa situacionAdmistrativa = vehiculos.getSituacionAdmistrativa();
        situacionAdmistrativa.setMotivoBaja(motivo);
        situacionAdmistrativa.setEstadoVehiculo(EstadoVehiculo.BAJA_DEFINITIVA);
        situacionAdmistrativa.setFechaEstado(LocalDateTime.now());

        vehiculos.setFechaActualizacion(LocalDateTime.now());

        Vehiculos vehiculosActualizado = (Vehiculos) vehicleRepository.save(vehiculos);

        return vehiculosActualizado;
    }

    public Vehiculos buscarPorMatricula(String matricula) {
        return vehicleRepository.findByMatricula(matricula);
    }

    public Vehiculos darBajaTemporal(String matricula, String motivo) {
        Vehiculos vehiculos = buscarPorMatricula(matricula);

        LocalDateTime fechaInicio = LocalDateTime.now();
        LocalDateTime fechaFin = fechaInicio.plusYears(1);

        SituacionAdmistrativa situacionAdmistrativa = vehiculos.getSituacionAdmistrativa();
        situacionAdmistrativa.setEstadoVehiculo(EstadoVehiculo.BAJA_TEMPORAL);
        situacionAdmistrativa.setMotivoBaja(motivo);
        situacionAdmistrativa.setFechaBajaTemporal(fechaInicio);
        situacionAdmistrativa.setFechaFinBajaTemporal(fechaFin);
        situacionAdmistrativa.setFechaEstado(fechaInicio);

        vehiculos.setFechaActualizacion(LocalDateTime.now());
        Vehiculos vehiculosActualizado = (Vehiculos) vehicleRepository.save(vehiculos);
        return vehiculosActualizado;
    }

    public Vehiculos reativarBajaTemporal(String matricula) {
        Vehiculos vehiculos = buscarPorMatricula(matricula);

        SituacionAdmistrativa situacionAdmistrativa = vehiculos.getSituacionAdmistrativa();
        situacionAdmistrativa.setEstadoVehiculo(EstadoVehiculo.ACTIVO);
        situacionAdmistrativa.setMotivoBaja(null);
        situacionAdmistrativa.setFechaEstado(LocalDateTime.now());
        situacionAdmistrativa.setFechaBajaTemporal(null);
        situacionAdmistrativa.setFechaFinBajaTemporal(null);

        vehiculos.setFechaActualizacion(LocalDateTime.now());

        Vehiculos vehiculoActualizado = (Vehiculos) vehicleRepository.save(vehiculos);
        return vehiculoActualizado;
    }

    public Vehiculos transferenciaVehiculo(String matricula, TransferenciaRequest request) {
        Vehiculos vehiculos = buscarPorMatricula(matricula);

        HistorialTitulares historial = new HistorialTitulares();
        historial.setDni(vehiculos.getTitular().getDni());
        historial.setNombre(vehiculos.getTitular().getNombre());
        historial.setApellidos(vehiculos.getTitular().getApellidos());
        historial.setFechaInicio(LocalDateTime.from(vehiculos.getFechaPrimeraMatriculacion()));
        historial.setFechaFin(LocalDateTime.now());
        historial.setMotivoTransferencia(request.getMotivoTransferencia());

        vehiculos.getHistorialTitulares().add(historial);
        vehiculos.setTitular(request.getNuevoTitular());

        vehiculos.setFechaActualizacion(LocalDateTime.now());

        Vehiculos vehiculoActualizado = (Vehiculos) vehicleRepository.save(vehiculos);

        return vehiculoActualizado;
    }


}
