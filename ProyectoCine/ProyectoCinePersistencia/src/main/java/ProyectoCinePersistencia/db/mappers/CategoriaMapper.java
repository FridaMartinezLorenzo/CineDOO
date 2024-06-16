package ProyectoCinePersistencia.db.mappers;


import java.util.List;

import ProyectoCinePersistencia.entities.Categoria;

public interface CategoriaMapper {
    void insertarCategoria(Categoria categoria);
    Categoria buscarCategoria(int id);
    void actualizarCategoria(Categoria categoria);
    void eliminarCategoria(int id);
    List<Categoria> listarCategorias();
}
