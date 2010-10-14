package uspceu.eps.is2.proyecto_avisos;

import java.util.ArrayList;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.ListActivity;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TabHost.TabSpec;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import android.content.Context;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ProyectoAvisos extends ListActivity {
	
	// Contenedor de tabs
	private TabHost tabHost;
	// Lista de avisos
	// De momento se actualiza al iniciar la aplicación y al guardar un nuevo aviso
	private ListView lv;
	
	// Usuario que está usando la aplicación
	// De momento no se usa
	private Usuario usuario;
	
	// Formulario de CrearAviso
	private FormularioCrearAviso formulario;
	
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        if(fileList().length==0){
        	this.crearArchivo("avisos_guardados");
        }
        
		/* Carga los avisos para construir la lista */
		this.lv=getListView();
		this.cargarLista(this.lv);
        
        /* Crea el formulario de Crear Avisos */
		this.formulario=new FormularioCrearAviso();
        this.hacerFormularioCrearAviso();
		
        /* Crea las pestañas */
        tabHost=(TabHost)findViewById(R.id.tabhost);
        tabHost.setup();

        TabSpec spec=tabHost.newTabSpec("Crear Avisos");
        spec.setIndicator("Crear Avisos",getResources().getDrawable(R.drawable.ic_tab_crear));
        spec.setContent(R.id.formcrear);
        tabHost.addTab(spec);
        
        spec=tabHost.newTabSpec("Ver Avisos");
        spec.setIndicator("Ver Avisos",getResources().getDrawable(R.drawable.ic_tab_ver));
       	spec.setContent(lv.getId());
        tabHost.addTab(spec);

        /* Para crear una nueva pestaña: */
//        spec=tabHost.newTabSpec("NombreTab");
//        spec.setIndicator("Nombre Tab",getResources().getDrawable(R.drawable.ic_tab_nombretab));
//        spec.setContent(R.id.iddellayoutdeltab);
//        tabHost.addTab(spec);
        
        /* Pestaña que se visualizará al iniciar la aplicación */
        tabHost.setCurrentTab(1);
        
    }
    
    private void cargarLista(ListView lv) {

    	final ArrayList<Aviso> avisoarray = new ArrayList<Aviso>();
    	this.cargarAvisos(avisoarray);

    	if(avisoarray.isEmpty()){
    		Toast.makeText(ProyectoAvisos.this, R.string.noavisos,Toast.LENGTH_LONG).show();
    	}
    	
		setListAdapter(new ArrayAdapter<Aviso>(this, R.layout.vista_lista,avisoarray));
		this.lv.setTextFilterEnabled(true);
		this.lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Aviso a = avisoarray.get((int) id);
				Toast.makeText(ProyectoAvisos.this, a.toMostrar(),Toast.LENGTH_LONG).show();
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
    
    /* Crea un formulario de Creación de Aviso genérico
     * En caso de querer fijar un valor (usuario, coordenadas), existen métodos de la clase
     *  FormularioCrearAviso para ello
     */
    public void hacerFormularioCrearAviso()
    {        
        this.formulario.setEditname((EditText) findViewById(R.id.editname));
        this.formulario.setEditdesc((EditText) findViewById(R.id.editdesc));
        this.formulario.setButton((Button) findViewById(R.id.save));
        
		this.formulario.getButton().setOnClickListener (new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				/* Cambiar por el Usuario registrado o UsuarioAnonimo... */
				Usuario usu=new Usuario("Anonimo");

				/* Cambiar por las coordenadas que correspondan...*/
				PuntoMapa pm=new PuntoMapa("40.383333", "-3.716667");
				
				/* Crear aviso a partir de lo introducido en el formulario */
				Aviso a=formulario.obtenerDatosFormulario(usu, pm);
				guardarAviso(a);
				
				/* Pone los campos en blanco (quitar??) */
				formulario.vaciarCampos();
				
			}
		});
    }

	
	/* Guarda el aviso creado */
    public void guardarAviso(Aviso a){
    	FileOutputStream fos;
    	try {
    		fos = openFileOutput("avisos_guardados", Context.MODE_APPEND);
    		fos.write((a.toCompleto()+"\n").getBytes());
    		fos.close();
    	} catch (FileNotFoundException e) {
    		e.printStackTrace();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	Toast.makeText(ProyectoAvisos.this,"Aviso Guardado",Toast.LENGTH_LONG).show();
    	this.cargarLista(lv);
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