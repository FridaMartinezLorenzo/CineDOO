package ProyectoCinePersistencia.db.mappers;

import java.util.List;

import ProyectoCinePersistencia.entities.Horario;

public interface HorarioMapper {
    void insertarHorario(Horario horario);
    Horario buscarHorario(int id);
    void actualizarHorario(Horario horario);
    void eliminarHorario(int id);
    List<Horario> listarHorarios();
}
