package uspceu.eps.is2.proyecto_avisos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
 
 public class AltaVehiculo extends Activity {
  
     //private EditText txtMessage;
     private EditText id_vehiculo;
   //--  private EditText id_marca;
   //--  private EditText id_modelo;
   //--  private EditText id_color;
     private TextView lab;
     private Button btnShowMessage;
     //private ImageView img; 
     
     @Override
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.form_alta_ve);
         
     
         id_vehiculo = (EditText) findViewById(R.id.id_vehiculo);
              lab= (TextView) findViewById(R.id.label);
            //  lab.setText("Italic, highlighted, bold.", TextView.BufferType.SPANNABLE); 
         //---  id_marca = (EditText) findViewById(R.id.id_marca);
       //---  id_modelo = (EditText) findViewById(R.id.id_modelo);
       //---  id_color = (EditText) findViewById(R.id.id_color);
       //  img= (ImageView)findViewById(R.drawable.meta);
      btnShowMessage = (Button) findViewById(R.id.btn_showMessage);
       
        


         btnShowMessage.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
             /*    String str =  id_vehiculo.getText().toString()+" "+id_modelo.getText().toString()
                 +" "+ id_marca.getText().toString()+ ""+id_color.getText().toString()
                 +
                     "!";*/
                 //Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
                 Vehiculo vehi=new Vehiculo(id_vehiculo.getText().toString(),1);
                		//-- ,id_marca.getText().toString()
                		//-- ,id_modelo.getText().toString(),id_color.getText().toString());
                // Toast.makeText(getBaseContext(),vehi.getMarca(), Toast.LENGTH_LONG).show();  		 
                 guardarVehiculo(vehi);    
                 leerVehiculo();
             }
         });      
     
     }
// }
 
 
 public void guardarVehiculo(Vehiculo a){
	 
		FileOutputStream fos;
		try {
			fos = openFileOutput("Crear_Vehiculo2", Context.MODE_APPEND);
			fos.write((a.toCompleto2()).getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 }//	Toast.makeText(CrearAvisos.this,"Aviso Guardado",Toast.LENGTH_LONG).show();

 public void leerVehiculo(){
	 Vehiculo a;
	 int numlins = -1;
		String aux = "";
		ArrayList<String> stringarray = new ArrayList<String>();

		try {
			InputStreamReader isr = new InputStreamReader(
					openFileInput("Crear_Vehiculo2"));
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
				 a = new Vehiculo(aux);
			 Toast.makeText(getBaseContext(),a.getMarca(), Toast.LENGTH_LONG).show();              
				//al.add(a);
				//aux = "";
			}
		}
	}
 }	//}

 