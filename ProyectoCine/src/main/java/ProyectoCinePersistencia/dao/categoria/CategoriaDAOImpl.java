package ProyectoCinePersistencia.dao.categoria;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import ProyectoCinePersistencia.db.mappers.CategoriaMapper;
import ProyectoCinePersistencia.entities.Categoria;

public class CategoriaDAOImpl implements CategoriaDAO {

    private SqlSessionFactory sqlSessionFactory;

    public CategoriaDAOImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public Categoria Crear(Categoria categoria) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CategoriaMapper mapper = session.getMapper(CategoriaMapper.class);
            mapper.insertarCategoria(categoria);
            session.commit();
            return categoria;
        }
    }

    @Override
    public Categoria Buscar(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CategoriaMapper mapper = session.getMapper(CategoriaMapper.class);
            return mapper.buscarCategoria(id);
        }
    }

    @Override
    public void Actualizar(Categoria categoria) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CategoriaMapper mapper = session.getMapper(CategoriaMapper.class);
            mapper.actualizarCategoria(categoria);
            session.commit();
        }
    }

    @Override
    public void Eliminar(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CategoriaMapper mapper = session.getMapper(CategoriaMapper.class);
            mapper.eliminarCategoria(id);
            session.commit();
        }
    }

    @Override
    public List<Categoria> Listar() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CategoriaMapper mapper = session.getMapper(CategoriaMapper.class);
            return mapper.listarCategorias();
        }
    }
}
