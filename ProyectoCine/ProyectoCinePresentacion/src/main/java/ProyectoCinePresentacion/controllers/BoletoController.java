package ProyectoCinePresentacion.controllers;

import java.util.List;

import ProyectoCinePersistencia.dao.boleto.BoletoDAO;
import ProyectoCinePersistencia.dao.boleto.BoletoDAOImpl;
import ProyectoCinePersistencia.entities.Boleto;
import ProyectoCinePersistencia.utils.MyBatisUtil;

public class BoletoController {

    private BoletoDAO boletoDAO;

    public BoletoController() {
        this.boletoDAO = new BoletoDAOImpl(MyBatisUtil.getSqlSessionFactory());
    }

    public List<Boleto> listarBoletos() {
        return boletoDAO.Listar();
    }

    public Boleto obtenerBoletoPorId(int id) {
        return boletoDAO.Buscar(id);
    }

    public void agregarBoleto(Boleto boleto) {
        boletoDAO.Crear(boleto);
    }

    public void actualizarBoleto(Boleto boleto) {
        boletoDAO.Actualizar(boleto);
    }

    public void eliminarBoleto(int id) {
        boletoDAO.Eliminar(id);
    }

    public double calcularTotal(int IdTipoBoleto, int cantidadBoletos) {
        Boleto boleto = obtenerBoletoPorId(IdTipoBoleto);
        return boleto.getPrecio() * cantidadBoletos;
    }

}
