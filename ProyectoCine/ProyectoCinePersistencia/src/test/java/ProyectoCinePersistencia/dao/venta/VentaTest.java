package ProyectoCinePersistencia.dao.venta;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import ProyectoCinePersistencia.entities.Venta;
import ProyectoCinePersistencia.utils.MyBatisUtil;

public class VentaTest {

    private VentaDAOImpl ventaDAO;
    private Venta venta;
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() {
        sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
        ventaDAO = new VentaDAOImpl(sqlSessionFactory);
        venta = new Venta();
        venta.setIdUsuario(1);
        venta.setIdFuncion(1);
        venta.setTotal(150.00f);
        venta.setCantBoletos(2);
    }

    @After
    public void tearDown() {
        ventaDAO = null;
        venta = null;
        sqlSessionFactory = null;
    }

    @Test
    public void testCrearVenta() {
        ventaDAO.Crear(venta);
        assertTrue("La venta creada debe tener un ID positivo", venta.getIdVenta() > 0);
    }

    @Test
    public void testBuscarVenta() {
        ventaDAO.Crear(venta);
        Venta ventaEncontrada = ventaDAO.Buscar(venta.getIdVenta());
        assertNotNull("Se espera encontrar la venta", ventaEncontrada);
        assertEquals("El ID de la venta debe coincidir", venta.getIdVenta(), ventaEncontrada.getIdVenta());
    }

    @Test
    public void testActualizarVenta() {
        ventaDAO.Crear(venta);
        float nuevoTotal = 200.00f;
        venta.setTotal(nuevoTotal);
        ventaDAO.Actualizar(venta);
        Venta ventaActualizada = ventaDAO.Buscar(venta.getIdVenta());
        assertEquals("El total de la venta debe ser actualizado", nuevoTotal, ventaActualizada.getTotal(), 0.01);
    }

    @Test
    public void testListarVentas() {
        List<Venta> listaVentasInicial = ventaDAO.Listar();
        int cantidadInicial = listaVentasInicial.size();
        ventaDAO.Crear(venta);
        List<Venta> listaVentas = ventaDAO.Listar();
        assertNotNull("La lista de ventas no debe ser nula", listaVentas);
        assertEquals("Se espera que la lista de ventas contenga una venta adicional", cantidadInicial + 1, listaVentas.size());
    }

    @Test
    public void testEliminarVenta() {
        ventaDAO.Crear(venta);
        ventaDAO.Eliminar(venta);
        Venta ventaEliminada = ventaDAO.Buscar(venta.getIdVenta());
        assertNull("La venta debe ser eliminada", ventaEliminada);
    }
}
