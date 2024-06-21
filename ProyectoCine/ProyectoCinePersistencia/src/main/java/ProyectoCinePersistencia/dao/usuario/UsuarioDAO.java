package ProyectoCinePersistencia.dao.usuario;

import java.util.List;

import ProyectoCinePersistencia.entities.Usuario;

public interface UsuarioDAO {

    boolean Crear(Usuario usuario);

    boolean Actualizar(Usuario usuario);

    boolean Eliminar(int idUsuario);

    List<Usuario> Listar();

    Usuario Buscar(int idUsuario);
}
