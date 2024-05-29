package ProyectoCinePersistencia.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Promocion {

    private int IdPromocion;
    private String FechaInicio;
    private String FechaFin;
    private int Descuento;

    public Promocion() {
        this.IdPromocion = 0;
        Date fechaI = new Date();
        Date fechaF = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.FechaInicio = dateFormat.format(fechaI);
        this.FechaFin = dateFormat.format(fechaF);
        this.Descuento = 0;
    }

    public Promocion(int IdPromocion, String FechaInicio, String FechaFin, int Descuento) {
        this.IdPromocion = IdPromocion;
        this.FechaInicio = FechaInicio;
        this.FechaFin = FechaFin;
        this.Descuento = Descuento;
    }

    public int getIdPromocion() {
        return IdPromocion;
    }

    public void setIdPromocion(int IdPromocion) {
        this.IdPromocion = IdPromocion;
    }

    public String getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(String FechaInicio) {
        this.FechaInicio = FechaInicio;
    }

    public String getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(String FechaFin) {
        this.FechaFin = FechaFin;
    }

    public int getDescuento() {
        return Descuento;
    }

    public void setDescuento(int Descuento) {
        this.Descuento = Descuento;
    }

}
