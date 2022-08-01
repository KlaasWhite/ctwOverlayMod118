package me.klaaswhite.ctwoverlay118.client;

import me.klaaswhite.ctwoverlay118.gui.ConnectGui;
import me.klaaswhite.ctwoverlay118.gui.ConnectScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import java.util.UUID;

@Environment(EnvType.CLIENT)
public class CtwOverlay118Client implements ClientModInitializer {

    public static String url = "https://ctwoverlay.herokuapp.com";
    public static boolean ctwMode = true;
    public static UUID[] knowPlayers;
    public static String privateGameId = "";

    @Override
    public void onInitializeClient() {
//        KeyBinding jKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
//                "key.mcCtwOverlay.start", // The translation key of the keybinding's name
//                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
//                GLFW.GLFW_KEY_J, // The keycode of the key
//                "category.mcCtwOverlay.start" // The translation key of the keybinding's category.
//        ));
//
//        KeyBinding kKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
//                "key.mcCtwOverlay.stop", // The translation key of the keybinding's name
//                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
//                GLFW.GLFW_KEY_K, // The keycode of the key
//                "category.mcCtwOverlay.stop" // The translation key of the keybinding's category.
//        ));

        KeyBinding lKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Open gui", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_L, // The keycode of the key
                "CtwOverlay" // The translation key of the keybinding's category.
        ));

//        ClientTickEvents.END_CLIENT_TICK.register(client -> {
//            while (jKey.wasPressed()) {
//                startOverlay();
//            }
//        });
//
//        ClientTickEvents.END_CLIENT_TICK.register(client -> {
//            while (kKey.wasPressed()) {
//                StartStopHandler.stopOverlay();
//            }
//        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (lKey.wasPressed()) {
                MinecraftClient.getInstance().setScreen(new ConnectScreen(new ConnectGui()));
            }
        });


    }
}
