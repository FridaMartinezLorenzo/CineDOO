package ProyectoCinePersistencia.dao.rol;

import java.util.List;

import ProyectoCinePersistencia.entities.Rol;

public interface RolDAO {

    List<Rol> Listar();

    boolean Crear(Rol rol);

    boolean Eliminar(int id);

    boolean Actualizar(Rol rol);

    Rol BuscaRol(int id);
}
