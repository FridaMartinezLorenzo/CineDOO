package ProyectoCinePersistencia.entities;

public class Pelicula {
    // Atributos
    private int IdPelicula;
    private String Titulo;
    private String Sinopsis;
    private int Duracion;
    private String fechaEstreno;
    private int IdCategoria;

    // Constructor vacío
    public Pelicula() {
    }

    // Constructor con parámetros
    public Pelicula(int IdPelicula, String Titulo, String Sinopsis, int Duracion, String fechaEstreno, int IdCategoria) {
        this.IdPelicula = IdPelicula;
        this.Titulo = Titulo;
        this.Sinopsis = Sinopsis;
        this.Duracion = Duracion;
        this.fechaEstreno = fechaEstreno;
        this.IdCategoria = IdCategoria;
    }

    // Getters y Setters
    public int getIdPelicula() {
        return IdPelicula;
    }

    public void setIdPelicula(int IdPelicula) {
        this.IdPelicula = IdPelicula;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getSinopsis() {
        return Sinopsis;
    }

    public void setSinopsis(String Sinopsis) {
        this.Sinopsis = Sinopsis;
    }

    public int getDuracion() {
        return Duracion;
    }

    public void setDuracion(int Duracion) {
        this.Duracion = Duracion;
    }

    public String getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(String fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public int getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(int IdCategoria) {
        this.IdCategoria = IdCategoria;
    }
}
