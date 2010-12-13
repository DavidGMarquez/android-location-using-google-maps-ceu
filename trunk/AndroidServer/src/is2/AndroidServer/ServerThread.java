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
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Handler;

public class ServerThread implements Runnable {
	private String serverStatus;
	private String clientSays;
	private Aviso avisoOutput;
	private String fichero = "avisos";
	public static String SERVERIP = "10.0.2.15";
	// designate a port
	public static final int SERVERPORT = 8080;
	private ServerSocket serverSocket;

	public void run() {

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
					} catch (Exception e) {
						System.out
								.println("Oops. Connection interrupted. Please reconnect your phone");
						e.printStackTrace();
					}
				}
			} else {
				System.out.println("Couldn't detect internet connection.");

			}
		} catch (Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		}
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

	public boolean guardarEnFichero(Aviso aviso) {
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

	public ArrayList<Aviso> cargarfichero() {
		ArrayList<Aviso> avisos = new ArrayList<Aviso>();
		MiObjectInputStream ois;
		try {
			ois = new MiObjectInputStream(new FileInputStream(fichero));
			Object aux = ois.readObject();
			while (aux != null) {
				if (aux instanceof Aviso) {
					System.out.println("Aviso leido");
					avisos.add((Aviso) aux);
				}
				aux = ois.readObject();
			}
			ois.close();

		} catch (FileNotFoundException e) {
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

	public void mostrarAvisos(ArrayList<Aviso> avisos) {
		for (int i = 0; i < avisos.size(); i++) {
			Aviso aviso = avisos.get(i);
			System.out.println(i + " Aviso" + aviso.getNombreAviso() + " Desc:"
					+ aviso.getDescripcionAviso());
		}
	}
}
