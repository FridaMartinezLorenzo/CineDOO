package ProyectoCinePersistencia.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ProyectoCinePersistencia.entities.Usuario;
import ProyectoCinePersistencia.dao.UsuariosDAO;
import ProyectoCinePersistencia.utils.dbConnection;

public class UsuariosDAOImpl implements UsuariosDAO {
    
    private dbConnection conexion;

    public UsuariosDAOImpl(){
        this.conexion = new dbConnection();
    }

    public boolean Crear(Usuario usuario){
        boolean resultado = false;
        String consulta = "INSERT INTO usuarios (Nombre, Correo, Contrasena, idRol) VALUES (?,?,?,?) ";
        Connection  connection =null;
        try{
            connection = conexion.conectar();
            PreparedStatement declaracion = connection.prepareStatement(consulta);
            declaracion.setString(1, usuario.getNombre());
            declaracion.setString(2, usuario.getCorreo());
            declaracion.setString(3, usuario.getContrasena());
            declaracion.setInt(4, usuario.getIdRol());
            int filasAfectadas = declaracion.executeUpdate();
            resultado = filasAfectadas > 0;
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            conexion.desconectar();
        }
        return resultado;
    }

    public boolean Actualizar(Usuario usuario){
        boolean resultado = false;
        String consulta = "UPDATE usuarios SET Nombre = ?, Correo = ?, Contrasena = ?, idRol = ? WHERE idUsuario = ?";
        Connection  connection =null;
        try{
            connection = conexion.conectar();
            PreparedStatement declaracion = connection.prepareStatement(consulta);
            declaracion.setString(1, usuario.getNombre());
            declaracion.setString(2, usuario.getCorreo());
            declaracion.setString(3, usuario.getContrasena());
            declaracion.setInt(4, usuario.getIdRol());
            declaracion.setInt(5, usuario.getIdUsuario());
            int filasAfectadas = declaracion.executeUpdate();
            resultado = filasAfectadas > 0;
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            conexion.desconectar();
        }
        return resultado;
    }

    public boolean Eliminar(int idUsuario){
        boolean resultado = false;
        String consulta = "DELETE FROM usuarios WHERE idUsuario = ?";
        Connection  connection =null;
        try{
            connection = conexion.conectar();
            PreparedStatement declaracion = connection.prepareStatement(consulta);
            declaracion.setInt(1, idUsuario);
            int filasAfectadas = declaracion.executeUpdate();
            resultado = filasAfectadas > 0;
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            conexion.desconectar();
        }
        return resultado; 
    }

    public List<Usuario> Listar(){
        List<Usuario> usuarios = new ArrayList<>();
        String consulta = "SELECT * FROM usuarios";
        Connection  connection =null;
        try{
            connection = conexion.conectar();
            PreparedStatement declaracion = connection.prepareStatement(consulta);
            ResultSet rs = declaracion.executeQuery();
            while(rs.next()){
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setCorreo(rs.getString("Correo"));
                usuario.setContrasena(rs.getString("Contrasena"));
                usuario.setIdRol(rs.getInt("idRol"));
                usuarios.add(usuario);
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            conexion.desconectar();
        }
        return usuarios;
    }

    public Usuario Buscar(int idUsuario){
        Usuario usuario = null;
        String consulta = "SELECT * FROM usuarios WHERE idUsuario = ?";
        Connection  connection =null;
        try{
            connection = conexion.conectar();
            PreparedStatement declaracion = connection.prepareStatement(consulta);
            declaracion.setInt(1, idUsuario);
            ResultSet rs = declaracion.executeQuery();
            if(rs.next()){
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setCorreo(rs.getString("Correo"));
                usuario.setContrasena(rs.getString("Contrasena"));
                usuario.setIdRol(rs.getInt("idRol"));
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            conexion.desconectar();
        }
        return usuario;
    }

    public static void main(String[] args) {
        UsuariosDAOImpl dao = new UsuariosDAOImpl();

        // Prueba de Crear
        /*
        Usuario usuario = new Usuario(0, "Juan", "Juan@gmail.co m", "1234", 2);
        if(dao.Crear(usuario)){
            System.out.println("Usuario creado");
        }else{
            System.out.println("Error al crear usuario");
        }
        */
        
        // Prueba de Actualizar
        /*Usuario usuario = new Usuario(6, "Juan Perez", "JuanP@gmail.co m", "12345", 2);
        if(dao.Actualizar(usuario)){
            System.out.println("Usuario actualizado");
        }else{
            System.out.println("Error al actualizar usuario");
        }*/

        // Prueba de Eliminar
        /*if(dao.Eliminar(6)){
            System.out.println("Usuario eliminado");
        }else{
            System.out.println("Error al eliminar usuario");
        }*/

        // Prueba de Listar
        /*List<Usuario> usuarios = dao.Listar();
        System.out.println("Usuarios:\n");
        for(Usuario usuario : usuarios){
            System.out.println(usuario.getIdUsuario());
            System.out.println(usuario.getNombre());
            System.out.println(usuario.getCorreo());
            System.out.println(usuario.getContrasena());
            System.out.println(usuario.getIdRol());
            System.out.println("_________________________");
        }*/

        // Prueba de Buscar
        /*Usuario usuario = dao.Buscar(5);
        if(usuario != null){
            System.out.println(usuario.getIdUsuario());
            System.out.println(usuario.getNombre());
            System.out.println(usuario.getCorreo());
            System.out.println(usuario.getContrasena());
            System.out.println(usuario.getIdRol());
        }else{
            System.out.println("Usuario no encontrado");
        }*/
    }
}
