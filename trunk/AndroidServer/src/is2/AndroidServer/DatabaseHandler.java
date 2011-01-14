package is2.AndroidServer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

import uspceu.eps.is2.aplicacion.Aviso;
import uspceu.eps.is2.aplicacion.FormatoCoordenadasException;
import uspceu.eps.is2.aplicacion.NombreAvisoException;
import uspceu.eps.is2.aplicacion.PuntoMapa;
import uspceu.eps.is2.aplicacion.Usuario;

public class DatabaseHandler {
	private Connection conn;
	private Statement stm;
	
	public DatabaseHandler(String file) throws SQLException, ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		this.conn = DriverManager.getConnection("jdbc:sqlite:" + file);
		this.stm = this.conn.createStatement();
	}
	
	public void initDB() {
		try {
			//this.stm.executeUpdate("DROP TABLE IF EXISTS Avisos");
			this.stm.executeUpdate("CREATE TABLE IF NOT EXISTS Avisos (" +
					"nombre varchar(100) PRIMARY KEY NOT NULL," +
					"descripcion varchar(100),"+
					"latitud varchar(200),"+
					"longitud varchar(200),"+
					"usuario varchar(100));");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public void addAviso(Aviso aviso) {
		try {
			this.stm = this.conn.createStatement();
			this.stm.executeUpdate("INSERT INTO Avisos VALUES (\"" +
					aviso.getNombreAviso() + "\",\"" + 
					aviso.getDescripcionAviso() + "\",\"" + 
					aviso.getPuntoMapa().getLatitud() + "\",\"" +
					aviso.getPuntoMapa().getLongitud() + "\",\"" +
					aviso.getUsuario().getNombre() + "\");");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public ArrayList<Aviso> getAvisos() {
		ArrayList<Aviso> avisos=new ArrayList<Aviso>();
		
		ResultSet rs;
		try {
			rs = this.stm.executeQuery("SELECT * FROM Avisos");
			while (rs.next()) {
				Usuario usuario= new Usuario(rs.getString("usuario"));
				PuntoMapa puntoMapa = null;
				try {
					puntoMapa = new PuntoMapa(rs.getString("latitud"), rs.getString("longitud"));
				} catch (FormatoCoordenadasException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Aviso aviso = null;
				try {
					aviso = new Aviso(rs.getString("nombre"), rs.getString("descripcion"), usuario, puntoMapa);
				} catch (NombreAvisoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				avisos.add(aviso);
				
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return avisos;
	}
}
