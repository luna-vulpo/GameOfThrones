package pl.gameofthrones.gameserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import pl.gameofthrones.gameboard.Player;
import pl.gameofthrones.gameserver.protocol.QueryServer;

import com.google.gson.Gson;

public final class PlayerConnectionTask implements Runnable {

    private final Socket playerSocket;
    private BufferedReader in;
    private PrintWriter out;
    private boolean ran = true;
	Player player = new Player(this);

	Gson gson = new Gson();

    public PlayerConnectionTask(Socket clientSocket) {
        playerSocket = clientSocket;

    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));
            out = new PrintWriter(playerSocket.getOutputStream(),true);

            String clientQuery = in.readLine();
            
            QueryServer qs = gson.fromJson(clientQuery, QueryServer.class);
            
            
            while(ran){
                // do thread work
                
            }

        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }
        finally {

            // finish the task

            try {
                out.close();
                in.close();
                playerSocket.close();

            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    
    public void stop(){
        ran = false;
    }

	
    public void setConnectionRefuse() {
		// TODO Auto-generated method stub
		
	}

	public Player getPlayer() {
		return player;
	}

}
