package ProyectoCinePersistencia.dao.rol;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import ProyectoCinePersistencia.entities.Rol;
import ProyectoCinePersistencia.utils.MyBatisUtil;

public class RolTest {

    private RolDAOImpl rolDAO;
    private Rol rol;
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
        rolDAO = new RolDAOImpl(sqlSessionFactory);
        rol = new Rol();
        rol.setNombre("Test Rol");
    }

    @After
    public void tearDown() throws Exception {
        if (rol != null && rol.getIdRol() != 0) {
            rolDAO.Eliminar(rol.getIdRol());
        }
    }

    @Test
    public void testCrearRol() {
        boolean exito = rolDAO.Crear(rol);
        assertTrue("El rol debe crearse exitosamente", exito);

        // Supongamos que el rol tiene un nombre único y podemos recuperarlo por su nombre
        Rol rolCreado = rolDAO.BuscaRol(rol.getIdRol());
        assertNotNull("El rol creado no debe ser nulo", rolCreado);
        assertTrue("El ID del rol creado debe ser mayor que 0", rolCreado.getIdRol() > 0);

        System.out.println("Rol Creado: " + rolCreado.getNombre());
    }

    @Test
    public void testBuscarRol() {
        boolean exito = rolDAO.Crear(rol);
        assertTrue("El rol debe crearse exitosamente", exito);

        Rol rolCreado = rolDAO.BuscaRol(rol.getIdRol());
        assertNotNull("El rol creado no debe ser nulo", rolCreado);
        assertEquals("El nombre del rol creado debe ser el mismo", rol.getNombre(), rolCreado.getNombre());

        System.out.println("Rol Encontrado: " + rolCreado);
    }

    @Test
    public void testActualizarRol() {
        boolean exito = rolDAO.Crear(rol);
        assertTrue("El rol debe crearse exitosamente", exito);

        rol.setNombre("Test Rol Actualizado");
        boolean exitoActualizacion = rolDAO.Actualizar(rol);
        assertTrue("El rol debe actualizarse exitosamente", exitoActualizacion);

        Rol rolActualizado = rolDAO.BuscaRol(rol.getIdRol());
        assertNotNull("El rol actualizado no debe ser nulo", rolActualizado);
        assertEquals("El nombre del rol actualizado debe ser el mismo", rol.getNombre(), rolActualizado.getNombre());

        System.out.println("Rol Actualizado: " + rolActualizado);
    }

    @Test
    public void testEliminarRol() {
        boolean exito = rolDAO.Crear(rol);
        assertTrue("El rol debe crearse exitosamente", exito);

        boolean exitoEliminacion = rolDAO.Eliminar(rol.getIdRol());
        assertTrue("El rol debe eliminarse exitosamente", exitoEliminacion);

        Rol rolEliminado = rolDAO.BuscaRol(rol.getIdRol());
        assertNull("El rol eliminado debe ser nulo", rolEliminado);

        System.out.println("Rol Eliminado: " + rolEliminado);
    }

    @Test
    public void testListarRoles() {
        List<Rol> listaRoles = rolDAO.Listar();
        System.out.println("Lista de Roles:");
        for (Rol r : listaRoles) {
            System.out.println(r);
        }
    }

}
