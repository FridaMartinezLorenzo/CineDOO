package ProyectoCinePersistencia.dao.login;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import ProyectoCinePersistencia.dao.usuario.UsuarioDAOImpl;
import ProyectoCinePersistencia.entities.Usuario;
import ProyectoCinePersistencia.utils.MyBatisUtil;

public class LoginTest {

    LoginDAOImpl loginDAO;
    UsuarioDAOImpl usuarioDAO;
    Usuario usuario;
    SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() {
        sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
        loginDAO = new LoginDAOImpl(sqlSessionFactory);
        usuarioDAO = new UsuarioDAOImpl(sqlSessionFactory); // Inicializar usuarioDAO
        usuario = new Usuario();
        usuario.setNombre("Usuario");
        usuario.setContrasena("1234");
        usuario.setCorreo("test");
        usuario.setIdRol(2);
    }

    @After
    public void tearDown() throws Exception {
        if (usuario != null && usuario.getIdUsuario() != 0) {
            usuarioDAO.Eliminar(usuario.getIdUsuario());
        }
    }

    @Test
    public void testLogin() {
        boolean exito = usuarioDAO.Crear(usuario);
        assertTrue("El usuario debe crearse exitosamente", exito);
        Usuario usuarioLogueado = loginDAO.login(usuario.getNombre(), usuario.getContrasena());
        assertNotNull("El usuario debe loguearse exitosamente", usuarioLogueado);
        assertEquals("El usuario logueado debe ser el mismo que el creado", usuario.getNombre(), usuarioLogueado.getNombre());
    }
}
