/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoCinePersistencia.entities;


public class Horario {
    //Atributos
    private int IdHorario;
    private String HoraInicio;

    //Constructor con parametros
    public Horario(int IdHorario, String HoraInicio) {
        this.IdHorario = IdHorario;
        this.HoraInicio = HoraInicio;
    }

    //Constructor vacio
    public Horario() {
        this.IdHorario = 0;
        this.HoraInicio = "00:00:00";
    }

    //Getters y Setters

    public int getIdHorario() {
        return IdHorario;
    }

    public void setIdHorario(int IdHorario) {
        this.IdHorario = IdHorario;
    }

    public String getHoraInicio() {
        return HoraInicio;
    }

    public void setHoraInicio(String HoraInicio) {
        this.HoraInicio = HoraInicio;
    }

}
