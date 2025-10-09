package Ejercicios;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Ejercicio3 {
    public static void main(String[] args) {
        File archivoOrigen = new File("texto.txt");
        FileInputStream entradaOrigen;
        try {
            // abrimos archivo lectura
            entradaOrigen = new FileInputStream(archivoOrigen);

            // creamos archivo para escritura
            File archivoDestino = new File("destinoCifrado.txt");
            FileOutputStream salidaDestino = new FileOutputStream(archivoDestino);

            // Generamos una clave
            String clave = "MiClaveDeCifrado";
            SecretKeySpec claveCifrado = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8), "AES");

            // Iniciar el cifrador
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, claveCifrado);

            // Cifrar
            // Primero leemos el fichero
            byte[] buffer = new byte[1024];
            int bytesLeidos;

            while ((bytesLeidos= entradaOrigen.read(buffer)) != -1) {
                byte[] bytesCifrados = cipher.update(buffer, 0 , bytesLeidos);
                // Escribimos en el fichero de salida
                salidaDestino.write(bytesCifrados);
            }
            byte[] bytesCifrados = cipher.doFinal();
            salidaDestino.write(bytesCifrados);

            entradaOrigen.close();
            salidaDestino.close();

            System.out.println("Fichero Cifrado");
            System.out.println("Vamos a descifrar");

            // abrimos archivo encriptado
            FileInputStream entradaCifrado = new FileInputStream(archivoDestino);
            FileOutputStream salidaDescifrada = new FileOutputStream(new File("descifrado.txt"));

            // Iniciar el descifrador
            cipher.init(Cipher.DECRYPT_MODE, claveCifrado);

            // Tratar el fichero Cifrado
            while ((bytesLeidos= entradaCifrado.read(buffer)) != -1) {
                byte[] bytesDescifrados = cipher.update(buffer, 0 , bytesLeidos);
                // Escribimos en el fichero de salida
                salidaDescifrada.write(bytesDescifrados);
            }
            byte[] bytesDescifrados = cipher.doFinal();
            salidaDescifrada.write(bytesDescifrados);

            entradaCifrado.close();
            salidaDescifrada.close();

            System.out.println("DESCIFRADO");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }
}
