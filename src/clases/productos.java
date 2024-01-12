/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author MSi
 */
public class productos {
    
    int id_producto;
    String producto;
    String tamano;
    Double precio;
    String sentencia;
    cls_conexion conex = new cls_conexion();
    
    public productos(){
        
        this.id_producto=0;
        this.producto="";
        this.tamano="";
        this.precio=0.0;
        
    }
    
    public productos(int id_producto, String producto, String tamano, Double precio){
        
        this.id_producto=id_producto;
        this.producto=producto;
        this.tamano=tamano;
        this.precio=precio;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
    public boolean insertarPizza() throws SQLException{
        
        int respuesta =0;
        try{
            String sentencia = "INSERT INTO productos(id_producto, producto, tamano, precio_unitario) values (?,?,?,?)";
            PreparedStatement st = conex.conectar().prepareStatement(sentencia);
            st.setInt(1, this.getId_producto());
            st.setString(2, this.getProducto());
            st.setString(3, this.getTamano());
            st.setDouble(4, this.getPrecio());
            respuesta = st.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
        return respuesta >0;
    }
    
    public boolean insertarBebida(){
        
        int respuesta =0;
        try{
            String sentencia = "INSERT INTO productos (id_producto, producto, precio_unitario) values (?,?,?)";
            PreparedStatement st = conex.conectar().prepareStatement(sentencia);
            st.setInt(1, this.getId_producto());
            st.setString(2, this.getProducto());
            st.setDouble(3, this.getPrecio());
            respuesta= st.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
        return respuesta >0;
    }
    
    public String[] consultarProducto(int id_producto){
        String[] obj = new String [4];
        try{
            this.sentencia="SELECT * FROM productos where id_producto="+id_producto;
            Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet datos = st.executeQuery(this.sentencia);
            datos.beforeFirst();
            datos.next();
            obj[0]=datos.getObject(1).toString();
            obj[1]=datos.getObject(2).toString();
            obj[2]=datos.getObject(3).toString();
            obj[3]=datos.getObject(4).toString();
        }catch(Exception e){
            System.out.println(e);
        }
        return obj;
    }
    
    public String[] consultarBebida(int id_producto){
        String[] obj = new String [2];
        try{
            this.sentencia="SELECT producto, precio_unitario FROM productos where id_producto="+id_producto;
            Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet datos = st.executeQuery(this.sentencia);
            datos.beforeFirst();
            datos.next();
            obj[0]=datos.getObject(1).toString();
            obj[1]=datos.getObject(2).toString();
        }catch(Exception e){
            System.out.println(e);
        }
        return obj;
    }
    
    public Object[][] consultar() throws SQLException{
        
        Object[][] obj=null;
        try{
            this.sentencia="SELECT * FROM productos";
            Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet datos = st.executeQuery(this.sentencia);
            datos.last();
            int nf = datos.getRow();
            obj = new Object[nf][4];
            int f=0;
            datos.beforeFirst();
            while(datos.next()){
                obj[f][0] = datos.getObject(1);
                obj[f][1] = datos.getObject(2);
                obj[f][2] = datos.getObject(3);
                obj[f][3] = datos.getObject(4);
                f++;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return obj;
    }
    
    public Object[][] consultarB() throws SQLException{
        
        Object[][] obj=null;
        try{
            this.sentencia="SELECT * FROM productos WHERE tamano IS NULL";
            Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet datos = st.executeQuery(this.sentencia);
            datos.last();
            int nf = datos.getRow();
            obj = new Object[nf][4];
            int f=0;
            datos.beforeFirst();
            while(datos.next()){
                obj[f][0] = datos.getObject(1);
                obj[f][1] = datos.getObject(2);
                obj[f][2] = datos.getObject(3);
                obj[f][3] = datos.getObject(4);
                f++;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return obj;
    }
    
    public Object[][] consultarP() throws SQLException{
        
        Object[][] obj=null;
        try{
            this.sentencia="SELECT * FROM productos WHERE NOT tamano = 'null'";
            Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet datos = st.executeQuery(this.sentencia);
            datos.last();
            int nf = datos.getRow();
            obj = new Object[nf][4];
            int f=0;
            datos.beforeFirst();
            while(datos.next()){
                obj[f][0] = datos.getObject(1);
                obj[f][1] = datos.getObject(2);
                obj[f][2] = datos.getObject(3);
                obj[f][3] = datos.getObject(4);
                f++;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return obj;
    }
    
    public boolean eliminarProducto(){
        
        int respuesta=0;
        try{
            String sentencia="DELETE FROM productos WHERE id_producto=?";
            PreparedStatement st = conex.conectar().prepareStatement(sentencia);
            st.setInt(1, this.getId_producto());
            respuesta= st.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
        return respuesta >0;
    }
    
    public boolean actualizarPizza(){
        
        boolean respuesta =false;
        try{
            String sentencia = "UPDATE productos SET producto=?, tamano=?, precio_unitario=? WHERE id_producto=?";
            PreparedStatement st = conex.conectar().prepareStatement(sentencia);
            st.setString(1, this.getProducto());
            st.setString(2, this.getTamano());
            st.setDouble(3, this.getPrecio());
            st.setInt(4, this.getId_producto());
            int r = st.executeUpdate();
            if(r>0){
                respuesta = true;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return respuesta;
    }
    
    public boolean actualizarBebida(){
        boolean respuesta = false;
        try{
            String sentencia = "UPDATE productos SET producto=?, precio_unitario=? WHERE id_producto=?";
            PreparedStatement st = conex.conectar().prepareStatement(sentencia);
            st.setString(1, this.getProducto());
            st.setDouble(2, this.getPrecio());
            st.setInt(3, this.getId_producto());
            int r = st.executeUpdate();
            if(r>0){
                respuesta=true;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return respuesta;
    }
    
    public String toString(){
        
        return producto+" - "+tamano;
        
    }
    
}
