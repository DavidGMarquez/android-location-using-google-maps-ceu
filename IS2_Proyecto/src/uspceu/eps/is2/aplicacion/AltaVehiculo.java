package uspceu.eps.is2.aplicacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
 
 public class AltaVehiculo extends AplicacionMain {
  
     //private EditText txtMessage;
     private EditText id_vehiculo;
     private EditText id_matricula;
   //--  private EditText id_marca;
   //--  private EditText id_modelo;
   //--  private EditText id_color;
     
     private TextView lab;
     private Button btnShowMessage;
     //private ImageView img; 
     
     @Override
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.form_altavehiculo);
     
         id_vehiculo = (EditText) findViewById(R.id.id_vehiculo);
         id_matricula = (EditText) findViewById(R.id.id_matricula);
       
         lab= (TextView) findViewById(R.id.label);
            //  lab.setText("Italic, highlighted, bold.", TextView.BufferType.SPANNABLE); 
         //---  id_marca = (EditText) findViewById(R.id.id_marca);
       //---  id_modelo = (EditText) findViewById(R.id.id_modelo);
       //---  id_color = (EditText) findViewById(R.id.id_color);
       //  img= (ImageView)findViewById(R.drawable.meta);
      btnShowMessage = (Button) findViewById(R.id.btn_showMessage);
       
      File file = getBaseContext().getFileStreamPath("Vehiculo");
      if(file.exists())
      {
  	    String stringVehiculo=leerVehiculo();
 		Vehiculo vehiculo;
		try {
			vehiculo = new Vehiculo(stringVehiculo);
			  Toast.makeText(getBaseContext(),"Ya existe un vehículo si le da a guardar se actualizaran los datos \n"+vehiculo.toString(), Toast.LENGTH_LONG).show();
		} catch (MatriculaVehiculoException e) {
			// TODO Auto-generated catch block
			Toast.makeText(getBaseContext(),"Matricula incorrecta del vehículo guardado, actualice los datos", Toast.LENGTH_LONG).show();
		}
    	
      }


      btnShowMessage.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
        	  Vehiculo vehi;
        	  
			try {
				vehi = new Vehiculo(id_vehiculo.getText().toString(), id_matricula.getText().toString());
	              guardarVehiculo(vehi);    
	              String stringVehiculo=leerVehiculo();
	      		Vehiculo vehiculo=new Vehiculo(stringVehiculo);
				 Toast.makeText(getBaseContext(),"Actualizado!!!\n"+vehiculo.toString(), Toast.LENGTH_LONG).show();

			} catch (MatriculaVehiculoException e) {
				// TODO Auto-generated catch block
				Toast.makeText(getBaseContext(),"Matricula incorrecta, introduzca formato válido", Toast.LENGTH_LONG).show();
			}
         	   

          
          }
      });   
     
     }

 
 
 public void guardarVehiculo(Vehiculo vehiculo){
	 
		FileOutputStream fos;
		try {
			deleteFile("Vehiculo");
			fos = openFileOutput("Vehiculo", Context.MODE_APPEND);
			fos.write((vehiculo.toStringDescMatri()).getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 }

 public String leerVehiculo(){
	 String stringVehiculo=new String();
	 int numlins = -1;
		String aux = "";
		ArrayList<String> stringarray = new ArrayList<String>();
		try {
			InputStreamReader isr = new InputStreamReader(
					openFileInput("Vehiculo"));
			BufferedReader br = new BufferedReader(isr);
			do {
				numlins++;
				aux = br.readLine();
				if (aux != null){
					stringarray.add(aux + '\n');
				stringVehiculo=stringVehiculo.concat(aux);}              
			} while (aux != null);
			br.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}              	
		return stringVehiculo;
	}
 
 @Override
 public boolean onCreateOptionsMenu(Menu menu) {
     return super.onCreateOptionsMenu(menu);
 }
 
 @Override
 public boolean onOptionsItemSelected(MenuItem item) {
     return super.onOptionsItemSelected(item);
 }
 }	//}

 