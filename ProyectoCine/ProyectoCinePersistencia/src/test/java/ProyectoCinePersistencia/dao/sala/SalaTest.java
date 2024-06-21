package ProyectoCinePersistencia.dao.sala;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import ProyectoCinePersistencia.entities.Sala;
import ProyectoCinePersistencia.utils.MyBatisUtil;

public class SalaTest {

    private SalaDAOImpl salaDAO;
    private Sala sala;
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
        salaDAO = new SalaDAOImpl(sqlSessionFactory);
        sala = new Sala();
        sala.setNumAsiento(100);
    }

    @After
    public void tearDown() throws Exception {
        if (sala != null && sala.getIdSala() != 0) {
            salaDAO.Eliminar(sala.getIdSala());
        }
    }

    @Test
    public void testCrearSala() {
        Sala salaCreada = salaDAO.Crear(sala);
        assertNotNull("La sala creada no debe ser nula", salaCreada);
        assertTrue("El ID de la sala creada debe ser mayor que 0", salaCreada.getIdSala() > 0);

        System.out.println("Sala Creada: " + salaCreada);
    }

    @Test
    public void testBuscarSala() {
        Sala salaCreada = salaDAO.Crear(sala);
        Sala salaEncontrada = salaDAO.Buscar(salaCreada.getIdSala());
        assertNotNull("La sala encontrada no debe ser nula", salaEncontrada);
        assertEquals("El número de asientos de la sala encontrada debe ser el mismo", sala.getNumAsiento(), salaEncontrada.getNumAsiento());

        System.out.println("Sala Encontrada: " + salaEncontrada);
    }

    @Test
    public void testActualizarSala() {
        Sala salaCreada = salaDAO.Crear(sala);
        salaCreada.setNumAsiento(150);

        System.out.println("Actualizando Sala: " + salaCreada);
        salaDAO.Actualizar(salaCreada);

        Sala salaActualizada = salaDAO.Buscar(salaCreada.getIdSala());
        assertEquals("El número de asientos de la sala actualizada debe ser '150'", 150, salaActualizada.getNumAsiento());

        System.out.println("Sala Actualizada: " + salaActualizada);
    }

    @Test
    public void testEliminarSala() {
        Sala salaCreada = salaDAO.Crear(sala);
        System.out.println("Sala a Eliminar: " + salaCreada);

        salaDAO.Eliminar(salaCreada.getIdSala());
        Sala salaEliminada = salaDAO.Buscar(salaCreada.getIdSala());
        assertNull("La sala eliminada debe ser nula", salaEliminada);

        List<Sala> listaSalas = salaDAO.Listar();
        System.out.println("Lista de Salas después de Eliminar:");
        for (Sala s : listaSalas) {
            System.out.println(s);
        }
    }

    @Test
    public void testListarSalas() {
        Sala salaCreada = salaDAO.Crear(sala);
        List<Sala> listaSalas = salaDAO.Listar();
        assertTrue("La lista de salas debe contener al menos una sala", listaSalas.size() > 0);

        System.out.println("Lista de Salas:");
        for (Sala s : listaSalas) {
            System.out.println(s);
        }
    }
}
