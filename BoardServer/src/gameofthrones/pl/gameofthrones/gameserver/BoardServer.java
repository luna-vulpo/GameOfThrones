package pl.gameofthrones.gameserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import pl.gameofthrones.gameboard.Board;

public class BoardServer {

    /**
     * not lazy thread safe singleton (draft implementation)
     * TODO: consider new diffrent implementation
     */
    private static BoardServer mBoardServer = new BoardServer();
    
    public static void main(String[] args) {

        mBoardServer.initBoard();
        mBoardServer.startSererSocket();
        mBoardServer.startGameManager();
    }


    private void initBoard() {
        Board.getBoard();
        
    }

    private void startSererSocket(){
        System.out.println("init");
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(666);
            Socket clientSocket = serverSocket.accept();
            
            PlayerTask gamer = new PlayerTask(clientSocket);
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
    

    private void startGameManager() {
        // TODO Auto-generated method stub
        
    }
}
