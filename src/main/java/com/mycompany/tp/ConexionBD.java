/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp;
  import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
  import java.util.logging.Level;  
  import java.util.logging.Logger;
/**
 *
 * @author Julio
 */
public class ConexionBD {
   private String bd ;
   private String url ;
   private String user ;
   private String pass;
   private String driver;
   Connection conn;

    public ConexionBD(String bd, String url, String user, String pass, String driver) {
        super();
        this.bd = bd;
        this.url = url;
        this.user = user;
        this.pass = pass;
        this.driver = driver;
    }
   
   public Connection conexion() throws SQLException {
       try {
           Class.forName(driver);
           conn = DriverManager.getConnection(url+bd,user,pass);
          // System.out.println("Se conectó correctamente a BD " + bd);
           
       
       } catch (ClassNotFoundException ex) {
           System.out.println("Error de Conexión a la Base de Datos " + bd);
           Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
       }
      return conn;
    }
      
  public void desconexion(){
       try {
           conn.close();
       
       } catch (SQLException ex) {
           Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
       }
     
  }
   
    
}
  

   
   

