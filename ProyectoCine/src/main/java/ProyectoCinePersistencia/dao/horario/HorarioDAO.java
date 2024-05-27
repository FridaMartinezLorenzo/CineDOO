package ProyectoCinePersistencia.dao.horario;

import java.util.List;

import ProyectoCinePersistencia.entities.Horario;

public interface HorarioDAO {
    Horario Crear(Horario horario);
    Horario Buscar(int id);
    void Actualizar(Horario horario);
    void Eliminar(int id);
    List<Horario> Listar();
}
