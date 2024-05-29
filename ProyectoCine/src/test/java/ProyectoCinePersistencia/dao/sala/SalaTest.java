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
            sala.setNumAsiento(40);
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
            assertEquals("El numero de asientos de la sala debe ser el mismo", sala.getNumAsiento(), salaEncontrada.getNumAsiento());
    
            System.out.println("Sala Encontrada: " + salaEncontrada);
        }

        @Test
        public void testActualizarSala() {
            Sala salaCreada = salaDAO.Crear(sala);
            salaCreada.setNumAsiento(50);
            salaDAO.Actualizar(salaCreada);
            Sala salaActualizada = salaDAO.Buscar(salaCreada.getIdSala());
            assertEquals("El numero de asientos de la sala actualizada debe ser el mismo", salaCreada.getNumAsiento(), salaActualizada.getNumAsiento());
    
            System.out.println("Sala Actualizada: " + salaActualizada);
        }

        @Test
        public void testEliminarSala() {
            Sala salaCreada = salaDAO.Crear(sala);
            salaDAO.Eliminar(salaCreada.getIdSala());
            Sala salaEliminada = salaDAO.Buscar(salaCreada.getIdSala());
            assertNull("La sala eliminada debe ser nula", salaEliminada);
    
            System.out.println("Sala Eliminada: " + salaEliminada);
        }

        @Test
        public void testListarSalas() {
            List<Sala> salas = salaDAO.Listar();
            assertNotNull("La lista de salas no debe ser nula", salas);
            assertTrue("La lista de salas debe tener al menos 1 sala", salas.size() > 0);
    
            System.out.println("Lista de Salas: " + salas);
        }
}
