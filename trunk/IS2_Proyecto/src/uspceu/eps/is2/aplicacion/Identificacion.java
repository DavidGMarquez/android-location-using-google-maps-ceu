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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Identificacion extends AplicacionMain {
	  
    private EditText user;
    private EditText passwd;
    private Button btnShowMessage;
    final ArrayList<Usuario> listaUsus = new ArrayList<Usuario>();
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_identificar_usuario);
        
        cargarUsuarios(listaUsus);
        //cargarUsuariosPredefinidos();
        
        //avisa si no existen usuarios almacenados
       /* if(listaUsus.isEmpty()){
    		Toast.makeText(Identificacion.this, R.string.nousuarios,Toast.LENGTH_LONG).show();
    	}*/
        
        user = (EditText) findViewById(R.id.id_usu); 
        passwd = (EditText) findViewById(R.id.id_pass); 
        btnShowMessage = (Button) findViewById(R.id.btn_showMessage);
        btnShowMessage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                                
            	//if((user.getText().toString()!= null)&&(user.getText().toString().equals(""))){
                //creo un usuario con los datos recibidos
            	   Usuario usu=new Usuario(user.getText().toString(),passwd.getText().toString());
            	   
            	   
            	   if(!usu.esUsuIncorrecto()){
            		   guardarUsuario(usu);
            	       /*redirección a la clase "ProyectoMain" tras la identificación del usuario*/
            	       Intent intent =new Intent();
                       intent.setClass (Identificacion.this, AplicacionMain.class);
                       startActivity(intent);
                       finish();}
               else{
            	   Toast.makeText(Identificacion.this, R.string.nousuarios, Toast.LENGTH_LONG).show();
               }
               
                    
            }
        });      
    
    }
    
    public void guardarUsuario(Usuario u){
		FileOutputStream fos;
		try {
			fos = openFileOutput("usuarios_guardado", Context.MODE_APPEND);
			fos.write((u.getNombre()+" "+u.getPassword()+"\n").getBytes());
			fos.close();
			
			String s = "usuario: "+u.getNombre().toString()+"\npassword: "+ u.getPassword().toString() + "\nalmacenado!";
			Toast.makeText(Identificacion.this,s,Toast.LENGTH_LONG).show();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Toast.makeText(Identificacion.this,"Usuario Guardado",Toast.LENGTH_LONG).show();
	}
    /*
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
				stringarray.add(aux + '\n');
	            //Toast.makeText(getBaseContext(),aux, Toast.LENGTH_LONG).show();
					Usuario u = new Usuario("","");
					int ini=0, fin=0;
					
					u.setNombre(aux);
					ini=aux.indexOf(" ",0)+1;
					fin=aux.indexOf("\n",ini);
					u.setNombre(aux.substring(0,ini));
					u.setPassword(aux.substring(ini,fin));	            
					
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

	}*/
    
    public void cargarUsuarios(ArrayList<Usuario> u) {

		int numlins = -1;
		String aux = "";
		ArrayList<String> stringarray = new ArrayList<String>();
		
		
		/* Lee los Avisos del fichero */
		try {
			InputStreamReader isr = new InputStreamReader(
					openFileInput("usuarios_guardado"));
			BufferedReader br = new BufferedReader(isr);
			
			do {
				numlins++;
				aux = br.readLine();
				if (aux != null)
					stringarray.add(aux + '\n');
					Toast.makeText(getBaseContext(),aux, Toast.LENGTH_LONG).show();
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
				Usuario usu = new Usuario(aux);
				u.add(usu);
				aux = "";
			}
		}
	}
    
private void cargarUsuariosPredefinidos() {
		
		Usuario u1 = new Usuario("Kik","10");
		Usuario u2 = new Usuario("Dolfi","11");
		Usuario u3 = new Usuario("Roman","18");
		Usuario u4 = new Usuario("Caye","13");
		Usuario u5 = new Usuario("Cohe","30");
		
		
		FileOutputStream fos;
		try {
			fos = openFileOutput("usuarios_guardados", Context.MODE_PRIVATE);
			fos.write(("Usuario: " + u1.getNombre() + "\t" + u1.getPassword()
					+ "\n" + u2.getNombre() + "\t" + u2.getPassword() + "\n"
					+ u3.getNombre() + "\t" + u3.getPassword() + "\n" + u4.getNombre()
					+ "\t" + u4.getPassword() + "\n" + u5.getNombre()
					+ "\t" + u5.getPassword() + "\n").getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
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