package ProyectoCinePersistencia.dao.venta;

import ProyectoCinePersistencia.db.mappers.VentaMapper;
import ProyectoCinePersistencia.entities.Venta;
import ProyectoCinePersistencia.utils.MyBatisUtil;

import java.util.List;

public class VentaDAOImpl implements VentaDAO {

    private final VentaMapper ventaMapper;

    public VentaDAOImpl() {
        this.ventaMapper = MyBatisUtil.getSqlSessionFactory().openSession(true).getMapper(VentaMapper.class);
    }

    @Override
    public void Crear(Venta venta) {
        ventaMapper.CrearVenta(venta);
    }

    @Override
    public void Actualizar(Venta venta) {
        ventaMapper.ActualizarVenta(venta);
    }

    @Override
    public void Eliminar(Venta venta) {
        ventaMapper.EliminarVenta(venta.getIdVenta());
    }

    @Override
    public Venta Buscar(int IdVenta) {
        return ventaMapper.BuscarVenta(IdVenta);
    }

    @Override
    public List<Venta> Listar() {
        return ventaMapper.ListarVentas();
    }

    /*
    public static void main(String[] args) {
        VentaDAOImpl ventaDAO = new VentaDAOImpl();
        
        // Crear una nueva venta
        Venta nuevaVenta = new Venta();
        nuevaVenta.setIdUsuario(3); // IdUsuario de "Maria Garcia"
        nuevaVenta.setIdFuncion(1); // IdFuncion para la función 1
        nuevaVenta.setTotal(150.00f);
        nuevaVenta.setCantBoletos(2);
        ventaDAO.Crear(nuevaVenta);
        System.out.println("Venta creada con ID: " + nuevaVenta.getIdVenta());

        // Buscar una venta por su ID
        Venta ventaEncontrada = ventaDAO.Buscar(nuevaVenta.getIdVenta());
        if (ventaEncontrada != null) {
            System.out.println("Venta encontrada: " + ventaEncontrada.getIdVenta() + ", Usuario: " + ventaEncontrada.getNombreUsuario());
        } else {
            System.out.println("Venta no encontrada.");
        }
        
        // Actualizar la venta
        ventaEncontrada.setTotal(200.00f);
        ventaDAO.Actualizar(ventaEncontrada);
        System.out.println("Venta actualizada: " + ventaEncontrada.getIdVenta() + ", Nuevo Total: " + ventaEncontrada.getTotal());
        
        // Listar todas las ventas
        List<Venta> listaVentas = ventaDAO.Listar();
        System.out.println("Lista de Ventas:");
        for (Venta venta : listaVentas) {
            System.out.println("ID: " + venta.getIdVenta() + ", Usuario: " + venta.getNombreUsuario() + ", Pelicula: " + venta.getTituloPelicula() + ", Hora: " + venta.getHoraInicio() + ", Total: " + venta.getTotal() + ", Boletos: " + venta.getCantBoletos());
        }
        
        // Eliminar una venta
        ventaDAO.Eliminar(nuevaVenta);
        System.out.println("Venta eliminada con ID: " + nuevaVenta.getIdVenta());
    }
     */
}
