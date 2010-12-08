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
        this.form.setEditlat((EditText) findViewById(R.id.editlat));
        this.form.setEditlon((EditText) findViewById(R.id.editlon));
        this.form.setButton((Button) findViewById(R.id.save));
        
        /* Cambiar por las coordenadas que correspondan...*/
		//PuntoMapa pm=new PuntoMapa("40.383333", "-3.716667");
		//form.defaultCoordenadas(pm);
		
		this.form.getButton().setOnClickListener (new OnClickListener() {
			public void onClick(View v) {
				
				/* Cambiar por el Usuario registrado o UsuarioAnonimo... */
				Usuario usu=new Usuario(getEmailUsuario());

				
				
				/* Crear aviso a partir de lo introducido en el formulario */
				Aviso a;
				try {
					a = form.obtenerDatosFormulario(usu);
					guardarAviso(a);
					
					/* Pone los campos en blanco (quitar??) */
					form.vaciarCampos();
				} catch (NombreAvisoException e) {
					Toast.makeText(CrearAvisos.this,"Introduzca el nombre del aviso",Toast.LENGTH_LONG).show();
				} catch (FormatoCoordenadasException e) {
					Toast.makeText(CrearAvisos.this,"Formato de las coordenadas incorrecto\nCompruebe que están en formato decimal (p ej: 40.38333)",Toast.LENGTH_LONG).show();
				}
				
				
				
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
