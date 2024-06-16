package ProyectoCinePresentacion.controllers;

import ProyectoCinePersistencia.entities.Usuario;
import ProyectoCinePersistencia.dao.login.LoginDAOImpl;
import ProyectoCinePresentacion.views.LoginView;
import ProyectoCinePresentacion.utils.ViewUtil;

public class MainController {
    
      private LoginDAOImpl loginDAO = new LoginDAOImpl(ViewUtil.getSqlSessionFactory()); 

    public MainController() {
        this.loginDAO  = new LoginDAOImpl(ViewUtil.getSqlSessionFactory()); 
    }

    public void iniciar() {
        LoginView loginView = new LoginView(this);
        loginView.mostrar();
    }

    public boolean autenticarUsuario(String email, String password) {
        Usuario usuario = loginDAO.login(email, password);
        return usuario != null;
    }

    // Métodos adicionales para manejar la navegación y la lógica de la aplicación
}
