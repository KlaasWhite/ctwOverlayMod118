package me.klaaswhite.ctwoverlay118;

import me.klaaswhite.ctwoverlay118.classes.StartResponse;
import me.klaaswhite.ctwoverlay118.client.CtwOverlay118Client;
import net.minecraft.network.MessageType;
import net.minecraft.text.*;
import com.google.gson.Gson;
import me.klaaswhite.ctwoverlay118.classes.CtwPlayer;
import me.klaaswhite.ctwoverlay118.classes.StartResponse;
import me.klaaswhite.ctwoverlay118.client.CtwOverlay118Client;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.network.MessageType;
import net.minecraft.scoreboard.Team;
import net.minecraft.text.LiteralText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import java.io.IOException;

public class CharacterCheck {
    public static void checkCharacter(){
        Gson gson = new Gson();
        MinecraftClient client = MinecraftClient.getInstance();
        ArrayList<CtwPlayer> array = new ArrayList<>();
        if(client.player != null) {
            ClientPlayNetworkHandler clientPlayNetworkHandler = client.player.networkHandler;
            Collection<PlayerListEntry> playerCollection = clientPlayNetworkHandler.getPlayerList();
            final String json = gson.toJson(array);
            try {
                String response = Utility.doApiCall("charCheck", json);
                StartResponse responseObject = gson.fromJson(response, StartResponse.class);
                if (responseObject.getCode() == 200) {
                    int charCode = Integer.parseInt(responseObject.getPrivateId());
                    char c = Character.toChars(charCode)[0];
                    client.inGameHud.addChatMessage(MessageType.SYSTEM, new LiteralText(c + " is character with code " + charCode), client.player.getUuid());
                }
            } catch (IOException e) {
                // print error to console
                e.printStackTrace();
            }
        }
    }
}
