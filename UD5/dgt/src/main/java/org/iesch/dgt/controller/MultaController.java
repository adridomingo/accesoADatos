package org.iesch.dgt.controller;

import org.iesch.dgt.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehiculos")
public class MultaController {

    @Autowired
    VehicleService vehicleService;

//    @PutMapping("{matricula}/multas")
//    public ResponseEntity<?> pagarMulta(@PathVariable String matricula) {
//        vehicleService.pagarMulta(matricula, )
//    }

}
