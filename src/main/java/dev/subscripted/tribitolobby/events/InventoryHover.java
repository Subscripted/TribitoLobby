package dev.subscripted.tribitolobby.events;

import dev.subscripted.tribitolobby.utils.sound.SoundLibrary;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class InventoryHover implements Listener {

    private final SoundLibrary soundLibrary;

    public InventoryHover(SoundLibrary soundLibrary) {
        this.soundLibrary = soundLibrary;
    }

    @EventHandler
    public void onInventoryHeld(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        soundLibrary.playLibrarySound(player, SoundLibrary.CustomSound.GUI_SOUND, 1f, 2f);
    }
}
