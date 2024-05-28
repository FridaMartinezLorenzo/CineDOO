package ProyectoCinePersistencia.dao.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ProyectoCinePersistencia.utils.dbConnection;
import ProyectoCinePersistencia.entities.Usuario;

public class LoginDAOImpl implements LoginDAO {
    
    private dbConnection conexion;

    public LoginDAOImpl(){
        this.conexion = new dbConnection();
    }

    @Override
    public Usuario Login(String usuario, String contrasena){
        Connection con = null;
        Usuario user = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = conexion.conectar();
            String query = "SELECT * FROM usuarios WHERE Nombre = ? AND Contrasena = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, usuario);
            ps.setString(2, contrasena);
            rs = ps.executeQuery();
            if(rs.next()){
                user = new Usuario();
                user.setIdUsuario(rs.getInt("idUsuario"));
                user.setNombre(rs.getString("Nombre")); // Asegúrate de que "nombre" coincida con el nombre en la BD
                user.setContrasena(rs.getString("Contrasena")); // Lo mismo aquí
                user.setIdRol(rs.getInt("idRol"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
           conexion.desconectar();
        }
        return user;
    }

    public static void main(String[] args) {
        LoginDAOImpl dao = new LoginDAOImpl();
        Usuario user = dao.Login("Maria Garcia", "maria123");
        if(user != null){
            if(user.getIdRol() == 1){
                System.out.println("Usuario administrador");
            } else if(user.getIdRol() == 2){
                System.out.println("Usuario Vendedor");
            }else if(user.getIdRol() == 3){
                System.out.println("Usuario Cliente");
            }else if(user.getIdRol() == 5){
                System.out.println("Usuario Proveedor");
            }
        } else {
            System.out.println("Usuario no encontrado");
        }
    }
}
