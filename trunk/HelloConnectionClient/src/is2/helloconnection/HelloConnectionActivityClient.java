package is2.helloconnection;

import is2.AndroidServer.Aviso;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HelloConnectionActivityClient extends Activity {

	private EditText serverIp;
	private EditText clientSays;

	private Button connectPhones;

	private String serverIpAddress = "";

	private boolean connected = false;

	private Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.client);

		serverIp = (EditText) findViewById(R.id.server_ip);
		clientSays = (EditText) findViewById(R.id.client_says);
		connectPhones = (Button) findViewById(R.id.connect_phones);
		connectPhones.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Log.d("ClientActivity", "V:Click");
				if (!connected) {
					serverIpAddress = serverIp.getText().toString();
					Log.d("ClientActivity", "V:Ip"+serverIpAddress);
					if (!serverIpAddress.equals("")) {
						Log.d("ClientActivity", "V:Tread");
						Thread cThread = new Thread(new ClientThread());
						cThread.start();
					}
				}
			}
		});
	}

	public class ClientThread implements Runnable {

		public void run() {
			try {
				InetAddress serverAddr = InetAddress.getByName(serverIpAddress);
				Log.d("ClientActivity", "C: Connecting...");
				Socket socket = new Socket(serverAddr, 8080);
				connected = true;
				if (connected) {
					try {
						Log.d("ClientActivity", "C: Sending command.");
						PrintWriter out = new PrintWriter(
								new BufferedWriter(new OutputStreamWriter(
										socket.getOutputStream())), true);
						// where you issue the commands
						MiObjectOutputStream objectOutputStream= new MiObjectOutputStream(socket.getOutputStream());
						objectOutputStream.writeObject(new Aviso(clientSays.getText().toString(),clientSays.getText().toString()));
//						out.println(clientSays.getText().toString());
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
	}
}