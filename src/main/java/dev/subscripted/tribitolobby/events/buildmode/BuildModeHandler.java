package dev.subscripted.tribitolobby.events.buildmode;

import dev.subscripted.tribitolobby.utils.gui.LobbyMenus;
import dev.subscripted.tribitolobby.utils.sound.SoundLibrary;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class BuildModeHandler {
    private final Set<UUID> playersInBuildMode = new HashSet<>();
    private final String prefix = "§7[§6Lobby§7]§r ";
    private final SoundLibrary soundLibrary;
    private final LobbyMenus lobbyMenus;

    public BuildModeHandler(SoundLibrary soundLibrary, LobbyMenus lobbyMenus) {
        this.soundLibrary = soundLibrary;
        this.lobbyMenus = lobbyMenus;
    }

    public boolean isInBuildMode(final Player player) {
        return playersInBuildMode.contains(player.getUniqueId());
    }

    public void addPlayerToBuildMode(Player player) {
        if (!isInBuildMode(player)) {
            player.getInventory().clear();
        }
        player.sendMessage(prefix + "§aDu bist nun im Baumodus.");
        playersInBuildMode.add(player.getUniqueId());
        soundLibrary.playLibrarySound(player, SoundLibrary.CustomSound.ACTIVATED, 1f, 3f);
    }

    public void removePlayerFromBuildMode(Player player) {
        player.sendMessage(prefix + "§cDu bist nun nicht mehr im Baumodus.");
        soundLibrary.playLibrarySound(player, SoundLibrary.CustomSound.DEACTIVATED, 1f, 3f);
        playersInBuildMode.remove(player.getUniqueId());
        player.getInventory().clear();
        lobbyMenus.setLobbyItems(player);
    }
}
