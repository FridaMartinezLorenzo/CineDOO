package ProyectoCinePersistencia.dao.boleto;

import java.util.List;

import ProyectoCinePersistencia.entities.Boleto;

public interface BoletoDAO {

    List<Boleto> Listar();

    Boleto Buscar(int id);

    void Crear(Boleto boleto);

    void Actualizar(Boleto boleto);

    void Eliminar(int id);

}
