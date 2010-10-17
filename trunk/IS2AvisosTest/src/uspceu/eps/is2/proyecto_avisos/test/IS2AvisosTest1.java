package uspceu.eps.is2.proyecto_avisos.test;

import android.test.ActivityInstrumentationTestCase2;
import uspceu.eps.is2.proyecto_avisos.ProyectoAvisos;
import android.widget.TextView;


public class IS2AvisosTest1 extends ActivityInstrumentationTestCase2<ProyectoAvisos> {
    private ProyectoAvisos mActivity;
    private TextView mView;
    private String resourceString;
    
	public IS2AvisosTest1() {
		super("uspceu.eps.is2.proyecto_avisos", ProyectoAvisos.class);
	}
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = this.getActivity();
        resourceString = mActivity.getString(uspceu.eps.is2.proyecto_avisos.R.string.app_name);
    }
    public void testPreconditions() {
        assertNotNull(mActivity);
      }
    public void testText() {
    	//No se como se hace esto System.out.println(resourceString);
        assertEquals(resourceString,(String)mActivity.getTitle());
      }

}
