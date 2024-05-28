package dev.subscripted.tribitolobby.events;

import dev.subscripted.tribitolobby.events.buildmode.BuildModeHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class ItemDrop implements Listener {

    private final BuildModeHandler buildModeHandler;

    public ItemDrop(BuildModeHandler buildModeHandler) {
        this.buildModeHandler = buildModeHandler;
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (!buildModeHandler.isInBuildMode(player)) {
            event.setCancelled(true);
        }
    }
}
