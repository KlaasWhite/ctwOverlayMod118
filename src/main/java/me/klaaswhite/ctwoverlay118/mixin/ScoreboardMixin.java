package me.klaaswhite.ctwoverlay118.mixin;

import me.klaaswhite.ctwoverlay118.TeamHandler;
import me.klaaswhite.ctwoverlay118.client.CtwOverlay118Client;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Scoreboard.class)
public class ScoreboardMixin {
    @Inject(method = "addPlayerToTeam", at = @At("TAIL"))
    private void addPlayerToTeam(String playerName, Team team, CallbackInfoReturnable<Boolean> cir) {
        if(CtwOverlay118Client.gamePrivateId != null && !CtwOverlay118Client.gamePrivateId.equals("")) {
            TeamHandler.addPlayerToTeam(playerName, team.getName());
        }
    }

    @Inject(method = "removePlayerFromTeam", at = @At("TAIL"))
    private void removePlayerFromTeam(String playerName, Team team, CallbackInfo info) {
        if(CtwOverlay118Client.gamePrivateId != null && !CtwOverlay118Client.gamePrivateId.equals("")) {
            TeamHandler.removePlayerFromTeam(playerName, team.getName());
        }
    }
}
