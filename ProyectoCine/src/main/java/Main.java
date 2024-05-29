
import ProyectoCinePersistencia.dao.ganancia.GananciaDAOImpl;
import ProyectoCinePersistencia.utils.MyBatisUtil;
import ProyectoCinePersistencia.dao.login.LoginDAOImpl;
import org.apache.ibatis.session.SqlSessionFactory;

public class Main {

    public static void main(String[] args) {
        // Crear instancia de SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();

// Crear instancia de GananciaDAO
        GananciaDAOImpl gananciaDAO = new GananciaDAOImpl(sqlSessionFactory);

        // ID de la película para la cual se quieren obtener las ganancias
        int idPelicula = 1; // Este valor puede ser cambiado según los datos disponibles en la base de datos

        // Obtener las ganancias de la película
        Double ganancias = gananciaDAO.obtenerGanancias(idPelicula);

        // Mostrar las ganancias obtenidas
if (ganancias != null) {
            System.out.println("Ganancias de la película con ID " + idPelicula + ": " + ganancias);
        } else {
            System.out.println("No se encontraron ganancias para la película con ID " + idPelicula);
        }
    }
}
