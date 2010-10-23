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
	
}