public class EjemploSalvajes {



    public static void main(String[] args) {
        Contenedor<String> StringContenedor = new Contenedor<>("Hola Mundo");
        Contenedor<Integer> IntegerContenedor = new Contenedor<>(10);

        imprimir(StringContenedor);
        imprimir(IntegerContenedor);
        imprimirNumeroDoble(IntegerContenedor); //*2 y acabe tipo double
        imprimirConMultiplicador(IntegerContenedor, 5);
    }

    private static void imprimirConMultiplicador(Contenedor<Integer> c, int multiplicador) {
        System.out.println(c.getObjeto().intValue() * multiplicador);
    }

    private static void imprimirNumeroDoble(Contenedor<Integer> c) {
        System.out.println(c.getObjeto().doubleValue() * 2);
    }

    private static void imprimir(Contenedor<?> c) {
        System.out.println(c.getObjeto());
    }
}

class Contenedor<T> {
    private T objeto;

    public Contenedor(T objeto) {
        this.objeto = objeto;
    }

    public T getObjeto() {
        return objeto;
    }

    public void setObjeto(T objeto) {
        this.objeto = objeto;
    }
}
