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

    private final Socket mGamerSocket;
    private BufferedReader mIn;
    private PrintWriter mOut;
    private boolean mRan = true;
	Player player = new Player(this);

	Gson gson = new Gson();

    public PlayerConnectionTask(Socket clientSocket) {
        mGamerSocket = clientSocket;

    }

    @Override
    public void run() {
        try {
            mIn = new BufferedReader(new InputStreamReader(mGamerSocket.getInputStream()));
            mOut = new PrintWriter(mGamerSocket.getOutputStream(),true);

            String clientQuery = mIn.readLine();
            
            QueryServer qs = gson.fromJson(clientQuery, QueryServer.class);
            
            
            while(mRan){
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
                mOut.close();
                mIn.close();
                mGamerSocket.close();

            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    
    public void stop(){
        mRan = false;
    }

	
    public void setConnectionRefuse() {
		// TODO Auto-generated method stub
		
	}

	public Player getPlayer() {
		return player;
	}

}
