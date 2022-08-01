package me.klaaswhite.ctwoverlay118.requests;

import me.klaaswhite.ctwoverlay118.Utility;
import me.klaaswhite.ctwoverlay118.classes.CtwOverlaySetPlayerClassRequest;
import me.klaaswhite.ctwoverlay118.client.CtwOverlay118Client;
import me.klaaswhite.ctwoverlay118.runnables.SendApiCall;
import net.minecraft.text.Text;

public class SetPlayerClass {

    public static void execute(Text oldDisplayName, Text newDisplayName, String playerUserName){

        if (!newDisplayName.getSiblings().isEmpty()) {
            String newClass = newDisplayName.getString();
            int newClassCode = newClass.charAt(0);
            if (!newDisplayName.equals(oldDisplayName))  {
                if (!newClass.equals(playerUserName)) {
                    CtwOverlaySetPlayerClassRequest request = new CtwOverlaySetPlayerClassRequest(playerUserName, newClassCode, CtwOverlay118Client.privateGameId);

                    Thread worker = new Thread(new SendApiCall("setPlayerClass", Utility.jsonIng(request)));
                    worker.start();
                }
            }
        }
    }
}
