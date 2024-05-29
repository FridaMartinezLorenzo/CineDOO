package ProyectoCinePersistencia.db.mappers;

import org.apache.ibatis.annotations.Param;

public interface GananciaMapper {

    Double sacarGanancia(@Param("idPelicula") int idPelicula);
}
