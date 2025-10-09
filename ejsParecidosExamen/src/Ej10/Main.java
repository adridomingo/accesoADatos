package Ej10;

import Ej10.Modelo.Producto;
import Ej10.Util.Utils;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Producto> products;
        try {
            products = Utils.cargarProductos();

            System.out.println("1 ---------------------------------------------------");
            //Imprimir
            products.stream().forEach(System.out::println);
            System.out.println("2 ---------------------------------------------------");

            products.stream().map(Producto::getProductName).forEach(System.out::println);
            System.out.println("3 ---------------------------------------------------");

            products.stream().filter(p -> p.getUnitStock() < 10)
                    .map(Producto::getProductName)
                    .forEach(System.out::println);
            System.out.println("4 ---------------------------------------------------");

            products.stream().filter(p -> p.getUnitStock() < 10)
                    .sorted(Comparator.comparingInt(Producto::getUnitStock))
                    .map(Producto::getProductName)
                    .forEach(System.out::println);
            System.out.println("5 ---------------------------------------------------");

            products.stream().filter(p -> p.getUnitStock() < 10)
                    .sorted(Comparator.comparingInt(Producto::getUnitStock).reversed())
                    .map(Producto::getProductName)
                    .forEach(System.out::println);
            System.out.println("5 ---------------------------------------------------");

            products.stream().filter(p -> p.getUnitStock() < 10)
                    .sorted()
                    .map(Producto::getProductName)
                    .forEach(System.out::println);

            System.out.println("6 ---------------------------------------------------");

            products.stream().filter(producto -> producto.getUnitStock() < 10)
                    .sorted(Comparator.comparing(Producto::getUnitStock)
                            .reversed().thenComparing(Producto::getProductName))
                    .map(Producto::getProductName)
                    .forEach(System.out::println);
            System.out.println("7 ---------------------------------------------------");

            Map<Integer, Long> c1 = products.stream()
                    .collect(Collectors.groupingBy(
                            Producto::getSupplierID, Collectors.counting()));

            c1.forEach((s, c) -> System.out.printf("Proveedor: %s productos: %s\n", s, c));

            System.out.println("8 ---------------------------------------------------");
            List<Map.Entry<Integer, Double>> entryList = products.stream()
                    .collect(Collectors.groupingBy(Producto::getUnitStock,
                            Collectors.summingDouble(Producto::getUnitPrice)))
                    .entrySet().stream().filter(p -> p.getValue()>100).toList();

            entryList.forEach(list -> System.out.printf("En stock %s, Suma: %s\n", list.getKey(), list.getValue()));

            System.out.println();

            System.out.println("9 ---------------------------------------------------");
            Double media = products.stream()
                    .collect(Collectors.averagingInt(Producto::getUnitStock));
            System.out.printf("Media: %s\n", media);

            System.out.println("10 ---------------------------------------------------");

            Optional<Producto> producto = products.stream().max(Comparator.comparing(Producto::getUnitPrice));
            System.out.println(producto.get());

            System.out.println("11 ---------------------------------------------------");
            products.stream().limit(50)
                    .forEach(System.out::println);

            System.out.println("IMPRIME DESDE EL 10 A ADELANTE");

            products.stream().skip(10).forEach(System.out::println);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
