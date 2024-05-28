package dev.subscripted.tribitolobby.events;

import dev.subscripted.tribitolobby.events.buildmode.BuildModeHandler;
import dev.subscripted.tribitolobby.utils.ItemBuilder;
import dev.subscripted.tribitolobby.utils.gui.LobbyMenus;
import dev.subscripted.tribitolobby.utils.gui.LobbyMenusText;
import dev.subscripted.tribitolobby.utils.gui.items.PlayerHiderManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeave implements Listener {

    private final LobbyMenus lobbyMenus;
    private final BuildModeHandler buildModeHandler;
    private final PlayerHiderManager playerHiderManager;

    public JoinLeave(LobbyMenus lobbyMenus, BuildModeHandler buildModeHandler, PlayerHiderManager playerHiderManager) {
        this.lobbyMenus = lobbyMenus;
        this.buildModeHandler = buildModeHandler;
        this.playerHiderManager = playerHiderManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String joinMessage = "§7[§a+§7] " + player.getName();
        event.setJoinMessage(null);
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (!onlinePlayer.equals(player)) {
                onlinePlayer.sendMessage(joinMessage);
            }
        }

        if (buildModeHandler.isInBuildMode(player)) {
            buildModeHandler.addPlayerToBuildMode(player);
        } else {
            lobbyMenus.setLobbyItems(player);

            ItemBuilder playerHiderInactive = new ItemBuilder(Material.LIME_DYE).setDisplayName(LobbyMenusText.PLAYERHIDER_INACTIVE_NAME.getText());
            ItemBuilder playerHiderActive = new ItemBuilder(Material.RED_DYE).setDisplayName(LobbyMenusText.PLAYERHIDER_ACTIVE_NAME.getText());

            if (playerHiderManager.hasPlayerHiderActive(player)) {
                player.getInventory().setItem(0, playerHiderActive.build());
            } else {
                player.getInventory().setItem(0, playerHiderInactive.build());
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String quitMessage = "§7[§c-§7] " + player.getName();
        event.setQuitMessage(null);
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (!onlinePlayer.equals(player)) {
                onlinePlayer.sendMessage(quitMessage);
            }
        }
    }
}
