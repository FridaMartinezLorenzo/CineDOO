package ProyectoCinePersistencia.db.mappers;

import java.util.List;

import ProyectoCinePersistencia.entities.Sala;

public interface SalaMapper {
    
    int insertarSala(Sala sala);
    
    Sala buscarSala(int id);
    
    void actualizarSala(Sala sala);
    
    void eliminarSala(int id);
    
    List<Sala> listarSalas();
}
