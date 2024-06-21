package ProyectoCinePersistencia.db.mappers;

import java.util.List;

import ProyectoCinePersistencia.entities.Promocion;

public interface PromocionMapper {

    void insertarPromocion(Promocion promocion);

    Promocion buscarPromocion(int id);

    void actualizarPromocion(Promocion promocion);

    void eliminarPromocion(int id);

    List<Promocion> listarPromociones();
}
