package pl.gameofthrones.gameserver;

public final class GameManager implements Runnable {

    /**
     * not lazy thread safe singleton
     */
    private static GameManager mGameManager = new GameManager();
    private boolean mRan = true;
    
    private GameManager(){}
    
    public static GameManager getGameManager(){
        return mGameManager;
    }

    @Override
    public void run() {
        
        while(mRan){
            
        }        
    }
    
    public void stop(){
        mRan = false;
    }
}
