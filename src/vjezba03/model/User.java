package vjezba03.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class User {
    private int ID;
    private String name;
    private String surname;
    private String uid;
    private String role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(int ID, String name, String surname, String uid, String role) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.uid = uid;
        this.role = role;
    }

    public User(String name, String surname, String uid, String role) {
        this.name = name;
        this.surname = surname;
        this.uid = uid;
        this.role = role;
    }

    public boolean save () throws SQLException {
        String sql = "INSERT INTO korisnik VALUES (null, ?, ?, ?, ?)"; // napravi sql upit
        PreparedStatement statement = Database.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); // napravi statement objekt preko kojeg se izvršava SQL upit
        statement.setString(1, this.name); // postavi parametar ime
        statement.setString(2, this.surname); // postavi parametar prezime
        statement.setString(3, this.uid); // postavi parametar jmbg
        statement.setString(4, this.role); // postavi parametar uloga
        int affectedRows = statement.executeUpdate(); // izvrši upit
        if (affectedRows == 0) return false; // ukoliko je broj redaka na koje smo utjecali jedak 0 vrati false
        ResultSet generatedKeys = statement.getGeneratedKeys(); // dohvaćanje generiranih ključeva
        if (generatedKeys.next()) this.ID = generatedKeys.getInt(1); // postavlja id na generirani ključ
        return true; // uspješno dodali podatak u bazu
    }

    public boolean update () throws SQLException {
        String sql = "UPDATE korisnik SET ime=?, prezime=?, JMBG=?, uloga=? WHERE ID=?";
        PreparedStatement statement = Database.connection.prepareStatement(sql);
        statement.setString(1, this.name);
        statement.setString(2, this.surname);
        statement.setString(3, this.uid);
        statement.setString(4, this.role);
        statement.setInt(5, this.ID);
        int affectedRows = statement.executeUpdate();
        return affectedRows != 0;
    }



    public static User getInstance(int id) throws SQLException {
        String sql = "SELECT * FROM korisnik WHERE id=" + String.valueOf(id);
        Statement statement = Database.connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        result.next();
        return new User(
            result.getInt("ID"),
            result.getString("ime"),
            result.getString("prezime"),
            result.getString("jmbg"),
            result.getString("uloga")
        );
    }

    public boolean delete () throws SQLException {
        String query = "DELETE FROM korisnik WHERE id = ?";
        PreparedStatement statement = Database.connection.prepareStatement(query);
        statement.setInt(1, this.ID);
        int affectedRows = statement.executeUpdate();
        return affectedRows != 0;
    }

    public static List<User> getAll () throws SQLException {
        List<User> users = new ArrayList<>(); // prazna lista
        String query = "SELECT * FROM korisnik"; // upit
        Statement statement = Database.connection.createStatement(); // stvaranje objekta za izvršavanje upita
        ResultSet result = statement.executeQuery(query); // izvršavanje upita i vraćanje svih rezultata
        while (result.next()){ // dok postoji rezultat
            users.add(new User(
                    result.getInt("ID"),
                    result.getString("ime"),
                    result.getString("prezime"),
                    result.getString("jmbg"),
                    result.getString("uloga")
            ));
        }
        return users; // lista nije prazna
    }

    @Override
    public String toString() {
        return  name + " " + surname + " " + uid;
    }
}
