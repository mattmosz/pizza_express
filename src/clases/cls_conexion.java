/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author MSi
 */
public class cls_conexion {
    
    static String db = "db_pizza_express";
    static String usu = "root";
    static String cla = "";
    static String url = "jdbc:mysql://localhost/" + db;
    Connection conexion = null;
    
    public cls_conexion(){
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url,usu,cla);
            if(conexion != null){
                System.out.println("Conexión con la base de datos OK");
            }else{
                System.out.println("Error con la base de datos");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        
    }
    
    public Connection conectar(){
        return conexion;
    }
}
