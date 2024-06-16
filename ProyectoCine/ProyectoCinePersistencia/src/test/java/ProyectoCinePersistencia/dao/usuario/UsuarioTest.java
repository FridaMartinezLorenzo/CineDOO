package ProyectoCinePersistencia.dao.usuario;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import ProyectoCinePersistencia.entities.Usuario;
import ProyectoCinePersistencia.utils.MyBatisUtil;

public class UsuarioTest {

    UsuarioDAOImpl usuarioDAO;
    Usuario usuario;
    SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
        usuarioDAO = new UsuarioDAOImpl(sqlSessionFactory);
        usuario = new Usuario();
        usuario.setNombre("Test Usuario");
        usuario.setIdRol(2);
        usuario.setCorreo("test");
        usuario.setContrasena("test");
    }

    @After
    public void tearDown() throws Exception {
        if (usuario != null && usuario.getIdUsuario() != 0) {
            usuarioDAO.Eliminar(usuario.getIdUsuario());
        }
    }

    @Test
    public void testCrearUsuario() {
        boolean exito = usuarioDAO.Crear(usuario);
        assertTrue("El usuario debe crearse exitosamente", exito);

        // Supongamos que el usuario tiene un nombre único y podemos recuperarlo por su nombre
        Usuario usuarioCreado = usuarioDAO.Buscar(usuario.getIdUsuario());
        assertNotNull("El usuario creado no debe ser nulo", usuarioCreado);
        assertTrue("El ID del usuario creado debe ser mayor que 0", usuarioCreado.getIdUsuario() > 0);

        System.out.println("Usuario Creado: " + usuarioCreado.getNombre());
    }

    @Test
    public void testActualizarUsuario() {
        boolean exito = usuarioDAO.Crear(usuario);
        assertTrue("El usuario debe crearse exitosamente", exito);

        usuario.setNombre("Test Usuario Actualizado");
        usuario.setCorreo("test2");
        usuario.setContrasena("test2");
        exito = usuarioDAO.Actualizar(usuario);
        assertTrue("El usuario debe actualizarse exitosamente", exito);

        Usuario usuarioActualizado = usuarioDAO.Buscar(usuario.getIdUsuario());
        assertNotNull("El usuario actualizado no debe ser nulo", usuarioActualizado);
        assertEquals("El nombre del usuario debe ser igual al nombre actualizado", usuario.getNombre(), usuarioActualizado.getNombre());
        assertEquals("El correo del usuario debe ser igual al correo actualizado", usuario.getCorreo(), usuarioActualizado.getCorreo());
        assertEquals("La contraseña del usuario debe ser igual a la contraseña actualizada", usuario.getContrasena(), usuarioActualizado.getContrasena());

        System.out.println("Usuario Actualizado: " + usuarioActualizado.getNombre());
    }

    @Test
    public void testEliminarUsuario() {
        boolean exito = usuarioDAO.Crear(usuario);
        assertTrue("El usuario debe crearse exitosamente", exito);

        exito = usuarioDAO.Eliminar(usuario.getIdUsuario());
        assertTrue("El usuario debe eliminarse exitosamente", exito);

        Usuario usuarioEliminado = usuarioDAO.Buscar(usuario.getIdUsuario());
        assertNull("El usuario eliminado debe ser nulo", usuarioEliminado);

        System.out.println("Usuario Eliminado: " + usuario.getNombre());
    }

    @Test
    public void testBuscarUsuario() {
        boolean exito = usuarioDAO.Crear(usuario);
        assertTrue("El usuario debe crearse exitosamente", exito);

        Usuario usuarioCreado = usuarioDAO.Buscar(usuario.getIdUsuario());
        assertNotNull("El usuario creado no debe ser nulo", usuarioCreado);
        assertEquals("El nombre del usuario debe ser igual al nombre creado", usuario.getNombre(), usuarioCreado.getNombre());
        assertEquals("El correo del usuario debe ser igual al correo creado", usuario.getCorreo(), usuarioCreado.getCorreo());
        assertEquals("La contraseña del usuario debe ser igual a la contraseña creada", usuario.getContrasena(), usuarioCreado.getContrasena());

        System.out.println("Usuario Encontrado: " + usuarioCreado.getNombre());
    }

    @Test
    public void testListarUsuarios() {
        List<Usuario> usuarios = usuarioDAO.Listar();
        assertNotNull("La lista de usuarios no debe ser nula", usuarios);
        assertTrue("La lista de usuarios debe tener al menos un usuario", usuarios.size() > 0);

        for (Usuario usuario : usuarios) {
            System.out.println("Usuario: " + usuario.getNombre());
        }
    }
}
