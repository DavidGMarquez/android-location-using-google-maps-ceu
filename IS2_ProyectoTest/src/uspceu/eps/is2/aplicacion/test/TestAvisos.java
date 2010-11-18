package uspceu.eps.is2.aplicacion.test;

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
		aviso=new Aviso(nombreAviso);
		aviso=new Aviso(nombreAviso, descripcionAviso);
		aviso=new Aviso(nombreAviso,tipoAviso,descripcionAviso);
		
		
		aviso=new Aviso(nombreAviso,descripcionAviso,usuario,puntoMapa);
		aviso=new Aviso(nombreAviso,descripcionAviso,tipoAviso,usuario,puntoMapa);
		//aviso=new Aviso(nombreAviso, tipoAviso, descripcionAviso, usuario, fechacreacion, puntoMapa, calificacion, votaciones);
		
		
	}
	public void testAvisoSinNombre() throws Exception {
		
		try{Aviso aviso=new Aviso("", "Deberia Fallar");
		fail("Deberia haber fallado");}
		catch(Exception e)
		{
		  
		}

	}

}
