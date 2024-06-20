package ProyectoCinePersistencia.entities;

public class Sala {
<<<<<<< HEAD:ProyectoCine/src/main/java/ProyectoCinePersistencia/entities/Sala.java
=======

>>>>>>> main:ProyectoCine/ProyectoCinePersistencia/src/main/java/ProyectoCinePersistencia/entities/Sala.java
    // Atributos
    private int IdSala;
    private int NumAsiento;

    // Constructor vacío
    public Sala() {
    }

    // Constructor con parámetros
    public Sala(int IdSala, int NumAsiento) {
        this.IdSala = IdSala;
        this.NumAsiento = NumAsiento;
    }

    // Getters y Setters
    public int getIdSala() {
        return IdSala;
    }

    public void setIdSala(int IdSala) {
        this.IdSala = IdSala;
    }

    public int getNumAsiento() {
        return NumAsiento;
    }

    public void setNumAsiento(int NumAsiento) {
        this.NumAsiento = NumAsiento;
    }

    @Override
    public String toString() {
        return "Sala{" + "IdSala=" + IdSala + ", NumAsiento=" + NumAsiento + '}';
    }
}
