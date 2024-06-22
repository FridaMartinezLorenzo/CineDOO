package ProyectoCinePresentacion.controllers;

import java.util.List;

import ProyectoCinePersistencia.dao.sala.SalaDAO;
import ProyectoCinePersistencia.dao.sala.SalaDAOImpl;
import ProyectoCinePersistencia.entities.Sala;
import ProyectoCinePresentacion.utils.ViewUtil;

public class SalaController {

    private SalaDAO salaDAO = new SalaDAOImpl(ViewUtil.getSqlSessionFactory());

    public Sala crearSala(Sala sala) {
        if (sala.getNumAsiento() == 0) {
            sala.setIdSala(-1);
            return sala;
        } else {
            return salaDAO.Crear(sala);
        }
    }

    public Sala buscarSala(int id) {
        return salaDAO.Buscar(id);
    }

    public boolean actualizarSala(Sala sala) {
        if (sala.getNumAsiento() == 0) {
            sala.setIdSala(-1);
            return false;
        } else {
            salaDAO.Actualizar(sala);
            return true;
        }
    }

    public boolean eliminarSala(int id) {
        salaDAO.Eliminar(id);
        return salaDAO.Buscar(id) == null;
    }

    public List<Sala> listarSalas() {
        return salaDAO.Listar();
    }

    // Otros métodos que necesites para manejar la lógica de la sala
}
