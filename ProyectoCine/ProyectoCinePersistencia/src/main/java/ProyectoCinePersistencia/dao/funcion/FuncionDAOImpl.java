package ProyectoCinePersistencia.dao.funcion;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import ProyectoCinePersistencia.db.mappers.FuncionMapper;
import ProyectoCinePersistencia.entities.Funcion;
import ProyectoCinePersistencia.utils.MyBatisUtil;

public class FuncionDAOImpl implements FuncionDAO {

    private final SqlSessionFactory sqlSessionFactory;

    public FuncionDAOImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public Funcion Crear(Funcion funcion) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            FuncionMapper mapper = session.getMapper(FuncionMapper.class);
            mapper.insertFuncion(funcion);
            session.commit();
            return funcion; // El ID generado está en el objeto Funcion después de la inserción
        }
    }

    @Override
    public Funcion Buscar(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            FuncionMapper mapper = session.getMapper(FuncionMapper.class);
            return mapper.getFuncionById(id);
        }
    }

    @Override
    public void Actualizar(Funcion funcion) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            FuncionMapper mapper = session.getMapper(FuncionMapper.class);
            mapper.updateFuncion(funcion);
            session.commit();
        }
    }

    @Override
    public void Eliminar(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            FuncionMapper mapper = session.getMapper(FuncionMapper.class);
            mapper.deleteFuncion(id);
            session.commit();
        }
    }

    @Override
    public List<Funcion> Listar() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            FuncionMapper mapper = session.getMapper(FuncionMapper.class);
            return mapper.getAllFunciones();
        }
    }

    @Override
    public List<Funcion> ListarPorPelicula(int idPelicula) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            FuncionMapper mapper = session.getMapper(FuncionMapper.class);
            return mapper.getFuncionesByPelicula(idPelicula);
        }
    }

    @Override
    public int BoletosDisponibles(int idFuncion) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            FuncionMapper mapper = session.getMapper(FuncionMapper.class);
            int numAsientosVendidos = mapper.getNumAsientosVendidos(idFuncion);
            int numAsientosTotales = mapper.getNumAsientosTotales(idFuncion);
            return numAsientosTotales - numAsientosVendidos;
        }
    }
}
