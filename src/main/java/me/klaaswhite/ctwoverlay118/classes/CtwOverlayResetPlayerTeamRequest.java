package me.klaaswhite.ctwoverlay118.classes;

public class CtwOverlayResetPlayerTeamRequest {
    private final String playerName;
    private final String privateGameId;

    public CtwOverlayResetPlayerTeamRequest(String playerName, String privateGameId){
        this.playerName = playerName;
        this.privateGameId = privateGameId;
    }

    public String getPlayerName(){return playerName;}

    public String getPrivateGameId(){return privateGameId;}
}
