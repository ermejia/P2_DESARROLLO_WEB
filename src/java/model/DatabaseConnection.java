package model;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 *
 * @author mejia
 */
public class DatabaseConnection {
    public Connection connection;
    private final String puerto= "3306";
    private final String bd= "db_productos";
    private final String urlConexion = String.format("jdbc:mysql://localhost:%s/%s?serverTimezone=UTC",puerto, bd);
    private final String usuario = "root";
    private final String contra = "1234";
    private final String jdbc ="com.mysql.cj.jdbc.Driver";
    
    public void openConnetion(){
            try{
                Class.forName(jdbc);
                connection = DriverManager.getConnection(urlConexion,usuario,contra);               
            }catch(ClassNotFoundException | SQLException ex){
                    System.out.println("Database Error Opening Connection: " + ex.getMessage());
            }
    }
    
    public void closeConnection(){
        try{
            connection.close();
        }catch(SQLException ex){
            System.out.println("Database Error Closing Connection: " + ex.getMessage());
        }
    
    }
}
