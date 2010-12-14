package uspceu.eps.is2.aplicacion;

public class PuntoMapa {
	private String latitud;
	private String longitud;

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}
	
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public PuntoMapa(String latitud, String longitud) throws FormatoCoordenadasException {
		super();
		double la=0,lo=0;
		try {
			la=new Double(latitud).doubleValue();
		} catch (NumberFormatException e) {
			throw new FormatoCoordenadasException("Latitud incorrecta");
		}
		try {
			lo=new Double(longitud).doubleValue();
		} catch (NumberFormatException e) {
			throw new FormatoCoordenadasException("Longitud incorrecta");
		}
		if(la>90||la<-90) throw new FormatoCoordenadasException("Latitud inexistente");
		if(lo>180||lo<-180) throw new FormatoCoordenadasException("Longitud inexistente");
		this.latitud = new Double(la).toString();
		this.longitud = new Double(lo).toString();
	}

	@Override
	public String toString() {
		String lat = "", lon = "";
		Double aux = new Double(this.latitud);
		if (aux >= 0) {
			lat = " N";
		} else {
			lat = " S";
		}
		aux = new Double(this.longitud);
		if (aux >= 0) {
			lon = " E";
		} else {
			lon = " W";
		}
		return latitud + lat + ", " + longitud + lon;
	}

}
