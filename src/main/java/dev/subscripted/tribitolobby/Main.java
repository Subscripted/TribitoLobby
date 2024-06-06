package dev.subscripted.tribitolobby;

import dev.subscripted.tribitolobby.events.*;
import dev.subscripted.tribitolobby.events.buildmode.BuildModeHandler;
import dev.subscripted.tribitolobby.events.buildmode.command.BuildModeCommand;
import dev.subscripted.tribitolobby.utils.api.LuckpermsManager;
import dev.subscripted.tribitolobby.utils.bossbar.BossbarManager;
import dev.subscripted.tribitolobby.utils.gui.LobbyMenus;
import dev.subscripted.tribitolobby.utils.gui.items.PlayerHiderManager;
import dev.subscripted.tribitolobby.utils.scoreboard.PlayerScoreboard;
import dev.subscripted.tribitolobby.utils.sound.SoundLibrary;
import dev.subscripted.tribitolobby.utils.syntax.WrongCommandArgument;
import dev.subscripted.tribitolobby.utils.tablist.Tablist;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public final class Main extends JavaPlugin {

    @Getter
    public static Main instance;

    @Setter
    private boolean menuBuilding = false;

    PlayerHiderManager playerHiderManager = new PlayerHiderManager();
    LobbyMenus lobbyMenus = new LobbyMenus(playerHiderManager);
    SoundLibrary soundLibrary = new SoundLibrary();
    BuildModeHandler buildModeHandler = new BuildModeHandler(soundLibrary, lobbyMenus);
    Tablist tablist = new Tablist();


    @Override
    public void onEnable() {
        instance = this;
        LuckpermsManager luckpermsManager = new LuckpermsManager();
        PlayerScoreboard playerScoreboard = new PlayerScoreboard(luckpermsManager);
        registerListener();
        registerCommands();
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                playerScoreboard.updatePlayerScoreboard(player);
            }
        }, 0L, 20L * 1);
        tablist.startUpdatingTablist();
    }

    @Override
    public void onDisable() {
        BossbarManager bossbarManager = new BossbarManager();
        bossbarManager.resetBossbar();
    }

    private void registerListener() {
        BossbarManager bossbarManager = new BossbarManager();
        LuckpermsManager luckpermsManager = new LuckpermsManager();
        PlayerScoreboard playerScoreboard = new PlayerScoreboard(luckpermsManager);
        getServer().getPluginManager().registerEvents(new BlockBreake(buildModeHandler), this);
        getServer().getPluginManager().registerEvents(new BlockPlace(buildModeHandler), this);
        getServer().getPluginManager().registerEvents(new ItemMove(buildModeHandler), this);
        getServer().getPluginManager().registerEvents(new ItemDrop(buildModeHandler), this);
        getServer().getPluginManager().registerEvents(new ItemPickup(buildModeHandler), this);
        getServer().getPluginManager().registerEvents(new InventoryHover(soundLibrary), this);
        getServer().getPluginManager().registerEvents(new JoinLeave(lobbyMenus, buildModeHandler, playerHiderManager, playerScoreboard, bossbarManager), this);
        getServer().getPluginManager().registerEvents(new ItemInteract(playerHiderManager, soundLibrary, lobbyMenus, buildModeHandler), this);
        getServer().getPluginManager().registerEvents(new WrongCommandArgument(soundLibrary), this);
    }

    private void registerCommands() {
        getCommand("baumodus").setExecutor(new BuildModeCommand(buildModeHandler, soundLibrary));
        getCommand("baumodus").setTabCompleter(new BuildModeCommand(buildModeHandler, soundLibrary));
    }
}
