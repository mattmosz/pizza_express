/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author MSi
 */
public class cls_clientes {
    
    int id_cliente;
    String cedula;
    String nombre;
    String apellido;
    String telefono;
    String direccion;
    String correo;
    String sentencia;
    
    cls_conexion conex = new cls_conexion();
    
    public cls_clientes(){
        
        this.id_cliente=0;
        this.cedula="";
        this.nombre="";
        this.apellido="";
        this.telefono="";
        this.direccion="";
        this.correo="";
        
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public Object[][] consultar (String cedula){
        
        Object obj[][]= null;
        
        try{
            
            if(cedula.equals("")){
                this.sentencia="Select * from clientes";
            }else{
                this.sentencia="Select * from clientes where cedula='"+cedula+"'";
            }
            
            Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet datos = st.executeQuery(this.sentencia);
            datos.last();
            int nf = datos.getRow();
            obj = new Object[nf][6];
            int f=0;
            datos.beforeFirst();
            while(datos.next()){
                
                obj[f][0] = datos.getObject(2);
                obj[f][1] = datos.getObject(3);
                obj[f][2] = datos.getObject(4);
                obj[f][3] = datos.getObject(5);
                obj[f][4] = datos.getObject(6);
                obj[f][5] = datos.getObject(7);
                f++;
                
            }
            
            
            
        }catch(Exception e){
            
        }
        
        return obj;
    }
    
    public boolean insertar(){
        
        int respuesta=0;
        try{
            
            String sentencia = "INSERT INTO clientes (cedula_cliente, nombre_cliente, apellido_cliente, telefono_cliente, direccion_cliente, correo_cliente) values (?,?,?,?,?,?)";
            PreparedStatement st = conex.conectar().prepareStatement(sentencia);
            st.setString(1, this.getCedula());
            st.setString(2, this.getNombre());
            st.setString(3, this.getApellido());
            st.setString(4, this.getTelefono());
            st.setString(5, this.getDireccion());
            st.setString(6, this.getCorreo());
            
            respuesta = st.executeUpdate();
            
        }catch(Exception e){
            System.out.println(e);
        }
        
        if(respuesta >0){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean actualizar(){
        
        int respuesta = 0;
        
        try{
            String sentencia = "UPDATE clientes SET cedula_cliente=?, nombre_cliente=?, apellido_cliente=?, telefono_cliente=?, direccion_cliente=?, correo_cliente=? where id_cliente=?";
            PreparedStatement st = conex.conectar().prepareStatement(sentencia);
            st.setString(1, this.getCedula());
            st.setString(2, this.getNombre());
            st.setString(3, this.getApellido());
            st.setString(4, this.getTelefono());
            st.setString(5, this.getDireccion());
            st.setString(6, this.getCorreo());
            st.setInt(7, this.getId_cliente());
            
            respuesta = st.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
        
        if (respuesta >0){
            return true;
        }else{
            return false;
        }
        
    }
    
    public boolean eliminar(){
        
        int respuesta =0;
        
        try{
            
            String sentencia = "DELETE FROM clientes where id_cliente=?";
            PreparedStatement st = conex.conectar().prepareStatement(sentencia);
            st.setInt(1, this.getId_cliente());
            respuesta = st.executeUpdate();
            
        }catch(Exception e){
            System.out.println(e);
        }
        
        if(respuesta>0){
            return true;
        }else{
            return false;
        }
    }
    
    public String[] consultarCed(String cedula){
        
        String obj[] = new String [7];
        
        try{
            
            this.sentencia="Select * from clientes where cedula_cliente="+cedula;
            Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet datos = st.executeQuery(this.sentencia);
            datos.beforeFirst();
            datos.next();
            
            obj[0]=datos.getObject(1).toString();
            obj[1]=datos.getObject(2).toString();
            obj[2]=datos.getObject(3).toString();
            obj[3]=datos.getObject(4).toString();
            obj[4]=datos.getObject(5).toString();
            obj[5]=datos.getObject(6).toString();
            obj[6]=datos.getObject(7).toString();
            
        }catch(Exception e){
            
        }
        
        return obj;
    }
    
    public boolean buscarCliente(String cedula) throws SQLException{
        
        boolean respuesta = false;
        
        try{
            Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        
            ResultSet datos = st.executeQuery("SELECT * FROM clientes WHERE cedula_cliente= '"+cedula+"'");
            while(datos.next()){
            this.setId_cliente(datos.getInt("id_cliente"));
            this.setCedula(datos.getString("cedula_cliente"));
            this.setNombre(datos.getString("nombre_cliente"));
            this.setApellido(datos.getString("apellido_cliente"));
            this.setTelefono(datos.getString("telefono_cliente"));
            this.setDireccion(datos.getString("direccion_cliente"));
            this.setCorreo(datos.getString("correo_cliente"));
            respuesta = true;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return respuesta;
    }
    
}
