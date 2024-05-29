import ProyectoCinePersistencia.dao.promocion.PromocionDAOImpl;
import ProyectoCinePersistencia.entities.Promocion;
import ProyectoCinePersistencia.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Crear instancia de SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
        
        // Crear instancia de PromocionDAO
        PromocionDAOImpl promocionDAO = new PromocionDAOImpl(sqlSessionFactory);
        
        // Crear una nueva promoción
        Promocion nuevaPromocion = new Promocion();
        nuevaPromocion.setFechaInicio("2024-06-01");
        nuevaPromocion.setFechaFin("2024-12-31");
        nuevaPromocion.setDescuento(20);
        promocionDAO.Crear(nuevaPromocion);
        System.out.println("Promoción creada con ID: " + nuevaPromocion.getIdPromocion());

        // Buscar una promoción por su ID
        Promocion promocionEncontrada = promocionDAO.Buscar(nuevaPromocion.getIdPromocion());
        if (promocionEncontrada != null) {
            System.out.println("Promoción encontrada: ID = " + promocionEncontrada.getIdPromocion() + 
                               ", FechaInicio = " + promocionEncontrada.getFechaInicio() + 
                               ", FechaFin = " + promocionEncontrada.getFechaFin() + 
                               ", Descuento = " + promocionEncontrada.getDescuento());
        } else {
            System.out.println("Promoción no encontrada.");
        
        }

        // Actualizar la promoción
        promocionEncontrada.setDescuento(25);
        promocionDAO.Actualizar(promocionEncontrada);
        System.out.println("Promoción actualizada: ID = " + promocionEncontrada.getIdPromocion() + 
                           ", Nuevo Descuento = " + promocionEncontrada.getDescuento());

        // Listar todas las promociones
        List<Promocion> listaPromociones = promocionDAO.Listar();
        System.out.println("Lista de Promociones:");
        for (Promocion promocion : listaPromociones) {
        
            System.out.println("ID: " + promocion.getIdPromocion() + 
                               ", FechaInicio: " + promocion.getFechaInicio() + 
                               ", FechaFin: " + promocion.getFechaFin() + 
                               ", Descuento: " + promocion.getDescuento());
        }

        // Eliminar una promoción
        
        promocionDAO.Eliminar(nuevaPromocion.getIdPromocion());
        System.out.println("Promoción eliminada con ID: " + nuevaPromocion.getIdPromocion());
    }
}
