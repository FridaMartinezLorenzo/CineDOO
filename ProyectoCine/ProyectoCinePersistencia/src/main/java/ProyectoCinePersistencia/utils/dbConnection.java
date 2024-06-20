package ProyectoCinePersistencia.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dbConnection {
    // Atributos (Datos de la conexion)
    String bd = "cine";
    String url = "jdbc:mysql://localhost:3306/";
    String user = "root";
    String password = "";
    String driver = "com.mysql.cj.jdbc.Driver";
    Connection cx;

    public dbConnection() {
        
    }

    public Connection conectar() throws SQLException {
        try {
            Class.forName(driver);
            cx = DriverManager.getConnection(url + bd, user, password);
           // System.out.println("Se conecto a la bd");
        } catch (ClassNotFoundException ex) {
           // System.out.println("No se conecto a la bd " + bd);
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cx; // Asegúrate de devolver la conexión
    }

    public void desconectar() {
        try {
            if (cx != null && !cx.isClosed()) { // Verifica si la conexión no es nula y está abierta
                cx.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public static void main(String[] args) {
        dbConnection conexion = new dbConnection();
        try {
            conexion.conectar();
        } catch (SQLException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al conectar a la base de datos");
        }
    }
    
    
}
