
import java.util.List;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import ProyectoCinePersistencia.dao.pelicula.PeliculaDAOImpl;
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
        pelicula.setIdCategoria(1); // Asegúrate de que exista una categoría con este ID
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

        System.out.println("Pelicula Creada: " + peliculaCreada);
    }

    @Test
    public void testBuscarPelicula() {
        Pelicula peliculaCreada = peliculaDAO.Crear(pelicula);
        Pelicula peliculaEncontrada = peliculaDAO.Buscar(peliculaCreada.getIdPelicula());
        assertNotNull("La película encontrada no debe ser nula", peliculaEncontrada);
        assertEquals("El título de la película encontrada debe ser el mismo", pelicula.getTitulo(), peliculaEncontrada.getTitulo());

        System.out.println("Pelicula Encontrada: " + peliculaEncontrada);
    }

    @Test
    public void testActualizarPelicula() {
        Pelicula peliculaCreada = peliculaDAO.Crear(pelicula);
        peliculaCreada.setTitulo("Nuevo Titulo");

        System.out.println("Actualizando Pelicula: " + peliculaCreada);
        peliculaDAO.Actualizar(peliculaCreada);

        Pelicula peliculaActualizada = peliculaDAO.Buscar(peliculaCreada.getIdPelicula());
        assertEquals("El título de la película actualizada debe ser 'Nuevo Titulo'", "Nuevo Titulo", peliculaActualizada.getTitulo());

        System.out.println("Pelicula Actualizada: " + peliculaActualizada);
    }

    @Test
    public void testEliminarPelicula() {
        Pelicula peliculaCreada = peliculaDAO.Crear(pelicula);
        System.out.println("Pelicula a Eliminar: " + peliculaCreada);

        peliculaDAO.Eliminar(peliculaCreada.getIdPelicula());
        Pelicula peliculaEliminada = peliculaDAO.Buscar(peliculaCreada.getIdPelicula());
        assertNull("La película eliminada debe ser nula", peliculaEliminada);

        List<Pelicula> listaPeliculas = peliculaDAO.Listar();
        System.out.println("Lista de Peliculas después de Eliminar:");
        for (Pelicula p : listaPeliculas) {
            System.out.println(p);
        }
    }

    @Test
    public void testListarPeliculas() {
        Pelicula peliculaCreada = peliculaDAO.Crear(pelicula);
        List<Pelicula> listaPeliculas = peliculaDAO.Listar();
        assertTrue("La lista de películas debe contener al menos una película", listaPeliculas.size() > 0);

        System.out.println("Lista de Peliculas:");
        for (Pelicula p : listaPeliculas) {
            System.out.println(p);
        }
    }
}
