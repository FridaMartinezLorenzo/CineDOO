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
    public void Crear(Pelicula pelicula) {
        String query = "INSERT INTO peliculas (Titulo, Sinopsis, Duracion, FechaEstreno, IdCategoria) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dbConnection.conectar();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, pelicula.getTitulo());
            statement.setString(2, pelicula.getSinopsis());
            statement.setInt(3, pelicula.getDuracion());
            statement.setString(4, pelicula.getFechaEstreno());
            statement.setInt(5, pelicula.getIdCategoria());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Pelicula Obtener(int id) {
        String query = "SELECT * FROM peliculas WHERE IdPelicula = ?";
        Pelicula pelicula = null;
        try (Connection connection = dbConnection.conectar();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                pelicula = new Pelicula();
                pelicula.setIdPelicula(resultSet.getInt("idPelicula"));
                pelicula.setTitulo(resultSet.getString("titulo"));
                pelicula.setSinopsis(resultSet.getString("sinopsis"));
                pelicula.setDuracion(resultSet.getInt("duracion"));
                pelicula.setFechaEstreno(resultSet.getString("fechaEstreno"));
                pelicula.setIdCategoria(resultSet.getInt("idCategoria"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pelicula;
    }

    @Override
    public void Actualizar(Pelicula pelicula) {
        String query = "UPDATE peliculas SET Titulo = ?, Sinopsis = ?, Duracion = ?, FechaEstreno = ? WHERE IdPelicula = ?";
        try (Connection connection = dbConnection.conectar();
             PreparedStatement statement = connection.prepareStatement(query)) {
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
        try (Connection connection = dbConnection.conectar();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Pelicula> ObtenerTodas() {
        String query = "SELECT * FROM peliculas";
        List<Pelicula> peliculas = new ArrayList<>();
        try (Connection connection = dbConnection.conectar();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Pelicula pelicula = new Pelicula();
                pelicula.setIdPelicula(resultSet.getInt("idPelicula"));
                pelicula.setTitulo(resultSet.getString("titulo"));
                pelicula.setSinopsis(resultSet.getString("sinopsis"));
                pelicula.setDuracion(resultSet.getInt("duracion"));
                pelicula.setFechaEstreno(resultSet.getString("fechaEstreno"));
                pelicula.setIdCategoria(resultSet.getInt("idCategoria"));
                peliculas.add(pelicula);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return peliculas;
    }
}
