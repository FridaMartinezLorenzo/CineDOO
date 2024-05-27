package ProyectoCinePersistencia.entities;

public class Usuario {
    int idUsuario;
    String nombre;
    String correo;
    String contrasena;
    int idRol;
    
    public Usuario() {
    }

    public Usuario(int idUsuario, String nombre, String correo, String contrasena, int idRol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.idRol = idRol;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdUsuario() {
        return this.idUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getContrasena() {
        return this.contrasena;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public int getIdRol() {
        return this.idRol;
    }
}
