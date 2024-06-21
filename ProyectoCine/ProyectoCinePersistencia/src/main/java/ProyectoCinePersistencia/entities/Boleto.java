package ProyectoCinePersistencia.entities;

public class Boleto {

    private int IdTipoBoleto;
    private String Nombre;
    private double Precio;

    public Boleto() {
    }

    public Boleto(int IdTipoBoleto, String Nombre, double Precio) {
        this.IdTipoBoleto = IdTipoBoleto;
        this.Nombre = Nombre;
        this.Precio = Precio;
    }

    public int getIdTipoBoleto() {
        return IdTipoBoleto;
    }

    public void setIdTipoBoleto(int IdTipoBoleto) {
        this.IdTipoBoleto = IdTipoBoleto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

}
