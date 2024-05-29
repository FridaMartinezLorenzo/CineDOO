package ProyectoCinePersistencia.dao.horario;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import ProyectoCinePersistencia.db.mappers.HorarioMapper;
import ProyectoCinePersistencia.entities.Horario;
import ProyectoCinePersistencia.utils.MyBatisUtil;

public class HorarioDAOImpl implements HorarioDAO {

    private SqlSessionFactory sqlSessionFactory;

    public HorarioDAOImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public Horario Crear(Horario horario) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            HorarioMapper mapper = session.getMapper(HorarioMapper.class);
            mapper.insertarHorario(horario);
            session.commit();
            return horario;
        }
    }

    @Override
    public Horario Buscar(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            HorarioMapper mapper = session.getMapper(HorarioMapper.class);
            return mapper.buscarHorario(id);
        }
    }

    @Override
    public void Actualizar(Horario horario) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            HorarioMapper mapper = session.getMapper(HorarioMapper.class);
            mapper.actualizarHorario(horario);
            session.commit();
        }
    }

    @Override
    public void Eliminar(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            HorarioMapper mapper = session.getMapper(HorarioMapper.class);
            mapper.eliminarHorario(id);
            session.commit();
        }
    }

    @Override
    public List<Horario> Listar() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            HorarioMapper mapper = session.getMapper(HorarioMapper.class);
            return mapper.listarHorarios();
        }
    }

    /* Código para probar las consultas */
    public static void main(String[] args) {
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
        HorarioDAOImpl horarioDAO = new HorarioDAOImpl(sqlSessionFactory);

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
}
