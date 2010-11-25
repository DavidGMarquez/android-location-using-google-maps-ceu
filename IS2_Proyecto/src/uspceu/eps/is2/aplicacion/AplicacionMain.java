package uspceu.eps.is2.aplicacion;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class AplicacionMain extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        if(fileList().length==0){
        	this.crearArchivo("avisos_guardados");
        }
    }
    
    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.ver_avisos:
        	
            this.startActivity(new Intent().setClass(this, VerAvisos.class));
            return true;
        case R.id.crear_avisos:
        	
        	this.startActivity(new Intent().setClass(this, CrearAvisos.class));
        	return true;
        
        case R.id.alta_vehiculo:
        	this.startActivity(new Intent().setClass(this, AltaVehiculo.class));
        	return true;
        	
        case R.id.mapa:
        	this.startActivity(new Intent().setClass(this, HolaMundo1.class));
        	return true;
        	
      /*case R.id.ID_ACTIVIDAD:
        	this.startActivity(new Intent().setClass(this, NOMBRE_ACTIVIDAD.class));
        	return true;
      */        	
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    /* Crea el archivo con el nombre especificado */
    public void crearArchivo(String name){
    	FileOutputStream fos;
		try {
			fos = openFileOutput(name, Context.MODE_PRIVATE);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
  }