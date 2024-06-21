package ProyectoCinePersistencia.db.mappers;

import ProyectoCinePersistencia.entities.Venta;

import java.util.List;

public interface VentaMapper {
    void CrearVenta(Venta venta);
    void ActualizarVenta(Venta venta);
    void EliminarVenta(int idVenta);
    Venta BuscarVenta(int idVenta);
    List<Venta> ListarVentas();
}
