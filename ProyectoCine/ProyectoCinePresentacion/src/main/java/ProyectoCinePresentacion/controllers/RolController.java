package ProyectoCinePresentacion.controllers;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import ProyectoCinePersistencia.dao.rol.RolDAO;
import ProyectoCinePersistencia.dao.rol.RolDAOImpl;
import ProyectoCinePersistencia.entities.Rol;

public class RolController {

    private static RolDAO rolDAO;

    static {
        try {
            String resource = "mybatis-config.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            rolDAO = new RolDAOImpl(sqlSessionFactory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Rol buscarRol(int idRol) {
        return rolDAO.BuscaRol(idRol);
    }

    public List<Rol> listarRoles() {
        return rolDAO.Listar();
    }
}
