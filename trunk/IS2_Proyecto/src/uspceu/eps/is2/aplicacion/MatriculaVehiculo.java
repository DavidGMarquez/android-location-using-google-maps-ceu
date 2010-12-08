package uspceu.eps.is2.aplicacion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatriculaVehiculo {
	private String matricula;

	

	public MatriculaVehiculo(String matricula) {
		super();
		this.matricula = matricula;
	}
	public boolean isCorrectSpain(){
	       	   Pattern p = Pattern.compile("(\\d{4}-[\\D\\w]{3}|[\\D\\w]{1,2}-\\d{4}-[\\D\\w]{2})");
           	      Matcher m = p.matcher(this.getMatricula());
           	      if (m.matches()){
           	    	  return true;            	      
           	    }
          return false;
	}
	@Override
	public String toString() {
		return this.getMatricula();
	}
		
	
	public String getMatricula() {
		return matricula;
	}
	

}
