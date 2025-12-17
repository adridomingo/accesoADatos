package org.iesch.ad.Ev1_Ej3.service;

import org.iesch.ad.Ev1_Ej3.model.Cliente;
import org.iesch.ad.Ev1_Ej3.repositorio.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientesService {

    @Autowired
    ClientesRepository clientesRepository;

//    public List<Cliente> clientesPorFecha(String fecha) {
//        return clientesRepository.findPedidosByFecha(fecha);
//    }


}
