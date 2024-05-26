/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ProyectoCinePersistencia.dao;

import ProyectoCinePersistencia.entities.Pelicula;
import java.util.List;
        
/**
 *
 * @author frida
 */
public interface PeliculaDAO {
    Pelicula Crear(Pelicula pelicula);
    Pelicula Obtener(int id);
    void Actualizar(Pelicula pelicula);
    void Eliminar (int id);
    List<Pelicula> ObtenerTodas();
    
}
