package ProyectoCinePersistencia.dao.boleto;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import ProyectoCinePersistencia.entities.Boleto;

public class BoletoDAOImpl implements BoletoDAO {

    private SqlSessionFactory sqlSessionFactory;

    public BoletoDAOImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<Boleto> listarTodos() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("BoletoMapper.listarTodos");
        }
    }

    @Override
    public Boleto obtenerPorId(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("BoletoMapper.obtenerPorId", id);
        }
    }

    @Override
    public void insertar(Boleto boleto) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.insert("BoletoMapper.insertar", boleto);
            session.commit();
        }
    }

    @Override
    public void actualizar(Boleto boleto) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.update("BoletoMapper.actualizar", boleto);
            session.commit();
        }
    }

    @Override
    public void eliminar(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.delete("BoletoMapper.eliminar", id);
            session.commit();
        }
    }
}
