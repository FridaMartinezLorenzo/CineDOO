package ProyectoCinePersistencia.dao.boleto;

import java.util.List;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import ProyectoCinePersistencia.entities.Boleto;
import ProyectoCinePersistencia.utils.MyBatisUtil;

public class BoletoTest {

    private BoletoDAOImpl boletoDAO;
    private Boleto boleto;

    @Before
    public void setUp() {
        boletoDAO = new BoletoDAOImpl(MyBatisUtil.getSqlSessionFactory());
        boleto = new Boleto();
        // Configura los atributos del boleto según sea necesario para las pruebas
        boleto.setNombre("Test Boleto");
        boleto.setPrecio(100.0);
    }

    @After
    public void tearDown() {
        boletoDAO = null;
        boleto = null;
    }

    @Test
    public void testCrearBoleto() {
        boletoDAO.Crear(boleto);
        assertNotNull("El boleto creado no debe ser nulo", boleto.getIdTipoBoleto());
    }

    @Test
    public void testBuscarBoleto() {
        boletoDAO.Crear(boleto);
        Boleto boletoEncontrado = boletoDAO.Buscar(boleto.getIdTipoBoleto());
        assertNotNull("Se espera encontrar el boleto", boletoEncontrado);
        assertEquals("El nombre del boleto encontrado debe ser el mismo", boleto.getNombre(), boletoEncontrado.getNombre());
        assertEquals("El precio del boleto encontrado debe ser el mismo", boleto.getPrecio(), boletoEncontrado.getPrecio(), 0.001); // Ajusta la precisión del valor double si es necesario
    }

    @Test
    public void testActualizarBoleto() {
        boletoDAO.Crear(boleto);
        double nuevoPrecio = 120.0;
        boleto.setPrecio(nuevoPrecio);
        boletoDAO.Actualizar(boleto);
        Boleto boletoActualizado = boletoDAO.Buscar(boleto.getIdTipoBoleto());
        assertEquals("El precio del boleto actualizado debe ser el nuevo precio", nuevoPrecio, boletoActualizado.getPrecio(), 0.001); // Ajusta la precisión del valor double si es necesario
    }

    @Test
    public void testListarBoletos() {
        List<Boleto> listaBoletosInicial = boletoDAO.Listar();
        int cantidadInicial = listaBoletosInicial.size();
        boletoDAO.Crear(boleto);
        List<Boleto> listaBoletos = boletoDAO.Listar();
        assertNotNull("La lista de boletos no debe ser nula", listaBoletos);
        assertTrue("Se espera que la lista de boletos contenga un boleto adicional", cantidadInicial + 1 == listaBoletos.size());
    }

    @Test
    public void testEliminarBoleto() {
        boletoDAO.Crear(boleto);
        boletoDAO.Eliminar(boleto.getIdTipoBoleto());
        Boleto boletoEliminado = boletoDAO.Buscar(boleto.getIdTipoBoleto());
        assertNull("El boleto debe ser eliminado", boletoEliminado);
    }
}
