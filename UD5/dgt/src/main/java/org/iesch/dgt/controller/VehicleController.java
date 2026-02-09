package org.iesch.dgt.controller;

import org.iesch.dgt.dto.BajaDefinitivaRequest;
import org.iesch.dgt.dto.BajaTemporalRequest;
import org.iesch.dgt.dto.TransferenciaRequest;
import org.iesch.dgt.modelo.Vehiculos;
import org.iesch.dgt.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehículos")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<?> darAltaVehiculo(@RequestBody Vehiculos vehiculos) throws Exception {
        Vehiculos vehiculoCreado = vehicleService.crearVehiculo(vehiculos);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{matricula}/baja-definitiva")
    public ResponseEntity<?> darBajaDefinitiva(@PathVariable String matricula, @RequestBody BajaDefinitivaRequest request) {
        Vehiculos vehiculos = vehicleService.darBajaDefinitiva(matricula, request.getMotivo(), request.getCertificadoDesguace());
        return ResponseEntity.ok(vehiculos);
    }

    @PutMapping("{matricula}/baja-temporal")
    public ResponseEntity<?> darBajaTemporal(@PathVariable String matricula, @RequestBody BajaTemporalRequest request) {
        Vehiculos vehiculos = vehicleService.darBajaTemporal(matricula, request.getMotivo());
        return ResponseEntity.ok(vehiculos);
    }

    @PutMapping("{matricula}/reactivar")
    public ResponseEntity<?> reactivarBajaTemporal(@PathVariable String matricula) {
        Vehiculos vehiculos = vehicleService.reativarBajaTemporal(matricula);
        return ResponseEntity.ok(vehiculos);
    }

    @PutMapping("{matricula}/transferencia")
    public ResponseEntity<?> transferenciaVehiculos(@PathVariable String matricula, @RequestBody TransferenciaRequest request) {
        Vehiculos vehiculos = vehicleService.transferenciaVehiculo(matricula, request);
        return ResponseEntity.ok(vehiculos);
    }


}
