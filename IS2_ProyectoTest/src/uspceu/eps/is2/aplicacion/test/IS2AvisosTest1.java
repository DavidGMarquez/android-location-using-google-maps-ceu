package uspceu.eps.is2.aplicacion.test;

import java.util.Random;

import android.os.Bundle;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import uspceu.eps.is2.aplicacion.AplicacionMain;
import uspceu.eps.is2.aplicacion.Aviso;
import uspceu.eps.is2.aplicacion.FormularioCrearAviso;
import uspceu.eps.is2.aplicacion.R;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

public class IS2AvisosTest1 extends
		ActivityInstrumentationTestCase2<AplicacionMain> {
	private AplicacionMain mActivity;
	private TabHost mTabHost;
	private String resourceString;
	private static final String TAG = "IS2Avisos";

	public IS2AvisosTest1() {
		super("uspceu.eps.is2.aplicacion", AplicacionMain.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Log.d(TAG, "setUp");
		// Para que no de problemas al depurar en telefono
		setActivityInitialTouchMode(false);

		// Aqui necesitamos algo para borrar la información del telefono, un
		// clear data.

		mActivity = this.getActivity();
		resourceString = mActivity
				.getString(uspceu.eps.is2.aplicacion.R.string.app_name);
	}

	public void testPreconditions() {
		assertNotNull(mActivity);
	}

	public void testAplicationName() {
		// No se como se hace esto System.out.println(resourceString);
		Log.d(TAG, "AplicationName");
		Log.d(TAG, (String) mActivity.getTitle());
		assertEquals(resourceString, (String) mActivity.getTitle());
	}

	public void testAvisoSinNombre() throws Exception {
		FormularioCrearAviso form;

		// ListView lv=(ListView) mActivity.findViewById(R.id.ver_avisos)
		form = new FormularioCrearAviso();

		EditText name = (EditText) mActivity.findViewById(R.id.editname);
		name.setText("AvisoPrueba");
		EditText desc = (EditText) mActivity.findViewById(R.id.editdesc);
		desc.setText("Prueba Descripcion");
		Button botonAviso = (Button) mActivity.findViewById(R.id.save);
		botonAviso.performClick();

	}

}
