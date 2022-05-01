package me.klaaswhite.ctwoverlay118.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import me.klaaswhite.ctwoverlay118.StartStopHandler;
import me.klaaswhite.ctwoverlay118.client.CtwOverlay118Client;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

import java.awt.*;

public class ConnectGui extends LightweightGuiDescription {
    public ConnectGui(){
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(256, 200);
        root.setInsets(Insets.ROOT_PANEL);

        WLabel title = new WLabel(new LiteralText("CTW Overlay"), 0x3200FF);
        root.add(title, 5, 0, 1, 1);

        WLabel urlLabel = new WLabel(new LiteralText("Url"), 0x000000);
        root.add(urlLabel, 0, 1, 1, 2);

        WTextField urlField = new WTextField();
        root.add(urlField, 0, 2, 8, 1);

        urlField.setText(CtwOverlay118Client.url);

        WButton urlButton = new WButton(new TranslatableText("Confirm"));
        root.add(urlButton, 10, 2, 3, 1);
        urlButton.setOnClick(() -> {
            CtwOverlay118Client.url = urlField.getText();
        });

//        WLabel ctwModeLabel = new WLabel(new LiteralText("Ctw mode"), 0xFFFFFF);
//        root.add(ctwModeLabel, 0, 3, 2, 1);
        WToggleButton ctwModeToggle = new WToggleButton(new LiteralText("Ctw mode"));
        root.add(ctwModeToggle, 0, 4, 2, 1);
        ctwModeToggle.setToggle(CtwOverlay118Client.ctwMode);
        ctwModeToggle.setOnToggle((mode) -> {
            CtwOverlay118Client.ctwMode = mode;
        });

        boolean connected = CtwOverlay118Client.gamePrivateId != null && !CtwOverlay118Client.gamePrivateId.equals("");

        WLabel label = new WLabel(new LiteralText("Status: " + (connected ? "Connected" : "Not connected")), connected ? 0x04FF00 : 0xff0c00);
        root.add(label, 0, 8, 2, 1);

        WButton connectButton = new WButton(new TranslatableText(connected? "Disconnect" : "Connect"));
        root.add(connectButton, 0, 9, 4, 1);
        connectButton.setOnClick(() -> {
            if (connected){
                StartStopHandler.stopOverlay();
            } else {
                StartStopHandler.startOverlay();
            }
            MinecraftClient.getInstance().setScreen(new ConnectScreen(new ConnectGui()));
        });




        root.validate(this);
    }
}
