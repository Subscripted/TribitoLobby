package dev.subscripted.tribitolobby.events;

import dev.subscripted.tribitolobby.events.buildmode.BuildModeHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreake implements Listener {

    private final BuildModeHandler buildModeHandler;
    private final String prefix = "§7[§6Lobby§7]§r ";

    public BlockBreake(BuildModeHandler buildModeHandler) {
        this.buildModeHandler = buildModeHandler;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!buildModeHandler.isInBuildMode(event.getPlayer())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(prefix + "§cDu darfst das hier nicht!");
        }
    }
}
