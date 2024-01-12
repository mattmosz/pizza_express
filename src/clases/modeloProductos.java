/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author MSi
 */
public class modeloProductos {
    
    public ArrayList<productos> combo_p(){
        
        cls_conexion conex = new cls_conexion();
        ArrayList<productos> lista_p = new ArrayList<>();
        
        try{
            
            Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet datos = st.executeQuery("Select * from productos where not tamano = 'null'");
            while(datos.next()){
                productos p = new productos();
                p.setId_producto(datos.getInt("id_producto"));
                p.setProducto(datos.getString("producto"));
                p.setTamano(datos.getString("tamano"));
                p.setPrecio(datos.getDouble("precio_unitario"));
                lista_p.add(p);
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
        return lista_p;
    }

    public ArrayList<productos> combo_b() throws SQLException{
        
        cls_conexion conex = new cls_conexion();
        ArrayList<productos> lista_b = new ArrayList<>();
        
        try{
            Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet datos = st.executeQuery("Select * from productos where tamano IS NULL");
            while(datos.next()){
                productos p = new productos();
                p.setId_producto(datos.getInt("id_producto"));
                p.setProducto(datos.getString("producto"));
                p.setTamano(datos.getString("tamano"));
                p.setPrecio(datos.getDouble("precio_unitario"));
                lista_b.add(p);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return lista_b;
    }
}
