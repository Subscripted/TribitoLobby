package dev.subscripted.tribitolobby.utils.bossbar;

import dev.subscripted.tribitolobby.Main;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class BossbarManager {
    private final BossBar bossbar;
    private final List<String> bossbarTexts = new ArrayList<>();
    private int currentIndex = 0;

    public BossbarManager() {
        bossbar = Bukkit.createBossBar("", BarColor.WHITE, BarStyle.SOLID);

        bossbarTexts.add("§x§C§B§A§0§4§2§lᴛ§x§C§E§9§7§3§E§lʀ§x§D§1§8§E§3§B§lɪ§x§D§4§8§5§3§7§lʙ§x§D§7§7§C§3§3§lɪ§x§D§9§7§4§3§0§lᴛ§x§D§C§6§B§2§C§lᴏ§x§D§F§6§2§2§8§l.§x§E§2§5§9§2§5§lɴ§x§E§5§5§0§2§1§lᴇ§x§E§8§4§7§1§D§lᴛ §x§E§B§3§E§1§A§lɴ§x§E§E§3§5§1§6§lᴇ§x§F§1§2§C§1§2§lᴛ§x§F§3§2§4§0§F§lᴢ§x§F§6§1§B§0§B§lᴡ§x§F§9§1§2§0§7§lᴇ§x§F§C§0§9§0§4§lʀ§x§F§F§0§0§0§0§lᴋ");
        bossbarTexts.add("§9§lᴅɪꜱᴄᴏʀᴅ §8| §8§l/ᴅɪꜱᴄᴏʀᴅ");
        bossbarTexts.add("§x§E§7§F§1§E§9§lɴ§x§C§C§D§C§C§D§lᴇ§x§B§1§C§7§B§2§lᴡ §x§9§5§B§2§9§6§lꜱ§x§7§A§9§D§7§B§lᴇ§x§5§F§8§7§5§F§lʀ§x§4§4§7§2§4§3§lᴠ§x§2§8§5§D§2§8§lᴇ§x§0§D§4§8§0§C§lʀ");

        startBossbarScheduler();
    }

    private void startBossbarScheduler() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (currentIndex >= bossbarTexts.size()) {
                    currentIndex = 0;
                }

                String text = bossbarTexts.get(currentIndex);
                bossbar.setTitle(text);
                bossbar.setVisible(true);
                currentIndex++;
            }
        }.runTaskTimer(Main.getInstance(), 0, 20 * 1);
    }

    public void addPlayerToBossbar(Player player) {
        bossbar.addPlayer(player);
    }

    public void removePlayerFromBossbar(Player player) {
        bossbar.removePlayer(player);
    }

    public void resetBossbar() {
        bossbar.removeAll();
    }
}
