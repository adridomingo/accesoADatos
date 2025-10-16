package org.iesch.ad.primerSpring.services;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class Utils {

    public String transformarMayus(String texto) {
        return texto.toUpperCase();
    }

    public String generarContrasenasAZ(int numeroCaracteres) {
        final String LETTERS = "abcdefghijklmnñopqrstuwxyzABCDEFGHIJKMLNÑOPQRSTUWXYZ";
        StringBuilder passwd = new StringBuilder(numeroCaracteres);
        Random random = new Random();
        for (int i = 0; i < numeroCaracteres ; i++) {
            int index = random.nextInt(LETTERS.length());
            passwd.append(LETTERS.charAt(index));
        }
        return passwd.toString();
    }

    public String generarContrasenasAlfanumericas(int longitud) {
        final String LETTERS = "abcdefghijklmnñopqrstuwxyzABCDEFGHIJKMLNÑOPQRSTUWXYZ0123456789";
        StringBuilder passwd = new StringBuilder(longitud);
        Random random = new Random();
        for (int i = 0; i < longitud ; i++) {
            int index = random.nextInt(LETTERS.length());
            passwd.append(LETTERS.charAt(index));
        }
        return passwd.toString();
    }

    public Double convierteAmillas(int km) {
        float aMillas = 1.609F;
        Double millas = (double) km/aMillas;

        return millas;
    }

    public float conTemperaturas(float temperatura) {
        return (temperatura * 9 / 5) + 32;
    }
}
