
import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ProyectoCinePersistencia.dao.impl.PeliculaDAOImpl;
import ProyectoCinePersistencia.entities.Pelicula;

public class PeliculaTest {

    private PeliculaDAOImpl peliculaDAO;
    private Pelicula pelicula;

    @Before
    public void setUp() throws Exception {
        peliculaDAO = new PeliculaDAOImpl();
        pelicula = new Pelicula();
        pelicula.setTitulo("Test Pelicula");
        pelicula.setSinopsis("Test Sinopsis");
        pelicula.setDuracion(120);
        pelicula.setFechaEstreno("2023-01-01");
        pelicula.setIdCategoria(1); 
    }

    @After
    public void tearDown() throws Exception {
        if (pelicula != null && pelicula.getIdPelicula() != 0) {
            peliculaDAO.Eliminar(pelicula.getIdPelicula());
        }
    }

    @Test
    public void testCrearPelicula() {
        Pelicula peliculaCreada = peliculaDAO.Crear(pelicula);
        assertNotNull("La película creada no debe ser nula", peliculaCreada);
        assertTrue("El ID de la película creada debe ser mayor que 0", peliculaCreada.getIdPelicula() > 0);
    }

    @Test
    public void testBuscarPelicula() {
        Pelicula peliculaCreada = peliculaDAO.Crear(pelicula);
        Pelicula peliculaEncontrada = peliculaDAO.Buscar(peliculaCreada.getIdPelicula());
        assertNotNull("La película encontrada no debe ser nula", peliculaEncontrada);
        assertEquals("El título de la película encontrada debe ser el mismo", pelicula.getTitulo(), peliculaEncontrada.getTitulo());
    }

    @Test
    public void testActualizarPelicula() {
        Pelicula peliculaCreada = peliculaDAO.Crear(pelicula);
        peliculaCreada.setTitulo("Nuevo Titulo");
        peliculaDAO.Actualizar(peliculaCreada);
        Pelicula peliculaActualizada = peliculaDAO.Buscar(peliculaCreada.getIdPelicula());
        assertEquals("El título de la película actualizada debe ser 'Nuevo Titulo'", "Nuevo Titulo", peliculaActualizada.getTitulo());
    }

    @Test
    public void testEliminarPelicula() {
        Pelicula peliculaCreada = peliculaDAO.Crear(pelicula);
        peliculaDAO.Eliminar(peliculaCreada.getIdPelicula());
        Pelicula peliculaEliminada = peliculaDAO.Buscar(peliculaCreada.getIdPelicula());
        assertNull("La película eliminada debe ser nula", peliculaEliminada);
    }

    @Test
    public void testListarPeliculas() {
        Pelicula peliculaCreada = peliculaDAO.Crear(pelicula);
        List<Pelicula> listaPeliculas = peliculaDAO.Listar();
        assertTrue("La lista de películas debe contener al menos una película", listaPeliculas.size() > 0);
    }
}
