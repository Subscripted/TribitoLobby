package dev.subscripted.tribitolobby.utils.scoreboard;

import dev.subscripted.tribitolobby.utils.api.LuckpermsManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class PlayerScoreboard {

    private final LuckpermsManager luckpermsManager;

    public PlayerScoreboard(LuckpermsManager luckpermsManager) {
        this.luckpermsManager = luckpermsManager;
    }

    public void setPlayerScoreboard(Player player) {
        String name = player.getName();
        int onlineplayers = Bukkit.getOnlinePlayers().size();

        Scoreboard scoreboard = new ScoreboardBuilder("§x§0§0§F§F§E§0§lᴛ§x§1§2§E§B§E§2§lʀ§x§2§4§D§8§E§5§lɪ§x§3§6§C§4§E§7§lʙ§x§4§8§B§1§E§A§lɪ§x§5§A§9§D§E§C§lᴛ§x§6§C§8§9§E§E§lᴏ §x§7§F§7§6§F§1§lɴ§x§9§1§6§2§F§3§lᴇ§x§A§3§4§E§F§5§lᴛ§x§B§5§3§B§F§8§lᴡ§x§C§7§2§7§F§A§lᴏ§x§D§9§1§4§F§D§lʀ§x§E§B§0§0§F§F§lᴋ")
                .addLine("line15", "§8✦§m" + "  ".repeat(20) + "§r§8✦", "", 14)
                .addLine("line14", "     §x§E§2§F§8§F§A§lᴘ§x§E§2§F§8§F§A§lʟ§x§E§2§F§8§F§A§lᴀ§x§E§2§F§8§F§A§lʏ§x§E§2§F§8§F§A§lᴇ§x§E§2§F§8§F§A§lʀ", "", 13)
                .addLine("line13", "        §8▪ §7ɴᴀᴍᴇ: ", "§x§C§0§E§8§E§8§l" + name, 12)
                .addLine("line12", "        §8▪ §7ʀᴀɴɢ: ", "Loading...", 11)
                .addLine("line11", "        §8▪ §7ᴄᴏɪɴꜱ: ", "", 10)
                .addLine("line10", " ", "", 9)
                .addLine("line9", "     §x§E§2§F§8§F§A§lꜱ§x§E§2§F§8§F§A§lᴛ§x§E§2§F§8§F§A§lᴀ§x§E§2§F§8§F§A§lᴛ§x§E§2§F§8§F§A§lꜱ", "", 8)
                .addLine("line8", "        §8▪ §7ᴄᴏɪɴᴛᴏᴘ", "", 7)
                .addLine("line7", "        §8▪ §7ꜰʀɪᴇɴᴅꜱ", "", 6)
                .addLine("line6", " ", "", 5)
                .addLine("line5", "     §x§E§2§F§8§F§A§lꜱ§x§E§2§F§8§F§A§lᴇ§x§E§2§F§8§F§A§lʀ§x§E§2§F§8§F§A§lᴠ§x§E§2§F§8§F§A§lᴇ§x§E§2§F§8§F§A§lʀ", "", 4)
                .addLine("line4", "        §8▪ §7ᴏɴʟɪɴᴇ", "§x§C§0§E§8§E§8§l " + onlineplayers, 3)
                .addLine("line2", "§8✦§m" + "  ".repeat(20) + "§r§8✦", "", 2)
                .addLine("line1", "             §8ᴛʀɪʙɪᴛᴏ.ᴅᴇ ʟᴏʙʙʏ", "", 1)
                .addLine("line0", " ", "", 0)
                .build();

        player.setScoreboard(scoreboard);

        String group = luckpermsManager.getPlayerGroup(player.getUniqueId());
        Team team = scoreboard.getTeam("line12");
        if (team == null) {
            team = scoreboard.registerNewTeam("line12");
            team.addEntry("       §8» §7ʀᴀɴɢ: ");
        }
        team.setSuffix("§x§C§0§E§8§E§8§l" + group);
    }


    public void updatePlayerScoreboard(Player player) {
        Scoreboard scoreboard = player.getScoreboard();
        int onlineplayers = Bukkit.getOnlinePlayers().size();

        Team team = scoreboard.getTeam("line4");
        if (team == null) {
            team = scoreboard.registerNewTeam("line4");
            team.addEntry("       §8» §7ᴏɴʟɪɴᴇ: ");
        }
        team.setSuffix("§x§C§0§E§8§E§8§l " + onlineplayers);

        updatePlayerGroup(player, scoreboard);
    }

    private void updatePlayerGroup(Player player, Scoreboard scoreboard) {
        String group = luckpermsManager.getPlayerGroup(player.getUniqueId());
        Team team = scoreboard.getTeam("line12");
        if (team == null) {
            team = scoreboard.registerNewTeam("line12");
            team.addEntry("       §8» §7ʀᴀɴɢ: ");
        }
        team.setSuffix("§x§C§0§E§8§E§8§ " + group);
    }
}

