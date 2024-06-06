package dev.subscripted.tribitolobby.events.buildmode.command;

import dev.subscripted.tribitolobby.events.buildmode.BuildModeHandler;
import dev.subscripted.tribitolobby.utils.sound.SoundLibrary;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


public class BuildModeCommand implements CommandExecutor, TabExecutor {

    private final BuildModeHandler buildModeHandler;
    private final String prefix = "§7[§6Lobby§7]§r ";
    private final SoundLibrary soundLibrary;

    public BuildModeCommand(BuildModeHandler buildModeHandler, SoundLibrary soundLibrary) {
        this.buildModeHandler = buildModeHandler;
        this.soundLibrary = soundLibrary;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Du musst ein Spieler sein, um diesen Befehl ausführen zu können!");
            return true;
        }
        if (sender.hasPermission("lobby.buildmode.*") || sender.isOp()) {
            Player player = (Player) sender;
            if (args.length == 0 || args.length > 1) {
                player.sendMessage(prefix + "§cBitte verwende /baumodus <an|aus>.");
                soundLibrary.playLibrarySound(player, SoundLibrary.CustomSound.WRONG_USAGE, 20f, 2f);
                return true;
            }
            switch (args[0]) {
                case "an":
                    if (buildModeHandler.isInBuildMode(player)) {
                        player.sendMessage(prefix + "§eDu bist bereits im Baumodus!");
                        soundLibrary.playLibrarySound(player, SoundLibrary.CustomSound.QUESTION, 1f, 1f);
                        return true;
                    }
                    buildModeHandler.addPlayerToBuildMode(player);
                    break;

                case "aus":
                    if (!buildModeHandler.isInBuildMode(player)) {
                        player.sendMessage(prefix + "§cDu bist nicht im Baumodus!");
                        soundLibrary.playLibrarySound(player, SoundLibrary.CustomSound.QUESTION, 1f, 1f);
                        return true;
                    }
                    buildModeHandler.removePlayerFromBuildMode(player);
                    break;
            }
            return true;
        } else {
            return false;
        }

    }


    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (args.length == 1 && (sender.hasPermission("lobby.buildmode.*") || sender.isOp())) {
            List<String> list = new ArrayList<>();
            list.add("an");
            list.add("aus");
            return list;
        } else {
            return new ArrayList<>();
        }
    }
}


