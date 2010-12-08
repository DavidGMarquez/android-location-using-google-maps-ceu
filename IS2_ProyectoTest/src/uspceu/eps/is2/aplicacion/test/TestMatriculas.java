package uspceu.eps.is2.aplicacion.test;

import java.util.Date;

import android.widget.Button;
import android.widget.EditText;
import uspceu.eps.is2.aplicacion.Aviso;
import uspceu.eps.is2.aplicacion.FormatoCoordenadasException;
import uspceu.eps.is2.aplicacion.FormularioCrearAviso;
import uspceu.eps.is2.aplicacion.MatriculaVehiculoException;
import uspceu.eps.is2.aplicacion.NombreAvisoException;
import uspceu.eps.is2.aplicacion.PuntoMapa;
import uspceu.eps.is2.aplicacion.R;
import uspceu.eps.is2.aplicacion.Usuario;
import uspceu.eps.is2.aplicacion.Vehiculo;
import junit.framework.TestCase;

public class TestMatriculas extends TestCase {
	public void testCrearMatriculasNormal(){
		Vehiculo vehiculo;	
		String descripcion="Description";
		try {
			vehiculo=new Vehiculo(descripcion, "AB-1234-ABC");
			fail("Deberia no valer el formato");
		} catch (MatriculaVehiculoException e) {
			
		}
		try {
			vehiculo=new Vehiculo(descripcion, "ABCD-123");
			fail("Deberia no valer el formato");
		} catch (MatriculaVehiculoException e) {
			
		}
		try {
			vehiculo=new Vehiculo(descripcion, "1234-ABCD");
			fail("Deberia no valer el formato");
		} catch (MatriculaVehiculoException e) {
			
		}
		try {
			vehiculo=new Vehiculo(descripcion, "AB-1234-ABC");
			fail("Deberia no valer el formato");
		} catch (MatriculaVehiculoException e) {
			
		}
		try {
			vehiculo=new Vehiculo(descripcion, "CO-1234-ABC");
			fail("Deberia no valer el formato");
		} catch (MatriculaVehiculoException e) {
			
		}
		try {
			vehiculo=new Vehiculo(descripcion, "CO-124-ABC");
			fail("Deberia no valer el formato");
		} catch (MatriculaVehiculoException e) {
			
		}
		//Correctos
		try {
			vehiculo=new Vehiculo(descripcion, "1234-ABC");
			
		} catch (MatriculaVehiculoException e) {
			fail("Deberia  valer el formato");
		}
		try {
			vehiculo=new Vehiculo(descripcion, "CO-1234-AB");
			
		} catch (MatriculaVehiculoException e) {
			fail("Deberia  valer el formato");
		}
		try {
			vehiculo=new Vehiculo(descripcion, "ab-2345-AC");
			
		} catch (MatriculaVehiculoException e) {
			fail("Deberia  valer el formato");
		}

	}
}
