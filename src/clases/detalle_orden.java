/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
/**
 *
 * @author MSi
 */
public class detalle_orden {
    
    int id_orden;
    int id_producto;
    int cantidad;
    double precio;
    cls_conexion conex = new cls_conexion();
    
    public detalle_orden(){
        
        this.id_orden=0;
        this.id_producto=0;
        this.cantidad=0;
        this.precio=0.0;
        
    }

    public int getId_orden() {
        return id_orden;
    }

    public void setId_orden(int id_orden) {
        this.id_orden = id_orden;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public boolean registrarDetalle(){
        
        int respuesta =0;
        try{
            String sentencia = "INSERT INTO detalle_orden (id_orden, id_producto, cantidad, precio) values (?,?,?,?)";
            PreparedStatement st = conex.conectar().prepareStatement(sentencia);
            st.setInt(1, this.id_orden);
            st.setInt(2, this.id_producto);
            st.setInt(3, this.cantidad);
            st.setDouble(4, this.precio);
            respuesta=st.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
        return respuesta >0;
    }
    
}
