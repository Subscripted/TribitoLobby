package dev.subscripted.tribitolobby.utils.syntax;

import dev.subscripted.tribitolobby.utils.sound.SoundLibrary;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class WrongCommandArgument implements Listener {

    private final String prefix = "§7[§6Lobby§7]§r ";
    private final SoundLibrary soundLibrary;

    public WrongCommandArgument(SoundLibrary soundLibrary) {
        this.soundLibrary = soundLibrary;
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        String msg = event.getMessage();
        String[] args = msg.split(" ");
        Player player = event.getPlayer();

        if (Bukkit.getServer().getHelpMap().getHelpTopic(args[0]) == null) {
            event.setCancelled(true);
            player.sendMessage(prefix + "§cDieser Command existiert nicht!");
            soundLibrary.playLibrarySound(player, SoundLibrary.CustomSound.NOT_ALLOWED, 1f, 1f);
        }
    }
}

