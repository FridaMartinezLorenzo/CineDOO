package ProyectoCinePersistencia.dao.usuario;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import ProyectoCinePersistencia.db.mappers.UsuarioMapper;
import ProyectoCinePersistencia.entities.Usuario;

public class UsuarioDAOImpl {

    private final SqlSessionFactory sqlSessionFactory;

    public UsuarioDAOImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public boolean Crear(Usuario usuario) {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            UsuarioMapper mapper = session.getMapper(UsuarioMapper.class);
            mapper.insertarUsuario(usuario);
            session.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public boolean Actualizar(Usuario usuario) {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            UsuarioMapper mapper = session.getMapper(UsuarioMapper.class);
            mapper.actualizarUsuario(usuario);
            session.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public boolean Eliminar(int id) {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            UsuarioMapper mapper = session.getMapper(UsuarioMapper.class);
            mapper.eliminarUsuario(id);
            session.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public Usuario Buscar(int id) {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            UsuarioMapper mapper = session.getMapper(UsuarioMapper.class);
            return mapper.buscarUsuario(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Usuario> Listar() {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            UsuarioMapper mapper = session.getMapper(UsuarioMapper.class);
            return mapper.listarUsuarios();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
