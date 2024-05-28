package dev.subscripted.tribitolobby.utils.gui;

import dev.subscripted.tribitolobby.Main;
import dev.subscripted.tribitolobby.utils.ItemBuilder;
import dev.subscripted.tribitolobby.utils.gui.items.PlayerHiderManager;
import dev.subscripted.tribitolobby.utils.sound.SoundLibrary;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

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
        ItemBuilder  playerHiderActive= new ItemBuilder(Material.RED_DYE).setDisplayName(LobbyMenusText.PLAYERHIDER_ACTIVE_NAME.getText());

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

        player.openInventory(inventory);

        new BukkitRunnable() {
            int index = 0;
            final List<ItemBuilder> corners = Arrays.asList(
                    new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).setDisplayName(" "),
                    new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).setDisplayName(" "),
                    new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).setDisplayName(" "),
                    new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).setDisplayName(" ")
            );

            @Override
            public void run() {
                if (index >= corners.size()) {
                    List<Integer> grayIndexes = Arrays.asList(
                            1, 2, 3, 4, 5, 6, 7, 16, 17, 26, 35, 44, 43, 52, 51, 50, 49, 48, 47, 46, 37, 36, 27, 18, 9, 10
                    );
                    placeGrayGlassPane(player, inventory, grayIndexes, 0);
                    cancel();
                    return;
                }

                int cornerIndex = cornerIndexes.get(index);
                inventory.setItem(cornerIndex, corners.get(index).build());
                index++;
                soundLibrary.playLibrarySound(player, SoundLibrary.CustomSound.GUI_SOUND, 1f, 2f);
            }
        }.runTaskTimer(Main.getInstance(), 0L, 4L);
    }

    private void placeGrayGlassPane(Player player, Inventory inventory, List<Integer> grayIndexes, int currentIndex) {
        SoundLibrary soundLibrary = new SoundLibrary();
        if (currentIndex >= grayIndexes.size()) {
            inventory.setItem(22, new ItemBuilder(Material.CHEST).build());
            inventory.setItem(21, new ItemBuilder(Material.END_PORTAL_FRAME).setDisplayName(LobbyMenusText.SERVER_SELECTOR_ITEM_NAME.getText())
                    .addLoreLine(LobbyMenusText.EMPTY_LORE.getText())
                    .addLoreLine(LobbyMenusText.SERVER_SELECTOR_ITEM_LORE_LINE_2.getText())
                    .addLoreLine(LobbyMenusText.EMPTY_LORE.getText())
                    .build());

            inventory.setItem(23, new ItemBuilder(Material.GOLD_INGOT).build());
            soundLibrary.playLibrarySound(player, SoundLibrary.CustomSound.LOADING_FINISHED, 1f, 5f);
            return;
        }

        int grayIndex = grayIndexes.get(currentIndex);
        inventory.setItem(grayIndex, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setDisplayName(" ").build());
        soundLibrary.playLibrarySound(player, SoundLibrary.CustomSound.GLASS_GUI_BUILD, 1f, 1f);

        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () ->
                placeGrayGlassPane(player, inventory, grayIndexes, currentIndex + 1), 1L);
    }

    public void openServerSelector(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 45, LobbyMenusText.SERVER_SELECTOR_TITLE.getText());

    }
}
