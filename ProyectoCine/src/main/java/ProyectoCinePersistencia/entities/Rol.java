
package ProyectoCinePersistencia.entities;

public class Rol {
    // Declaración de atributos
    private int idRol;
    private String nombre;
    
    // Constructor vacío
    public Rol() {
    }
    
    // Constructor con parámetros
    public Rol(int idRol, String nombre) {
        this.idRol = idRol;
        this.nombre = nombre;
    }
    
    // Métodos getters y setters
    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
    
    public int getIdRol() {
        return this.idRol;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return this.nombre;
    }
}