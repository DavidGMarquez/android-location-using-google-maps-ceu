package uspceu.eps.is2.aplicacion;

//import java.util.Date;

public class Vehiculo {
	private String descripcion;
	private String matricula;
	private String marca;
	private String modelo;
	private String color;
	
	
	
	public Vehiculo(String matricula, String marca,
	String modelo,String color,String descripcion){ //Usuario usuario) {
		super();
		this.matricula = matricula;
		this.marca = marca;
		this.modelo=modelo;
		this.color = color;
		this.descripcion=descripcion;
		
		
	}

	public Vehiculo(String descripcion,String matricula){
		this.matricula = matricula;
		this.descripcion=descripcion;
		this.marca ="";
		this.modelo="";
		this.color ="";
	
	}
	
	public Vehiculo(String datosVehiculo){
		int ini=0, fin=0;
	//	String au="";
		
		ini=datosVehiculo.indexOf("$$$",0)+3;
		fin=datosVehiculo.indexOf("$$$",ini);
		this.descripcion=datosVehiculo.substring(ini,fin);
		
		ini=datosVehiculo.indexOf("$$$",fin)+3;
		fin=datosVehiculo.indexOf("$$$",ini);
		this.matricula=datosVehiculo.substring(ini,fin);
		
		
	}
	
	
	
	
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matri) {
		this.matricula = matri;
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

	public String toStringDescMatri() {		
		return new String("$$$"+this.getDescripcion()+"$$$"+this.getMatricula()+"$$$");				 			
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
