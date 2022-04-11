package vjezba01;

public class Program extends Object {
    public static void main (String [] args){
        Osoba o1 = new Osoba(
                "Ivo" ,
                "Ivić"
        );
        Osoba o2 = new Osoba(
                "Pero", "Perić"
        );
        System.out.println(o1);
        System.out.println(o2);
    }
}
