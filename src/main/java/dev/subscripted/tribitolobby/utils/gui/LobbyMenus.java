package dev.subscripted.tribitolobby.utils.gui;

import dev.subscripted.tribitolobby.utils.ItemBuilder;
import dev.subscripted.tribitolobby.utils.gui.items.PlayerHiderManager;
import dev.subscripted.tribitolobby.utils.sound.SoundLibrary;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;
import java.util.List;

public class LobbyMenus {

    private final PlayerHiderManager playerHiderManager;

    public LobbyMenus(PlayerHiderManager playerHiderManager) {
        this.playerHiderManager = playerHiderManager;
    }

    public void setLobbyItems(Player player) {
        ItemBuilder locator = new ItemBuilder(Material.COMPASS).setDisplayName(LobbyMenusText.TELEPORTER_NAME.getText());
        ItemBuilder settings = new ItemBuilder(Material.COMPARATOR).setDisplayName(LobbyMenusText.SETTINGS_NAME.getText());
        ItemBuilder playerHiderInactive = new ItemBuilder(Material.LIME_DYE).setDisplayName(LobbyMenusText.PLAYERHIDER_INACTIVE_NAME.getText());
        ItemBuilder playerHiderActive = new ItemBuilder(Material.RED_DYE).setDisplayName(LobbyMenusText.PLAYERHIDER_ACTIVE_NAME.getText());

        

        player.getInventory().setItem(4, locator.build());
        player.getInventory().setItem(3, settings.build());
        if (playerHiderManager.hasPlayerHiderActive(player)) {
            player.getInventory().setItem(0, playerHiderActive.build());
        } else {
            player.getInventory().setItem(0, playerHiderInactive.build());
        }
    }

    public void openLobbyMenu(Player player) {
        SoundLibrary soundLibrary = new SoundLibrary();
        Inventory inventory = Bukkit.createInventory(player, 54, LobbyMenusText.LOBBY_MENU_TITLE.getText());

        List<Integer> cornerIndexes = Arrays.asList(0, 8, 45, 53);

        for (int i = 0; i < cornerIndexes.size(); i++) {
            int cornerIndex = cornerIndexes.get(i);
            ItemBuilder cornerPane = new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).setDisplayName(" ");
            inventory.setItem(cornerIndex, cornerPane.build());
        }

        List<Integer> grayIndexes = Arrays.asList(
                1, 2, 3, 4, 5, 6, 7, 16, 17, 26, 35, 44, 43, 52, 51, 50, 49, 48, 47, 46, 37, 36, 27, 18, 9, 10
        );
        for (int grayIndex : grayIndexes) {
            ItemBuilder grayPane = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setDisplayName(" ");
            inventory.setItem(grayIndex, grayPane.build());
        }

        inventory.setItem(22, new ItemBuilder(Material.CHEST).build());
        inventory.setItem(21, new ItemBuilder(Material.END_PORTAL_FRAME).setDisplayName(LobbyMenusText.SERVER_SELECTOR_ITEM_NAME.getText())
                .addLoreLine(LobbyMenusText.EMPTY_LORE.getText())
                .addLoreLine(LobbyMenusText.SERVER_SELECTOR_ITEM_LORE_LINE_2.getText())
                .addLoreLine(LobbyMenusText.EMPTY_LORE.getText())
                .build());
        inventory.setItem(23, new ItemBuilder(Material.GOLD_INGOT).build());

        player.openInventory(inventory);
        soundLibrary.playLibrarySound(player, SoundLibrary.CustomSound.GUI_OPEN, 1f, 2f);
    }

    public void openServerSelector(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 45, LobbyMenusText.SERVER_SELECTOR_TITLE.getText());

    }
}