package uspceu.eps.is2.aplicacion;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class VerAvisos extends ListActivity {
	
	private ListView lv;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_avisos);
        this.lv=getListView();
        lv.setTextFilterEnabled(true);
        this.cargarLista(lv);
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
    	 this.cargarLista(lv);
    };
    
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
        	
      /*case R.id.ID_ACTIVIDAD:
        	this.startActivity(new Intent().setClass(this, NOMBRE_ACTIVIDAD.class));
        	return true;
      */        	
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    
    private void cargarLista(ListView lv) {

    	final ArrayList<Aviso> avisoarray = new ArrayList<Aviso>();
    	this.cargarAvisos(avisoarray);

    	if(avisoarray.isEmpty()){
    		Toast.makeText(VerAvisos.this, R.string.noavisos,Toast.LENGTH_LONG).show();
    	}
    	
		setListAdapter(new ArrayAdapter<Aviso>(this, R.layout.vista_lista,avisoarray));
		this.lv.setTextFilterEnabled(true);
		this.lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Aviso a = avisoarray.get((int) id);
				Toast.makeText(VerAvisos.this, a.toMostrar(),Toast.LENGTH_LONG).show();
			}
		});
    	
	}
    
    /* Carga los avisos a un array de Avisos */
    public void cargarAvisos(ArrayList<Aviso> al) {

		int numlins = -1;
		String aux = "";
		ArrayList<String> stringarray = new ArrayList<String>();

		/* Lee los Avisos del fichero */
		try {
			InputStreamReader isr = new InputStreamReader(
					openFileInput("avisos_guardados"));
			BufferedReader br = new BufferedReader(isr);
			do {
				numlins++;
				aux = br.readLine();
				if (aux != null)
					stringarray.add(aux + '\n');
			} while (aux != null);
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		/* Guarda los Avisos en el array */
		int i = 0, ai = 0;
		aux = "";
		for (i = 0; i < numlins; i++) {
			aux += stringarray.get(i);
			if ((i + 1) % 8 == 0) {
				ai++;
				Aviso a = new Aviso(aux);
				al.add(a);
				aux = "";
			}
		}
	}
    
    
    
    
}
