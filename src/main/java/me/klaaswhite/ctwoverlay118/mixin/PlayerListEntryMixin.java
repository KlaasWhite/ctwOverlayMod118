package me.klaaswhite.ctwoverlay118.mixin;

import com.mojang.authlib.GameProfile;
import me.klaaswhite.ctwoverlay118.client.CtwOverlay118Client;
import me.klaaswhite.ctwoverlay118.requests.SetPlayerClass;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerListEntry.class)
public class PlayerListEntryMixin {

    @Shadow
    private Text displayName;

    @Final
    @Shadow
    private GameProfile profile;

//    @Inject(method = "getDisplayName", at = @At("TAIL"))
//    private void getDisplayName(CallbackInfoReturnable<Boolean> cir) {
//        System.out.println("getDisplayName");
//        MinecraftClient mc = MinecraftClient.getInstance();
//        if (mc.player != null) {
//            mc.inGameHud.addChatMessage(MessageType.SYSTEM, new LiteralText("getPrefix"), mc.player.getUuid());
//        }
//    }

    @Inject(method = "setDisplayName", at = @At("HEAD"))
    public void setDisplayName(Text newName, CallbackInfo info) {
        try {
            if (CtwOverlay118Client.privateGameId != null && !CtwOverlay118Client.privateGameId.equals("") && CtwOverlay118Client.ctwMode) {
                MinecraftClient mc = MinecraftClient.getInstance();
                Text oldName = this.displayName;
                if (mc.player != null) {
                    if (!(newName == null || oldName == null))  {
                        SetPlayerClass.execute(oldName, newName, this.profile.getName());
                    }
                }
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
