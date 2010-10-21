package uspceu.eps.is2.aplicacion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Identificacion extends Activity {
	  
    private EditText user;
    private EditText passwd;
    private Button btnShowMessage;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_identificar_usuario);
        
        user = (EditText) findViewById(R.id.id_usu); 
        passwd = (EditText) findViewById(R.id.id_pass); 
        btnShowMessage = (Button) findViewById(R.id.btn_showMessage);
        btnShowMessage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String str = "usuario: "+user.getText().toString()+"\npassword: "+ passwd.getText() + "\nIdentificado!";
                Toast.makeText(Identificacion.this, str, Toast.LENGTH_LONG).show();
                
                //creo un usuario con los datos recibidos
               Usuario usu=new Usuario(user.getText().toString(),passwd.getText().toString());
                
               guardarUsuario(usu);
               
               
              /* 
               ArrayList<Usuario> listaUsus = new ArrayList<Usuario>();
               getListaUsuarios(listaUsus);
               Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
               Toast.makeText(getBaseContext(), listaUsus.get(0).toCompleto(),
						Toast.LENGTH_LONG).show();
               */
               
               
            //String a="YEEEEAH!!!"+listaUsus.get(1).getNombre().toString()+ " oooo "+listaUsus.get(1).getPassword().toString();
            //Toast.makeText(getBaseContext(), a, Toast.LENGTH_LONG).show();
                
          /*
           * 
                 redirección a la clase "ProyectoAvisos" tras la identificación del usuario*/
           
                Intent intent =new Intent();
               intent.setClass (Identificacion.this, AplicacionMain.class);
                startActivity(intent);
              finish();
           
                
           
                
            }
        });      
    
    }
    
    public void guardarUsuario(Usuario u){
		FileOutputStream fos;
		try {
			fos = openFileOutput("usuarios_guardados", Context.MODE_APPEND);
			fos.write((u.getNombre()+"\n").getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Toast.makeText(Identificacion.this,"Usuario Guardado",Toast.LENGTH_LONG).show();
	}
    
    public void getListaUsuarios(ArrayList<Usuario> usu) {

		int numlins = -1;
		String aux = "";
		ArrayList<String> stringarray = new ArrayList<String>();

		try {
			InputStreamReader isr = new InputStreamReader(
					openFileInput("usuarios_registrados"));
			BufferedReader br = new BufferedReader(isr);
			do {
				numlins++;
				aux = br.readLine();
				if (aux != null){
				//	stringarray.add(aux + '\n');
	            //Toast.makeText(getBaseContext(),aux, Toast.LENGTH_LONG).show();
					Usuario u = new Usuario("","");
					int ini=0, fin=0;
					
					u.setNombre(aux);
				/*	ini=aux.indexOf(" ",0)+1;
					fin=aux.indexOf("\n",ini);
					u.setNombre(aux.substring(0,ini));
					u.setPassword(aux.substring(ini,fin));	            
					*/
					usu.add(u);
					
				}
				
			} while (aux != null);
			br.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int i = 0, ai = 0;
		aux = "";
		for (i = 0; i < numlins; i++) {
			aux += stringarray.get(i);
			if ((i + 1) % 8 == 0) {
				ai++;
				Usuario a = new Usuario(aux);
				usu.add(a);
				aux = "";
			}
		}

	}
}