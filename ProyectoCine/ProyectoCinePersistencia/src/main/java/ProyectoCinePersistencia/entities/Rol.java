package ProyectoCinePersistencia.entities;

public class Rol {
    // Declaración de atributos

    private int IdRol;
    private String nombre;

    // Constructor vacío
    public Rol() {
    }

    // Constructor con parámetros
    public Rol(int idRol, String nombre) {
        this.IdRol = idRol;
        this.nombre = nombre;
    }

    // Métodos getters y setters
    public void setIdRol(int idRol) {
        this.IdRol = idRol;
    }

    public int getIdRol() {
        return this.IdRol;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }
}
