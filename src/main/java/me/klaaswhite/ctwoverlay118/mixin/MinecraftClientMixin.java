package me.klaaswhite.ctwoverlay118.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.MessageType;
import net.minecraft.text.LiteralText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.klaaswhite.ctwoverlay118.StartStopHandler.stopOverlay;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(method="setWorld", at=@At("HEAD"))
    private void setWorld(ClientWorld world, CallbackInfo ci){
        MinecraftClient mc = MinecraftClient.getInstance();
        if(mc.player != null) {
            stopOverlay();
        }
    }
}
