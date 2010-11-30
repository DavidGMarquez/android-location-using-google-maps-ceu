package uspceu.eps.is2.aplicacion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.TimePickerDialog;
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

	// Ids de las ventanas de elegir fecha y hora (para el switch)
	static final int DATEINI_DIALOG_ID = 0;
	static final int DATEFIN_DIALOG_ID = 1;
	static final int HOURINI_DIALOG_ID = 2;
	static final int HOURFIN_DIALOG_ID = 3;

	// Valores actuales de los filtros
	private int anioini, aniofin;
	private int mesini, mesfin;
	private int diaini, diafin;
	private int horaini, horafin;
	private int minutoini, minutofin;

	private boolean filtrofecha;

	static final int USER_DIALOG_ID = 4;
	private String usu;
	private boolean filtrousuario;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_avisos);
		this.lv = getListView();
		lv.setTextFilterEnabled(true);
		this.cargarLista(lv);

		// Pone las fechas del filtro por defecto a la fecha actual
		Calendar c = Calendar.getInstance();
		this.anioini = c.get(Calendar.YEAR);
		this.mesini = c.get(Calendar.MONTH);
		this.diaini = c.get(Calendar.DATE);
		this.horaini = c.get(Calendar.HOUR_OF_DAY);
		this.minutoini = c.get(Calendar.MINUTE);
		this.aniofin = this.anioini;
		this.mesfin = this.mesini;
		this.diafin = this.diaini;
		this.horafin = this.horaini;
		this.minutofin = this.minutoini;

		this.filtrofecha = false;

		usu = "";
		this.filtrousuario = false;
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
			this.startActivity(new Intent().setClass(this, HolaMundo1.class));
			return true;

		case R.id.filtrofecha:
			showDialog(DATEINI_DIALOG_ID);
			return true;

			/*
			 * case R.id.ID_ACTIVIDAD: this.startActivity(new
			 * Intent().setClass(this, NOMBRE_ACTIVIDAD.class)); return true;
			 */
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// Se actualizan los valores de año, mes y día a los que ha dicho el usuario
	private DatePickerDialog.OnDateSetListener iniDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			anioini = year;
			mesini = monthOfYear;
			diaini = dayOfMonth;
			filtrofecha = true;
			showDialog(HOURINI_DIALOG_ID);
		}
	};

	// Se actualizan los valores de año, mes y día a los que ha dicho el usuario
	private DatePickerDialog.OnDateSetListener finDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			aniofin = year;
			mesfin = monthOfYear;
			diafin = dayOfMonth;
			showDialog(HOURFIN_DIALOG_ID);
		}
	};

	private TimePickerDialog.OnTimeSetListener iniTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			horaini = hourOfDay;
			minutoini = minute;
			showDialog(DATEFIN_DIALOG_ID);
		}
	};

	private TimePickerDialog.OnTimeSetListener finTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			horafin = hourOfDay;
			minutofin = minute;
			cargarLista(lv);
		}
	};

	private void cargarLista(ListView lv) {

		final ArrayList<Aviso> avisoarray = new ArrayList<Aviso>();
		this.cargarAvisos(avisoarray);

		if (avisoarray.isEmpty()) {
			Toast
					.makeText(VerAvisos.this, R.string.noavisos,
							Toast.LENGTH_LONG).show();
		} else {
			if (filtrofecha == true) {
				Date dini = new Date();
				dini.setDate(this.diaini);
				dini.setMonth(this.mesini);
				dini.setYear(this.anioini - 1900);
				dini.setHours(this.horaini);
				dini.setMinutes(this.minutoini);
				Date dfin = new Date();
				dfin.setDate(this.diafin);
				dfin.setMonth(this.mesfin);
				dfin.setYear(this.aniofin - 1900);
				dfin.setHours(this.horafin);
				dfin.setMinutes(this.minutofin);

				for (int i = 0; i < avisoarray.size(); i++) {
					Date d = ((Aviso) avisoarray.get(i)).getFechacreacion();
					dini.setSeconds(d.getSeconds());
					dfin.setSeconds(d.getSeconds());
					// Elimina de la lista los elementos que no pasen el filtro
					if (d.before(dini) || d.after(dfin)) {
						avisoarray.remove(i);
						i--;
					}
				}

			}
		}

		setListAdapter(new ArrayAdapter<Aviso>(this, R.layout.vista_lista,
				avisoarray));
		this.lv.setTextFilterEnabled(true);
		this.lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Aviso a = avisoarray.get((int) id);
				Toast
						.makeText(VerAvisos.this, a.toMostrar(),
								Toast.LENGTH_LONG).show();
			}
		});

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog t = null;
		switch (id) {
		case DATEINI_DIALOG_ID:
			t = new DatePickerDialog(this, iniDateSetListener, anioini, mesini,
					diaini);
			t.setTitle(R.string.filtro_fechaini);
			break;

		case DATEFIN_DIALOG_ID:
			t = new DatePickerDialog(this, finDateSetListener, aniofin, mesfin,
					diafin);
			t.setTitle(R.string.filtro_fechafin);
			break;
			
		case HOURINI_DIALOG_ID:
			t = new TimePickerDialog(this, iniTimeSetListener, horaini,
					minutoini, true);
			t.setTitle(R.string.filtro_horaini);
			break;
			
		case HOURFIN_DIALOG_ID:
			t = new TimePickerDialog(this, finTimeSetListener, horafin,
					minutofin, true);
			t.setTitle(R.string.filtro_horafin);
			break;
		}
		return t;
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
				Aviso a = new Aviso(aux);
				al.add(a);
				aux = "";
			}
		}
	}

}
