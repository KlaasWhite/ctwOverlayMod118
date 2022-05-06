package me.klaaswhite.ctwoverlay118;

import me.klaaswhite.ctwoverlay118.client.CtwOverlay118Client;
import me.klaaswhite.ctwoverlay118.runnables.SendApiCall;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.text.LiteralText;

import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

public class TeamHandler {

    public static void addPlayerToTeam(String name, String teamName){

        Collection<PlayerListEntry> playerList = Utility.getPlayerList();
        AtomicBoolean userOnline = new AtomicBoolean(false);
        if (playerList != null) {
            playerList.forEach(playerListEntry -> {
//                System.out.println("PlayerListEntry" + playerListEntry);

                if (playerListEntry.getProfile().getName().equals(name)) {
                    userOnline.set(true);
                }
            });
            if (userOnline.get()) {
                Thread worker = new Thread(new SendApiCall("addPlayerToTeam", String.format("{\"name\": \"%s\", \"team\": \"%s\", \"gameId\": \"%s\"}", name, teamName, CtwOverlay118Client.gamePrivateId)));
//                    Utility.doApiCall("addPlayerToTeam", String.format("{\"name\": \"%s\", \"team\": \"%s\", \"gameId\": \"%s\"}", name, teamName, CtwOverlay118Client.gamePrivateId));
                worker.start();
            } else {
//                System.out.println("User not online");
            }
        } else {
//            System.out.println("Player list is null");
        }
    }

    public static void removePlayerFromTeam(String name, String teamName){
        MinecraftClient client = MinecraftClient.getInstance();
        String message = name + " left " + teamName;
//        System.out.println(message);
        LiteralText text = new LiteralText(message);
        if( client.player != null) {
//            client.inGameHud.addChatMessage(MessageType.SYSTEM, text, client.player.getUuid());
        }
        Thread worker = new Thread(new SendApiCall("removePlayerFromTeam", String.format("{\"name\": \"%s\", \"gameId\": \"%s\"}", name, CtwOverlay118Client.gamePrivateId)));
//            Utility.doApiCall("removePlayerFromTeam", String.format("{\"name\": \"%s\", \"gameId\": \"%s\"}", name, CtwOverlay118Client.gamePrivateId));
        worker.start();
    }
}
