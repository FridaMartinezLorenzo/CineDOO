package ProyectoCinePersistencia.dao.categoria;

import java.util.List;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import ProyectoCinePersistencia.entities.Categoria;
import ProyectoCinePersistencia.utils.MyBatisUtil;

public class TestCategoria {

    private CategoriaDAOImpl categoriaDAO;
    private Categoria categoria;

    @Before
    public void setUp() {
        categoriaDAO = new CategoriaDAOImpl(MyBatisUtil.getSqlSessionFactory());
        categoria = new Categoria();
        categoria.setNombre("Test Categoria");
    }

    @After
    public void tearDown() {
        categoriaDAO = null;
        categoria = null;
    }

    @Test
    public void testCrearCategoria() {
        categoriaDAO.Crear(categoria);
        assertNotNull("La categoría creada no debe ser nula", categoria.getIdCategoria());
    }

    @Test
    public void testBuscarCategoria() {
        categoriaDAO.Crear(categoria);
        Categoria categoriaEncontrada = categoriaDAO.Buscar(categoria.getIdCategoria());
        assertNotNull("Se espera encontrar la categoría", categoriaEncontrada);
        assertEquals("El nombre de la categoría encontrada debe ser el mismo", categoria.getNombre(), categoriaEncontrada.getNombre());
    }

    @Test
    public void testActualizarCategoria() {
        categoriaDAO.Crear(categoria);
        String nuevoNombre = "Nueva Categoria";
        categoria.setNombre(nuevoNombre);
        categoriaDAO.Actualizar(categoria);
        Categoria categoriaActualizada = categoriaDAO.Buscar(categoria.getIdCategoria());
        assertEquals("El nombre de la categoría actualizada debe ser el nuevo nombre", nuevoNombre, categoriaActualizada.getNombre());
    }

    @Test
    public void testListarCategorias() {
        List<Categoria> listaCategoriasInicial = categoriaDAO.Listar();
        int cantidadInicial = listaCategoriasInicial.size();
        categoriaDAO.Crear(categoria);
        List<Categoria> listaCategorias = categoriaDAO.Listar();
        assertNotNull("La lista de categorías no debe ser nula", listaCategorias);
        assertTrue("Se espera que la lista de categorías contenga una categoría adicional", cantidadInicial + 1 == listaCategorias.size());
    }

    @Test
    public void testEliminarCategoria() {
        categoriaDAO.Crear(categoria);
        categoriaDAO.Eliminar(categoria.getIdCategoria());
        Categoria categoriaEliminada = categoriaDAO.Buscar(categoria.getIdCategoria());
        assertNull("La categoría debe ser eliminada", categoriaEliminada);
    }
}
