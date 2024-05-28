import ProyectoCinePersistencia.dao.venta.VentaDAOImpl;
import ProyectoCinePersistencia.entities.Venta;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Crear instancia de VentaDAO
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
}
