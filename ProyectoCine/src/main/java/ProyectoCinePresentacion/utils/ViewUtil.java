package ProyectoCinePresentacion.utils;

import org.apache.ibatis.session.SqlSessionFactory;
import ProyectoCinePersistencia.utils.MyBatisUtil;

public class ViewUtil {

    public static SqlSessionFactory getSqlSessionFactory() {
        return MyBatisUtil.getSqlSessionFactory();
    }

    // Métodos adicionales de utilidad para la interfaz de usuario
}
