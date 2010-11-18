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
		
		try {
			aviso=new Aviso(nombreAviso, descripcionAviso,usuario,puntoMapa);
		} catch (Exception e) {
			fail("Esto no es normal!!");
		}
		try {
			aviso=new Aviso(nombreAviso,descripcionAviso,tipoAviso,usuario,puntoMapa);
		} catch (Exception e) {
			fail("Esto no es normal!!");
		}
		try {
			aviso=new Aviso(nombreAviso, tipoAviso, descripcionAviso, usuario, fechacreacion, puntoMapa, calificacion, votaciones);
		} catch (Exception e) {
			fail("Esto no es normal!!");
		}
		
		
	}
	public void testAvisoSinNombre() throws Exception {
		
		Date fechacreacion=new Date();
		Usuario u=null;
		PuntoMapa pm=null;
		try{Aviso aviso = new Aviso("", "Aviso sin nombre",u,pm);
		fail("Deberia haber fallado");}
		catch(Exception e)
		{
		  //Se ha lanzado la excepción por intentar meter un aviso sin nombre
		  e.printStackTrace();
		}
		
		try{Aviso aviso = new Aviso("", "Aviso sin nombre","",u,pm);
		fail("Deberia haber fallado");}
		catch(Exception e)
		{
		  //Se ha lanzado la excepción por intentar meter un aviso sin nombre
		  e.printStackTrace();
		}
		
		try{Aviso aviso = new Aviso("", "Aviso sin nombre","",u,fechacreacion,pm,0,0);
		fail("Deberia haber fallado");}
		catch(Exception e)
		{
		  //Se ha lanzado la excepción por intentar meter un aviso sin nombre
		  e.printStackTrace();
		}

	}

}
