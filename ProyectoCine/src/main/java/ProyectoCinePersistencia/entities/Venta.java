/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoCinePersistencia.entities;
import java.util.Date;
/**
 *
 * @author frida
 */
public class Venta {
    //Atributos
    private int IdVenta;
    private int NoBoletos;
    private Date Fecha;
    
    //Constructor
    public Venta(){
        IdVenta = 0;
        NoBoletos = 0;
        Fecha = new Date();//Obtenemos fecha actual
    }
    
    //Métodos
    public void setIdVenta(int id){
        IdVenta = id;
    }
    
    public int getIdVenta(){
        return IdVenta;
    }
    
    public void setNoBoletos(int nB){
        NoBoletos = nB;
    }
    
    public int getNoBoletos(){
        return NoBoletos;
    }
    
    public void setFecha(Date f){
        Fecha = f;
    }
    public Date getFecha (){
        return Fecha;
    }
}
