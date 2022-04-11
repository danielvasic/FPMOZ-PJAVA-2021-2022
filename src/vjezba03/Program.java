package vjezba03;

import vjezba03.model.User;

import java.sql.SQLException;

public class Program {

    public static void main (String [] args){
        try {
            /*
            User marko = new User("Marko", "Markić", "1231231231232", "nastavnik");
            marko.save();
            for (User u: User.getAll())
                System.out.println(u);
             */
            User u = User.getInstance(3);
            u.setName("Mirko");
            u.setSurname("Mirkić");
            u.update();

            User u2 = User.getInstance(7);
            u2.delete();
            System.out.println(u2);
        } catch (SQLException e) {
            System.out.println("There was an error running query: " + e.getMessage());
            // e.printStackTrace();
        }
    }
}
