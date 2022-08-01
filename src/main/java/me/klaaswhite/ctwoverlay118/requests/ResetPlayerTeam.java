package me.klaaswhite.ctwoverlay118.requests;

import me.klaaswhite.ctwoverlay118.Utility;
import me.klaaswhite.ctwoverlay118.classes.CtwOverlayResetPlayerTeamRequest;
import me.klaaswhite.ctwoverlay118.client.CtwOverlay118Client;
import me.klaaswhite.ctwoverlay118.runnables.SendApiCall;

public class ResetPlayerTeam {
    public static void execute(String playerName, String teamName){
        CtwOverlayResetPlayerTeamRequest request = new CtwOverlayResetPlayerTeamRequest(playerName, CtwOverlay118Client.privateGameId, teamName);
        Thread worker = new Thread(new SendApiCall("ResetPlayerTeam", Utility.jsonIng(request)));
        worker.start();
    }
}
