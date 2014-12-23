package pl.gameofthrones.gameserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BoardServer {

    public static void main(String[] args) {

        System.out.println("init");
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(666);
            Socket clientSocket = serverSocket.accept();
            
            Gamer gamer = new Gamer(clientSocket);
            new Thread(gamer).start();
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(serverSocket != null) try {
                serverSocket.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
