package ProyectoCinePersistencia.dao.login;

import ProyectoCinePersistencia.entities.Usuario;

public interface LoginDAO {
    public Usuario Login(String usuario, String contrasena);
}
