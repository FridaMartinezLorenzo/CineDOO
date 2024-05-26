package ProyectoCinePersistencia.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ProyectoCinePersistencia.dao.VentaDAO;
import ProyectoCinePersistencia.entities.Venta;
import ProyectoCinePersistencia.utils.dbConnection;

public class VentaDAOImpl implements VentaDAO {

    private dbConnection dbConnection;

    public VentaDAOImpl() {
        this.dbConnection = new dbConnection();
    }

    @Override
    public void Crear(Venta venta) {
        String query = "INSERT INTO ventas (IdUsuario, IdFuncion, Total, CantBoletos) VALUES (?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnection.conectar();
            statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, venta.getIdUsuario());
            statement.setInt(2, venta.getIdFuncion());
            statement.setFloat(3, venta.getTotal());
            statement.setInt(4, venta.getCantBoletos());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                venta.setIdVenta(generatedKeys.getInt(1));
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
    }

    @Override
    public void Actualizar(Venta venta) {
        String query = "UPDATE ventas SET IdUsuario = ?, IdFuncion = ?, Total = ?, CantBoletos = ? WHERE IdVenta = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnection.conectar();
            statement = connection.prepareStatement(query);
            statement.setInt(1, venta.getIdUsuario());
            statement.setInt(2, venta.getIdFuncion());
            statement.setFloat(3, venta.getTotal());
            statement.setInt(4, venta.getCantBoletos());
            statement.setInt(5, venta.getIdVenta());
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
    public void Eliminar(Venta venta) {
        String query = "DELETE FROM ventas WHERE IdVenta = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnection.conectar();
            statement = connection.prepareStatement(query);
            statement.setInt(1, venta.getIdVenta());
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
    public Venta Buscar(int IdVenta) {
        String query = "SELECT "
                + "ventas.IdVenta, "
                + "ventas.IdUsuario, "
                + "usuarios.Nombre, "
                + "funciones.IdFuncion, "
                + "peliculas.Titulo, "
                + "horarios.HoraInicio, "
                + "ventas.Total, "
                + "ventas.CantBoletos "
                + "FROM ventas "
                + "JOIN usuarios ON ventas.IdUsuario = usuarios.IdUsuario "
                + "JOIN funciones ON ventas.IdFuncion = funciones.IdFuncion "
                + "JOIN peliculas ON funciones.IdPelicula = peliculas.IdPelicula "
                + "JOIN horarios ON funciones.IdHorario = horarios.IdHorario "
                + "WHERE ventas.IdVenta = ?";
        Venta venta = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbConnection.conectar();
            statement = connection.prepareStatement(query);
            statement.setInt(1, IdVenta);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                venta = new Venta();
                venta.setIdVenta(resultSet.getInt("IdVenta"));
                venta.setIdUsuario(resultSet.getInt("IdUsuario"));
                venta.setNombreUsuario(resultSet.getString("Nombre"));
                venta.setIdFuncion(resultSet.getInt("IdFuncion"));
                venta.setTituloPelicula(resultSet.getString("Titulo"));
                venta.setHoraInicio(resultSet.getString("HoraInicio"));
                venta.setTotal(resultSet.getFloat("Total"));
                venta.setCantBoletos(resultSet.getInt("CantBoletos"));
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
        return venta;
    }

    @Override
    public List<Venta> Listar() {
        String query = "SELECT "
                + "ventas.IdVenta, "
                + "ventas.IdUsuario, "
                + "usuarios.Nombre, "
                + "funciones.IdFuncion, "
                + "peliculas.Titulo, "
                + "horarios.HoraInicio, "
                + "ventas.Total, "
                + "ventas.CantBoletos "
                + "FROM ventas "
                + "JOIN usuarios ON ventas.IdUsuario = usuarios.IdUsuario "
                + "JOIN funciones ON ventas.IdFuncion = funciones.IdFuncion "
                + "JOIN peliculas ON funciones.IdPelicula = peliculas.IdPelicula "
                + "JOIN horarios ON funciones.IdHorario = horarios.IdHorario "
                + "ORDER BY IdVenta";
        List<Venta> ventas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbConnection.conectar();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Venta venta = new Venta();
                venta.setIdVenta(resultSet.getInt("IdVenta"));
                venta.setIdUsuario(resultSet.getInt("IdUsuario"));
                venta.setNombreUsuario(resultSet.getString("Nombre"));
                venta.setIdFuncion(resultSet.getInt("IdFuncion"));
                venta.setTituloPelicula(resultSet.getString("Titulo"));
                venta.setHoraInicio(resultSet.getString("HoraInicio"));
                venta.setTotal(resultSet.getFloat("Total"));
                venta.setCantBoletos(resultSet.getInt("CantBoletos"));
                ventas.add(venta);
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
        return ventas;
    }

    public static void main(String[] args) {
        VentaDAOImpl ventaDAO = new VentaDAOImpl();

        // Crear una nueva venta
        Venta nuevaVenta = new Venta();
        nuevaVenta.setIdUsuario(3); // IdUsuario de "Maria Garcia"
        nuevaVenta.setIdFuncion(1); // IdFuncion para la función 1
        nuevaVenta.setTotal(150.00f);
        nuevaVenta.setCantBoletos(2);
        ventaDAO.Crear(nuevaVenta);
        System.out.println("Venta creada con ID: " + nuevaVenta.getIdVenta());

        // Buscar una venta por su ID
        Venta ventaEncontrada = ventaDAO.Buscar(nuevaVenta.getIdVenta());
        if (ventaEncontrada != null) {
            System.out.println("Venta encontrada: " + ventaEncontrada.getIdVenta() + ", Usuario: " + ventaEncontrada.getNombreUsuario());
        } else {
            System.out.println("Venta no encontrada.");
        }

        // Actualizar la venta
        ventaEncontrada.setTotal(200.00f);
        ventaDAO.Actualizar(ventaEncontrada);
        System.out.println("Venta actualizada: " + ventaEncontrada.getIdVenta() + ", Nuevo Total: " + ventaEncontrada.getTotal());

        // Listar todas las ventas
        List<Venta> listaVentas = ventaDAO.Listar();
        System.out.println("Lista de Ventas:");
        for (Venta venta : listaVentas) {
            System.out.println("ID: " + venta.getIdVenta() + ", Usuario: " + venta.getNombreUsuario() + ", Pelicula: " + venta.getTituloPelicula() + ", Hora: " + venta.getHoraInicio() + ", Total: " + venta.getTotal() + ", Boletos: " + venta.getCantBoletos());
        }

        // Eliminar una venta
        ventaDAO.Eliminar(nuevaVenta);
        System.out.println("Venta eliminada con ID: " + nuevaVenta.getIdVenta());
    }
}
