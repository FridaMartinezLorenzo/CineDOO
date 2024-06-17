package ProyectoCinePresentacion.controllers;

import java.util.List;

import ProyectoCinePersistencia.dao.pelicula.PeliculaDAO;
import ProyectoCinePersistencia.dao.pelicula.PeliculaDAOImpl;
import ProyectoCinePersistencia.entities.Pelicula;
import ProyectoCinePresentacion.utils.ViewUtil;

public class PeliculaController {

    private PeliculaDAO peliculaDAO = new PeliculaDAOImpl(ViewUtil.getSqlSessionFactory());

    public Pelicula crearPelicula(Pelicula pelicula) {
        // Verificamos que los campos no estén vacíos y la información sea la correcta
        if (pelicula.getTitulo().isEmpty() || pelicula.getSinopsis().isEmpty() || pelicula.getFechaEstreno().isEmpty() || pelicula.getDuracion() == 0) {
            pelicula.setIdPelicula(-1);
            return pelicula;
        } else {
            // Puedes llamar a métodos de PeliculaDAO para buscar la película por título u otro criterio
            if (peliculaDAO.BuscarPorTitulo(pelicula.getTitulo()) != null) {
                // Si la película ya existe, entonces retornamos la película con un ID negativo
                pelicula.setIdPelicula(-1);
                return pelicula;
            }
            // Si la película no existe, entonces procedemos a crearla
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
            // Puedes llamar a métodos de PeliculaDAO para buscar la película por título u otro criterio
            if (peliculaDAO.BuscarPorTitulo(pelicula.getTitulo()) != null) {
                // Si la película ya existe, entonces retornamos la película con un ID negativo
                pelicula.setIdPelicula(-1);
                return false;
            }
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
