package me.klaaswhite.ctwoverlay118.requests;

import com.google.gson.Gson;
import me.klaaswhite.ctwoverlay118.Utility;
import me.klaaswhite.ctwoverlay118.classes.CtwOverlayPlayer;
import me.klaaswhite.ctwoverlay118.classes.CtwOverlayStartOverlayRequest;
import me.klaaswhite.ctwoverlay118.classes.CtwOverlayStartOverlayResponse;
import me.klaaswhite.ctwoverlay118.client.CtwOverlay118Client;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.MessageType;
import net.minecraft.text.LiteralText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class StartOverlay {
    public static void execute(){
        if(Objects.equals(CtwOverlay118Client.privateGameId, "")){
            MinecraftClient client = MinecraftClient.getInstance();
            if( client.player != null) {
                ArrayList<CtwOverlayPlayer> playerList = Utility.getCtwOverlayPlayerList();

                try {
                    CtwOverlayStartOverlayRequest request = new CtwOverlayStartOverlayRequest(CtwOverlay118Client.ctwMode, client.player.getName().getString(), playerList, "1.1");
                    String response = Utility.doApiCall("startOverlay", Utility.jsonIng(request));
                    Gson gson = new Gson();
                    CtwOverlayStartOverlayResponse responseObject = gson.fromJson(response, CtwOverlayStartOverlayResponse.class);
                    if (responseObject.getCode() == 200) {
                        CtwOverlay118Client.privateGameId = responseObject.getPrivateGameId();
                        client.inGameHud.addChatMessage(MessageType.SYSTEM, new LiteralText("Game has started on your in game username"), client.player.getUuid());
                    } else if (responseObject.getCode() == 410){
                        client.inGameHud.addChatMessage(MessageType.SYSTEM, new LiteralText("Wrong version of the mod, please update"), client.player.getUuid());
                    }
                } catch (IOException e) {
                    // print error to console
                    e.printStackTrace();
                }
            }
        }
    }
}
