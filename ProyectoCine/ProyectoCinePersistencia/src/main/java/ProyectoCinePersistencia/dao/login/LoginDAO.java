package ProyectoCinePersistencia.dao.login;

import ProyectoCinePersistencia.entities.Usuario;

public interface LoginDAO {

    Usuario Login(String nombre, String nontrasena);
}
