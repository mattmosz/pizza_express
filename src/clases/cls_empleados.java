/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author MSi
 */
public class cls_empleados {
 
    int id_empleado;
    String cedula;
    String nombre;
    String apellido;
    String telefono;
    String cargo;
    String direccion;
    String correo;
    String clave;
    cls_conexion conex = new cls_conexion();
    String sentencia;
    
    public cls_empleados(){
        
        this.id_empleado=0;
        this.cedula="";
        this.nombre="";
        this.apellido="";
        this.telefono="";
        this.cargo="";
        this.direccion="";
        this.correo="";
        this.clave="";
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

  
    public String [][] loginC(String cedula, String clave){
        
        String obj[][]=null;
        try{
            
            this.sentencia="Select * from empleados where cedula='"+cedula+"' and clave='"+clave+"' and cargo='Cajero'";
            Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet datos = st.executeQuery(this.sentencia);
            obj=new String[datos.getRow()][9];
            datos.last();
            if(datos.getRow()>0){
                
                obj[0][0]=datos.getObject(1).toString();
                obj[0][1]=datos.getObject(1).toString();
                obj[0][2]=datos.getObject(1).toString();
                obj[0][3]=datos.getObject(1).toString();
                obj[0][4]=datos.getObject(1).toString();
                obj[0][5]=datos.getObject(1).toString();
                obj[0][6]=datos.getObject(1).toString();
                obj[0][7]=datos.getObject(1).toString();
                obj[0][8]=datos.getObject(1).toString();
                
            }else{
                obj=null;
            }
            
        }catch(Exception e){
            
        }
        
        return obj;
    }
    
    public String [][] loginA(String cedula, String clave){
        
        String obj[][]=null;
        try{
            
            this.sentencia="Select * from empleados where cedula='"+cedula+"' and clave='"+clave+"' and cargo='Administrador'";
            Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet datos = st.executeQuery(this.sentencia);
            obj=new String[datos.getRow()][9];
            datos.last();
            if(datos.getRow()>0){
                
                obj[0][0]=datos.getObject(1).toString();
                obj[0][1]=datos.getObject(1).toString();
                obj[0][2]=datos.getObject(1).toString();
                obj[0][3]=datos.getObject(1).toString();
                obj[0][4]=datos.getObject(1).toString();
                obj[0][5]=datos.getObject(1).toString();
                obj[0][6]=datos.getObject(1).toString();
                obj[0][7]=datos.getObject(1).toString();
                obj[0][8]=datos.getObject(1).toString();
                
            }else{
                obj=null;
            }
            
        }catch(Exception e){
            
        }
        
        return obj;
        
    }
    
    public boolean insertar(){
        
        int respuesta = 0;
        try{
            
            String sentencia = "INSERT INTO empleados (cedula,nombre,apellido,telefono,cargo,direccion,correo,clave) values (?,?,?,?,?,?,?,?)";
            PreparedStatement st = conex.conectar().prepareStatement(sentencia);
            st.setString(1, this.getCedula());
            st.setString(2, this.getNombre());
            st.setString(3, this.getApellido());
            st.setString(4, this.getTelefono());
            st.setString(5, this.getCargo());
            st.setString(6, this.getDireccion());
            st.setString(7, this.getCorreo());
            st.setString(8, this.getClave());
            
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
    
    public boolean actualizar(){
        
        int respuesta=0;
        
        try{
            String sentencia = "UPDATE empleados SET cedula=?, nombre=?, apellido=?, telefono=?, cargo=?, direccion=?, correo=?, clave=? where id_empleado=?";
            PreparedStatement st = conex.conectar().prepareStatement(sentencia);
            st.setString(1, this.getCedula());
            st.setString(2, this.getNombre());
            st.setString(3, this.getApellido());
            st.setString(4, this.getTelefono());
            st.setString(5, this.getCargo());
            st.setString(6, this.getDireccion());
            st.setString(7, this.getCorreo());
            st.setString(8, this.getClave());
            st.setInt(9, this.getId_empleado());
            
            respuesta=st.executeUpdate();
            
        }catch(Exception e){
            System.out.println(e);
        }
        
        if(respuesta>0){
            return true;
        }else{
            return false;
        }
        
    }
    
    public boolean eliminar() throws SQLException{
        
        int respuesta=0;
        
        try{
            
            String sentencia = "DELETE FROM empleados where id_empleado=?";
            PreparedStatement st = conex.conectar().prepareStatement(sentencia);
            st.setInt(1, this.getId_empleado());
            respuesta=st.executeUpdate();
            
        }catch(Exception e){
            System.out.println(e);
        }
        
        if(respuesta>0){
            return true;
        }else{
            return false;
        }
    }
    
    public Object[][] consultar(){
        
        Object obj [][] = null;
        
        try{
            if(cedula.equals("")){
                this.sentencia="SELECT * FROM empleados";
            }
            
            Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet datos = st.executeQuery(this.sentencia);
            datos.last();
            int nf = datos.getRow();
            obj = new Object[nf][7];
            int f=0;
            datos.beforeFirst();
            while(datos.next()){
                
                obj[f][0]=datos.getObject(2);
                obj[f][1]=datos.getObject(3);
                obj[f][2]=datos.getObject(4);
                obj[f][3]=datos.getObject(5);
                obj[f][4]=datos.getObject(6);
                obj[f][5]=datos.getObject(7);
                obj[f][6]=datos.getObject(8);
                f++;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return obj;
    }
    
    public String [] consultarE(String cedula) throws SQLException{
        
        String obj [] = new String [9];
        
        try{
            
            this.sentencia="SELECT * FROM empleados where cedula="+cedula;
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
            obj[7]=datos.getObject(8).toString();
            obj[8]=datos.getObject(9).toString();
            
        }catch(Exception e){
            System.out.println(e);
        }
        return obj;
    }
    
}
