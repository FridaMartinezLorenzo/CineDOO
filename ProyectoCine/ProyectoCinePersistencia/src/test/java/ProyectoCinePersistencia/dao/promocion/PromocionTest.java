package ProyectoCinePersistencia.dao.promocion;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import ProyectoCinePersistencia.entities.Promocion;
import ProyectoCinePersistencia.utils.MyBatisUtil;

public class PromocionTest {

    private PromocionDAOImpl promocionDAO;
    private Promocion promocion;
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
        promocionDAO = new PromocionDAOImpl(sqlSessionFactory);
        promocion = new Promocion();
        promocion.setFechaInicio("2023-01-01");
        promocion.setFechaFin("2023-12-31");
        promocion.setDescuento(10);
    }

    @After
    public void tearDown() throws Exception {
        if (promocion != null && promocion.getIdPromocion() != 0) {
            promocionDAO.Eliminar(promocion.getIdPromocion());
        }
    }

    @Test
    public void testCrearPromocion() {
        Promocion promocionCreada = promocionDAO.Crear(promocion);
        assertNotNull("La promoción creada no debe ser nula", promocionCreada);
        assertTrue("El ID de la promoción creada debe ser mayor que 0", promocionCreada.getIdPromocion() > 0);

        System.out.println("Promoción Creada: " + promocionCreada);
    }

    @Test
    public void testBuscarPromocion() {
        Promocion promocionCreada = promocionDAO.Crear(promocion);
        Promocion promocionEncontrada = promocionDAO.Buscar(promocionCreada.getIdPromocion());
        assertNotNull("La promoción encontrada no debe ser nula", promocionEncontrada);
        assertEquals("El descuento de la promoción encontrada debe ser el mismo", promocion.getDescuento(), promocionEncontrada.getDescuento());

        System.out.println("Promoción Encontrada: " + promocionEncontrada);
    }

    @Test
    public void testActualizarPromocion() {
        Promocion promocionCreada = promocionDAO.Crear(promocion);
        promocionCreada.setDescuento(20);

        System.out.println("Actualizando Promoción: " + promocionCreada);
        promocionDAO.Actualizar(promocionCreada);

        Promocion promocionActualizada = promocionDAO.Buscar(promocionCreada.getIdPromocion());
        assertEquals("El descuento de la promoción actualizada debe ser '20'", 20, promocionActualizada.getDescuento());

        System.out.println("Promoción Actualizada: " + promocionActualizada);
    }

    @Test
    public void testEliminarPromocion() {
        Promocion promocionCreada = promocionDAO.Crear(promocion);
        System.out.println("Promoción a Eliminar: " + promocionCreada);

        promocionDAO.Eliminar(promocionCreada.getIdPromocion());
        Promocion promocionEliminada = promocionDAO.Buscar(promocionCreada.getIdPromocion());
        assertNull("La promoción eliminada debe ser nula", promocionEliminada);

        List<Promocion> listaPromociones = promocionDAO.Listar();
        System.out.println("Lista de Promociones después de Eliminar:");
        for (Promocion p : listaPromociones) {
            System.out.println(p);
        }
    }

    @Test
    public void testListarPromociones() {
        Promocion promocionCreada = promocionDAO.Crear(promocion);
        List<Promocion> listaPromociones = promocionDAO.Listar();
        assertTrue("La lista de promociones debe contener al menos una promoción", listaPromociones.size() > 0);

        System.out.println("Lista de Promociones:");
        for (Promocion p : listaPromociones) {
            System.out.println(p);
        }
    }
}
