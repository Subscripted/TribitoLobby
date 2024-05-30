package dev.subscripted.tribitolobby.utils.scoreboard;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;


public class PlayerScoreboard {


    public void setPlayerScoreboard(Player player) {
        String name = player.getName();
        Scoreboard scoreboard = new ScoreboardBuilder("&6&lᴛʀɪʙɪᴛᴏ ɴᴇᴛᴡᴏʀᴋ")
                .addLine("line15", "§8◂§m" + "─".repeat(15)+"§r§8▸", "", 14)
                .addLine("line14", "    §f§lᴘʟᴀʏᴇʀ", "", 13)
                .addLine("line13", "       §8» §7ɴᴀᴍᴇ: ", "§e" + name, 12)
                .addLine("line12", "       §8» §7ʀᴀɴɢ: ", "", 11)
                .addLine("line11", "       §8» §7ᴄᴏɪɴꜱ: ", "", 10)
                .addLine("line10", " ", "", 9)
                .addLine("line9", "    §f§lSTATS", "", 8)
                .addLine("line8", "       §8» §7COINTOP", "", 7)
                .addLine("line7", "       §8» §7", "", 6)
                .addLine("line6", " ", "", 5)
                .addLine("line5", " ", "", 4)
                .addLine("line4", " ", "", 3)
                .addLine("line2", " ", "", 2)
                .addLine("line1", " ", "", 1)
                .addLine("line0", " ", "", 0)
                .build();
        player.setScoreboard(scoreboard);
    }
}
