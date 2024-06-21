package ProyectoCinePersistencia.dao.funcion;

import java.util.List;

import ProyectoCinePersistencia.entities.Funcion;

public interface FuncionDAO {

    Funcion Crear(Funcion funcion);

    Funcion Buscar(int id);

    void Actualizar(Funcion funcion);

    void Eliminar(int id);

    List<Funcion> Listar();

    List<Funcion> ListarPorPelicula(int idPelicula);

    int BoletosDisponibles(int idFuncion);
}
