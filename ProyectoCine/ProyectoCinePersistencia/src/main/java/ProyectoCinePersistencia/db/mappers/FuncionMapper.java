package ProyectoCinePersistencia.db.mappers;

import java.util.List;

import ProyectoCinePersistencia.entities.Funcion;

public interface FuncionMapper {

    Funcion getFuncionById(int id);

    List<Funcion> getAllFunciones();

    List<Funcion> getFuncionesByPelicula(int idPelicula);

    void insertFuncion(Funcion funcion);

    void updateFuncion(Funcion funcion);

    void deleteFuncion(int id);

    int getNumAsientosVendidos(int idFuncion);

    int getNumAsientosTotales(int idFuncion);
}
