package me.klaaswhite.ctwoverlay118.requests;

import me.klaaswhite.ctwoverlay118.Utility;
import me.klaaswhite.ctwoverlay118.classes.CtwOverlayStopOverlayRequest;
import me.klaaswhite.ctwoverlay118.client.CtwOverlay118Client;
import me.klaaswhite.ctwoverlay118.runnables.SendApiCall;

public class StopOverlay {
    public static void execute(){
        if(CtwOverlay118Client.privateGameId != null && !CtwOverlay118Client.privateGameId.equals("")) {
            CtwOverlayStopOverlayRequest request = new CtwOverlayStopOverlayRequest(CtwOverlay118Client.privateGameId);
            Thread worker = new Thread(new SendApiCall("stopOverlay", Utility.jsonIng(request)));
            worker.start();
            CtwOverlay118Client.privateGameId = "";
        }
    }
}
