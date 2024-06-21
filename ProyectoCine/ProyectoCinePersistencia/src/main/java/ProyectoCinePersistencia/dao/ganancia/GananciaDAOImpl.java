package ProyectoCinePersistencia.dao.ganancia;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import ProyectoCinePersistencia.db.mappers.GananciaMapper;

public class GananciaDAOImpl implements GananciaDAO{

    private SqlSessionFactory sqlSessionFactory;

    public GananciaDAOImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public Double obtenerGanancias(int idPelicula) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            GananciaMapper mapper = session.getMapper(GananciaMapper.class);
            return mapper.sacarGanancia(idPelicula);
        }
    }
}
