package uspceu.eps.is2.proyecto_avisos.test;

import java.util.ArrayList;

import uspceu.eps.is2.proyecto_avisos.ProyectoAvisos;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.jayway.android.robotium.solo.Solo;

public class MainTest extends ActivityInstrumentationTestCase2<ProyectoAvisos> {

	private Solo solo;
	private Activity activity;

	public MainTest() {
		super("uspceu.eps.is2.proyecto_avisos", ProyectoAvisos.class);
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
		//this.activity.finish();
		super.tearDown();
	}

	/**
	 * @throws Exception
	 *             Exception
	 */
	public void testDisplay() throws Exception {

//assertTrue(solo.searchText("Ver Avisos"));
assertTrue(solo.searchText("JASdlfkajslñfjasdf"));
//assertFalse(solo.searchText("Pepe"));
}

}