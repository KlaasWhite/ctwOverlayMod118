package me.klaaswhite.ctwoverlay118.classes;

import java.util.ArrayList;

public class CtwOverlayResetOverlayRequest {

    private final String privateGameId;
    private final ArrayList<CtwOverlayPlayer> players;

    public CtwOverlayResetOverlayRequest(String privateGameId, ArrayList<CtwOverlayPlayer> players){
        this.privateGameId = privateGameId;
        this.players = players;
    }

    public ArrayList<CtwOverlayPlayer> getPlayers(){
        return players;
    }

    public String getPrivateGameId(){
        return privateGameId;
    }

}
