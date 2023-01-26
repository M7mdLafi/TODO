package org.example;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

public class Task {
    private int id;
    private String name;

    private String username;

    private boolean isComplete;


    public Task(String username, String name, boolean isComplete){
        this.name = name;
        this.isComplete = isComplete;
        this.username = username;
    }
    public Task(int id,String username, String name, boolean isComplete){
        this.id = id;
        this.name = name;
        this.isComplete = isComplete;
        this.username = username;
    }
    public Task(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.name = username;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public void insertTask(){
        try {
            Connection dbConnection = DBConnection.getInstance().getConnection();
            Statement stmt = dbConnection.createStatement();
            PreparedStatement insertStmt =
                    dbConnection.prepareStatement("INSERT INTO todo (task, done, username) VALUES (?, ?, ?);");
            insertStmt.setString(1, this.name);
            insertStmt.setBoolean(2, (this.isComplete));
            insertStmt.setString(3, this.username);
            int rows = insertStmt.executeUpdate();
            System.out.println("Rows affected: " + rows);
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void retrieveTasks(){
        try {
            Connection dbConnection = DBConnection.getInstance().getConnection();
            Statement stmt = dbConnection.createStatement();
            String query = "SELECT id, username, task, done FROM todo";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                //Display values
                String row =  "ID: " + rs.getInt("id") +
                        " Username: " + rs.getString("username") +
                        " Task: " + rs.getString("task") +
                        " Done: " + rs.getBoolean("done") +
                        "\n";
                System.out.print(row);
            }
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateTask() throws SQLException {try {
        Connection dbConnection = DBConnection.getInstance().getConnection();
        Statement stmt = dbConnection.createStatement();
        PreparedStatement updateStmt = dbConnection.prepareStatement("UPDATE todo SET task=?, done=?, username=? WHERE id=?;");

        updateStmt.setString(1, this.name);
        updateStmt.setBoolean(2, this.isComplete);
        updateStmt.setString(3, this.username);
        updateStmt.setInt(4, this.id);
        int rows = updateStmt.executeUpdate();
        System.out.println("Updated Rows affected: " + rows);
    } catch (SQLException | IOException e) {
        e.printStackTrace();
    }
    }
    public void deleteTask() throws SQLException {
        try {
            Connection dbConnection = DBConnection.getInstance().getConnection();
            Statement stmt = dbConnection.createStatement();
            PreparedStatement updateStmt = dbConnection.prepareStatement("DELETE FROM todo  WHERE id=?;");
            updateStmt.setInt(1, id);
            int rows = updateStmt.executeUpdate();
            System.out.println("Rows affected: " + rows);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        }

    public String toString(){
        return "Username: "+ this.username +"Task: " + this.name + "\nDone: " + (this.isComplete ? "1": "0");
    }
}
