package ProyectoCinePersistencia.dao.pelicula;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import ProyectoCinePersistencia.db.mappers.PeliculaMapper;
import ProyectoCinePersistencia.entities.Pelicula;

public class PeliculaDAOImpl implements PeliculaDAO {

    private final SqlSessionFactory sqlSessionFactory;

    public PeliculaDAOImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public Pelicula Crear(Pelicula pelicula) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PeliculaMapper mapper = session.getMapper(PeliculaMapper.class);
            int rowsAffected = mapper.insertarPelicula(pelicula);
            session.commit();
            return pelicula; // El ID generado está en el objeto Pelicula después de la inserción
        }
    }

    @Override
    public Pelicula Buscar(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PeliculaMapper mapper = session.getMapper(PeliculaMapper.class);
            return mapper.buscarPelicula(id);
        }
    }

    @Override
    public Pelicula BuscarPorTitulo(String titulo) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PeliculaMapper mapper = session.getMapper(PeliculaMapper.class);
            return mapper.buscarPeliculaPorTitulo(titulo);
        }
    }

    @Override
    public void Actualizar(Pelicula pelicula) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PeliculaMapper mapper = session.getMapper(PeliculaMapper.class);
            mapper.actualizarPelicula(pelicula);
            session.commit();
        }
    }

    @Override
    public void Eliminar(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PeliculaMapper mapper = session.getMapper(PeliculaMapper.class);
            mapper.eliminarPelicula(id);
            session.commit();
        }
    }

    @Override
    public List<Pelicula> Listar() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PeliculaMapper mapper = session.getMapper(PeliculaMapper.class);
            return mapper.listarPeliculas();
        }
    }
}
