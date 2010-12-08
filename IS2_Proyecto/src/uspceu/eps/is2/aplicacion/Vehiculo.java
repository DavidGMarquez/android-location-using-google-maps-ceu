package uspceu.eps.is2.aplicacion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Character;

import android.widget.Toast;

//import java.util.Date;

public class Vehiculo {
	private String descripcion;
	private MatriculaVehiculo matricula;
	private String marca;
	private String modelo;
	private String color;

	
	

	
	public Vehiculo(String descripcion,String matricula) throws MatriculaVehiculoException{
		
		this.ConstruirVehiculoSpain(descripcion, matricula);
		this.marca ="";
		this.modelo="";
		this.color ="";
		}		
		
	
	
	public Vehiculo(String datosVehiculo) throws MatriculaVehiculoException{
		int ini=0, fin=0;
	
		ini=datosVehiculo.indexOf("$$$",0)+3;
		fin=datosVehiculo.indexOf("$$$",ini);
		String descripcion=datosVehiculo.substring(ini,fin);
		
		ini=datosVehiculo.indexOf("$$$",fin)+3;
		fin=datosVehiculo.indexOf("$$$",ini);
		String matricula=datosVehiculo.substring(ini,fin);
		
		this.ConstruirVehiculoSpain(descripcion, matricula);
		
	}
	public void ConstruirVehiculoSpain(String descripcion,String matricula) throws MatriculaVehiculoException
	{
		this.descripcion=descripcion;
		this.matricula=new MatriculaVehiculo(matricula);
		if(!this.matricula.isCorrectSpain()){			
			throw   new MatriculaVehiculoException("Formato Matrícula incorrecto");
		}
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String mar) {
		this.marca = mar;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String model) {
		this.modelo = model;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String col) {
		this.color = col;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public MatriculaVehiculo getMatricula() {
		return matricula;
	}



	public void setMatricula(MatriculaVehiculo matricula) {
		this.matricula = matricula;
	}
	public String toStringDescMatri() {		
		return new String("$$$"+this.getDescripcion()+"$$$"+this.getMatricula().toString()+"$$$");				 			
	}
	public String toString(){
		return new String("Vehiculo:"+this.getDescripcion()+"\nMatricula:"+this.getMatricula());
	}

	
/*	public String toCompleto() {		
		return "Matricula: " + matricula + "\n" +
				"Marca: " + marca + "\n" +
				"Modelo:"+ modelo+"\n" +
				"Color: " + color+ "\n";  
				
	}*/
	
}
