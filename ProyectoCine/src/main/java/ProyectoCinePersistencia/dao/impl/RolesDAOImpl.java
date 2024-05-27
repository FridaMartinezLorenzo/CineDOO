
package ProyectoCinePersistencia.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ProyectoCinePersistencia.dao.RolesDAO;
import ProyectoCinePersistencia.entities.Rol;
import ProyectoCinePersistencia.utils.dbConnection;


public class RolesDAOImpl implements RolesDAO{
    
    //variables
    private dbConnection conexion;
     
    public RolesDAOImpl(){
        this.conexion = new dbConnection();
    }
    
    @Override
    public boolean Crear(Rol rol){
        boolean resultado = false;
        String consulta = "INSERT INTO roles (Nombre) VALUES (?) ";
        Connection  connection =null;
        try{
            connection = conexion.conectar();
            PreparedStatement declaracion = connection.prepareStatement(consulta);
            declaracion.setString(1, rol.getNombre());
            int filasAfectadas = declaracion.executeUpdate();
            resultado = filasAfectadas > 0;
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            conexion.desconectar();
        }
        return resultado;
    }
    
    @Override
    public List<Rol> Listar() {
        String consulta = "SELECT * FROM roles";
        List<Rol> roles = new ArrayList<>();
        Connection connection = null;
        try{
            connection = conexion.conectar();
            PreparedStatement declaracion = connection.prepareStatement(consulta);
            ResultSet resultados = declaracion.executeQuery();
            while(resultados.next()){
                Rol rol = new Rol();
                rol.setIdRol(resultados.getInt("idRol"));
                rol.setNombre(resultados.getString("nombre"));
                roles.add(rol);
            }
        }catch(SQLException ex){
           ex.printStackTrace();
        }finally{
            conexion.desconectar();
        }
        return roles;
       
    }
    
    @Override 
    public boolean Eliminar(int id){
        String consulta = "DELETE FROM roles WHERE idRol = ?";
        Connection connection = null;
        boolean resultado = false; 
        
        try{
            connection = conexion.conectar();
            PreparedStatement declaracion = connection.prepareStatement(consulta);
            declaracion.setInt(1,id);
            int filasAfectadas = declaracion.executeUpdate();
            resultado = filasAfectadas > 0 ;
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            conexion.desconectar();
        }
        return resultado;
    }
    
    @Override
    public boolean Actualizar(Rol rol){
        String consulta = "UPDATE roles SET nombre = ? WHERE idRol = ?";
        Connection connection = null;
        boolean resultado = false;
        
         try {
            connection = conexion.conectar();
            PreparedStatement declaracion = connection.prepareStatement(consulta);
            declaracion.setString(1, rol.getNombre());
            declaracion.setInt(2, rol.getIdRol());

            int filasAfectadas = declaracion.executeUpdate();
            resultado = filasAfectadas > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
           conexion.desconectar();
        }

        return resultado;
    }
    
    @Override
    public Rol BuscaRol(int id){
        String consulta = "SELECT * FROM roles WHERE idRol = ?";
        Connection connection = null;
        Rol rol = null;

        try {
            connection = conexion.conectar();
            PreparedStatement declaracion = connection.prepareStatement(consulta);
            declaracion.setInt(1, id);

            ResultSet resultados = declaracion.executeQuery();
            if (resultados.next()) {
                rol = new Rol();
                rol.setIdRol(resultados.getInt("idRol"));
                rol.setNombre(resultados.getString("nombre"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
           conexion.desconectar();
        }

        return rol;
    }
       
    public static void main(String [] args){
        //variables
        RolesDAOImpl Rol =  new RolesDAOImpl();
        Rol RolBuscado;
        //Prueba listar todos los roles
       /* List<Rol> roles = Rol.Listar();
        System.out.println("Todas las peliculas");
        for(Rol rol : roles){
            System.out.println(rol.getIdRol() + ":" + rol.getNombre()  );
        }*/
        
        //Prueba de crear
       /* Rol rol= new Rol(0,"Barrendero");
        if(Rol.Crear(rol)==true){
            System.out.println("Se inserto correctamente");
        }*/
       
        //Prueba de eliminar
       /*if(Rol.Eliminar(4)==true){
           System.out.println("Se elimino correctamente el rol");
       }else{
            System.out.println("No se elimino");
       }*/
       
       //Prueba de Actualizar
       /*Rol rol= new Rol(5,"Proveedor");
       if(Rol.Actualizar(rol)==true){
           System.out.println("Se actualizo correctamente el rol");
       }else{
           System.out.println("No se actualizo");
       }*/
       
       //prueba de BuscaRol
       RolBuscado=Rol.BuscaRol(5);
       if(RolBuscado != null){
            System.out.println("El nombre del rol es " + RolBuscado.getNombre());
       }
    }
      
}
