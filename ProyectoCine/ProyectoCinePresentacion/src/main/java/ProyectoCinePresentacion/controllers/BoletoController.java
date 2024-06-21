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
        return boletoDAO.listarTodos();
    }

    public Boleto obtenerBoletoPorId(int id) {
        return boletoDAO.obtenerPorId(id);
    }

    public void agregarBoleto(Boleto boleto) {
        boletoDAO.insertar(boleto);
    }

    public void actualizarBoleto(Boleto boleto) {
        boletoDAO.actualizar(boleto);
    }

    public void eliminarBoleto(int id) {
        boletoDAO.eliminar(id);
    }
}
