/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ProyectoCinePersistencia.dao;

import java.util.List;

import ProyectoCinePersistencia.entities.Pelicula;

/**
 *
 * @author frida
 */
public interface PeliculaDAO {

    void Crear(Pelicula pelicula);

    Pelicula Buscar(int id);

    void Actualizar(Pelicula pelicula);

    void Eliminar(int id);

    List<Pelicula> Listar();

}
