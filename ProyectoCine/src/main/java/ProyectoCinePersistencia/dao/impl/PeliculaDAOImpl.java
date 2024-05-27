package ProyectoCinePersistencia.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ProyectoCinePersistencia.dao.PeliculaDAO;
import ProyectoCinePersistencia.entities.Pelicula;
import ProyectoCinePersistencia.utils.dbConnection;

public class PeliculaDAOImpl implements PeliculaDAO {

    private dbConnection dbConnection;

    public PeliculaDAOImpl() {
        this.dbConnection = new dbConnection();
    }

    @Override
    public Pelicula Crear(Pelicula pelicula) {
        String query = "INSERT INTO peliculas (Titulo, Sinopsis, Duracion, FechaEstreno, IdCategoria) VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        try {
            connection = dbConnection.conectar();
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, pelicula.getTitulo());
            statement.setString(2, pelicula.getSinopsis());
            statement.setInt(3, pelicula.getDuracion());
            statement.setString(4, pelicula.getFechaEstreno());
            statement.setInt(5, pelicula.getIdCategoria());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                pelicula.setIdPelicula(generatedKeys.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dbConnection.desconectar();
        }
        return pelicula;
    }

    @Override
    public Pelicula Buscar(int id) {
        String query = "SELECT * FROM peliculas WHERE IdPelicula = ?";
        Pelicula pelicula = null;
        Connection connection = null;
        try {
            connection = dbConnection.conectar();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                pelicula = new Pelicula();
                pelicula.setIdPelicula(resultSet.getInt("IdPelicula"));
                pelicula.setTitulo(resultSet.getString("Titulo"));
                pelicula.setSinopsis(resultSet.getString("Sinopsis"));
                pelicula.setDuracion(resultSet.getInt("Duracion"));
                pelicula.setFechaEstreno(resultSet.getString("FechaEstreno"));
                pelicula.setIdCategoria(resultSet.getInt("IdCategoria"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dbConnection.desconectar();
        }
        return pelicula;
    }

    @Override
    public void Actualizar(Pelicula pelicula) {
        // Verificar si la película existe antes de intentar actualizarla
        Pelicula peliculaExistente = Buscar(pelicula.getIdPelicula());
        if (peliculaExistente == null) {
            System.out.println("La película con ID " + pelicula.getIdPelicula() + " no existe en la base de datos.");
            return; // Salir del método si la película no existe
        }

        String query = "UPDATE peliculas SET Titulo = ?, Sinopsis = ?, Duracion = ?, FechaEstreno = ?, IdCategoria = ? WHERE IdPelicula = ?";
        try (Connection connection = dbConnection.conectar(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, pelicula.getTitulo());
            statement.setString(2, pelicula.getSinopsis());
            statement.setInt(3, pelicula.getDuracion());
            statement.setString(4, pelicula.getFechaEstreno());
            statement.setInt(5, pelicula.getIdCategoria());
            statement.setInt(6, pelicula.getIdPelicula());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void Eliminar(int id) {
        String query = "DELETE FROM peliculas WHERE IdPelicula = ?";
        Connection connection = null;
        try {
            connection = dbConnection.conectar();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dbConnection.desconectar();
        }
    }

    @Override
    public List<Pelicula> Listar() {
        String query = "SELECT * FROM peliculas";
        List<Pelicula> peliculas = new ArrayList<>();
        Connection connection = null;
        try {
            connection = dbConnection.conectar();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Pelicula pelicula = new Pelicula();
                pelicula.setIdPelicula(resultSet.getInt("IdPelicula"));
                pelicula.setTitulo(resultSet.getString("Titulo"));
                pelicula.setSinopsis(resultSet.getString("Sinopsis"));
                pelicula.setDuracion(resultSet.getInt("Duracion"));
                pelicula.setFechaEstreno(resultSet.getString("FechaEstreno"));
                pelicula.setIdCategoria(resultSet.getInt("IdCategoria"));
                peliculas.add(pelicula);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dbConnection.desconectar();
        }
        return peliculas;
    }

    /*Codigo utilizado para probar la implementación de la clase PeliculaDAOImpl
    public static void main(String[] args) {
        PeliculaDAOImpl peliculaDAO = new PeliculaDAOImpl();

        // Crear una nueva película
        Pelicula nuevaPelicula = new Pelicula(0, "Inception", "A mind-bending thriller", 148, "2010-07-16", 1);
        peliculaDAO.Crear(nuevaPelicula);
        System.out.println("Id de la nueva pelicula :" + nuevaPelicula.getIdPelicula());

        // Buscar la película creada
        Pelicula peliculaObtenida = peliculaDAO.Buscar(2); // Cambia el ID según sea necesario
        if (peliculaObtenida != null) {
            System.out.println("\n\nPelicula obtenida: " + peliculaObtenida.getTitulo());
        } else {
            System.out.println("No se encontro la pelicula con ID 1.");
        }

        // Actualizar la película
        if (peliculaObtenida != null) {
            peliculaObtenida.setSinopsis("A thriller that bends the mind");
            peliculaDAO.Actualizar(peliculaObtenida);
            System.out.println("\n\nSinopsis de Pelicula Actualizada:" + peliculaObtenida.getSinopsis());
        }

        // Buscar todas las películas
        List<Pelicula> todasPeliculas = peliculaDAO.Listar();
        System.out.println("\n\nTodas las peliculas:");
        for (Pelicula pelicula : todasPeliculas) {
            System.out.println(pelicula.getTitulo());
        }

        // Eliminar la película
        peliculaDAO.Eliminar(20); // Cambia el ID según sea necesario
    }
     */
}
