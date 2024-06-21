package ProyectoCinePresentacion.controllers;

import ProyectoCinePersistencia.dao.login.LoginDAOImpl;
import ProyectoCinePersistencia.entities.Usuario;
import ProyectoCinePresentacion.presentacion.VentanaLogin;
import ProyectoCinePresentacion.utils.ViewUtil;

public class MainController {

    private LoginDAOImpl loginDAO = new LoginDAOImpl(ViewUtil.getSqlSessionFactory());

    public MainController() {
        this.loginDAO = new LoginDAOImpl(ViewUtil.getSqlSessionFactory());
    }

    public void iniciar() {
        VentanaLogin loginView = new VentanaLogin(this);
        loginView.mostrar();
    }

    public Usuario autenticarUsuario(String email, String password) {
        Usuario usuario = loginDAO.login(email, password);
        return usuario;
    }

    // Métodos adicionales para manejar la navegación y la lógica de la aplicación
}
