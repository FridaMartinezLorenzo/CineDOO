package ProyectoCinePersistencia.dao.venta;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;

import ProyectoCinePersistencia.db.mappers.VentaMapper;
import ProyectoCinePersistencia.entities.Venta;

public class VentaDAOImpl implements VentaDAO {

    private final VentaMapper ventaMapper;

    public VentaDAOImpl(SqlSessionFactory sqlSessionFactory) {
        this.ventaMapper = sqlSessionFactory.openSession(true).getMapper(VentaMapper.class);
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
}
