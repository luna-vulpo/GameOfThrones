package pl.gameofthrones.gameclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import pl.gameofthrones.gameserver.BoardServer;
import pl.gameofthrones.util.Log;

public class GameClient implements Runnable {
	private static final String TAG = GameClient.class.getSimpleName();
	private Socket serverSocket;
	private BufferedReader in;
	private PrintWriter out;
	private boolean ran = true;

	public GameClient() {

	}

	@Override
	public void run() {

		try {
			serverSocket = new Socket("localhost", BoardServer.PORT);
			in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
			out = new PrintWriter(serverSocket.getOutputStream(), true);
			
			Log.d(TAG, "conection to server established");
			while (ran) {
				
				//the important part here

			}

		} catch (UnknownHostException e) {
			Log.e(TAG, "", e);
		} catch (IOException e) {
			Log.e(TAG, "", e);
		} finally {
			try {
				if (out != null) out.close();
				if (in != null) in.close();
				if (serverSocket != null) serverSocket.close();

			} catch (IOException e) {
				Log.e(TAG, "", e);
			}

		}

	}

}
