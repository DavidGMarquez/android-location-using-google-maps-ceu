package is2.AndroidServer;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Handler;

import uspceu.eps.is2.aplicacion.Aviso;

public class ServerThread implements Runnable {
	private String serverStatus;
	private String clientSays;
	private Aviso avisoOutput;
	private String fichero = "avisos";
	public static String SERVERIP = "10.0.2.15";
	// designate a port
	public static final int SERVERPORT = 8080;
	private ServerSocket serverSocket;
	private int database=1;
	private String databasename="avisos.db";
	DatabaseHandler databasehandler;
	

	public void run() {
		try {
			databasehandler=new DatabaseHandler(databasename);
			databasehandler.initDB();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		SERVERIP = getLocalIpAddress();

		try {
			if (SERVERIP != null) {
				System.out.println("Listening on IP: " + SERVERIP);
				serverSocket = new ServerSocket(SERVERPORT);
				while (true) {
					System.out.println("ESCUCHANDO...");
					System.out.println("LISTA AVISOS");
					System.out.println("--------------");
					this.mostrarAvisos(this.cargarfichero());
					System.out.println("--------------");
					// listen for incoming clients
					Socket client = serverSocket.accept();
					System.out.println("Connected.");
					this.procesarCliente(client);
					
					
				}
			} else {
				System.out.println("Couldn't detect internet connection.");

			}
		} catch (Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}

	private void procesarCliente(Socket client) {

		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(client.getInputStream()));
			MiObjectInputStream objectInputStream = new MiObjectInputStream(
					client.getInputStream());
			System.out.println("Recibiendo Comando");
			char option=objectInputStream.readChar();
			System.out.println("Recibido Comando "+option);
			switch(option)
			{
			case 'A':
			{
				System.out.println("Añadiendo Aviso");
				this.recibirAviso(client);
				break;
			}
			
			case 'N':
			{System.out.println("Enviando avisos");
				this.enviarAvisos(client);
				break;
			}
			
			default:
			{
				System.out.println("Opcion no reconocida");
				break;
			}
			
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	private boolean enviarAvisos(Socket client) {

			ArrayList<Aviso> avisos=new ArrayList<Aviso>(this.cargarfichero());
			try {
				MiObjectOutputStream objectOutputStream= new MiObjectOutputStream(client.getOutputStream());
				
				try {
					for (int i=0;i<avisos.size();i++){
						Aviso aviso=avisos.get(i);
						objectOutputStream.writeObject(aviso);
						System.out
						.println("Enviado aviso "+i+" "+aviso.getNombreAviso());
					}			
				} catch (EOFException e) {
					System.out.println("Fin conexion");				
				} finally {
					objectOutputStream.close();
					
				}
				return true;
			} catch (Exception e) {
				System.out
						.println("Oops. Connection interrupted. Please reconnect your phone");
				e.printStackTrace();
			}
			return false;			
	}

	private String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
			System.out.println("ServerActivity" + ex.toString());
		}
		return null;
	}

	private boolean recibirAviso(Socket client)
	{
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(client.getInputStream()));
			MiObjectInputStream objectInputStream = new MiObjectInputStream(
					client.getInputStream());
			Object avisoObject = null;
			Aviso aviso = null;
			try {
				while ((avisoObject = objectInputStream
						.readObject()) != null) {
					if (avisoObject instanceof Aviso) {
						aviso = (Aviso) avisoObject;
						System.out.println("ServerActivity"
								+ aviso.getNombreAviso());
						this.guardarEnFichero(aviso);
					} else {
						System.out
								.println("Objeto recibido no aviso");
					}
					avisoOutput = aviso;
					System.out.println(avisoOutput.toMostrar());
				}
			} catch (EOFException e) {
				System.out.println("Fin conexion");				
			} finally {
				objectInputStream.close();
				
			}
			return true;
		} catch (Exception e) {
			System.out
					.println("Oops. Connection interrupted. Please reconnect your phone");
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean guardarEnFichero(Aviso aviso) {
		if(database==0){
		System.out.println("Guardando en fichero");
		try {
			MiObjectOutputStream oos = new MiObjectOutputStream(
					new FileOutputStream(fichero,true));
			oos.writeObject(aviso);
			oos.flush();
			oos.close();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		}
		else
		{
			
			System.out.println("Guardando en base de datos");
			databasehandler.addAviso(aviso);
			return true;			
			
		}
	}

	public ArrayList<Aviso> cargarfichero() {
		ArrayList<Aviso> avisos = new ArrayList<Aviso>();

		if(database==0)
		{
		MiObjectInputStream ois;
		try {
			ois = new MiObjectInputStream(new FileInputStream(fichero));
			Object aux = ois.readObject();
			
			while (aux != null) {
				if (aux instanceof Aviso) {
					//System.out.println("Recibiendo Comando");					
					avisos.add((Aviso) aux);
				}
				aux = ois.readObject();
			}
			ois.close();

		}catch (EOFException e){
			System.out.println("Terminado de leer fichero");
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return avisos;
		}
		else
		{
			return databasehandler.getAvisos();
		}

	}

	public void mostrarAvisos(ArrayList<Aviso> avisos) {
		for (int i = 0; i < avisos.size(); i++) {
			Aviso aviso = avisos.get(i);
			System.out.println(i + " Aviso" + aviso.getNombreAviso() + " Desc:"
					+ aviso.getDescripcionAviso());
		}
	}
}
