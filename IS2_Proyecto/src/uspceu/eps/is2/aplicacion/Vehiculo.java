package uspceu.eps.is2.aplicacion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Character;

import android.widget.Toast;

//import java.util.Date;

public class Vehiculo {
	private String descripcion;
	private String matricula;
	private String marca;
	private String modelo;
	private String color;
	private int estado=0;
	private boolean valida = true;

	
	
	public Vehiculo(String matricula, String marca,
	String modelo,String color,String descripcion){ //Usuario usuario) {
		super();
		this.matricula = matricula;
		this.marca = marca;
		this.modelo=modelo;
		this.color = color;
		this.descripcion=descripcion;
		
		
	}

	public Vehiculo(){
		this.matricula ="";
		this.descripcion="";
		this.marca ="";
		this.modelo="";
		this.color ="";
	
	}
	
	
	
	public Vehiculo(String descripcion,String matricula){
		System.out.print("Dentro del contrucctor");
		boolean correcta = true;
		char cc;
		//matricula.toUpperCase();
		//	int comprobacion=validaMatricula(matricula);
//		if (comprobacion==1){
//			System.out.print("matricula formato correcto");
		//if ()
		correcta = validarLongitud(matricula);
	    if (correcta){ 
	    	cc=matricula.charAt(0);
	    	  if(Character.isDigit(cc))//se trata de matricula moderna
	    		//matriculas Formato Europeo Actuales 4888FBM o 1234FRT
	  	    	valida = validarMatEuropea(matricula);
	    	  
	    	  else////matriculas formato antiguo PO1234T o M1234TX
              setValida(validarMatAntigua(matricula));
	        
	    }
	    else{ //
	    	setValida(false);
    	System.out.print("La matricula debe contener 7 caracteres");
          }
		//setValida(validarMatAntigua(matricula));

		if (isValida()==true){
		this.matricula = matricula;
		this.descripcion=descripcion;
		this.marca ="";
		this.modelo="";
		this.color ="";
		}
		else{
		 //System.out.print("no se pudo cear objeto");	
		
		}
		
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
	
	
	public int validaMatricula(String matricula){
		   Pattern p = Pattern.compile("(\\d{4}-[\\D\\w]{3}|[\\D\\w]{1,2}-\\d{4}-[\\D\\w]{2})");
 	      Matcher m = p.matcher(matricula);
 	      int estado=0;
 	      if (!m.find())
 	    	//  Toast.makeText(getBaseContext(),"Matricula incorrecta, introduzca formato válido", Toast.LENGTH_LONG).show();            	      
 	    estado=-1;
 	      
  else
	estado=1;
 	 return estado;     
	}


	
	//nuevo formato de matriculas
	public boolean validarMatEuropea(String mat)
	{
	boolean correcta1 = true, correcta2 = true;
	String numericaEurop = mat.substring(0, 4);
	correcta1 = validarNumerica(numericaEurop);
	//if (!correcta1)
	//system.out.print("Las cuatro primeras posiciones
	///deben ser numéricas. Repetir ");
	correcta2 = validarLetra(mat.charAt(4)) &&
	validarLetra(mat.charAt(5)) && validarLetra(mat.charAt(6));
//	if (!correcta2)
	//System.out.print("Las tres últimas posiciones
	///alfabéticas no pueden contener vocales ni 'Ñ' ni 'Q'. Repetir	");
	return (correcta1 && correcta2);
	}

	
	
	
	
	//valida matriculas formato antiguo
	public boolean validarMatAntigua(String mat)
	{
	boolean inicio=true,correcta1 = false, correcta2 = false;
	//mat.substring(0,2)
	
     //correcto2=validarLetra(mat.substring(0,1));
    	char c1 =mat.charAt(0);
    	char c2 =mat.charAt(1); 
    
    	//matriculas antiguas formato OU6996P CO1234X BA3004D
    	if (Character.isLetter(c1) && Character.isLetter(c2))	
    {    
	String numericaAnt = mat.substring(2, 6);
	correcta1 = validarNumerica(numericaAnt);
	
	if (!correcta1)
		System.out.print("incorrecto,Las posiciones 3, 4, 5 y 6 debenser números");
		char ultPos;
		ultPos = mat.charAt(6);
	       correcta2 = validarLetra(ultPos);
	if (!correcta2)
		System.out.print("La última letra no puede ser vocal ni 'Ñ' ni 'Q'.");
	 
	}
    	else{
    		if(Character.isDigit(c2)){
    			String numericaAnt = mat.substring(1, 5);
    			correcta1 = validarNumerica(numericaAnt);
    			 c1 =mat.charAt(5);
    	    	 c2 =mat.charAt(6); 
    	    	 correcta2=Character.isLetter(c1) && Character.isLetter(c2);
    			
    		}
    		
    		
    		
    	}
   
    	return (correcta1 && correcta2);
     
    
    }

	//Validar longitud
	public  boolean validarLongitud(String mat)
	{
	return (mat.length() == 7);
	}

	
	
		
	private boolean validarNumerica(String num)
	{
	boolean correcta = true;
	int longitud = num.length();
	int i =0;
	while( i <= longitud-1 && correcta == true)
	{
	char aux = num.charAt(i);
	if ( aux < '0' || aux > '9')
	correcta = false;
	i++;
	}
	return correcta;
	}

	private boolean validarLetra (char letra)
	{
	return (letra != 'A' && letra != 'E' &&
	letra != 'I' && letra != 'O' && letra != 'U' &&
	letra != 'Ñ' && letra != 'Q' &&
	letra >= 'A' && letra <='Z');
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

	public void setEstado(int estado) {
		estado = estado;
	}

	public int getEstado() {
		return estado;
	}

	public void setValida(boolean valida) {
		this.valida = valida;
	}

	public boolean isValida() {
		return valida;
	}
	
/*	public String toCompleto() {		
		return "Matricula: " + matricula + "\n" +
				"Marca: " + marca + "\n" +
				"Modelo:"+ modelo+"\n" +
				"Color: " + color+ "\n";  
				
	}*/
	
}
