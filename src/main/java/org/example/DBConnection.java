package org.example;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection{
    private final String url;
    private final int port;
    private final String dbName;
    private Connection connection;

    private static DBConnection instance;

    private DBConnection() throws SQLException, IOException {
        FileReader fileReader = new FileReader("C:\\Users\\mlafi\\IdeaProjects\\todo-app\\src\\main\\USERNAMES\\USER.properties");
        this.dbName = "tododb";
        this.port = 5432;
        this.url = "jdbc:postgresql://localhost:" + Integer.toString(this.port) + "/" + this.dbName;
        Properties props = new Properties();
        Properties b = new Properties() ;
        b.load(fileReader);
        props.setProperty("user",b.getProperty("dbUser"));
        props.setProperty("password",b.getProperty("dbPassword"));
        props.setProperty("ssl","false");
        this.connection = DriverManager.getConnection(url, props);
    }

    public Connection getConnection() {
        return this.connection;
    }

    public static DBConnection getInstance() throws SQLException, IOException {
        if(instance == null){
            instance = new DBConnection();
        }
        else if (instance.getConnection().isClosed()) {
            instance = new DBConnection();
        }
        return instance;
    }
}