package ProyectoCinePersistencia.dao.horario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ProyectoCinePersistencia.dao.HorarioDAO;
import ProyectoCinePersistencia.entities.Horario;
import ProyectoCinePersistencia.utils.dbConnection;

public class HorarioDAOImpl implements HorarioDAO {

    private dbConnection dbConnection;

    public HorarioDAOImpl() {
        this.dbConnection = new dbConnection();
    }

    @Override
    public Horario Crear(Horario horario) {
        String query = "INSERT INTO horarios (HoraInicio) VALUES (?)";
        Connection connection = null;
        try {
            connection = dbConnection.conectar();
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, horario.getHoraInicio());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                horario.setIdHorario(generatedKeys.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dbConnection.desconectar();
        }
        return horario;
    }

    @Override
    public Horario Buscar(int id) {
        String query = "SELECT * FROM horarios WHERE IdHorario = ?";
        Horario horario = null;
        Connection connection = null;
        try {
            connection = dbConnection.conectar();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                horario = new Horario();
                horario.setIdHorario(resultSet.getInt("IdHorario"));
                horario.setHoraInicio(resultSet.getString("HoraInicio"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dbConnection.desconectar();
        }
        return horario;
    }

    @Override
    public void Actualizar(Horario horario) {
        // Verificar si el horario existe antes de intentar actualizarlo
        Horario horarioExistente = Buscar(horario.getIdHorario());
        if (horarioExistente == null) {
            System.out.println("El horario con ID " + horario.getIdHorario() + " no existe en la base de datos.");
            return; // Salir del método si el horario no existe
        }

        String query = "UPDATE horarios SET HoraInicio = ? WHERE IdHorario = ?";
        try (Connection connection = dbConnection.conectar(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, horario.getHoraInicio());
            statement.setInt(2, horario.getIdHorario());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dbConnection.desconectar();
        }
    }

    @Override
    public void Eliminar(int id) {
        String query = "DELETE FROM horarios WHERE IdHorario = ?";
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
    public List<Horario> Listar() {
        String query = "SELECT * FROM horarios";
        List<Horario> horarios = new ArrayList<>();
        Connection connection = null;
        try {
            connection = dbConnection.conectar();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Horario horario = new Horario();
                horario.setIdHorario(resultSet.getInt("IdHorario"));
                horario.setHoraInicio(resultSet.getString("HoraInicio"));
                horarios.add(horario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dbConnection.desconectar();
        }
        return horarios;
    }

    /*Codigo para probar las consultas 
    public static void main(String[] args) {
        HorarioDAOImpl horarioDAO = new HorarioDAOImpl();

        // Crear un nuevo horario
        Horario nuevoHorario = new Horario(0, "10:00:00");
        Horario horarioCreado = horarioDAO.Crear(nuevoHorario);
        System.out.println("Horario creado: ID = " + horarioCreado.getIdHorario() + ", HoraInicio = " + horarioCreado.getHoraInicio());

        // Buscar el horario creado
        Horario horarioObtenido = horarioDAO.Buscar(horarioCreado.getIdHorario());
        System.out.println("Horario obtenido: ID = " + horarioObtenido.getIdHorario() + ", HoraInicio = " + horarioObtenido.getHoraInicio());

        // Actualizar el horario
        if (horarioObtenido != null) {
            horarioObtenido.setHoraInicio("12:00:00");
            horarioDAO.Actualizar(horarioObtenido);
            Horario horarioActualizado = horarioDAO.Buscar(horarioObtenido.getIdHorario());
            System.out.println("Horario actualizado: ID = " + horarioActualizado.getIdHorario() + ", HoraInicio = " + horarioActualizado.getHoraInicio());
        }

        // Buscar todos los horarios
        List<Horario> todosHorarios = horarioDAO.Listar();
        System.out.println("Todos los horarios:");
        for (Horario horario : todosHorarios) {
            System.out.println("ID = " + horario.getIdHorario() + ", HoraInicio = " + horario.getHoraInicio());
        }

        // Eliminar el horario
        horarioDAO.Eliminar(horarioCreado.getIdHorario());
        System.out.println("Horario con ID " + horarioCreado.getIdHorario() + " eliminado.");

        // Verificar que el horario ha sido eliminado
        Horario horarioEliminado = horarioDAO.Buscar(horarioCreado.getIdHorario());
        if (horarioEliminado == null) {
            System.out.println("El horario con ID " + horarioCreado.getIdHorario() + " no existe en la base de datos.");
        }
    }
     */
}
