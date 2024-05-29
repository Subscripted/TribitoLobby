package dev.subscripted.tribitolobby.events;

import dev.subscripted.tribitolobby.events.buildmode.BuildModeHandler;
import dev.subscripted.tribitolobby.utils.ItemBuilder;
import dev.subscripted.tribitolobby.utils.gui.LobbyMenus;
import dev.subscripted.tribitolobby.utils.gui.LobbyMenusText;
import dev.subscripted.tribitolobby.utils.gui.items.PlayerHiderManager;
import dev.subscripted.tribitolobby.utils.sound.SoundLibrary;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
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
    private final BuildModeHandler buildModeHandler;

    public ItemInteract(PlayerHiderManager playerHiderManager, SoundLibrary soundLibrary, LobbyMenus lobbyMenus, BuildModeHandler buildModeHandler) {
        this.playerHiderManager = playerHiderManager;
        this.soundLibrary = soundLibrary;
        this.lobbyMenus = lobbyMenus;
        this.buildModeHandler = buildModeHandler;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getHand() != EquipmentSlot.HAND) return;

        ItemStack item = event.getItem();
        if (item != null && event.getAction().isRightClick()) {
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

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = event.getClickedBlock();
            if (block != null) {
                Material material = block.getType();
                if (isDoor(material) || isTrapDoor(material)) {
                    if (!buildModeHandler.isInBuildMode(player)) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    private boolean isDoor(Material material) {
        switch (material) {
            case OAK_DOOR:
            case SPRUCE_DOOR:
            case BIRCH_DOOR:
            case JUNGLE_DOOR:
            case ACACIA_DOOR:
            case DARK_OAK_DOOR:
            case IRON_DOOR:
            case CRIMSON_DOOR:
            case CHERRY_DOOR:
            case WARPED_DOOR:
            case MANGROVE_DOOR:
                return true;
            default:
                return false;
        }
    }

    private boolean isTrapDoor(Material material) {
        switch (material) {
            case OAK_TRAPDOOR:
            case SPRUCE_TRAPDOOR:
            case BIRCH_TRAPDOOR:
            case JUNGLE_TRAPDOOR:
            case ACACIA_TRAPDOOR:
            case DARK_OAK_TRAPDOOR:
            case IRON_TRAPDOOR:
            case CRIMSON_TRAPDOOR:
            case CHERRY_TRAPDOOR:
            case WARPED_TRAPDOOR:
            case MANGROVE_TRAPDOOR:
                return true;
            default:
                return false;
        }
    }
}
