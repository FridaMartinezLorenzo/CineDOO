/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoCinePersistencia.entities;

/**
 *
 * @author frida
 */
public class Venta {

    private int IdVenta;
    private int IdUsuario;
    private String NombreUsuario;
    private int IdFuncion;
    private String TituloPelicula;
    private String HoraInicio;
    private float Total;
    private int CantBoletos;

    //Constructor con parametros
    public Venta(int IdVenta, int IdUsuario, String NombreUsuario, int IdFuncion, String TituloPelicula, String HoraInicio, float Total, int cantBoletos) {
        this.IdVenta = IdVenta;
        this.IdUsuario = IdUsuario;
        this.NombreUsuario = NombreUsuario;
        this.IdFuncion = IdFuncion;
        this.TituloPelicula = TituloPelicula;
        this.HoraInicio = HoraInicio;
        this.Total = Total;
        this.CantBoletos = cantBoletos;
    }

    //Constructor vacio
    public Venta() {
        this.IdVenta = 0;
        this.NombreUsuario = "";
        this.IdFuncion = 0;
        this.TituloPelicula = "";
        this.HoraInicio = "";
        this.Total = 0;
    }

    //Getters y Setters
    public int getIdVenta() {
        return this.IdVenta;
    }

    public void setIdVenta(int IdVenta) {
        this.IdVenta = IdVenta;
    }

    public int getIdUsuario() {
        return this.IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getNombreUsuario() {
        return this.NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    public int getIdFuncion() {
        return this.IdFuncion;
    }

    public void setIdFuncion(int IdFuncion) {
        this.IdFuncion = IdFuncion;
    }

    public String getTituloPelicula() {
        return this.TituloPelicula;
    }

    public void setTituloPelicula(String TituloPelicula) {
        this.TituloPelicula = TituloPelicula;
    }

    public String getHoraInicio() {
        return this.HoraInicio;
    }

    public void setHoraInicio(String HoraInicio) {
        this.HoraInicio = HoraInicio;
    }

    public float getTotal() {
        return this.Total;
    }

    public void setTotal(float Total) {
        this.Total = Total;
    }

    public int getCantBoletos() {
        return this.CantBoletos;
    }

    public void setCantBoletos(int CantBoletos) {
        this.CantBoletos = CantBoletos;
    }
}
