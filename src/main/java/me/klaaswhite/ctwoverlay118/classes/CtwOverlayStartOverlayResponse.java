package me.klaaswhite.ctwoverlay118.classes;

public class CtwOverlayStartOverlayResponse {
    private final String privateGameId;
    private final Integer code;

    public CtwOverlayStartOverlayResponse(String privateGameId, Integer code) {
        this.privateGameId = privateGameId;
        this.code = code;
    }

    public String getPrivateGameId() {
        return privateGameId;
    }

    public Integer getCode() {
        return code;
    }
}
