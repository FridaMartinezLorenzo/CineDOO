package ProyectoCinePersistencia.dao.boleto;

import java.util.List;

import ProyectoCinePersistencia.entities.Boleto;

public interface BoletoDAO {

    List<Boleto> listarTodos();

    Boleto obtenerPorId(int id);

    void insertar(Boleto boleto);

    void actualizar(Boleto boleto);

    void eliminar(int id);
}
