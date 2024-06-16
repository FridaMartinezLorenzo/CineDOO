package ProyectoCinePersistencia.dao.promocion;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ProyectoCinePersistencia.db.mappers.PromocionMapper;
import ProyectoCinePersistencia.entities.Promocion;

public class PromocionDAOImpl implements PromocionDAO {

    private SqlSessionFactory sqlSessionFactory;

    public PromocionDAOImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public Promocion Crear(Promocion promocion) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PromocionMapper mapper = session.getMapper(PromocionMapper.class);
            mapper.insertarPromocion(promocion);
            session.commit();
            return promocion;
        }
    }

    @Override
    public Promocion Buscar(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PromocionMapper mapper = session.getMapper(PromocionMapper.class);
            return mapper.buscarPromocion(id);
        }
    }

    @Override
    public void Actualizar(Promocion promocion) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PromocionMapper mapper = session.getMapper(PromocionMapper.class);
            mapper.actualizarPromocion(promocion);
            session.commit();
        }
    }

    @Override
    public void Eliminar(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PromocionMapper mapper = session.getMapper(PromocionMapper.class);
            mapper.eliminarPromocion(id);
            session.commit();
        }
    }

    @Override
    public List<Promocion> Listar() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PromocionMapper mapper = session.getMapper(PromocionMapper.class);
            return mapper.listarPromociones();
        }
    }
}
