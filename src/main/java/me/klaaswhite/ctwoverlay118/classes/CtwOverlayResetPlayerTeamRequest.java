package me.klaaswhite.ctwoverlay118.classes;

public class CtwOverlayResetPlayerTeamRequest {
    private final String playerName;
    private final String privateGameId;
    private final String teamName;

    public CtwOverlayResetPlayerTeamRequest(String playerName, String privateGameId, String teamName){
        this.playerName = playerName;
        this.privateGameId = privateGameId;
        this.teamName = teamName;

    }

    public String getPlayerName(){return playerName;}

    public String getPrivateGameId(){return privateGameId;}

    public String getTeamName(){return teamName;}
}
