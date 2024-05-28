package dev.subscripted.tribitolobby.events;

import dev.subscripted.tribitolobby.events.buildmode.BuildModeHandler;
import dev.subscripted.tribitolobby.utils.gui.items.PlayerHiderManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ItemMove implements Listener {

    private final BuildModeHandler buildModeHandler;

    public ItemMove(BuildModeHandler buildModeHandler) {
        this.buildModeHandler = buildModeHandler;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getClickedInventory() == null) return;
        if (!buildModeHandler.isInBuildMode(player)) {
            event.setCancelled(true);
        }
    }
}
