package ProyectoCinePresentacion.controllers;

import java.util.List;

import ProyectoCinePersistencia.dao.pelicula.PeliculaDAO;
import ProyectoCinePersistencia.dao.pelicula.PeliculaDAOImpl;
import ProyectoCinePersistencia.entities.Pelicula;
import ProyectoCinePresentacion.utils.ViewUtil;

public class PeliculaController {

    private PeliculaDAO peliculaDAO = new PeliculaDAOImpl(ViewUtil.getSqlSessionFactory());

    public Pelicula crearPelicula(Pelicula pelicula) {
        if (pelicula.getTitulo().isEmpty() || pelicula.getSinopsis().isEmpty() || pelicula.getFechaEstreno().isEmpty() || pelicula.getDuracion() <= 0) {
            System.err.println("Error: Campos vacíos o información incorrecta");
            pelicula.setIdPelicula(-1);
            return pelicula;
        } else {
            if (peliculaDAO.BuscarPorTitulo(pelicula.getTitulo()) != null) {
                pelicula.setIdPelicula(-1);
                return pelicula;
            }
            return peliculaDAO.Crear(pelicula);
        }
    }

    public Pelicula buscarPelicula(int id) {
        return peliculaDAO.Buscar(id);
    }

    public boolean actualizarPelicula(Pelicula pelicula) {
        // Verificamos que los campos no estén vacíos y la información sea la correcta
        if (pelicula.getTitulo().isEmpty() || pelicula.getSinopsis().isEmpty() || pelicula.getFechaEstreno().isEmpty() || pelicula.getDuracion() == 0) {
            pelicula.setIdPelicula(-1);
            return false;
        } else {
            peliculaDAO.Actualizar(pelicula);
            return true;
        }
    }

    public boolean eliminarPelicula(int id) {
        peliculaDAO.Eliminar(id);
        //Haremos una busqueda para verificar que la pelicula fue eliminada
        return peliculaDAO.Buscar(id) == null;
    }

    public List<Pelicula> listarPeliculas() {
        return peliculaDAO.Listar();
    }

    // Otros métodos para la lógica de negocio relacionada con películas
}
