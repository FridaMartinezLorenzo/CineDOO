package ProyectoCinePersistencia.dao.horario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ProyectoCinePersistencia.dao.horario.HorarioDAOImpl;
import ProyectoCinePersistencia.entities.Horario;
import ProyectoCinePersistencia.utils.MyBatisUtil;

public class TestHorario {

    private HorarioDAOImpl horarioDAO;
    private Horario horario;

    @Before
    public void setUp() {
        horarioDAO = new HorarioDAOImpl(MyBatisUtil.getSqlSessionFactory());
        horario = new Horario();
        horario.setHoraInicio("10:00:00");
    }

    @After
    public void tearDown() {
        horarioDAO = null;
        horario = null;
    }

    @Test
    public void testCrearHorario() {
        horarioDAO.Crear(horario);
        assertNotNull("El horario creado no debe ser nulo", horario.getIdHorario());
    }

    @Test
    public void testBuscarHorario() {
        horarioDAO.Crear(horario);
        Horario horarioEncontrado = horarioDAO.Buscar(horario.getIdHorario());
        assertNotNull("Se espera encontrar el horario", horarioEncontrado);
        assertEquals("La hora de inicio del horario encontrado debe ser la misma", horario.getHoraInicio(), horarioEncontrado.getHoraInicio());
    }

    @Test
    public void testActualizarHorario() {
        horarioDAO.Crear(horario);
        String nuevaHora = "12:00:00";
        horario.setHoraInicio(nuevaHora);
        horarioDAO.Actualizar(horario);
        Horario horarioActualizado = horarioDAO.Buscar(horario.getIdHorario());
        assertEquals("La hora de inicio del horario actualizado debe ser la nueva hora", nuevaHora, horarioActualizado.getHoraInicio());
    }

    @Test
    public void testListarHorarios() {
        List<Horario> listaHorariosInicial = horarioDAO.Listar();
        int cantidadInicial = listaHorariosInicial.size();
        horarioDAO.Crear(horario);
        List<Horario> listaHorarios = horarioDAO.Listar();
        assertNotNull("La lista de horarios no debe ser nula", listaHorarios);
        assertTrue("Se espera que la lista de horarios contenga un horario adicional", cantidadInicial + 1 == listaHorarios.size());
    }

    @Test
    public void testEliminarHorario() {
        horarioDAO.Crear(horario);
        horarioDAO.Eliminar(horario.getIdHorario());
        Horario horarioEliminado = horarioDAO.Buscar(horario.getIdHorario());
        assertNull("El horario debe ser eliminado", horarioEliminado);
    }
}
