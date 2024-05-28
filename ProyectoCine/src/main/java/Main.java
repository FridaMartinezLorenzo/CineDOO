
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;

import ProyectoCinePersistencia.dao.pelicula.PeliculaDAO;
import ProyectoCinePersistencia.dao.pelicula.PeliculaDAOImpl;
import ProyectoCinePersistencia.entities.Pelicula;
import ProyectoCinePersistencia.utils.MyBatisUtil;

public class Main {

    public static void main(String[] args) {
        // Obtén una instancia de SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();

        // Pasa la instancia de SqlSessionFactory al constructor de PeliculaDAOImpl
        PeliculaDAO peliculaDAO = new PeliculaDAOImpl(sqlSessionFactory);

        // Crear una nueva película
        Pelicula nuevaPelicula = new Pelicula();
        nuevaPelicula.setTitulo("Inception");
        nuevaPelicula.setSinopsis("A mind-bending thriller");
        nuevaPelicula.setDuracion(148);
        nuevaPelicula.setFechaEstreno("2010-07-16");
        nuevaPelicula.setIdCategoria(1);

        // Insertar la película y obtener el objeto Pelicula que contiene el ID generado
        Pelicula peliculaCreada = peliculaDAO.Crear(nuevaPelicula);
        int idPeliculaCreada = peliculaCreada.getIdPelicula();
        System.out.println("Película creada con ID: " + idPeliculaCreada);

        // Buscar la película creada
        Pelicula peliculaEncontrada = peliculaDAO.Buscar(idPeliculaCreada);
        System.out.println("Película encontrada: " + peliculaEncontrada.getTitulo());

        // Actualizar la película
        peliculaEncontrada.setSinopsis("An updated synopsis");
        peliculaDAO.Actualizar(peliculaEncontrada);
        Pelicula peliculaActualizada = peliculaDAO.Buscar(idPeliculaCreada);
        System.out.println("Película actualizada: " + peliculaActualizada.getSinopsis());

        // Listar todas las películas
        List<Pelicula> peliculas = peliculaDAO.Listar();
        for (Pelicula p : peliculas) {
            System.out.println("Película: " + p.getTitulo());
        }

        // Eliminar la película
        peliculaDAO.Eliminar(idPeliculaCreada);
        System.out.println("Película eliminada");
    }
}
