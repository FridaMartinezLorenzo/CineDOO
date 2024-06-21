/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoCinePersistencia.entities;


public class Categoria {
    //Atributos
    private int IdCategoria;
    private String Nombre;
    
    //Constructor
    public Categoria(){
        IdCategoria = 0;
        Nombre = "";
    }
    
    //Metodos
    public void setIdCategoria( int id){
        IdCategoria = id;
    }
    
    public int getCategoria(){
        return IdCategoria;
    }
    
    public void setNombre(String nombre){
        Nombre = nombre;
    }
    
    public String getNombre(){
        return Nombre;
    }
}

