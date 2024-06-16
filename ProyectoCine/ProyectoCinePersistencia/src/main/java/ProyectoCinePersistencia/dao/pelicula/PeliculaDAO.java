package ProyectoCinePersistencia.dao.pelicula;

import java.util.List;

import ProyectoCinePersistencia.entities.Pelicula;
        
public interface PeliculaDAO {
    Pelicula Crear(Pelicula pelicula);
    Pelicula Buscar(int id);
    Pelicula BuscarPorTitulo(String titulo);
    void Actualizar(Pelicula pelicula);
    void Eliminar (int id);
    List<Pelicula> Listar();
    
}
