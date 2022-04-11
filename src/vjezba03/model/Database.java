package vjezba03.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private String host;
    private String user;
    private String password;
    private String db;

    private Connection conn;

    public static Database database;
    public static Connection connection;

    static {
        database = new Database();
        connection = database.getConn();
    }

    public Connection getConn() {
        return conn;
    }

    public Database(String host, String user, String password, String db) {
        this.host = host;
        this.user = user;
        this.password = password;
        this.db = db;
        this.connect();
    }

    public Database() {
        this.host = "localhost";
        this.user = "root";
        this.password = "";
        this.db = "pjava-ednevnik";
        this.connect();
    }

    private void connect () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://"+this.host+"/"+this.db+"?user="+this.user+"&password="+this.password
            );
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("There is an problem with database connection: "+ e.getMessage());
            e.printStackTrace();
        }

    }
}
