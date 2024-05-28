package dev.subscripted.tribitolobby.events;

import dev.subscripted.tribitolobby.utils.ItemBuilder;
import dev.subscripted.tribitolobby.utils.gui.LobbyMenus;
import dev.subscripted.tribitolobby.utils.gui.LobbyMenusText;
import dev.subscripted.tribitolobby.utils.gui.items.PlayerHiderManager;
import dev.subscripted.tribitolobby.utils.sound.SoundLibrary;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemInteract implements Listener {

    private static final Logger log = LoggerFactory.getLogger(ItemInteract.class);
    private final PlayerHiderManager playerHiderManager;
    private final SoundLibrary soundLibrary;
    private final LobbyMenus lobbyMenus;

    public ItemInteract(PlayerHiderManager playerHiderManager, SoundLibrary soundLibrary, LobbyMenus lobbyMenus) {
        this.playerHiderManager = playerHiderManager;
        this.soundLibrary = soundLibrary;
        this.lobbyMenus = lobbyMenus;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getHand() != EquipmentSlot.HAND) return;

        ItemStack item = event.getItem();
        if (item == null) return;

        if (!event.getAction().isRightClick())
            return;

        String displayName = item.getItemMeta().getDisplayName();
        switch (item.getType()) {
            case LIME_DYE:
                if (displayName.contains("§a§lPlayerhider")) {
                    playerHiderManager.activatePlayerHider(player);
                    ItemBuilder playerHiderActive = new ItemBuilder(Material.RED_DYE).setDisplayName(LobbyMenusText.PLAYERHIDER_ACTIVE_NAME.getText());
                    player.getInventory().setItem(0, playerHiderActive.build());
                    soundLibrary.playLibrarySound(player, SoundLibrary.CustomSound.LOBBY_HIDER_SWITCH, 2f, 0.5f);
                }
                break;
            case RED_DYE:
                if (displayName.contains("§c§lPlayerhider")) {
                    playerHiderManager.deactivatePlayerHider(player);
                    ItemBuilder playerHiderInactive = new ItemBuilder(Material.LIME_DYE).setDisplayName(LobbyMenusText.PLAYERHIDER_INACTIVE_NAME.getText());
                    player.getInventory().setItem(0, playerHiderInactive.build());
                    soundLibrary.playLibrarySound(player, SoundLibrary.CustomSound.LOBBY_HIDER_SWITCH, 2f, 0.5f);
                }
                break;
            case COMPASS:
                lobbyMenus.openLobbyMenu(player);
                break;
        }
    }
}
