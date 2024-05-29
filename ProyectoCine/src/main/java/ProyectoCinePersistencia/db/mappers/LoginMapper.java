package ProyectoCinePersistencia.db.mappers;

import org.apache.ibatis.annotations.Param;

import ProyectoCinePersistencia.entities.Usuario;

public interface LoginMapper {

    Usuario login(@Param("email") String email, @Param("password") String password);
}
