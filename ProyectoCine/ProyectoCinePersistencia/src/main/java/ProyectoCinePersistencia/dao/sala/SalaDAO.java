package ProyectoCinePersistencia.dao.sala;

import java.util.List;

import ProyectoCinePersistencia.entities.Sala;

public interface SalaDAO {

    Sala Crear(Sala sala);

    Sala Buscar(int id);

    void Actualizar(Sala sala);

    void Eliminar(int id);

    List<Sala> Listar();
}
