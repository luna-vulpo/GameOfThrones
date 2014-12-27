package pl.gameofthrones.gameserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import pl.gameofthrones.gameboard.Board;
import pl.gameofthrones.gameboard.Board.Builder.TooManyPlayersException;

public class BoardServer {

    /**
     * not lazy thread safe singleton (draft implementation)
     * TODO: consider new diffrent implementation
     */
    private static BoardServer mBoardServer = new BoardServer();

    private Board.Builder mBoardBuilder = null;
    
    public static void main(String[] args) {

        mBoardServer.initBoard();
        mBoardServer.startSererSocket();
        mBoardServer.startGameManager();
    }


    private void initBoard() {
        mBoardBuilder = new Board.Builder();
        
    }

    private void startSererSocket(){
        System.out.println("init");
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(6666);
            Socket clientSocket = serverSocket.accept();
            
            PlayerTask gamer = new PlayerTask(clientSocket);
            new Thread(gamer).start();
            mBoardBuilder.addPlayer(gamer);
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (TooManyPlayersException e) {
            // TODO sent info to client that he is not accepted
            // and end connection according to the protocol
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
        //first dump implementation
        GameManager.getGameManager().setBoard(mBoardBuilder.build());
        new Thread(GameManager.getGameManager()).start();
        
    }
}
