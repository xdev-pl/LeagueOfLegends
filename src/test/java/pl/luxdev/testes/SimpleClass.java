package pl.luxdev.testes;

@SuppressWarnings("unchecked")
public class SimpleClass {

    public static void main(String[] args) {
        SimpleManager manager = new SimpleManager();
        manager.getFilteredList(o -> o.getId() > 1, o -> o.getName().startsWith("C")).forEach(o -> System.out.println(o.getName() + ":" + o.getId()));
    }

}
