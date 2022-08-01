package me.klaaswhite.ctwoverlay118.classes;

public class CtwOverlayStopOverlayRequest {
    private final String privateGameId;

    public CtwOverlayStopOverlayRequest(String privateId){
        this.privateGameId = privateId;
    }

    public String getPrivateGameId() {
        return privateGameId;
    }
}
