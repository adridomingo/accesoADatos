package org.iesch.ad.Ev2_Hotel.controlador;

import org.iesch.ad.Ev2_Hotel.dto.*;
import org.iesch.ad.Ev2_Hotel.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    ReservaService reservaService;

    @PostMapping
    public ResponseEntity<CrearReservaResponse> crearReserva(@RequestBody CrearReservaRequest request) {
        CrearReservaResponse response = reservaService.crearReserva(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{codigo}/check-in")
    public ResponseEntity<CheckInResponse> realizarCheckIn(@PathVariable String codigo, @RequestBody CheckInRequest request) {
        CheckInResponse response = reservaService.realizarCheckIn(codigo, request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{codigo}/check-out")
    public ResponseEntity<CheckOutResponse> hacerCheckOut(@PathVariable String codigo) {
        CheckOutResponse response = reservaService.realizarCheckOut(codigo);
        return ResponseEntity.ok(response);
    }
}
