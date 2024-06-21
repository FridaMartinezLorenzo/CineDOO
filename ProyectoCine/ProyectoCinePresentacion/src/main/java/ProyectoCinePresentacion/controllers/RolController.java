package ProyectoCinePresentacion.controllers;

import java.util.List;

import ProyectoCinePersistencia.dao.rol.RolDAO;
import ProyectoCinePersistencia.dao.rol.RolDAOImpl;
import ProyectoCinePersistencia.entities.Rol;
import ProyectoCinePresentacion.utils.ViewUtil;

public class RolController {

    private RolDAO rolDAO = new RolDAOImpl(ViewUtil.getSqlSessionFactory());

    public Rol buscarRol(int id) {
        return rolDAO.BuscaRol(id);
    }

    public List<Rol> listarRoles() {
        return rolDAO.Listar();
    }
}
