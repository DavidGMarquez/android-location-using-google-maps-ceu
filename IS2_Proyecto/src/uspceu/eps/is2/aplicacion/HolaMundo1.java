package uspceu.eps.is2.aplicacion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import uspceu.eps.is2.AndroidServer.SerializadorServidor;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class HolaMundo1 extends MapActivity {
	// ???
	public static final String TAG = "HolaMundo1";

	private MapView mapView;
	private MyLocationOverlay myLocationOverlay;
	private MapController mapController;

	private List<Overlay> mapOverlays;
	private ArrayList<Aviso> avisos;
	private HelloItemizedOverlay overlay_avisos;

	// Ids de las ventanas de elegir fecha y hora (para el switch)
	static final int DATEINI_DIALOG_ID = 0;
	static final int DATEFIN_DIALOG_ID = 1;
	static final int HOURINI_DIALOG_ID = 2;
	static final int HOURFIN_DIALOG_ID = 3;

	// Valores actuales de los filtros
	private FiltroFecha filtro_fecha;

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFormat(PixelFormat.TRANSPARENT);
		setContentView(R.layout.mapa);
		mapView = (MapView) findViewById(R.id.mapa);

		mapOverlays = mapView.getOverlays();
		
		
		//Busca la ubicación del usuario
		myLocationOverlay = new MyLocationOverlay(this, mapView);
		myLocationOverlay.enableMyLocation();
		mapOverlays.add(myLocationOverlay);
		myLocationOverlay.enableCompass();
		myLocationOverlay.runOnFirstFix(new Runnable() {
			public void run() {
				mapController.animateTo(myLocationOverlay.getMyLocation());
			}
		});
	

		// Poner botones de zoom y tráfico
		mapController = mapView.getController();
		mapView.setBuiltInZoomControls(true);
		mapView.setTraffic(true);
		mapController.setZoom(10);

		// Inicialización del filtro
		Calendar c = Calendar.getInstance();
		this.filtro_fecha = new FiltroFecha(c);

		// Se cargan TODOS los avisos y la ubicación del usuario
		avisos = cargarAvisos();
		if(avisos!=null)
		{
		overlay_avisos=this.ponerIconosAvisos(avisos, getResources()
				.getDrawable(R.drawable.radar));
		mapOverlays.add(overlay_avisos);
		}
		else{
			Toast.makeText(this, "Servidor no disponible intentelo más tarde", Toast.LENGTH_LONG)
			.show();
		}
	}

	/* Carga los avisos a un array de Avisos */
	public ArrayList<Aviso> cargarAvisos() {
		SerializadorServidor serializadorServidor=new SerializadorServidor();
		return serializadorServidor.obtenerAvisos();
	}

	public ArrayList<Aviso> filtrarAvisosFecha() {
		ArrayList<Aviso> filtrado = new ArrayList<Aviso>();
		for (int i = 0; i < avisos.size(); i++) {
			Aviso a = avisos.get(i);
			Date d = a.getFechacreacion();
			if (filtro_fecha.isFechaValida(d)) {
				filtrado.add(a);
			}
		}
		return filtrado;
	}

	public HelloItemizedOverlay ponerIconosAvisos(ArrayList<Aviso> al,
			Drawable imagen) {
		HelloItemizedOverlay itemizedoverlay = new HelloItemizedOverlay(imagen,
				this);
		for (int i = 0; i < al.size(); i++) {
			Aviso a = al.get(i);
			GeoPoint point = new GeoPoint(a.getPuntoMapa().latitudE6(), a
					.getPuntoMapa().longitudE6());
			OverlayItem overlayitem = new OverlayItem(point,
					a.getNombreAviso(), a.toMostrar());
			itemizedoverlay.addOverlay(overlayitem);
		}
		return itemizedoverlay;
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
			 this.startActivity(new Intent().setClass(this,AltaVehiculo.class));
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
				if (!mapOverlays.contains(myLocationOverlay))
					mapOverlays.add(myLocationOverlay);
			}
			return true;

		case R.id.filtrofecha:
			showDialog(DATEINI_DIALOG_ID);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// Se actualizan los valores de año, mes y día a los que ha dicho el usuario
	private DatePickerDialog.OnDateSetListener iniDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			filtro_fecha.setFechaIni(year, monthOfYear, dayOfMonth);
			filtro_fecha.setFiltroactivado(true);
			showDialog(HOURINI_DIALOG_ID);
		}
	};

	// Se actualizan los valores de año, mes y día a los que ha dicho el usuario
	private DatePickerDialog.OnDateSetListener finDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			filtro_fecha.setFechaFin(year, monthOfYear, dayOfMonth);
			showDialog(HOURFIN_DIALOG_ID);
		}
	};

	private TimePickerDialog.OnTimeSetListener iniTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			filtro_fecha.setHoraIni(hourOfDay, minute);
			showDialog(DATEFIN_DIALOG_ID);
		}
	};

	private TimePickerDialog.OnTimeSetListener finTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			filtro_fecha.setHoraFin(hourOfDay, minute);
			mapOverlays.remove(overlay_avisos);
			overlay_avisos=ponerIconosAvisos(filtrarAvisosFecha(), getResources().getDrawable(R.drawable.radar));
			mapOverlays.add(overlay_avisos);
		}
	};

	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog t = null;
		switch (id) {
		case DATEINI_DIALOG_ID:
			t = new DatePickerDialog(this, iniDateSetListener, filtro_fecha
					.getAnioini(), filtro_fecha.getMesini(), filtro_fecha
					.getDiaini());
			t.setTitle(R.string.filtro_fechaini);
			break;

		case DATEFIN_DIALOG_ID:
			t = new DatePickerDialog(this, finDateSetListener, filtro_fecha
					.getAniofin(), filtro_fecha.getMesfin(), filtro_fecha
					.getDiafin());
			t.setTitle(R.string.filtro_fechafin);
			break;

		case HOURINI_DIALOG_ID:
			t = new TimePickerDialog(this, iniTimeSetListener, filtro_fecha
					.getHoraini(), filtro_fecha.getMinutoini(), true);
			t.setTitle(R.string.filtro_horaini);
			break;

		case HOURFIN_DIALOG_ID:
			t = new TimePickerDialog(this, finTimeSetListener, filtro_fecha
					.getHorafin(), filtro_fecha.getMinutofin(), true);
			t.setTitle(R.string.filtro_horafin);
			break;
		}
		return t;
	}
}
