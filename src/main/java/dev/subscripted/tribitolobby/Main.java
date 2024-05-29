package dev.subscripted.tribitolobby;

import dev.subscripted.tribitolobby.events.*;
import dev.subscripted.tribitolobby.events.buildmode.BuildModeHandler;
import dev.subscripted.tribitolobby.events.buildmode.command.BuildModeCommand;
import dev.subscripted.tribitolobby.utils.gui.LobbyMenus;
import dev.subscripted.tribitolobby.utils.gui.items.PlayerHiderManager;
import dev.subscripted.tribitolobby.utils.scoreboard.PlayerScoreboard;
import dev.subscripted.tribitolobby.utils.sound.SoundLibrary;
import dev.subscripted.tribitolobby.utils.syntax.WrongCommandArgument;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;


public final class Main extends JavaPlugin {

    @Getter
    public static Main instance;

    PlayerHiderManager playerHiderManager = new PlayerHiderManager();
    LobbyMenus lobbyMenus = new LobbyMenus(playerHiderManager);
    SoundLibrary soundLibrary = new SoundLibrary();
    BuildModeHandler buildModeHandler = new BuildModeHandler(soundLibrary, lobbyMenus);
    PlayerScoreboard playerScoreboard = new PlayerScoreboard();



    @Override
    public void onEnable() {
        instance = this;
        registerListener();
        registerCommands();
    }

    @Override
    public void onDisable() {

    }

    private void registerListener() {
        getServer().getPluginManager().registerEvents(new BlockBreake(buildModeHandler), this);
        getServer().getPluginManager().registerEvents(new BlockPlace(buildModeHandler), this);
        getServer().getPluginManager().registerEvents(new ItemMove(buildModeHandler), this);
        getServer().getPluginManager().registerEvents(new ItemDrop(buildModeHandler), this);
        getServer().getPluginManager().registerEvents(new ItemPickup(buildModeHandler), this);
        getServer().getPluginManager().registerEvents(new InventoryHover(soundLibrary), this);
        getServer().getPluginManager().registerEvents(new JoinLeave(lobbyMenus, buildModeHandler, playerHiderManager, playerScoreboard), this);
        getServer().getPluginManager().registerEvents(new ItemInteract(playerHiderManager, soundLibrary, lobbyMenus, buildModeHandler), this);
        getServer().getPluginManager().registerEvents(new WrongCommandArgument(soundLibrary), this);
    }

    private void registerCommands() {
        getCommand("baumodus").setExecutor(new BuildModeCommand(buildModeHandler, soundLibrary));
        getCommand("baumodus").setTabCompleter(new BuildModeCommand(buildModeHandler, soundLibrary));
    }
}
