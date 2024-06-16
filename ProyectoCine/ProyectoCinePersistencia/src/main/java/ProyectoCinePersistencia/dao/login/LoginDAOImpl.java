package ProyectoCinePersistencia.dao.login;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import ProyectoCinePersistencia.db.mappers.LoginMapper;
import ProyectoCinePersistencia.entities.Usuario;

public class LoginDAOImpl {

    private final SqlSessionFactory sqlSessionFactory;

    public LoginDAOImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public Usuario login(String email, String password) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            LoginMapper mapper = session.getMapper(LoginMapper.class);
            return mapper.login(email, password);
        }
    }
}
