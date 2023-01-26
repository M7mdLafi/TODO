package org.example;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
public class App
{
    public static void main(String[] args) {
        try {
            DBConnection db = DBConnection.getInstance();
            // Insert
            Task t2 = new Task("Ahmad Al-Ghamdi","Submit CPIT-250 assignment",true);
            Task t = new Task(2,"Mohammed Al-Ghamdi","Submit CPIT-251 assignment",true);
            Task t3= new Task(7);
            //t.insertTask();

            // Update a Task
            t2.updateTask();

            // Delete a task
            t3.deleteTask();

            // Retrieve all tasks
            t.retrieveTasks();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
