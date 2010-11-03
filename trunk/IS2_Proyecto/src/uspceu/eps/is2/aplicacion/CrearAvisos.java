package uspceu.eps.is2.aplicacion;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CrearAvisos extends AplicacionMain {
	
	private FormularioCrearAviso form;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.form_crearaviso);
		
		 /* Crea el formulario de Crear Avisos */
		this.form=new FormularioCrearAviso();
        this.hacerFormularioCrearAviso();

	}
	
	public void hacerFormularioCrearAviso()
    {        
		
        this.form.setEditname((EditText) findViewById(R.id.editname));
        this.form.setEditdesc((EditText) findViewById(R.id.editdesc));
        this.form.setButton((Button) findViewById(R.id.save));
        
		this.form.getButton().setOnClickListener (new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				/* Cambiar por el Usuario registrado o UsuarioAnonimo... */
				Usuario usu=new Usuario("Anonimo");

				/* Cambiar por las coordenadas que correspondan...*/
				PuntoMapa pm=new PuntoMapa("40.383333", "-3.716667");
				
				/* Crear aviso a partir de lo introducido en el formulario */
				Aviso a=form.obtenerDatosFormulario(usu, pm);
				if(!form.nombreVacio())
					guardarAviso(a);
				else{
					Toast.makeText(CrearAvisos.this,"Introduzca el nombre del aviso",Toast.LENGTH_LONG).show();
				}
				
				/* Pone los campos en blanco (quitar??) */
				form.vaciarCampos();
				
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
    	Toast.makeText(CrearAvisos.this,"Aviso Guardado",Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
