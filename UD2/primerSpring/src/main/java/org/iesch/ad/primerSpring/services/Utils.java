package org.iesch.ad.primerSpring.services;

import org.springframework.stereotype.Service;

@Service
public class Utils {
    public String transformarMayus(String texto) {
        return texto.toUpperCase();
    }
}
