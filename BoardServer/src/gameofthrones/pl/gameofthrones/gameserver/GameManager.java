package pl.gameofthrones.gameserver;

public final class GameManager {

    /**
     * not lazy thread safe singleton
     */
    private static GameManager mGameManager = new GameManager();
    
    private GameManager(){}
    
    public static GameManager getGameManager(){
        return mGameManager;
    }
}
