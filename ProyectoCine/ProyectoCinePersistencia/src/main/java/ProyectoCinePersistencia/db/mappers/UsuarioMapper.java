package ProyectoCinePersistencia.db.mappers;

import java.util.List;

import ProyectoCinePersistencia.entities.Usuario;

public interface UsuarioMapper {

    int insertarUsuario(Usuario usuario);

    boolean actualizarUsuario(Usuario usuario);

    boolean eliminarUsuario(int idUsuario);

    List<Usuario> listarUsuarios();

    Usuario buscarUsuario(int idUsuario);
}
