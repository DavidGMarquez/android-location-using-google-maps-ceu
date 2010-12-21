package uspceu.eps.is2.aplicacion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class VerAvisos extends ListActivity {

	private ListView lv;
	private ArrayList<Aviso> avisoarray;
	private Context lContext;

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
		lContext = this;

		// Pone las fechas del filtro por defecto a la fecha actual
		Calendar c = Calendar.getInstance();
		this.filtro_fecha = new FiltroFecha(c);

		Bundle extras = getIntent().getExtras();
		avisoarray = (ArrayList<Aviso>) extras.get("avis");

		setContentView(R.layout.lista_avisos);
		this.lv = getListView();
		lv.setTextFilterEnabled(true);
		this.cargarLista(lv);

	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		this.cargarLista(lv);
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.lista_menu, menu);
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

			this.startActivity(new Intent().setClass(this, CrearAvisos.class));
			return true;

		case R.id.alta_vehiculo:
			this.startActivity(new Intent().setClass(this, AltaVehiculo.class));
			return true;

		case R.id.mapa:
			ProgressDialog dialog = ProgressDialog.show(VerAvisos.this, "",
					"Cargando avisos. Espere un momento, por favor...", true);
			this.startActivity(new Intent().setClass(this, HolaMundo1.class));
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
			cargarLista(lv);
		}
	};

	private void cargarLista(ListView lv) {
		ArrayList<Aviso> avisoarray_aux = new ArrayList<Aviso>();
		if (avisoarray.isEmpty()) {
			Toast
					.makeText(VerAvisos.this, R.string.noavisos,
							Toast.LENGTH_LONG).show();
		} else {
			if (filtro_fecha.isFiltroactivado() == true) {
				for (int i = 0; i < avisoarray.size(); i++) {
					Aviso av = (Aviso) avisoarray.get(i);
					Date d = av.getFechacreacion();
					if (filtro_fecha.isFechaValida(d))
						avisoarray_aux.add(av);
				}

			} else {
				avisoarray_aux = avisoarray;
			}

		}
		final ArrayList<Aviso> avisoarray2 = avisoarray_aux;
		setListAdapter(new ArrayAdapter<Aviso>(this, R.layout.vista_lista,
				avisoarray2));
		this.lv.setTextFilterEnabled(true);
		this.lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Aviso a = avisoarray2.get((int) id);
				AlertDialog.Builder dialog = new AlertDialog.Builder(lContext);
				dialog.setTitle(a.getNombreAviso());
				dialog.setMessage(a.toMostrar());
				dialog.show();
			}
		});

	}

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
