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
	public void testCrearMatriculasNormal() {
		Vehiculo vehiculo;
		String descripcion = "Description";

		try {
			vehiculo = new Vehiculo(descripcion, "CUALQUIERCOSA123");
		} catch (MatriculaVehiculoException e) {
			fail("Debería valer el formato");
		  }
		
		try {
			vehiculo = new Vehiculo(descripcion, "");
			fail("Debería no valer el formato");
		} catch (MatriculaVehiculoException e) {
		}

	}
}
