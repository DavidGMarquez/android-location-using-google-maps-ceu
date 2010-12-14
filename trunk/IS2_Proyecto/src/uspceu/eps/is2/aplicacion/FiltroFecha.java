/**
 * 
 */
package uspceu.eps.is2.aplicacion;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Cayette
 * 
 */
public class FiltroFecha {

	private int anioini;
	private int aniofin;
	private int mesini;
	private int mesfin;
	private int diaini;
	private int diafin;
	private int horaini;
	private int horafin;
	private int minutoini;
	private int minutofin;
	private boolean filtroactivado;

	/**
	 * @return the anioini
	 */
	public int getAnioini() {
		return anioini;
	}

	/**
	 * @param anioini
	 *            the anioini to set
	 */
	public void setAnioini(int anioini) {
		this.anioini = anioini;
	}

	/**
	 * @return the aniofin
	 */
	public int getAniofin() {
		return aniofin;
	}

	/**
	 * @param aniofin
	 *            the aniofin to set
	 */
	public void setAniofin(int aniofin) {
		this.aniofin = aniofin;
	}

	/**
	 * @return the mesini
	 */
	public int getMesini() {
		return mesini;
	}

	/**
	 * @param mesini
	 *            the mesini to set
	 */
	public void setMesini(int mesini) {
		this.mesini = mesini;
	}

	/**
	 * @return the mesfin
	 */
	public int getMesfin() {
		return mesfin;
	}

	/**
	 * @param mesfin
	 *            the mesfin to set
	 */
	public void setMesfin(int mesfin) {
		this.mesfin = mesfin;
	}

	/**
	 * @return the diaini
	 */
	public int getDiaini() {
		return diaini;
	}

	/**
	 * @param diaini
	 *            the diaini to set
	 */
	public void setDiaini(int diaini) {
		this.diaini = diaini;
	}

	/**
	 * @return the diafin
	 */
	public int getDiafin() {
		return diafin;
	}

	/**
	 * @param diafin
	 *            the diafin to set
	 */
	public void setDiafin(int diafin) {
		this.diafin = diafin;
	}

	/**
	 * @return the horaini
	 */
	public int getHoraini() {
		return horaini;
	}

	/**
	 * @param horaini
	 *            the horaini to set
	 */
	public void setHoraini(int horaini) {
		this.horaini = horaini;
	}

	/**
	 * @return the horafin
	 */
	public int getHorafin() {
		return horafin;
	}

	/**
	 * @param horafin
	 *            the horafin to set
	 */
	public void setHorafin(int horafin) {
		this.horafin = horafin;
	}

	/**
	 * @return the minutoini
	 */
	public int getMinutoini() {
		return minutoini;
	}

	/**
	 * @param minutoini
	 *            the minutoini to set
	 */
	public void setMinutoini(int minutoini) {
		this.minutoini = minutoini;
	}

	/**
	 * @return the minutofin
	 */
	public int getMinutofin() {
		return minutofin;
	}

	/**
	 * @param minutofin
	 *            the minutofin to set
	 */
	public void setMinutofin(int minutofin) {
		this.minutofin = minutofin;
	}

	/**
	 * @return the filtrofecha
	 */
	public boolean isFiltroactivado() {
		return filtroactivado;
	}

	/**
	 * @param filtrofecha
	 *            the filtrofecha to set
	 */
	public void setFiltroactivado(boolean filtroactivado) {
		this.filtroactivado = filtroactivado;
	}

	public void setFechaIni(int anio, int mes, int dia) {
		this.anioini = anio;
		this.mesini = mes;
		this.diaini = dia;
	}

	public void setFechaFin(int anio, int mes, int dia) {
		this.aniofin = anio;
		this.mesfin = mes;
		this.diafin = dia;
	}

	public void setHoraIni(int hora, int min) {
		this.horaini = hora;
		this.minutoini = min;
	}

	public void setHoraFin(int hora, int min) {
		this.horafin = hora;
		this.minutofin = min;
	}

	/**
	 * @param anioini
	 * @param aniofin
	 * @param mesini
	 * @param mesfin
	 * @param diaini
	 * @param diafin
	 * @param horaini
	 * @param horafin
	 * @param minutoini
	 * @param minutofin
	 * @param filtroactivado
	 */
	public FiltroFecha(int anioini, int aniofin, int mesini, int mesfin,
			int diaini, int diafin, int horaini, int horafin, int minutoini,
			int minutofin, boolean filtroactivado) {
		super();
		this.anioini = anioini;
		this.aniofin = aniofin;
		this.mesini = mesini;
		this.mesfin = mesfin;
		this.diaini = diaini;
		this.diafin = diafin;
		this.horaini = horaini;
		this.horafin = horafin;
		this.minutoini = minutoini;
		this.minutofin = minutofin;
		this.filtroactivado = filtroactivado;
	}

	public FiltroFecha(Calendar c) {
		this.anioini = c.get(Calendar.YEAR);
		this.aniofin = c.get(Calendar.YEAR);
		this.mesini = c.get(Calendar.MONTH);
		this.mesfin = c.get(Calendar.MONTH);
		this.diaini = c.get(Calendar.DATE);
		this.diafin = c.get(Calendar.DATE);
		this.horaini = c.get(Calendar.HOUR_OF_DAY);
		this.horafin = c.get(Calendar.HOUR_OF_DAY);
		this.minutoini = c.get(Calendar.MINUTE);
		this.minutofin = c.get(Calendar.MINUTE);
		this.filtroactivado = false;
	}

	public Date dateIni() {
		Date d = new Date();
		d.setDate(this.diaini);
		d.setMonth(this.mesini);
		d.setYear(this.anioini - 1900);
		d.setHours(this.horaini);
		d.setMinutes(this.minutoini);
		return d;
	}

	public Date dateFin() {
		Date d = new Date();
		d.setDate(this.diafin);
		d.setMonth(this.mesfin);
		d.setYear(this.aniofin - 1900);
		d.setHours(this.horafin);
		d.setMinutes(this.minutofin);
		return d;
	}
	
	public boolean isFechaValida(Date d){
		Date dini=this.dateIni();
		Date dfin=this.dateFin();
		dini.setSeconds(d.getSeconds());
		dfin.setSeconds(d.getSeconds());
		if(d.before(dini)||d.after(dfin)) return false;
		else return true;
	}

}
