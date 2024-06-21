package ProyectoCinePersistencia.dao.boleto;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import ProyectoCinePersistencia.db.mappers.BoletoMapper;
import ProyectoCinePersistencia.entities.Boleto;

public class BoletoDAOImpl implements BoletoDAO {

    private SqlSessionFactory sqlSessionFactory;

    public BoletoDAOImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<Boleto> Listar() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            BoletoMapper mapper = session.getMapper(BoletoMapper.class);
            return mapper.listarTodos();
        }
    }

    @Override
    public Boleto Buscar(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            BoletoMapper mapper = session.getMapper(BoletoMapper.class);
            return mapper.obtenerPorId(id);
        }
    }

    @Override
    public void Crear(Boleto boleto) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            BoletoMapper mapper = session.getMapper(BoletoMapper.class);
            mapper.insertar(boleto);
            session.commit();
        }
    }

    @Override
    public void Actualizar(Boleto boleto) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            BoletoMapper mapper = session.getMapper(BoletoMapper.class);
            mapper.actualizar(boleto);
            session.commit();
        }
    }

    @Override
    public void Eliminar(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            BoletoMapper mapper = session.getMapper(BoletoMapper.class);
            mapper.eliminar(id);
            session.commit();
        }
    }
}
