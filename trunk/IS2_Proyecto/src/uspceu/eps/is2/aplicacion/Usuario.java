package uspceu.eps.is2.aplicacion;

public class Usuario {
	private String nombre;
	private String password;
	
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Usuario(String nombre, String pass) {
		this.nombre = nombre;
		this.password = pass;
	}
	
	public Usuario(String name){
		this.nombre = name;
	}

    @Override
	public String toString() {
		return nombre;
	}
	
	

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
	
	public String toCompleto() {		
		return "Usuario: "+ nombre + "\n" +
				"pass: " + password + "\n" ;
				
	}
	
	public boolean esUsuIncorrecto(){
		
		//si usuario y contraseña vacios
		if (this.nombre==null){
			return true;
			}
		if (this.nombre.equals("")||(this.nombre.contains(" "))){
			return true;
		}
		if (this.password==null){
			return true;
		}
		if(this.password.equals("")||(this.password.contains(" "))){
			return true;
		}
		//si usu y pass no tienen entre 6 y 20 caracteres
		if((this.nombre.length()<6)||(this.password.length()<6)){
			return true;
		}
		if((this.nombre.length()>20)||(this.password.length()>20)){
			return true;
		}
				
		return false;
	}
	
}
