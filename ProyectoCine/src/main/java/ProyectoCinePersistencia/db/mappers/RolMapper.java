package ProyectoCinePersistencia.db.mappers;

import java.util.List;

import ProyectoCinePersistencia.entities.Rol;

public interface RolMapper {

    List<Rol> listarRoles();

    boolean insertarRol(Rol rol);

    boolean eliminarRol(int id);

    boolean actualizarRol(Rol rol);

    Rol buscarRol(int id);
}
