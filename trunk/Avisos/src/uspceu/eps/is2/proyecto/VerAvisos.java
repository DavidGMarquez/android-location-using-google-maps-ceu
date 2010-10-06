package uspceu.eps.is2.proyecto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class VerAvisos extends ListActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final ArrayList<Aviso> avisoarray = new ArrayList<Aviso>();

		cargarAvisos(avisoarray);

		setListAdapter(new ArrayAdapter<Aviso>(this, R.layout.vista_lista,
				avisoarray));

		final ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Aviso a = avisoarray.get((int) id);
				Toast.makeText(VerAvisos.this, a.toCompleto(),
						Toast.LENGTH_LONG).show();
			}
		});
	}
	
	public void cargarAvisos(ArrayList<Aviso> al) {

		int numlins = -1;
		String aux = "";
		ArrayList<String> stringarray = new ArrayList<String>();

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
				Aviso a = new Aviso(aux);
				al.add(a);
				aux = "";
			}
		}
	}

}