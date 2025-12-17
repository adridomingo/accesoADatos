package org.iesch.ad.Ev1_Ej3.controlador;

import org.iesch.ad.Ev1_Ej3.model.Cliente;
import org.iesch.ad.Ev1_Ej3.service.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientesController {

    @Autowired
    ClientesService clientesService;

//    @GetMapping("/api/clientesPorFecha?fecha={fecha}")
//    public ResponseEntity<?> clientesPorFecha(@PathVariable String fecha) {
//        List<Cliente> list = clientesService.clientesPorFecha(fecha);
//
//        if (list.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(list);
//    }


}
