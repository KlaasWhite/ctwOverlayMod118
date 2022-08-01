package me.klaaswhite.ctwoverlay118.classes;


public class CtwOverlayRemoveTeamRequest {
    private final String privateGameId;
    private final String teamName;

    public CtwOverlayRemoveTeamRequest(String privateGameId, String teamName){
        this.privateGameId = privateGameId;
        this.teamName = teamName;
    }

    public String getTeamName(){
        return teamName;
    }

    public String getPrivateGameId(){
        return privateGameId;
    }
}
