package ProyectoCinePersistencia.db.mappers;

import java.util.List;

import ProyectoCinePersistencia.entities.Boleto;

public interface BoletoMapper {

    List<Boleto> listarTodos();

    Boleto obtenerPorId(int id);

    void insertar(Boleto boleto);

    void actualizar(Boleto boleto);

    void eliminar(int id);
}
