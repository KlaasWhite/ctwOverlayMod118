package me.klaaswhite.ctwoverlay118.mixin;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
//    @Inject(method = "onPlayerSpawn", at = @At("TAIL"))
//    private void onPlayerSpawn(PlayerSpawnS2CPacket spawnPacket, CallbackInfo ci) {
//        ListHandler.playerSpawned(spawnPacket.getPlayerUuid());
//    }
}