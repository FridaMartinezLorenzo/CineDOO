
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;

import ProyectoCinePersistencia.dao.sala.SalaDAOImpl;
import ProyectoCinePersistencia.entities.Sala;
import ProyectoCinePersistencia.utils.MyBatisUtil;

public class Main {

    public static void main(String[] args) {
        // Crear instancia de SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();

        // Crear instancia de SalaDAOImpl
        SalaDAOImpl salaDAO = new SalaDAOImpl(sqlSessionFactory);

        // Crear una nueva sala
        Sala nuevaSala = new Sala();
        nuevaSala.setNumAsiento(150);

        // Insertar la nueva sala en la base de datos
        Sala salaCreada = salaDAO.Crear(nuevaSala);
        System.out.println("Sala creada con ID: " + salaCreada.getIdSala());

        // Buscar una sala por su ID
        Sala salaEncontrada = salaDAO.Buscar(salaCreada.getIdSala());
        if (salaEncontrada != null) {
            System.out.println("Sala encontrada: ID = " + salaEncontrada.getIdSala() + ", NumAsiento = " + salaEncontrada.getNumAsiento());
        } else {
            System.out.println("Sala no encontrada.");
        }

        // Actualizar la sala
        if (salaEncontrada != null) {
            salaEncontrada.setNumAsiento(200);
            salaDAO.Actualizar(salaEncontrada);
            System.out.println("Sala actualizada: ID = " + salaEncontrada.getIdSala() + ", Nuevo NumAsiento = " + salaEncontrada.getNumAsiento());
        }

        // Listar todas las salas
        List<Sala> listaSalas = salaDAO.Listar();
        System.out.println("Lista de Salas:");
        for (Sala sala : listaSalas) {
            System.out.println("ID: " + sala.getIdSala() + ", NumAsiento: " + sala.getNumAsiento());
        }

        // Eliminar una sala
        if (salaEncontrada != null) {
            salaDAO.Eliminar(salaEncontrada.getIdSala());
            System.out.println("Sala eliminada con ID: " + salaEncontrada.getIdSala());
        }
    }
}
