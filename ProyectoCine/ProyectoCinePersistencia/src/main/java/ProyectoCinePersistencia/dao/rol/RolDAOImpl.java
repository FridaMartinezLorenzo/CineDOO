package ProyectoCinePersistencia.dao.rol;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import ProyectoCinePersistencia.db.mappers.RolMapper;
import ProyectoCinePersistencia.entities.Rol;

public class RolDAOImpl implements RolDAO {

    private SqlSessionFactory sqlSessionFactory;

    public RolDAOImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<Rol> Listar() {
        List<Rol> roles = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            RolMapper rolMapper = session.getMapper(RolMapper.class);
            roles = rolMapper.listarRoles();
        } finally {
            session.close();
        }
        return roles;
    }

    @Override
    public boolean Crear(Rol rol) {
        boolean result = false;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            RolMapper rolMapper = session.getMapper(RolMapper.class);
            result = rolMapper.insertarRol(rol);
            session.commit();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public boolean Eliminar(int id) {
        boolean result = false;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            RolMapper rolMapper = session.getMapper(RolMapper.class);
            result = rolMapper.eliminarRol(id);
            session.commit();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public boolean Actualizar(Rol rol) {
        boolean result = false;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            RolMapper rolMapper = session.getMapper(RolMapper.class);
            result = rolMapper.actualizarRol(rol);
            session.commit();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public Rol BuscaRol(int id) {
        Rol rol = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            RolMapper rolMapper = session.getMapper(RolMapper.class);
            rol = rolMapper.buscarRol(id);
        } finally {
            session.close();
        }
        return rol;
    }
}
