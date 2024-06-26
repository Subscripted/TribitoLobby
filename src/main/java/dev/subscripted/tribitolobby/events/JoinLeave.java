package dev.subscripted.tribitolobby.events;

import dev.subscripted.tribitolobby.Main;
import dev.subscripted.tribitolobby.events.buildmode.BuildModeHandler;
import dev.subscripted.tribitolobby.utils.ItemBuilder;
import dev.subscripted.tribitolobby.utils.bossbar.BossbarManager;
import dev.subscripted.tribitolobby.utils.gui.LobbyMenus;
import dev.subscripted.tribitolobby.utils.gui.LobbyMenusText;
import dev.subscripted.tribitolobby.utils.gui.items.PlayerHiderManager;
import dev.subscripted.tribitolobby.utils.scoreboard.PlayerScoreboard;
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
    private final PlayerScoreboard playerScoreboard;
    private final BossbarManager bossbarManager;

    public JoinLeave(LobbyMenus lobbyMenus, BuildModeHandler buildModeHandler, PlayerHiderManager playerHiderManager, PlayerScoreboard playerScoreboard, BossbarManager bossbarManager) {
        this.lobbyMenus = lobbyMenus;
        this.buildModeHandler = buildModeHandler;
        this.playerHiderManager = playerHiderManager;
        this.playerScoreboard = playerScoreboard;
        this.bossbarManager = bossbarManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        bossbarManager.addPlayerToBossbar(player);
        playerScoreboard.setPlayerScoreboard(player);
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
                for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
                    player.hidePlayer(Main.getInstance(), otherPlayer);
                }
            } else {
                player.getInventory().setItem(0, playerHiderInactive.build());
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        bossbarManager.removePlayerFromBossbar(player);
        String quitMessage = "§7[§c-§7] " + player.getName();
        event.setQuitMessage(null);
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (!onlinePlayer.equals(player)) {
                onlinePlayer.sendMessage(quitMessage);
            }
        }
    }
}
