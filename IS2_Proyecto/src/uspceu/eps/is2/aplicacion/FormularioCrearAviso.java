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
	

	public FormularioCrearAviso() {
		
	}
	
	public Aviso obtenerDatosFormulario(Usuario u, PuntoMapa pm){
		String nombre=editname.getText().toString();
		if (editname.getText().length()==0)
			nombre="SinNombre";
		String descripcion=editdesc.getText().toString();
		if (editdesc.getText().length()==0)
			descripcion="(sin descripción)";
		Aviso a=new Aviso(nombre,descripcion,u,pm);
		return a;
	}
	public void vaciarCampos(){
		editname.setText("");
		editdesc.setText("");
	}
	
}
