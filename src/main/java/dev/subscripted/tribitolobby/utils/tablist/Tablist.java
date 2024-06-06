package dev.subscripted.tribitolobby.utils.tablist;

import dev.subscripted.tribitolobby.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Tablist {
    public void startUpdatingTablist() {

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    String header = "§aWillkommen auf dem Server!";
                    StringBuilder footer = new StringBuilder("§bSpieler und Ping:\n");
                    player.setPlayerListHeaderFooter(header, footer.toString());
                }
            }
        }.runTaskTimer(Main.getInstance(), 0, 20 * 2);
    }
}


