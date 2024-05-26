package ProyectoCinePersistencia.dao;

import java.util.List;

import ProyectoCinePersistencia.entities.Horario;

public interface HorarioDAO {
    Horario Crear(Horario horario);
    Horario Obtener(int id);
    void Actualizar(Horario horario);
    void Eliminar(int id);
    List<Horario> ObtenerTodos();
}
