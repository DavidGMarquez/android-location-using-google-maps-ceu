package uspceu.eps.is2.proyecto_avisos.test;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import uspceu.eps.is2.proyecto_avisos.ProyectoAvisos;
import uspceu.eps.is2.proyecto_avisos.R;
import android.widget.TabHost;
import android.widget.TextView;


public class IS2AvisosTest1 extends ActivityInstrumentationTestCase2<ProyectoAvisos> {
    private ProyectoAvisos mActivity;
    private TabHost mTabHost;
    private String resourceString;
    private static final String TAG = "IS2Avisos";
	public IS2AvisosTest1() {
		super("uspceu.eps.is2.proyecto_avisos", ProyectoAvisos.class);
	}
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Log.d(TAG,"setUp");
        //Para que no de problemas al depurar en telefono
        setActivityInitialTouchMode(false);       
        
        //Aqui necesitamos algo para borrar la información del telefono, un clear data.
        
        mActivity = this.getActivity();
        resourceString = mActivity.getString(uspceu.eps.is2.proyecto_avisos.R.string.app_name);
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
    public void testTabHost(){
    	mTabHost =(TabHost) mActivity.findViewById(uspceu.eps.is2.proyecto_avisos.R.id.tabhost);
    	assertTrue(mTabHost!=null);
    	Log.d(TAG,"TabHost");
    	Log.d(TAG,(mTabHost.toString()));
    }    
    
    public void testTabHost1(){
    	//Esto debería de funcionar pero no lo consigo    	
    	//assertEquals(mTabHost.getCurrentTabTag(),"Crear Avisos");
    	
    	mTabHost.setCurrentTab(1);
    }
}
