package me.klaaswhite.ctwoverlay118.requests;

import com.google.gson.Gson;
import me.klaaswhite.ctwoverlay118.Utility;
import me.klaaswhite.ctwoverlay118.classes.CtwOverlayResetOverlayRequest;
import me.klaaswhite.ctwoverlay118.client.CtwOverlay118Client;
import me.klaaswhite.ctwoverlay118.runnables.SendApiCall;

public class ResetOverlay {
    public static void execute(){
        CtwOverlayResetOverlayRequest request = new CtwOverlayResetOverlayRequest(CtwOverlay118Client.privateGameId, Utility.getCtwOverlayPlayerList());
        Gson gson = new Gson();
        final String requestJson = gson.toJson(request);

        System.out.println(requestJson);
        if (request.getPlayers().size() > 0){
            Thread worker = new Thread(new SendApiCall("resetOverlay", requestJson));
            worker.start();
        }
    }
}
