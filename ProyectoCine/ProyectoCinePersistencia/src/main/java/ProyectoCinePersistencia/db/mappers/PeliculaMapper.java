package ProyectoCinePersistencia.db.mappers;

import java.util.List;

import ProyectoCinePersistencia.entities.Pelicula;

public interface PeliculaMapper {

    int insertarPelicula(Pelicula pelicula);

    Pelicula buscarPelicula(int id);

    Pelicula buscarPeliculaPorTitulo(String titulo);

    void actualizarPelicula(Pelicula pelicula);

    void eliminarPelicula(int id);

    List<Pelicula> listarPeliculas();
}
