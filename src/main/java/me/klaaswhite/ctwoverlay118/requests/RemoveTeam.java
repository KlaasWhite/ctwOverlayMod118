package me.klaaswhite.ctwoverlay118.requests;

import me.klaaswhite.ctwoverlay118.Utility;
import me.klaaswhite.ctwoverlay118.classes.CtwOverlayRemoveTeamRequest;
import me.klaaswhite.ctwoverlay118.client.CtwOverlay118Client;
import me.klaaswhite.ctwoverlay118.runnables.SendApiCall;

public class RemoveTeam {
    public static void execute(String teamName){
        CtwOverlayRemoveTeamRequest request = new CtwOverlayRemoveTeamRequest(CtwOverlay118Client.privateGameId, teamName);
        Thread worker = new Thread(new SendApiCall("removeTeam", Utility.jsonIng(request)));
        worker.start();
    }
}
