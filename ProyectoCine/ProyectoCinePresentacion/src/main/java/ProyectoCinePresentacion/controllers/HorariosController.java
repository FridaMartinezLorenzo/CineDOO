package ProyectoCinePresentacion.controllers;

import java.util.List;

import ProyectoCinePersistencia.dao.horario.HorarioDAO;
import ProyectoCinePersistencia.dao.horario.HorarioDAOImpl;
import ProyectoCinePersistencia.entities.Horario;
import ProyectoCinePresentacion.utils.ViewUtil;

public class HorariosController {
    
    private HorarioDAO horarioDAO = new HorarioDAOImpl(ViewUtil.getSqlSessionFactory());
    
    public Horario CrearHorario(Horario horario){
        if(horario.getHoraInicio().isEmpty()){
            horario.setIdHorario(-1);
            return horario;
        }else{
            return horarioDAO.Crear(horario);
        }
    }

    public Horario BuscarHorario(int id){
        return horarioDAO.Buscar(id);
    }

    public boolean ActualizarHorario(Horario horario){
        if(horario.getHoraInicio().isEmpty()){
            horario.setIdHorario(-1);
        }else{
            boolean estado =horarioDAO.Actualizar(horario);
            if(estado){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public boolean EliminarHorario(int id){
        boolean exito = horarioDAO.Eliminar(id);
        if (exito) {
            return true;
        }else{
            return false;
        }
    }

    public List<Horario> ListarHorarios(){
        return horarioDAO.Listar();
    }
}
