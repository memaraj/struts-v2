package com.example.proj.action;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Connection;                
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;
import com.example.proj.model.Person;

public class List extends ActionSupport {

    ArrayList<Person> persons = new ArrayList<Person>();

    public ArrayList<Person> getPersons() {  
        return persons;  
    }  
    public void setList(ArrayList<Person> persons) {  
        this.persons = persons;  
    }  

    public String execute() throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/sampledb?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "SELECT * FROM persons";
                preparedStatement = connection.prepareStatement(sql);
                ResultSet rs= preparedStatement.executeQuery();

                while(rs.next()){  
                    Person person=new Person();
                    person.setLastName(rs.getString(2));   
                    person.setFirstName(rs.getString(3)); 
                    person.setAge(rs.getInt(4)); 
                    persons.add(person);  
                }
            } 
         } catch (Exception e) {

         } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
         }

         return SUCCESS;
    }
    
}
