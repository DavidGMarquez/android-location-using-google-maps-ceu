package uspceu.eps.is2.aplicacion;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class AplicacionMain extends Activity {
	protected boolean registrado;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView tv=(TextView) findViewById(R.id.ppal);
        registrado=!(this.getEmailUsuario().equals(""));
        
        if (registrado==true)
        	tv.append("\nBienvenido, "+this.getEmailUsuario());
        else {
        	Toast.makeText(AplicacionMain.this,R.string.sinregistrar,Toast.LENGTH_LONG).show();
        	this.finish();
        }
        
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
    public boolean onPrepareOptionsMenu(Menu menu)
    {   super.onPrepareOptionsMenu(menu);
    	if(!registrado)
    		menu.clear();
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {	
        case R.id.mapa:
    		ProgressDialog dialog = ProgressDialog.show(AplicacionMain.this, "", 
                    "Loading. Please wait...", true);
        	this.startActivity(new Intent().setClass(this, HolaMundo1.class));
        	return true;   	
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
    
    public String getEmailUsuario(){
    	String usuario="";
    	Account[] accounts=AccountManager.get(this).getAccountsByType("com.google");
    	if(accounts.length>0)
    		usuario=accounts[0].name;
    	return usuario;
    }
    
  }