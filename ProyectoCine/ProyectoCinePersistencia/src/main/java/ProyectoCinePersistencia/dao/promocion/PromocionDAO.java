package ProyectoCinePersistencia.dao.promocion;

import java.util.List;

import ProyectoCinePersistencia.entities.Promocion;

public interface PromocionDAO {

    Promocion Crear(Promocion promocion);

    Promocion Buscar(int id);

    void Actualizar(Promocion promocion);

    void Eliminar(int id);

    List<Promocion> Listar();
}
