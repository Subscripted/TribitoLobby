package dev.subscripted.tribitolobby.utils.sound;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundLibrary {

    @Getter
    public enum CustomSound {
        NOT_ALLOWED(Sound.ENTITY_VILLAGER_NO),
        NO_PERMISSION(Sound.BLOCK_ANVIL_LAND),
        ACTIVATED(Sound.BLOCK_BEACON_ACTIVATE),
        DEACTIVATED(Sound.BLOCK_BEACON_DEACTIVATE),
        GUI_SOUND(Sound.UI_BUTTON_CLICK),
        WRONG_USAGE(Sound.UI_TOAST_IN),
        QUESTION(Sound.ENTITY_VILLAGER_TRADE),
        GLASS_GUI_BUILD(Sound.BLOCK_GLASS_PLACE),
        LOADING_FINISHED(Sound.BLOCK_LAVA_EXTINGUISH),
        LOBBY_HIDER_SWITCH(Sound.ENTITY_ARROW_HIT),
        GUI_OPEN(Sound.BLOCK_CHEST_OPEN);

        private final Sound sound;

        CustomSound(Sound sound) {
            this.sound = sound;
        }

    }

    public void playLibrarySound(Player player, CustomSound customSound, float volume, float pitch) {
        if (player != null && customSound != null) {
            player.playSound(player.getLocation(), customSound.getSound(), volume, pitch);
        }
    }

    public void playSoundForAll(CustomSound customSound, float volume, float pitch) {
        if (customSound != null) {
            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                playLibrarySound(player, customSound, volume, pitch);
            }
        }
    }
}
