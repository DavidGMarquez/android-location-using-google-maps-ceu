package uspceu.eps.is2.aplicacion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;




public class HolaMundo1 extends MapActivity 
{    
	  public static final String TAG = "HolaMundo1";

	    private MapView mapView;
	    private MyLocationOverlay myLocationOverlay;
	    private MapController mapController;

	    
	    /** Called when the activity is first created. */
	    
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);	     
	        getWindow().setFormat(PixelFormat.TRANSPARENT);
	        // Inflate our UI from its XML layout description.
	        setContentView(R.layout.mapa);
	        mapView = (MapView)findViewById(R.id.mapa);

	       /* List<Overlay> mapOverlays = mapView.getOverlays();
		    Drawable drawable = this.getResources().getDrawable(R.drawable.androidmarker);
		    HelloItemizedOverlay itemizedoverlay = new HelloItemizedOverlay(drawable);*/

	        
	        
	        
	       //a�ade mi localizaci�n al mapa
	        myLocationOverlay = new MyLocationOverlay(this, mapView);
	        myLocationOverlay.enableMyLocation();
	        mapView.getOverlays().add(myLocationOverlay);
	     
	        //Poner brujula
	        myLocationOverlay.enableCompass();
	        //Poner botones de zoom
	        mapController = mapView.getController();
	        mapView.setBuiltInZoomControls(true);
	        
	        
	        // CENTRAR PANTALLA, faltar�a poner un layout con el bot�n
	       // GeoPoint puntoc = myLocationOverlay.getMyLocation();
	       // mapController.setCenter(puntoc);
	        
	        
	        
	        mapView.setTraffic(true);
	        
	        ArrayList<Aviso> avisosmostrar = new ArrayList<Aviso>();
	        
	       // cargarAvisos(avisosmostrar);
	         
	       // int LatitudINT = Integer.parseInt(((avisosmostrar.get(1)).getPuntoMapa()).getLatitud());
	       // int LongitudINT = Integer.parseInt(((avisosmostrar.get(1)).getPuntoMapa()).getLongitud());
	        
	        
	      //ejemplo Radar fijo
	        List<Overlay> mapOverlays = mapView.getOverlays();
	        Drawable drawable =	this.getResources().getDrawable(R.drawable.radar);
	        HelloItemizedOverlay itemizedoverlay = new	HelloItemizedOverlay(drawable,this);
	        GeoPoint point = new GeoPoint(40397255,-3842747);
	        OverlayItem overlayitem = new OverlayItem(point, "Radar fijo", "Vel. m�xima 100 km/h");
	        itemizedoverlay.addOverlay(overlayitem);
	        mapOverlays.add(itemizedoverlay);
	        
	       
	        //ejemplo alcoholemia
	        
	        drawable =	this.getResources().getDrawable(R.drawable.alcohol);
	        itemizedoverlay = new	HelloItemizedOverlay(drawable,this);
	        GeoPoint point2 = new GeoPoint(40399395,-3835666);
	        OverlayItem overlayitem2 = new OverlayItem(point2, "Control Alcoholemia", "Sin salida");
	        itemizedoverlay.addOverlay(overlayitem2);
	        mapOverlays.add(itemizedoverlay);
	        
	        //truquito lo haces correr cuando fija su posici�n de otra forma nunca funciona
	        myLocationOverlay.runOnFirstFix(new Runnable() {
	            public void run() {
	                mapController.animateTo(myLocationOverlay.getMyLocation());
	            }
	        });
	        //Fijar el zoom
	        mapController.setZoom(10);

   
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
					Aviso a;
					try {
						a = new Aviso(aux);
						al.add(a);
					} catch (FormatoCoordenadasException e) {
						
					}
					aux = "";
				}
			}
		}
	 
	    @Override
	    protected boolean isRouteDisplayed() {
	        return false;
	    }
	    
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.mapa_menu, menu);
	        return true;
	    }
	    
	   
		
	    @Override
		public boolean onOptionsItemSelected(MenuItem item) {
			// Handle item selection
			switch (item.getItemId()) {
			case R.id.ver_avisos:

				this.startActivity(new Intent().setClass(this, VerAvisos.class));
				return true;
			case R.id.crear_avisos:

				if (myLocationOverlay.getMyLocation() == null)
					Toast.makeText(this, R.string.noubicacion, Toast.LENGTH_LONG)
							.show();
				else {
					Intent intent = new Intent();
					Double lati = new Double((double) myLocationOverlay
							.getMyLocation().getLatitudeE6() / 1000000);
					Double longi = new Double((double) myLocationOverlay
							.getMyLocation().getLongitudeE6() / 1000000);
					intent.putExtra("lat", lati.toString());
					intent.putExtra("lon", longi.toString());
					this.startActivity(intent.setClass(this, CrearAvisos.class));
				}
				return true;

			case R.id.alta_vehiculo:
				this.startActivity(new Intent().setClass(this, AltaVehiculo.class));
				return true;

			case R.id.mapa:
				this.startActivity(new Intent().setClass(this, HolaMundo1.class));
				return true;

			case R.id.localizar:
				if (myLocationOverlay.getMyLocation() == null)
					Toast.makeText(this, R.string.noubicacion, Toast.LENGTH_LONG)
							.show();
				else {
					mapController.animateTo(myLocationOverlay.getMyLocation());
				}
				return true;

			default:
				return super.onOptionsItemSelected(item);
			}
		}	    
}