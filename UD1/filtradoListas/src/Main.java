import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Pepe");
        list.add("Juan");
        list.add("Maria");
        list.add("Pedro");
        list.add("Maria");

        List<String> list2 = new ArrayList<>(list);
        list2.removeIf((n) -> !n.toUpperCase().startsWith("M"));
        System.out.println(list2);
    }

}