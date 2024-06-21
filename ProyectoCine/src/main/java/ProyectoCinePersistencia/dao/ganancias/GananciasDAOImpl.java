package ProyectoCinePersistencia.dao.ganancias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ProyectoCinePersistencia.utils.dbConnection;

public class GananciasDAOImpl implements GananciasDAO{

    private dbConnection conexion;

    public GananciasDAOImpl(){
        this.conexion = new dbConnection();
    }

    public double obtenerGanancias(int id){
        double ganancias = 0;
        String consulta = "SELECT SUM(Total) as Ventas FROM ventas,funciones where ventas.IdFuncion = funciones.IdFuncion and funciones.IdPelicula = ?";
        Connection  connection =null;
        try{
            connection = conexion.conectar();
            PreparedStatement declaracion = connection.prepareStatement(consulta);
            declaracion.setInt(1, id);
            ResultSet resultado = declaracion.executeQuery();
            if(resultado.next()){
                ganancias = resultado.getDouble("Ventas");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            conexion.desconectar();
        }
        return ganancias;
    }

    public static void main(String[] args) {
        GananciasDAOImpl ganancias = new GananciasDAOImpl();
        System.out.println("Ganancias de la pelicula con id " + 1 +": " +ganancias.obtenerGanancias(1));
    }
}
