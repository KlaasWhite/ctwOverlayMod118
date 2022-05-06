package me.klaaswhite.ctwoverlay118;

import me.klaaswhite.ctwoverlay118.client.CtwOverlay118Client;
import me.klaaswhite.ctwoverlay118.runnables.SendApiCall;
import net.minecraft.network.MessageType;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;

import java.io.IOException;

public class ClassHandler {
    public static void displayNameChanged(Text oldDisplayName, Text newDisplayName, String playerUserName){
        MinecraftClient mc = MinecraftClient.getInstance();

//        mc.inGameHud.addChatMessage(MessageType.SYSTEM, new LiteralText("Old display name: " + oldDisplayName), mc.player.getUuid());
//
//        mc.inGameHud.addChatMessage(MessageType.SYSTEM, new LiteralText("New display name: " + newDisplayName), mc.player.getUuid());
//        mc.inGameHud.addChatMessage(MessageType.SYSTEM, new LiteralText("New display name string: " + newDisplayName.toString()), mc.player.getUuid());
        if (!newDisplayName.getSiblings().isEmpty()) {
            String newClass = newDisplayName.getString();
            int newClassCode = (int) newClass.charAt(0);
//            System.out.println("newclass: " + newClass);
//            System.out.println("newclassCode: " + newClassCode);
            if (newDisplayName.equals(oldDisplayName)) {
//                System.out.println("New display name equals old display name");
//                mc.inGameHud.addChatMessage(MessageType.SYSTEM, new LiteralText("New display name: " + newDisplayName + " equals old display name: " + oldDisplayName), mc.player.getUuid());
            } else {
                if (newClass.equals(playerUserName)) {
//                    System.out.println("New class equals playerUserName");
//                    mc.inGameHud.addChatMessage(MessageType.SYSTEM, new LiteralText("New class: " + newClass + " equals username: " + playerUserName), mc.player.getUuid());
                } else {
//                    mc.inGameHud.addChatMessage(MessageType.SYSTEM, new LiteralText(playerUserName + " is now: " + newClass), mc.player.getUuid());
//                    mc.inGameHud.addChatMessage(MessageType.SYSTEM, new LiteralText(playerUserName + " is now class with code: " + newClassCode), mc.player.getUuid());
//                    mc.inGameHud.addChatMessage(MessageType.SYSTEM, new LiteralText(String.format("{\"name\": \"%s\", \"class\": \"%s\", \"gameId\": \"%s\"}", playerUserName, newClassCode, CtwOverlay118Client.gamePrivateId)), mc.player.getUuid());
                    Thread worker = new Thread(new SendApiCall("classChangeNumber", String.format("{\"name\": \"%s\", \"class\": \"%s\", \"gameId\": \"%s\"}", playerUserName, newClassCode, CtwOverlay118Client.gamePrivateId)));
//                        Utility.doApiCall("classChangeNumber", String.format("{\"name\": \"%s\", \"class\": \"%s\", \"gameId\": \"%s\"}", playerUserName, newClassCode, CtwOverlay118Client.gamePrivateId));
                    worker.start();
                }
            }
        }
    }
}
