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
public class orden {
    
    int id_orden;
    int id_cliente;
    int id_empleado;
    double total;
    cls_conexion conex = new cls_conexion();
    
    public orden(){
        
        this.id_orden=0;
        this.id_cliente=0;
        this.id_empleado=0;
        this.total=0.0;
    }

    public int getId_orden() {
        return id_orden;
    }

    public void setId_orden(int id_orden) {
        this.id_orden = id_orden;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public boolean resgistrarOrden(){
        
        int respuesta=0;
        
        try{
            String sentencia = "INSERT INTO orden(id_cliente, id_empleado, total) values (?,?,?)";
            PreparedStatement st = conex.conectar().prepareStatement(sentencia);
            st.setInt(1, this.id_cliente);
            st.setInt(2, this.id_empleado);
            st.setDouble(3, this.total);
            respuesta = st.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
        return respuesta>0;
    }
    
    public int id_orden(){
        
        int id=0;
        String sentencia = "SELECT MAX(id_orden) FROM orden";
        try{
            Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet dato = st.executeQuery(sentencia);
            if(dato.next()){
                id=dato.getInt(1);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return id;
    }
    
    public Object[][] consultarOrden(){
        
        Object [][] obj = null;
        try{
            String sentencia = "SELECT orden.id_orden, clientes.nombre_cliente, clientes.apellido_cliente, orden.id_empleado, orden.total FROM orden INNER JOIN clientes ON orden.id_cliente = clientes.id_cliente";
            Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet datos = st.executeQuery(sentencia);
            datos.last();
            int nf = datos.getRow();
            obj = new Object [nf][5];
            int f = 0;
            datos.beforeFirst();
            while(datos.next()){
                
                obj[f][0] = datos.getObject(1);
                obj[f][1] = datos.getObject(2);
                obj[f][2] = datos.getObject(3);
                obj[f][3] = datos.getObject(4);
                obj[f][4] = datos.getObject(5);
                f++;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return obj;
    }
    
}
