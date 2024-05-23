/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoCinePersistencia.dao.impl;


import ProyectoCinePersistencia.dao.PeliculaDAO;
import ProyectoCinePersistencia.entities.Pelicula;
import ProyectoCinePersistencia.utils.dbConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author frida
 */
public class PeliculaDAOImpl implements PeliculaDAO{
    private dbConnection conexion;
    
    public PeliculaDAOImpl(){
        this.conexion = new dbConnection();
    }
    
    @Override
    public void Crear(Pelicula pelicula){
        
    }
    
    @Override
    public List<Pelicula> ObtenerTodas()
    {
        List <Pelicula> peliculas = new ArrayList<>();
        return peliculas;
    }    

    @Override
    public Pelicula Obtener(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void Actualizar(Pelicula pelicula) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void Eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
