package me.klaaswhite.ctwoverlay118.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import me.klaaswhite.ctwoverlay118.client.CtwOverlay118Client;
import me.klaaswhite.ctwoverlay118.requests.ResetOverlay;
import me.klaaswhite.ctwoverlay118.requests.StartOverlay;
import me.klaaswhite.ctwoverlay118.requests.StopOverlay;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;


public class ConnectGui extends LightweightGuiDescription {
    public ConnectGui(){
        boolean connected = CtwOverlay118Client.privateGameId != null && !CtwOverlay118Client.privateGameId.equals("");
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(256, 200);
        root.setInsets(Insets.ROOT_PANEL);
        WLabel title = new WLabel(new LiteralText("CTW Overlay"), 0x3200FF);
        root.add(title, 5, 0, 1, 1);
        if (!connected) {
            WLabel urlLabel = new WLabel(new LiteralText("Url: " + CtwOverlay118Client.url), 0x000000);
            root.add(urlLabel, 0, 1, 1, 2);

            WTextField urlField = new WTextField();
            root.add(urlField, 0, 2, 10, 1);
            urlField.setMaxLength(100);

            WButton urlButton = new WButton(new TranslatableText("Confirm"));
            root.add(urlButton, 0, 3, 3, 1);
            urlButton.setOnClick(() -> {
                CtwOverlay118Client.url = urlField.getText();
                urlField.setText("");
                urlLabel.setText(new LiteralText("Url: " + CtwOverlay118Client.url));
            });

            WToggleButton ctwModeToggle = new WToggleButton(new LiteralText("Ctw mode"));
            root.add(ctwModeToggle, 0, 5, 2, 1);
            ctwModeToggle.setToggle(CtwOverlay118Client.ctwMode);
            ctwModeToggle.setOnToggle((mode) -> {
                CtwOverlay118Client.ctwMode = mode;
            });



            WLabel label = new WLabel(new LiteralText("Status: " + ("Not connected")), 0xff0c00);
            root.add(label, 0, 8 , 2, 1);

            WButton connectButton = new WButton(new TranslatableText("Connect"));
            root.add(connectButton, 0, 9, 4, 1);
            connectButton.setOnClick(() -> {
                StartOverlay.execute();
                MinecraftClient.getInstance().setScreen(new ConnectScreen(new ConnectGui()));
            });
        } else {
            WLabel label = new WLabel(new LiteralText("Status: " + ("Connected")), 0x04FF00 );
            root.add(label, 0, 1 , 2, 1);

            WButton resetButton = new WButton(new TranslatableText("Reset"));
            root.add(resetButton, 0, 2, 4 ,1 );
            resetButton.setOnClick(ResetOverlay::execute);

            WButton connectButton = new WButton(new TranslatableText("Disconnect"));
            root.add(connectButton, 0, 9, 4, 1);
            connectButton.setOnClick(() -> {
                StopOverlay.execute();

                MinecraftClient.getInstance().setScreen(new ConnectScreen(new ConnectGui()));
            });
        }

        root.validate(this);
    }
}
