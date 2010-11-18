/**
 * 
 */
package uspceu.eps.is2.aplicacion;

//import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Cayette
 *
 */
public class Aviso {


	private String nombreAviso;
	private String tipoAviso;
	private String descripcionAviso;
	private int calificacion;
	private int votaciones;
	private Usuario usuario;
	private Date fechacreacion;
	private PuntoMapa puntoMapa;
	
	
	public String getNombreAviso() {
		return nombreAviso;
	}
	public void setNombreAviso(String nombreAviso) {
		this.nombreAviso = nombreAviso;
	}
	public String getTipoAviso() {
		return tipoAviso;
	}
	public void setTipoAviso(String tipoAviso) {
		this.tipoAviso = tipoAviso;
	}
	public String getDescripcionAviso() {
		return descripcionAviso;
	}
	public void setDescripcionAviso(String descripcionAviso) {
		this.descripcionAviso = descripcionAviso;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getFechacreacion() {
		return fechacreacion;
	}
	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}
	public PuntoMapa getPuntoMapa() {
		return puntoMapa;
	}
	public void setPuntoMapa(PuntoMapa puntoMapa) {
		this.puntoMapa = puntoMapa;
	}
	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	public int getCalificacion() {
		return calificacion;
	}
	public void setVotaciones(int votaciones) {
		this.votaciones = votaciones;
	}
	public int getVotaciones() {
		return votaciones;
	}
	
	//Constructor completo
	public Aviso(String nombreAviso, String tipoAviso,
			String descripcionAviso, Usuario usuario, Date fechacreacion,
			PuntoMapa puntoMapa, int calificacion, int votaciones) {
		this.nombreAviso = nombreAviso;
		this.tipoAviso = tipoAviso;
		this.descripcionAviso = descripcionAviso;
		this.usuario = usuario;
		this.fechacreacion = fechacreacion;
		this.puntoMapa = puntoMapa;
		this.calificacion=calificacion;
		this.votaciones=votaciones;
	}
	
	//Constructor que pone los valores de fecha, calificación y votaciones por defecto
	public Aviso(String nombreAviso, String descripcionAviso, String tipo, Usuario usuario, PuntoMapa puntoMapa) {
		
		this.nombreAviso = nombreAviso;
		this.tipoAviso = tipo;
		this.descripcionAviso = descripcionAviso;
		this.usuario = usuario;
		this.puntoMapa = puntoMapa;
		this.fechacreacion=new Date();
		this.calificacion=0;
		this.votaciones=0;
	}	
	
	// Constructor que pone los valores de tipo, fecha, calificación y votaciones por defecto
	public Aviso(String nombreAviso, String descripcionAviso, Usuario usuario, PuntoMapa puntoMapa) {
		
		this.nombreAviso = nombreAviso;
		this.tipoAviso = "Evento";
		this.descripcionAviso = descripcionAviso;
		this.usuario = usuario;
		this.puntoMapa = puntoMapa;
		this.fechacreacion=new Date();
		this.calificacion=0;
		this.votaciones=0;
	}
		
	public String toCompleto() {		
		return "Aviso: "+ nombreAviso + "\n" +
				"Tipo: " + tipoAviso + "\n" +
				descripcionAviso + "\n" +
				"Punto: " + puntoMapa + "\n" +
				"Creado el: " + fechacreacion + "\n" +
				"Creado por: " + usuario + "\n" +
				"Puntuacion: " + calificacion + " de 5" + "\n";
	}

	public String toMostrar() {		
		return "Aviso: "+ nombreAviso + "\n" +
				descripcionAviso + "\n";
				//"Creado el: " + fechacreacion + "\n";
	}
	
	@Override
	public String toString(){
		//SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yy");
		//return this.getNombreAviso()+", "+sdf.format(this.getFechacreacion())+'\n';
		return this.getNombreAviso()+'\n';
	}
	
	/* Metodos para leer avisos de fichero */
	
	public Aviso(String aviso){
		int ini=0, fin=0;
		String au="";
		
		ini=aviso.indexOf(" ",0)+1;
		fin=aviso.indexOf("\n",ini);
		this.nombreAviso=aviso.substring(ini,fin);
		
		ini=aviso.indexOf(" ",fin)+1;
		fin=aviso.indexOf("\n",ini);
		this.tipoAviso=aviso.substring(ini,fin);
		
		ini=fin+1;
		fin=aviso.indexOf("\n",ini);
		this.descripcionAviso=aviso.substring(ini,fin);
		
		
		ini=aviso.indexOf(" ",fin)+1;
		fin=aviso.indexOf(" ",ini);
		au=aviso.substring(ini,fin);
		ini=aviso.indexOf(", ",fin)+2;
		fin=aviso.indexOf(" ",ini);
		this.puntoMapa=new PuntoMapa(au,aviso.substring(ini,fin));
		
		ini=aviso.indexOf(": ",fin)+2;
		fin=aviso.indexOf("\n",ini);
		this.fechacreacion=new Date(aviso.substring(ini,fin));
		
		ini=aviso.indexOf(" ",fin)+1;
		fin=aviso.indexOf("\n",ini);
		this.usuario=new Usuario(aviso.substring(ini, fin));
		
		ini=aviso.indexOf(" ",fin)+1;
		fin=aviso.indexOf(" ",ini);
		this.calificacion=new Integer(aviso.substring(ini, fin)).intValue();
		
		
	}
	
	
}
