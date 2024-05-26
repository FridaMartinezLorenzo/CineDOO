package ProyectoCinePersistencia.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ProyectoCinePersistencia.dao.CategoriaDAO;
import ProyectoCinePersistencia.entities.Categoria;
import ProyectoCinePersistencia.utils.dbConnection;

public class CategoriaDAOImpl implements CategoriaDAO {

    private dbConnection dbConnection;

    public CategoriaDAOImpl() {
        this.dbConnection = new dbConnection();
    }

    @Override
    public Categoria Crear(Categoria categoria) {
        String query = "INSERT INTO categorias (Nombre) VALUES (?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnection.conectar();
            statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, categoria.getNombre());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                categoria.setIdCategoria(generatedKeys.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    dbConnection.desconectar();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return categoria;
    }

    @Override
    public Categoria Buscar(int id) {
        String query = "SELECT * FROM categorias WHERE IdCategoria = ?";
        Categoria categoria = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbConnection.conectar();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                categoria = new Categoria();
                categoria.setIdCategoria(resultSet.getInt("IdCategoria"));
                categoria.setNombre(resultSet.getString("Nombre"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    dbConnection.desconectar();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return categoria;
    }

    @Override
    public void Actualizar(Categoria categoria) {
        String query = "UPDATE categorias SET Nombre = ? WHERE IdCategoria = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnection.conectar();
            statement = connection.prepareStatement(query);
            statement.setString(1, categoria.getNombre());
            statement.setInt(2, categoria.getCategoria());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    dbConnection.desconectar();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void Eliminar(int id) {
        String query = "DELETE FROM categorias WHERE IdCategoria = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnection.conectar();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    dbConnection.desconectar();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Categoria> Listar() {
        String query = "SELECT * FROM categorias";
        List<Categoria> categorias = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbConnection.conectar();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(resultSet.getInt("IdCategoria"));
                categoria.setNombre(resultSet.getString("Nombre"));
                categorias.add(categoria);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    dbConnection.desconectar();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return categorias;
    }
    /*
    public static void main(String[] args) {
        CategoriaDAOImpl categoriaDAO = new CategoriaDAOImpl();

        // Crear una nueva categoría
        Categoria nuevaCategoria = new Categoria();
        nuevaCategoria.setNombre("Acción");
        Categoria categoriaCreada = categoriaDAO.Crear(nuevaCategoria);
        System.out.println("Categoría creada: ID = " + categoriaCreada.getCategoria() + ", Nombre = " + categoriaCreada.getNombre());

        // Buscar la categoría creada
        Categoria categoriaObtenida = categoriaDAO.Buscar(categoriaCreada.getCategoria());
        System.out.println("Categoría obtenida: ID = " + categoriaObtenida.getCategoria() + ", Nombre = " + categoriaObtenida.getNombre());

        // Actualizar la categoría
        if (categoriaObtenida != null) {
            categoriaObtenida.setNombre("Aventura");
            categoriaDAO.Actualizar(categoriaObtenida);
            Categoria categoriaActualizada = categoriaDAO.Buscar(categoriaObtenida.getCategoria());
            System.out.println("Categoría actualizada: ID = " + categoriaActualizada.getCategoria() + ", Nombre = " + categoriaActualizada.getNombre());
        }

        // Listar todas las categorías
        List<Categoria> todasCategorias = categoriaDAO.Listar();
        System.out.println("Todas las categorías:");
        for (Categoria categoria : todasCategorias) {
            System.out.println("ID = " + categoria.getCategoria() + ", Nombre = " + categoria.getNombre());
        }

        // Eliminar la categoría
        categoriaDAO.Eliminar(categoriaCreada.getCategoria());
        System.out.println("Categoría con ID " + categoriaCreada.getCategoria() + " eliminada.");

        // Verificar que la categoría ha sido eliminada
        Categoria categoriaEliminada = categoriaDAO.Buscar(categoriaCreada.getCategoria());
        if (categoriaEliminada == null) {
            System.out.println("La categoría con ID " + categoriaCreada.getCategoria() + " no existe en la base de datos.");
        }
    }
     */
}
