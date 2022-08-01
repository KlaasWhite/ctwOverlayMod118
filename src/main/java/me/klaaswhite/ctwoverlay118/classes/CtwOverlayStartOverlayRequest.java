package me.klaaswhite.ctwoverlay118.classes;

import java.util.ArrayList;

public class CtwOverlayStartOverlayRequest {
    private final Boolean ctwMode;
    private final String playerName;
    private final ArrayList<CtwOverlayPlayer> players;
    private final String version;

    public CtwOverlayStartOverlayRequest(Boolean ctwMode, String playerName, ArrayList<CtwOverlayPlayer> players, String version){
        this.ctwMode = ctwMode;
        this.players = players;
        this.playerName = playerName;
        this.version = version;
    }

    public Boolean getCtwMode() {return ctwMode;}

    public String getPlayerName(){return playerName;}

    public ArrayList<CtwOverlayPlayer> getPlayers(){return players; }

    public String getVersion(){return version;}
}
