package ProyectoCinePersistencia.dao.categoria;

import java.util.List;

import ProyectoCinePersistencia.entities.Categoria;

public interface CategoriaDAO {
    Categoria Crear(Categoria categoria);
    Categoria Buscar(int id);
    void Actualizar(Categoria categoria);
    void Eliminar(int id);
    List<Categoria> Listar();

}
