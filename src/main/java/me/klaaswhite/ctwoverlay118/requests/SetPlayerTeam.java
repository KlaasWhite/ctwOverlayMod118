package me.klaaswhite.ctwoverlay118.requests;

import me.klaaswhite.ctwoverlay118.Utility;
import me.klaaswhite.ctwoverlay118.classes.CtwOverlaySetPlayerTeamRequest;
import me.klaaswhite.ctwoverlay118.client.CtwOverlay118Client;
import me.klaaswhite.ctwoverlay118.runnables.SendApiCall;
import net.minecraft.client.network.PlayerListEntry;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

public class SetPlayerTeam {

    public static void execute(String name, String teamName){

        Collection<PlayerListEntry> playerList = Utility.getPlayerList();
        AtomicBoolean userOnline = new AtomicBoolean(false);
        if (playerList != null) {
            playerList.forEach(playerListEntry -> {

                if (playerListEntry.getProfile().getName().equals(name)) {
                    userOnline.set(true);
                }
            });
            if (userOnline.get()) {
                CtwOverlaySetPlayerTeamRequest request = new CtwOverlaySetPlayerTeamRequest(name, teamName, CtwOverlay118Client.privateGameId);
                Thread worker = new Thread(new SendApiCall("setPlayerTeam", Utility.jsonIng(request)));
                worker.start();
            }
        }
    }
}
