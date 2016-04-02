package com.company;
import java.sql.*;



public class Main {

    public static void main(String[] args) {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost:797";
            Connection con = null;
            con = DriverManager.getConnection(connectionUrl,"the_user","the_password");
            System.out.println("Connected to Server");

            

        }
        catch(ClassNotFoundException e)
        {
            System.out.print("Didn't connect to server");
        }
        catch (SQLException e)
        {
            System.out.print("SQL problem");
        }


	// write your code here
    }
}
