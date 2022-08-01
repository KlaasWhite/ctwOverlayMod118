package me.klaaswhite.ctwoverlay118.classes;

public class CtwOverlaySetPlayerTeamRequest {
    private final String playerName;
    private final String teamName;
    private final String privateGameId;

    public CtwOverlaySetPlayerTeamRequest(String playerName, String teamName, String privateGameId){
        this.playerName = playerName;
        this.teamName = teamName;
        this.privateGameId = privateGameId;
    }

    public String getPlayerName(){return this.playerName;}

    public String getTeamName(){return this.teamName;}

    public String getPrivateGameId(){return this.privateGameId;}
}
