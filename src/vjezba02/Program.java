package vjezba02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Program {
    public static void main (String [] args){
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/pjava-ednevnik?user=root&password="
            );
            PreparedStatement stmnt = conn.prepareStatement(
                    "INSERT INTO korisnik VALUES (null, ?, ?, ?, ?)"
            );
            stmnt.setString(1, "Pero");
            stmnt.setString(2, "Perić");
            stmnt.setString(3, "1234567891011");
            stmnt.setString(4, "nastavnik");
            stmnt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
