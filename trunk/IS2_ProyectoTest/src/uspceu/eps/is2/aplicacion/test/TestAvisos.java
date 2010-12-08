package uspceu.eps.is2.aplicacion.test;

import java.util.Date;

import android.widget.Button;
import android.widget.EditText;
import uspceu.eps.is2.aplicacion.Aviso;
import uspceu.eps.is2.aplicacion.FormatoCoordenadasException;
import uspceu.eps.is2.aplicacion.FormularioCrearAviso;
import uspceu.eps.is2.aplicacion.NombreAvisoException;
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
	public void testAvisoSinNombre() throws NombreAvisoException {
		
		Date fechacreacion=new Date();
		Usuario u=null;
		PuntoMapa pm=null;
		try{Aviso aviso = new Aviso("", "Aviso sin nombre",u,pm);
		fail("Deberia haber fallado");}
		catch(NombreAvisoException e)
		{
		  //Se ha lanzado la excepción por intentar meter un aviso sin nombre
		  e.printStackTrace();
		}
		
		try{Aviso aviso = new Aviso("", "Aviso sin nombre","",u,pm);
		fail("Deberia haber fallado");}
		catch(NombreAvisoException e)
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
	
	public void testFormatoCoordenadas() throws FormatoCoordenadasException {
		
		
		try{PuntoMapa pm=new PuntoMapa("hola","0");;
		fail("Deberia haber fallado");}
		catch(FormatoCoordenadasException e)
		{
		  //Se ha lanzado la excepción por intentar meter unas coordenadas con la latitud incorrecta
		  e.printStackTrace();
		}
		
		try{PuntoMapa pm=new PuntoMapa("0","bye");;
		fail("Deberia haber fallado");}
		catch(FormatoCoordenadasException e)
		{
		  //Se ha lanzado la excepción por intentar meter unas coordenadas con la longitud incorrecta
		  e.printStackTrace();
		}
		
		try{PuntoMapa pm=new PuntoMapa("91","0");;
		fail("Deberia haber fallado");}
		catch(FormatoCoordenadasException e)
		{
		  //Se ha lanzado la excepción por intentar meter unas coordenadas con la latitud inexistente
		  e.printStackTrace();
		}
		
		try{PuntoMapa pm=new PuntoMapa("0","181");;
		fail("Deberia haber fallado");}
		catch(FormatoCoordenadasException e)
		{
		  //Se ha lanzado la excepción por intentar meter unas coordenadas con la longitud inexistente
		  e.printStackTrace();
		}
	}
}
