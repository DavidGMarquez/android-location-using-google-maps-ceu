package uspceu.eps.is2.proyecto;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class ProyectoMain extends TabActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, CrearAvisos.class);

		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost.newTabSpec("crearavisos").setIndicator("Crear Aviso",
				res.getDrawable(R.drawable.ic_tab_crear)).setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, VerAvisos.class);
		spec = tabHost.newTabSpec("veravisos").setIndicator("Ver Avisos",
				res.getDrawable(R.drawable.ic_tab_ver)).setContent(intent);
		
		tabHost.addTab(spec);
		tabHost.setCurrentTab(1);
		
		
		//Carga una serie de avisos en el archivo avisos_guardados
		if(fileList().length==0)
			this.cargarDatos();
			

	}

	public void cargarDatos() {
		
		// Se crean los avisos con un usuario y coordenadas
		Usuario cayette = new Usuario("Cayette");
		PuntoMapa madrid = new PuntoMapa("40.383333", "-3.716667");

		Aviso nuevoaviso = new Aviso("RetencionM40", "Atasco",
				"No se puede entrar en el CEU, como siempre", cayette, madrid);
		Aviso nuevoaviso2 = new Aviso("RadarMovil", "Radar",
				"En el km 40 de la M40!!!", cayette, madrid);
		Aviso nuevoaviso3 = new Aviso("AtencionBorrachos",
				"ControlAlcoholemia", "Si bebes no conduzcas", cayette, madrid);
		Aviso nuevoaviso4 = new Aviso("OtroRadar", "Radar",
				"Que pesados con los controles de velocidad!", cayette, madrid);
		Aviso nuevoaviso5 = new Aviso("AccidenteA6", "Atasco",
				"Ya esta controlado asi que no os pareis a mirar como tontos",
				cayette, madrid);
		Aviso nuevoaviso6 = new Aviso("AccesoM40 Cortado", "Obras",
				"Pero es que nunca van a acabar las obras??", cayette, madrid);

		
		// Se guardan los avisos en el archivo avisos_guardados
		FileOutputStream fos;
		try {
			fos = openFileOutput("avisos_guardados", Context.MODE_PRIVATE);
			fos.write((nuevoaviso.toCompleto() + "\n" + nuevoaviso2.toCompleto()
					+ "\n" + nuevoaviso3.toCompleto() + "\n"
					+ nuevoaviso4.toCompleto() + "\n" + nuevoaviso5.toCompleto()
					+ "\n" + nuevoaviso6.toCompleto() + "\n").getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}