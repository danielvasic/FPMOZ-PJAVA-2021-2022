package vjezba01.iznimke;

public class Program {
    public static void main (String [] args){
        try {
            System.out.println(1 / 0);
        } catch (ArithmeticException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Kraj programa ...");
    }
}
