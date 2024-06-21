
package ProyectoCinePersistencia.dao.roles;

import ProyectoCinePersistencia.entities.Rol;
import java.util.List;



public interface RolesDAO {
    List<Rol> Listar();
    boolean Crear(Rol rol); 
    boolean Eliminar(int id);
    boolean Actualizar(Rol rol);
    Rol BuscaRol(int id);
}
