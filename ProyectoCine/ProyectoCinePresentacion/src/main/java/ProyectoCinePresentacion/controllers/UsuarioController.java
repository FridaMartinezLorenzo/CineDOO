package ProyectoCinePresentacion.controllers;

import java.util.List;

import ProyectoCinePersistencia.dao.usuario.UsuarioDAO;
import ProyectoCinePersistencia.dao.usuario.UsuarioDAOImpl;
import ProyectoCinePersistencia.entities.Usuario;
import ProyectoCinePresentacion.utils.ViewUtil;

public class UsuarioController {
    private UsuarioDAO usuarioDAO = new UsuarioDAOImpl(ViewUtil.getSqlSessionFactory());

    public Usuario crearUsuario(Usuario usuario) {
        if (usuario.getNombre() == null) {
            usuario.setIdUsuario(-1);
            return usuario;
        } else {
            usuarioDAO.Crear(usuario);
            return usuario;
        }
    }

    public Usuario buscarUsuario(int id) {
        return usuarioDAO.Buscar(id);
    }

    public boolean actualizarUsuario(Usuario usuario) {
        if (usuario.getNombre() == null) {
            usuario.setIdUsuario(-1);
            return false;
        } else {
            usuarioDAO.Actualizar(usuario);
            return true;
        }
    }

    public boolean eliminarUsuario(int id) {
        usuarioDAO.Eliminar(id);
        return usuarioDAO.Buscar(id) == null;
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDAO.Listar();
    }
}
