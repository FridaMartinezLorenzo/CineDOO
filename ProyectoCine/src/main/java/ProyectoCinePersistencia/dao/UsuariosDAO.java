package ProyectoCinePersistencia.dao;


import ProyectoCinePersistencia.entities.Usuario;
import java.util.List;

public interface UsuariosDAO {
    public boolean Crear(Usuario usuario);
    public boolean Actualizar(Usuario usuario);
    public boolean Eliminar(int idUsuario);
    public List<Usuario> Listar();
    public Usuario Buscar(int idUsuario);
}
