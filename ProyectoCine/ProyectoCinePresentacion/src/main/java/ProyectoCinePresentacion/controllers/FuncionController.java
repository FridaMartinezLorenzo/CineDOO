package ProyectoCinePresentacion.controllers;

import java.util.List;

import ProyectoCinePersistencia.dao.funcion.FuncionDAO;
import ProyectoCinePersistencia.dao.funcion.FuncionDAOImpl;
import ProyectoCinePersistencia.entities.Funcion;
import ProyectoCinePersistencia.utils.MyBatisUtil;

public class FuncionController {

    private FuncionDAO funcionDAO;

    public FuncionController() {
        this.funcionDAO = new FuncionDAOImpl(MyBatisUtil.getSqlSessionFactory());
    }

    public Funcion crearFuncion(Funcion funcion) {
        return funcionDAO.Crear(funcion);
    }

    public Funcion buscarFuncion(int id) {
        return funcionDAO.Buscar(id);
    }

    public void actualizarFuncion(Funcion funcion) {
        funcionDAO.Actualizar(funcion);
    }

    public void eliminarFuncion(int id) {
        funcionDAO.Eliminar(id);
    }

    public List<Funcion> listarFunciones() {
        return funcionDAO.Listar();
    }

    public List<Funcion> listarFuncionesPorPelicula(int idPelicula) {
        return funcionDAO.ListarPorPelicula(idPelicula);
    }

    public int boletosDisponibles(int idFuncion) {
        return funcionDAO.BoletosDisponibles(idFuncion);
    }
}
