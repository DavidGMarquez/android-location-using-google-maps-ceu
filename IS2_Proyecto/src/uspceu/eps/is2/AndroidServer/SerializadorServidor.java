package uspceu.eps.is2.AndroidServer;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import uspceu.eps.is2.aplicacion.Aviso;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SerializadorServidor {
	private String serverIp = "172.23.12.235";
	private String serverIpAddress = "";
	private boolean connected = false;

	public SerializadorServidor(String serverIp) {
		super();
		this.serverIp = serverIp;
	}
	public SerializadorServidor() {
		super();
	
	}

	protected void conectar() {
		Log.d("ClientActivity", "V:Conectar");
		if (!connected) {
			serverIpAddress = serverIp;
			Log.d("ClientActivity", "V:Ip" + serverIpAddress);
		}
	}

	public void enviarAviso(Aviso aviso) {
		this.conectar();
		try {
			InetAddress serverAddr = InetAddress.getByName(serverIpAddress);
			Log.d("ClientActivity", "C: Connecting...");
			Socket socket = new Socket(serverAddr, 8080);
			connected = true;
			if (connected) {
				try {
					Log.d("ClientActivity", "C: Sending command.");
					PrintWriter out = new PrintWriter(new BufferedWriter(
							new OutputStreamWriter(socket.getOutputStream())),
							true);
					// where you issue the commands
					MiObjectOutputStream objectOutputStream = new MiObjectOutputStream(
							socket.getOutputStream());
					char option = 'A';
					objectOutputStream.writeChar(option);
					objectOutputStream.flush();
					Log.d("ClientActivity", "C: Send command ." + option);
					objectOutputStream.writeObject(aviso);
					Log.d("ClientActivity", "C: Sent.");
				} catch (Exception e) {
					Log.e("ClientActivity", "S: Error", e);
				}
			}
			socket.close();
			Log.d("ClientActivity", "C: Closed.");
			connected = false;
		} catch (Exception e) {
			connected = false;
			Log.e("ClientActivity", "C: Error", e);

		}

	}

	public ArrayList<Aviso> obtenerAvisos() {

		this.conectar();
		ArrayList<Aviso> avisos = new ArrayList<Aviso>();
		try {
			InetAddress serverAddr = InetAddress.getByName(serverIpAddress);
			Log.d("ClientActivity", "C: Connecting...");
			Socket socket = new Socket(serverAddr, 8080);
			connected = true;
			if (connected) {
				try {
					Log.d("ClientActivity", "C: Sending command.");
					PrintWriter out = new PrintWriter(new BufferedWriter(
							new OutputStreamWriter(socket.getOutputStream())),
							true);
					// where you issue the commands
					MiObjectOutputStream objectOutputStream = new MiObjectOutputStream(
							socket.getOutputStream());
					char option = 'N';
					objectOutputStream.writeChar(option);
					objectOutputStream.flush();
					Log.d("ClientActivity", "C: Send command ." + option);
					MiObjectInputStream objectInputStream = new MiObjectInputStream(
							socket.getInputStream());
					Log.d("ClientActivityList", "C: Sending request List.");
					Object avisoObject = null;
					while ((avisoObject = objectInputStream.readObject()) != null) {
						Log.d("ClientActivityList", "Recibido");
						if (avisoObject instanceof Aviso) {
							Aviso aviso = (Aviso) avisoObject;
							Log.d("ClientActivityList", "ServerActivity"
									+ aviso.getNombreAviso());
							avisos.add(aviso);
						} else {
							System.out.println("Objeto recibido no aviso");
						}

					}
					Log.d("ClientActivity", "C: Sent.");
				} catch (Exception e) {
					Log.e("ClientActivity", "S: Error", e);
				}
			}
			socket.close();
			Log.d("ClientActivity", "C: Closed.");
			connected = false;
		}catch (SocketException e) {
			connected = false;
			Log.e("ClientActivity", "C: Error", e);
			return null;

		}
		catch (Exception e) {
			connected = false;
			Log.e("ClientActivity", "C: Error", e);

		}
		return avisos;

	}

}
