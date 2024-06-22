package ProyectoCinePersistencia.dao.usuario;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import ProyectoCinePersistencia.db.mappers.UsuarioMapper;
import ProyectoCinePersistencia.entities.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {

    private final SqlSessionFactory sqlSessionFactory;

    public UsuarioDAOImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
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
