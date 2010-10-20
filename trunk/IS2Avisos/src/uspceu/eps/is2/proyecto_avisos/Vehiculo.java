package uspceu.eps.is2.proyecto_avisos;

//import java.util.Date;

public class Vehiculo {
	private String matricula;
	private String marca;
	private String modelo;
	private String color;
	
	
	
	public Vehiculo(String matricula, String marca,
	String modelo,String color){ //Usuario usuario) {
		super();
		this.matricula = matricula;
		this.marca = marca;
		this.modelo=modelo;
		this.color = color;
		//this.usuario = usuario;
		
	}

	public Vehiculo(String datos,int n){
		this.matricula = datos;
		this.marca =" ";
		this.modelo=" ";
		this.color =" ";
	
	}
	
	public Vehiculo(String aviso){
		int ini=0, fin=0;
	//	String au="";
		
		ini=aviso.indexOf(" ",0)+1;
		fin=aviso.indexOf("\n",ini);
		this.matricula=aviso.substring(ini,fin);
		
		ini=aviso.indexOf(" ",fin)+1;
		fin=aviso.indexOf("\n",ini);
		this.marca=aviso.substring(ini,fin);
		
		ini=fin+1;
		fin=aviso.indexOf("\n",ini);
		this.modelo=aviso.substring(ini,fin);
			
		ini=aviso.indexOf(" ",fin)+1;
		fin=aviso.indexOf("\n",ini);
		this.color=aviso.substring(ini,fin);
		
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

	public String toCompleto2() {		
		return "Descripcion: " + matricula + "\n";
				 				
	}
	
	public String toCompleto() {		
		return "Matricula: " + matricula + "\n" +
				"Marca: " + marca + "\n" +
				"Modelo:"+ modelo+"\n" +
				"Color: " + color+ "\n";  
				
	}
	
}
