package me.klaaswhite.ctwoverlay118.classes;

public class CtwOverlaySetPlayerClassRequest {
    private final String name;
    private final int classCode;
    private final String privateGameId;

    public CtwOverlaySetPlayerClassRequest(String name, int classCode, String privateGameId){
        this.name = name;
        this.classCode = classCode;
        this.privateGameId = privateGameId;
    }

    public String getName(){return this.name;}

    public int getClassCode(){return this.classCode;}

    public String getPrivateGameId(){return this.privateGameId;}
}
