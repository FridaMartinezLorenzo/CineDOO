package ProyectoCinePersistencia.dao.sala;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import ProyectoCinePersistencia.db.mappers.SalaMapper;
import ProyectoCinePersistencia.entities.Sala;

public class SalaDAOImpl implements SalaDAO {

    private final SqlSessionFactory sqlSessionFactory;

    public SalaDAOImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public Sala Crear(Sala sala) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            SalaMapper mapper = session.getMapper(SalaMapper.class);
            mapper.insertarSala(sala);
            session.commit();
            return sala; // El ID generado está en el objeto Sala después de la inserción
        }
    }

    @Override
    public Sala Buscar(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            SalaMapper mapper = session.getMapper(SalaMapper.class);
            return mapper.buscarSala(id);
        }
    }

    @Override
    public void Actualizar(Sala sala) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            SalaMapper mapper = session.getMapper(SalaMapper.class);
            mapper.actualizarSala(sala);
            session.commit();
        }
    }

    @Override
    public void Eliminar(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            SalaMapper mapper = session.getMapper(SalaMapper.class);
            mapper.eliminarSala(id);
            session.commit();
        }
    }

    @Override
    public List<Sala> Listar() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            SalaMapper mapper = session.getMapper(SalaMapper.class);
            return mapper.listarSalas();
        }
    }
}
