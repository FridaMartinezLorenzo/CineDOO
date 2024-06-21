package ProyectoCinePresentacion.controllers;
import ProyectoCinePersistencia.dao.ganancia.GananciaDAO;
import ProyectoCinePersistencia.dao.ganancia.GananciaDAOImpl;
import java.util.List;
import ProyectoCinePresentacion.utils.ViewUtil;

public class GananciaController {
 
    private GananciaDAO gananciaDAO = new GananciaDAOImpl(ViewUtil.getSqlSessionFactory());

    public Double obtenerGanancias(int idPelicula) {
        return gananciaDAO.obtenerGanancias(idPelicula);
    }
}
