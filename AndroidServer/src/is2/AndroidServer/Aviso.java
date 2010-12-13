/**
 * 
 */
package is2.AndroidServer;

//import java.text.SimpleDateFormat;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Cayette
 *
 */
public class Aviso implements Serializable {


	private String nombreAviso;
	private String tipoAviso;
	private String descripcionAviso;
	private int calificacion;
	private int votaciones;
	
	private Date fechacreacion;
	
	
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
	public Date getFechacreacion() {
		return fechacreacion;
	}
	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
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
	
	

		
	public String toCompleto() {		
		return "Aviso: "+ nombreAviso + "\n" +
				"Tipo: " + tipoAviso + "\n" +
				descripcionAviso + "\n" +
				"Creado el: " + fechacreacion + "\n" +
				"Puntuacion: " + calificacion + " de 5" + "\n";
	}

	public String toMostrar() {		
		
		
		return "Aviso: "+ nombreAviso + "\n" +
				descripcionAviso + "\n" +
				"En: "+ '\n'+
				"Creado el: " + 
				 new SimpleDateFormat("HH:mm dd/MM/yy").format(this.getFechacreacion())+'\n' +
				"Usuario: ";
	}
	
	@Override
	public String toString(){
		//SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yy");
		//return this.getNombreAviso()+", "+sdf.format(this.getFechacreacion())+'\n';
		return this.getNombreAviso()+"  "+new SimpleDateFormat("HH:mm dd/MM/yy").format(this.getFechacreacion())+'\n';
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
		
		
		ini=aviso.indexOf(": ",fin)+2;
		fin=aviso.indexOf("\n",ini);
		this.fechacreacion=new Date(aviso.substring(ini,fin));
		
		ini=aviso.indexOf(": ",fin)+1;
		fin=aviso.indexOf("\n",ini);
		
		
		ini=aviso.indexOf(" ",fin)+1;
		fin=aviso.indexOf(" ",ini);
		this.calificacion=new Integer(aviso.substring(ini, fin)).intValue();
		
		
	}
	public Aviso(String nombreAviso, String descripcionAviso) {
		super();
		this.nombreAviso = nombreAviso;
		this.descripcionAviso = descripcionAviso;
		this.fechacreacion=new Date();
		
	}
	
	
	
}
