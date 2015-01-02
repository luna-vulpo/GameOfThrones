package pl.gameofthrones.gameserver.protocol;

import com.google.gson.annotations.SerializedName;

public class QueryServer {

    @SerializedName("v") 
    int mProtocolVersion = 0;
    //TODO change this dump implementation to real one
    @SerializedName("is_game_server") 
    String mIsGameServer = "a u game server?";

}
