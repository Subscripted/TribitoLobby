package dev.subscripted.tribitolobby.utils.gui.items;

import dev.subscripted.tribitolobby.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PlayerHiderManager {

    private final Set<UUID> lobbyHiderActive = new HashSet<>();

    public boolean hasPlayerHiderActive(Player player) {
        return lobbyHiderActive.contains(player.getUniqueId());
    }

    public void deactivatePlayerHider(Player player) {
        lobbyHiderActive.remove(player.getUniqueId());
        for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
            player.showPlayer(Main.getInstance(), otherPlayer);
        }
    }

    public void activatePlayerHider(Player player) {
        lobbyHiderActive.add(player.getUniqueId());
        for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
            player.hidePlayer(Main.getInstance(), otherPlayer);
        }
    }

    public void updatePlayerHider(Player player) {
        if (hasPlayerHiderActive(player)) {
            for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
                player.hidePlayer(Main.getInstance(), otherPlayer);
            }
        } else {
            for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
                player.showPlayer(Main.getInstance(), otherPlayer);
            }
        }
    }
}
