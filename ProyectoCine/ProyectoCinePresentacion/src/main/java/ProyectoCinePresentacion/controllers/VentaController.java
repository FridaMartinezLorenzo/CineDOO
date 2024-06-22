package ProyectoCinePresentacion.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSessionFactory;

import ProyectoCinePersistencia.dao.venta.VentaDAO;
import ProyectoCinePersistencia.dao.venta.VentaDAOImpl;
import ProyectoCinePersistencia.entities.Venta;
import ProyectoCinePresentacion.utils.ViewUtil;

public class VentaController {

    private VentaDAO ventaDAO;

    public VentaController() {
        SqlSessionFactory sqlSessionFactory = ViewUtil.getSqlSessionFactory();
        this.ventaDAO = new VentaDAOImpl(sqlSessionFactory);
    }

    public List<Object[]> listarVentas() {
        List<Venta> ventas = ventaDAO.Listar();
        return ventas.stream()
                .map(venta -> new Object[]{venta.getTituloPelicula(), venta.getTotal()})
                .collect(Collectors.toList());
    }

    public void crearVenta(Venta venta) {
        ventaDAO.Crear(venta);
    }
}
