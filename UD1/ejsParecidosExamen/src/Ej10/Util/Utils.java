package Ej10.Util;

import Ej10.Modelo.Producto;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Utils {

    public static List<Producto> cargarProductos() throws IOException {
        String DATA_FILE = "src/Ej10/products.csv";
        String WORKING_DIRECTORY = System.getProperty("user.dir");
        Path path = Paths.get(WORKING_DIRECTORY + File.separator + DATA_FILE);
        final List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

        List<Producto> productoList = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            StringTokenizer tokenizer = new StringTokenizer(lines.get(i), ",");
            Producto producto = new Producto();

            producto.setProductID(Integer.parseInt(tokenizer.nextToken()));
            producto.setProductName(tokenizer.nextToken());
            producto.setSupplierID(Integer.parseInt(tokenizer.nextToken()));
            producto.setCategoryID(Integer.parseInt(tokenizer.nextToken()));
            producto.setQuantityUnit(tokenizer.nextToken());
            producto.setUnitPrice(Double.parseDouble(tokenizer.nextToken()));
            producto.setUnitStock(Integer.parseInt(tokenizer.nextToken()));
            producto.setUnitOrder(Integer.parseInt(tokenizer.nextToken()));
            producto.setReorderLevel(Integer.parseInt(tokenizer.nextToken()));
            producto.setDiscontinued(Integer.parseInt(tokenizer.nextToken()));

            productoList.add(producto);
        }

        return productoList;
    }
}
