package me.klaaswhite.ctwoverlay118;

import com.google.gson.Gson;
import me.klaaswhite.ctwoverlay118.classes.CtwOverlayPlayer;
import me.klaaswhite.ctwoverlay118.client.CtwOverlay118Client;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.network.MessageType;
import net.minecraft.scoreboard.Team;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;

public class Utility {

    public static String doApiCall(String endPoint, String body) throws IOException {
        URL url = new URL(String.format(CtwOverlay118Client.url + "/api/mc/%s", endPoint));
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        con.setDoInput(true);
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = body.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
           return (response.toString());
        }
    }

    public static Collection<PlayerListEntry> getPlayerList (){
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            ClientPlayNetworkHandler clientPlayNetworkHandler = client.player.networkHandler;
            return clientPlayNetworkHandler.getPlayerList();
        }
        return null;
    }

    public static ArrayList<CtwOverlayPlayer> getCtwOverlayPlayerList(){
        MinecraftClient client = MinecraftClient.getInstance();
        ArrayList<CtwOverlayPlayer> array = new ArrayList<>();
        if( client.player != null) {
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
                    if (displayName.getSiblings().size() != 0) {
                        ctwClassNumber = (int) displayName.getString().charAt(0);
                    }

                }
                String finalTeamName = teamName;
                int finalCtwClassNumber = ctwClassNumber;
                CtwOverlayPlayer ctwPlayer = new CtwOverlayPlayer(playerName, finalTeamName, finalCtwClassNumber);
                array.add(ctwPlayer);
            }
        }
        return array;
    }



    public static String jsonIng(Object object){
        Gson gson = new Gson();
        return gson.toJson(object);
    }


}
