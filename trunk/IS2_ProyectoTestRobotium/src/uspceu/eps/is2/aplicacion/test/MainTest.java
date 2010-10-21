package uspceu.eps.is2.aplicacion.test;

import java.util.ArrayList;

import uspceu.eps.is2.aplicacion.AplicacionMain;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
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
	public void testDisplay() throws Exception {
		assertTrue(solo.searchText("Hello World"));
		solo.clickOnMenuItem("Crear Aviso");
		solo.assertCurrentActivity("Expected activity","CrearAvisos");		
		solo.goBack(); // Go back															
		solo.assertCurrentActivity("Expected activity", "AplicacionMain");
		solo.clickOnMenuItem("Crear Aviso");
		solo.assertCurrentActivity("Expected activity","CrearAvisos");		

		assertTrue(solo.searchText("Nombre:"));
		
		solo.clickOnMenuItem("Ver Avisos");
		solo.assertCurrentActivity("Expected NoteEditor activity", "VerAvisos");
		solo.clickOnMenuItem("Alta Vehiculo");
		assertTrue(solo.searchText("Introducir datos"));
		solo.assertCurrentActivity("Expected activity","AltaVehiculo");
		solo.clickOnMenuItem("Mapa Avisos");
		solo.assertCurrentActivity("Expected activity", "HolaMundo1");


	}

}