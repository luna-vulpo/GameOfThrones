package pl.gameofthrones.gameserver;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public final class PlayerTask implements Runnable {

    private final Socket mGamerSocket;
    private BufferedInputStream mIn;
    private PrintWriter mOut;
    private boolean mRun = true;
    
    public PlayerTask(Socket clientSocket) {
        mGamerSocket = clientSocket;

    }

    @Override
    public void run() {
        try {
            mIn = new BufferedInputStream(mGamerSocket.getInputStream());
            mOut = new PrintWriter(mGamerSocket.getOutputStream(),true);
        }
        catch (IOException e) {           
            e.printStackTrace();
            return;
        }
        
        while(mRun){
            // do thread work
        }
        
        //finish the task
        
        try {
            mOut.close();
            mIn.close();
            mGamerSocket.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        
    }
    
    public void stop(){
        mRun = false;
    }

}
