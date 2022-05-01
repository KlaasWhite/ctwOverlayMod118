package me.klaaswhite.ctwoverlay118.classes;

public class StartResponse {
    private final String privateId;
    private final Integer code;

    public StartResponse(String privateId, Integer code) {
        this.privateId = privateId;
        this.code = code;
    }

    public String getPrivateId() {
        return privateId;
    }

    public Integer getCode() {
        return code;
    }
}
