package uspceu.eps.is2.aplicacion.test;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import uspceu.eps.is2.aplicacion.AplicacionMain;
import uspceu.eps.is2.aplicacion.R;
import android.widget.TabHost;
import android.widget.TextView;


public class IS2AvisosTest1 extends ActivityInstrumentationTestCase2<AplicacionMain> {
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
        Log.d(TAG,"setUp");
        //Para que no de problemas al depurar en telefono
        setActivityInitialTouchMode(false);       
        
        //Aqui necesitamos algo para borrar la información del telefono, un clear data.
        
        mActivity = this.getActivity();
        resourceString = mActivity.getString(uspceu.eps.is2.aplicacion.R.string.app_name);
    }
    public void testPreconditions() {
        assertNotNull(mActivity);
      }
    public void testAplicationName() {
    	//No se como se hace esto System.out.println(resourceString);
    	Log.d(TAG,"AplicationName");
    	Log.d(TAG,(String)mActivity.getTitle());
        assertEquals(resourceString,(String)mActivity.getTitle());
      }

}
