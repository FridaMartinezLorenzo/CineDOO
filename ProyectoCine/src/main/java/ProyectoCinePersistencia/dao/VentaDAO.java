package ProyectoCinePersistencia.dao;

import java.util.List;

import ProyectoCinePersistencia.entities.Venta;

public interface VentaDAO {
    public void Crear(Venta venta);
    public void Actualizar(Venta venta);
    public void Eliminar(Venta venta);
    public Venta Buscar(int IdVenta);
    public List<Venta> Listar();

}
