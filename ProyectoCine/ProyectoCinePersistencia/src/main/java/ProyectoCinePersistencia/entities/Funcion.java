package ProyectoCinePersistencia.entities;

public class Funcion {

    private int IdFuncion;
    private Pelicula pelicula;
    private Horario horario;
    private Sala sala;

    public Funcion() {
    }

    public Funcion(int IdFuncion, Pelicula pelicula, Horario horario, Sala sala) {
        this.IdFuncion = IdFuncion;
        this.pelicula = pelicula;
        this.horario = horario;
        this.sala = sala;
    }

    public int getIdFuncion() {
        return IdFuncion;
    }

    public void setIdFuncion(int IdFuncion) {
        this.IdFuncion = IdFuncion;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}
