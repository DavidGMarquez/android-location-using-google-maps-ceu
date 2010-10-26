package uspceu.eps.is2.aplicacion.test;

import java.util.ArrayList;

import uspceu.eps.is2.aplicacion.AplicacionMain;
import uspceu.eps.is2.aplicacion.CrearAvisos;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;
import android.widget.TextView;

import com.jayway.android.robotium.solo.Solo;

public class MainTest extends ActivityInstrumentationTestCase2<AplicacionMain> {

	private Solo solo;
	private Activity activity;

	public MainTest() {
		super("uspceu.eps.is2.aplicacion", AplicacionMain.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		this.activity = this.getActivity();
		this.solo = new Solo(getInstrumentation(), this.activity);
	}

	@Override
	public void tearDown() throws Exception {
		try {
			this.solo.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		// this.activity.finish();
		super.tearDown();
	}

	/**
	 * @throws Exception
	 *             Exception
	 */
	public void testMenuAplicationMain() throws Exception {
		
		solo.assertCurrentActivity("Expected activity", "AplicacionMain");
		solo.clickOnMenuItem("Crear Aviso");
		solo.assertCurrentActivity("Expected activity","CrearAvisos");		
		solo.goBack(); // Go back		
		solo.assertCurrentActivity("Expected activity", "AplicacionMain");		
		solo.clickOnMenuItem("Ver Avisos");	
		solo.assertCurrentActivity("Expected activity", "VerAvisos");
		solo.goBack(); // Go back		
		solo.assertCurrentActivity("Expected activity", "AplicacionMain");
		solo.clickOnMenuItem("Alta Vehiculo");	
		solo.assertCurrentActivity("Expected activity", "AltaVehiculo");
		solo.goBack(); // Go back		
		solo.assertCurrentActivity("Expected activity", "AplicacionMain");
		solo.clickOnMenuItem("Identificar Usuario");	
		solo.assertCurrentActivity("Expected activity", "Identificacion");
		solo.goBack(); // Go back		
		solo.assertCurrentActivity("Expected activity", "AplicacionMain");
		solo.clickOnMenuItem("Mapa Avisos");	
		solo.assertCurrentActivity("Expected activity", "HolaMundo1");
		solo.goBack(); // Go back		
		//Esto debería funcionar
		//solo.assertCurrentActivity("Expected activity", "AplicacionMain");				
	}
	public void testGoBack() throws Exception{
		solo.clickOnMenuItem("Crear Aviso");
		solo.assertCurrentActivity("Expected activity","CrearAvisos");		
		solo.goBack(); // Go back															
		solo.assertCurrentActivity("Expected activity", "AplicacionMain");			
	}
	public void testInit() throws Exception {
		assertTrue(solo.searchText("Hello World"));
	}
	public void testContent() throws Exception {
		assertTrue(solo.searchText("Hello World"));
		solo.assertCurrentActivity("Expected activity", "AplicacionMain");
		solo.clickOnMenuItem("Crear Aviso");
		solo.assertCurrentActivity("Expected activity","CrearAvisos");		
		assertTrue(solo.searchText("Nombre:"));		
		solo.clickOnMenuItem("Alta Vehiculo");
		assertTrue(solo.searchText("Introducir datos"));
		solo.assertCurrentActivity("Expected activity","AltaVehiculo");
	}
	
	public void testAvisoSinNombre() throws Exception{
		
		//Comprueba cuántos avisos hay
		solo.clickOnMenuItem("Ver Avisos");
		ListView lv=solo.getCurrentListViews().get(0);
		int items=lv.getCount(); //Numero de avisos creados
		
		//Intenta crear un aviso sin nombre
		solo.clickOnMenuItem("Crear Aviso");
		solo.assertCurrentActivity("Expected Activity", "CrearAvisos");
		solo.clearEditText(0); //Se asegura de que no haya nada en el campo de nombre
		solo.clickOnButton("Guardar"); //Intenta guardar un aviso vacío
		
		//Comprueba que no se haya creado un aviso nuevo
		solo.clickOnMenuItem("Ver Avisos");
		assertTrue(items==solo.getCurrentListViews().get(0).getCount());
	}

}