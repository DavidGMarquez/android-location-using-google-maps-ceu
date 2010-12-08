/**
 * 
 */
package uspceu.eps.is2.aplicacion;


import android.widget.Button;
import android.widget.EditText;

/**
 * @author Cayette
 *
 */
public class FormularioCrearAviso {

	private EditText editname;
	private EditText editdesc;
	private EditText editlat;
	private EditText editlon;
	private Button button;
	
	/**
	 * @return the editname
	 */
	public EditText getEditname() {
		return editname;
	}
	/**
	 * @param editname the editname to set
	 */
	public void setEditname(EditText editname) {
		this.editname = editname;
	}
	/**
	 * @return the editdesc
	 */
	public EditText getEditdesc() {
		return editdesc;
	}
	/**
	 * @param editdesc the editdesc to set
	 */
	public void setEditdesc(EditText editdesc) {
		this.editdesc = editdesc;
	}


	/**
	 * @return the button
	 */
	public Button getButton() {
		return button;
	}
	/**
	 * @param button the button to set
	 */
	public void setButton(Button button) {
		this.button = button;
	}
	

	public void setEditlat(EditText editlat) {
		this.editlat = editlat;
	}
	public EditText getEditlat() {
		return editlat;
	}
	public void setEditlon(EditText editlon) {
		this.editlon = editlon;
	}
	public EditText getEditlon() {
		return editlon;
	}
	public FormularioCrearAviso() {
		
	}
	
	public Aviso obtenerDatosFormulario(Usuario u) throws NombreAvisoException, FormatoCoordenadasException{
		String nombre=editname.getText().toString();
		String descripcion=editdesc.getText().toString();
		if (editdesc.getText().length()==0)
			descripcion="(sin descripción)";
		PuntoMapa pm=new PuntoMapa(editlat.getText().toString(),editlon.getText().toString());
		Aviso a=new Aviso(nombre,descripcion,u,pm);
		return a;
	}
	public void vaciarCampos(){
		editname.setText("");
		editdesc.setText("");
		editlat.setText("");
		editlon.setText("");
	}
	
	public boolean nombreVacio(){
		return(this.editname.getText().length()==0);
	}
	
	public boolean descripcionVacio(){
		return(this.editdesc.getText().length()==0);
	}
	
	/* Si las coordenadas se recogen automáticamente, desactiva la escritura en los campos */
	public void defaultCoordenadas(PuntoMapa pm){
		this.editlat.setText(pm.getLatitud());
		this.editlon.setText(pm.getLongitud());
		//this.editlat.setEnabled(false);
		//this.editlon.setEnabled(false);
	}
	
}
