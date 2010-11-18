package uspceu.eps.is2.aplicacion.test;

import java.util.Date;

import android.widget.Button;
import android.widget.EditText;
import uspceu.eps.is2.aplicacion.Aviso;
import uspceu.eps.is2.aplicacion.FormularioCrearAviso;
import uspceu.eps.is2.aplicacion.PuntoMapa;
import uspceu.eps.is2.aplicacion.R;
import uspceu.eps.is2.aplicacion.Usuario;
import junit.framework.TestCase;

public class TestAvisos extends TestCase {
	public void testCrearAvisos(){
		Aviso aviso;
		String nombreAviso="Nombre";
		String descripcionAviso="Description";
		String tipoAviso="Tipo";
		Usuario usuario = null;
		PuntoMapa puntoMapa=null;
		Date fechacreacion=new Date();
		int calificacion=0;
		int votaciones=0;
		
		aviso=new Aviso(nombreAviso, descripcionAviso,usuario,puntoMapa);
		aviso=new Aviso(nombreAviso,descripcionAviso,tipoAviso,usuario,puntoMapa);
		aviso=new Aviso(nombreAviso, tipoAviso, descripcionAviso, usuario, fechacreacion, puntoMapa, calificacion, votaciones);
		
		
	}
	public void testAvisoSinNombre() throws Exception {
		Usuario u=null;
		PuntoMapa pm=null;
		try{Aviso aviso = new Aviso("", "Deberia Fallar",u,pm);
		fail("Deberia haber fallado");}
		catch(Exception e)
		{
		  
		}

	}

}
