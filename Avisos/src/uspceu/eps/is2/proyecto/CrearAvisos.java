package uspceu.eps.is2.proyecto;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CrearAvisos extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.crear_form);

		final Spinner spinner = (Spinner) findViewById(R.id.spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.tipo_array, android.R.layout.simple_spinner_item);
		adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		
		final EditText editname = (EditText) findViewById(R.id.editname);
		final EditText editdesc = (EditText) findViewById(R.id.editdesc);
		final EditText edituser = (EditText) findViewById(R.id.edituser);
		final EditText editlat = (EditText) findViewById(R.id.editlat);
		final EditText editlon = (EditText) findViewById(R.id.editlon);
		Button bt=(Button) findViewById(R.id.save);

		OnKeyListener okl = new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// If the event is a key-down event on the "enter" button
				if ((event.getAction() == KeyEvent.ACTION_DOWN)
						&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
					// Perform action on key press
					Toast.makeText(CrearAvisos.this,
							"No se permiten saltos de linea",
							Toast.LENGTH_SHORT).show();
					return true;}return false;}};
					
		editname.setOnKeyListener(okl);
		editdesc.setOnKeyListener(okl);
		editlat.setOnKeyListener(okl);
		editlon.setOnKeyListener(okl);
		edituser.setOnKeyListener(okl);
		
		bt.setOnClickListener (new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Usuario usu=new Usuario(edituser.getText().toString());
				PuntoMapa pm=new PuntoMapa(editlat.getText().toString(),editlon.getText().toString());
				Aviso a=new Aviso(editname.getText().toString(),spinner.getSelectedItem().toString()
						,editdesc.getText().toString(),usu,pm);
				guardarAviso(a);
				
			}
		});

	}
public void guardarAviso(Aviso a){
	FileOutputStream fos;
	try {
		fos = openFileOutput("avisos_guardados", Context.MODE_APPEND);
		fos.write((a.toCompleto()+"\n").getBytes());
		fos.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Toast.makeText(CrearAvisos.this,"Aviso Guardado",Toast.LENGTH_LONG).show();
}


}