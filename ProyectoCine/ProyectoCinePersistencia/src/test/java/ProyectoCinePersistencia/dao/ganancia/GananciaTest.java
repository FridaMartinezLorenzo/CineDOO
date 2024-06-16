package ProyectoCinePersistencia.dao.ganancia;

import org.apache.ibatis.session.SqlSessionFactory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

import ProyectoCinePersistencia.dao.pelicula.PeliculaDAOImpl;
import ProyectoCinePersistencia.entities.Pelicula;
import ProyectoCinePersistencia.utils.MyBatisUtil;

public class GananciaTest {

    private SqlSessionFactory sqlSessionFactory;
    private GananciaDAOImpl gananciaDAO;
    private PeliculaDAOImpl peliculaDAO;
    private Pelicula pelicula;

    @Before
    public void setUp() {
        sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
        gananciaDAO = new GananciaDAOImpl(sqlSessionFactory);
        peliculaDAO = new PeliculaDAOImpl(sqlSessionFactory);
        pelicula = peliculaDAO.Buscar(2);
    }

    @Test
    public void testGanancia() {
        // Verificar que la película no sea nula después de la búsqueda
        assertNotNull("La película no debe ser nula después de la búsqueda", pelicula);

        // Verificar que el ID de la película sea el esperado (por ejemplo, ID 1)
        assertEquals("El ID de la película debe ser 1", 2, pelicula.getIdPelicula());
        Double ganancia = gananciaDAO.obtenerGanancias(pelicula.getIdPelicula());
        assertNotNull("La ganancia no debe ser nula", ganancia);
    }
}
