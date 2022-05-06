package me.klaaswhite.ctwoverlay118;

import me.klaaswhite.ctwoverlay118.client.CtwOverlay118Client;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.PlayerListEntry;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

public class Utility {

    public static String doApiCall(String endPoint, String data) throws IOException {
        URL url = new URL(String.format(CtwOverlay118Client.url + "/api/mc/%s", endPoint));
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        con.setDoInput(true);
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = data.getBytes(StandardCharsets.UTF_8);
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

//    public static String doApiCall(String endPoint, String data) throws IOException {
//        HttpClient httpClient = HttpClientBuilder.create().build();
//        try {
//            HttpPost request = new HttpPost(String.format("http://localhost:8080/api/%s", endPoint));
//            StringEntity params = new StringEntity(data);
//            System.out.println(params);
//            request.addHeader("content-type", "application/json");
//            request.setEntity(params);
//            HttpResponse response = httpClient.execute(request);
//            String entity = response.getEntity().
//            System.out.println(entity);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return endPoint;
//    }

    public static Collection<PlayerListEntry> getPlayerList (){
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            ClientPlayNetworkHandler clientPlayNetworkHandler = client.player.networkHandler;
            return clientPlayNetworkHandler.getPlayerList();
        }
        return null;
    }



//    public static void playerSpawned(UUID id) {
//        AtomicReference<String> playerName = new AtomicReference<>();
//        MinecraftClient client = MinecraftClient.getInstance();
//        if( client.player != null && client.world != null) {
//            ClientPlayNetworkHandler clientPlayNetworkHandler = client.player.networkHandler;
//            Collection<PlayerListEntry> playerCollection = clientPlayNetworkHandler.getPlayerList();
//
//            playerCollection.forEach(playerListEntry -> {
//                if (playerListEntry.getProfile().getId() == id) {
//                    playerName.set(playerListEntry.getProfile().getName());
//                }
//            });
//
//        }
//        if(playerName.get() == null) {
//            playerName.set("unknown");}
//        String message =  " Player joined " + playerName.get();
//        System.out.println(message);
//        LiteralText text = new LiteralText(message);
//        if( client.player != null) {
//            client.inGameHud.addChatMessage(MessageType.SYSTEM, text, client.player.getUuid());
//        }
//    }

//    public static void doRequest(CtwPlayer[] players) {
//        URL url = new URL("http://localhost:8080");
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        try {
//            connection.setDoOutput(true);
//            connection.setChunkedStreamingMode(0);
//
//            OutputStream out = new BufferedOutputStream(connection.getOutputStream());
//            writeStream(out);
//
//            InputStream in = new BufferedInputStream(connection.getInputStream());
//            readStream(in);
//        } finally {
//            connection.disconnect();
//        }
//        //print players
//        for(CtwPlayer player : players){
//            System.out.println(player.getName() + " " + player.getTeamName());
//        }
//    }
//
//
//
//    public void updateList(){
//        MinecraftClient client = MinecraftClient.getInstance();
//        if( client.player != null && client.world != null){
//            ClientPlayNetworkHandler clientPlayNetworkHandler = client.player.networkHandler;
//            Collection<PlayerListEntry> playerCollection = clientPlayNetworkHandler.getPlayerList();
//            CtwPlayer[] ctwPlayers = new CtwPlayer[playerCollection.size()];
//            AtomicInteger i = new AtomicInteger();
//            playerCollection.forEach(playerListEntry -> {
//                String name = playerListEntry.getProfile().getName();
//                Scoreboard scoreboard = client.world.getScoreboard();
//                Team team = scoreboard.getPlayerTeam(name);
//                String teamName;
//                if (team != null) {teamName = team.getName();} else { teamName = "noTeam";}
//                CtwPlayer player = new CtwPlayer(name, teamName, "temp");
//                ctwPlayers[i.get()] = player;
//                i.getAndIncrement();
//            });
//            //print ctwPlayers
//            for(CtwPlayer player : ctwPlayers){
//                System.out.println(player.getName() + " " + player.getTeamName());
//            }
//            doRequest(ctwPlayers);
//        }
//    }
}
