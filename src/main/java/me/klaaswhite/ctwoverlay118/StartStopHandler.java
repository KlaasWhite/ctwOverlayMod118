package me.klaaswhite.ctwoverlay118;

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
import net.minecraft.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class StartStopHandler {
    public static void startOverlay(){
        if(Objects.equals(CtwOverlay118Client.gamePrivateId, "")){
            MinecraftClient client = MinecraftClient.getInstance();
            if( client.player != null) {
                ArrayList<CtwPlayer> array = new ArrayList<>();
                ClientPlayNetworkHandler clientPlayNetworkHandler = client.player.networkHandler;
                Collection<PlayerListEntry> playerCollection = clientPlayNetworkHandler.getPlayerList();
                for (PlayerListEntry playerListEntry : playerCollection) {
                    String playerName = playerListEntry.getProfile().getName();
                    Team scoreboardTeam = playerListEntry.getScoreboardTeam();
                    String teamName = "";
                    if (scoreboardTeam != null) {
                        teamName = scoreboardTeam.getName();
                    }
                    Text displayName = playerListEntry.getDisplayName();
                    int ctwClassNumber = 0;
                    if (displayName != null) {
                        System.out.println(displayName);
                        if (displayName.getSiblings().size() != 0){
                            ctwClassNumber = (int) displayName.getString().charAt(0);
                        }

                    }
                    String finalTeamName = teamName;
                    int finalCtwClassNumber = ctwClassNumber;
                    CtwPlayer ctwPlayer = new CtwPlayer(playerName, finalTeamName, finalCtwClassNumber);
                    array.add(ctwPlayer);
                }
                Gson gson = new Gson();
                final String json = gson.toJson(array);
                System.out.println(json);

                try {
                    String response = Utility.doApiCall(String.format("startGame/%s", client.player.getName().getString()), json);
                    StartResponse responseObject = gson.fromJson(response, StartResponse.class);
                    if (responseObject.getCode() == 200) {
                        CtwOverlay118Client.gamePrivateId = responseObject.getPrivateId();
                        client.inGameHud.addChatMessage(MessageType.SYSTEM, new LiteralText("Game has started on your in game username"), client.player.getUuid());
                        Utility.doApiCall("setCtwMode", String.format("{\"enabled\": \"%s\", \"gameId\": \"%s\"}", CtwOverlay118Client.ctwMode ? "true" : "false", CtwOverlay118Client.gamePrivateId));
                    }
                } catch (IOException e) {
                    // print error to console
                    e.printStackTrace();
                }
            }
        }
    }

    public static void stopOverlay(){
        if(CtwOverlay118Client.gamePrivateId != null && !CtwOverlay118Client.gamePrivateId.equals("")) {
            try {
                Utility.doApiCall("endGame", String.format("{\"gameId\": \"%s\"}", CtwOverlay118Client.gamePrivateId));
                CtwOverlay118Client.gamePrivateId = "";
            } catch (IOException e) {
                // print error to console
                e.printStackTrace();
            }
        }
    }
}
