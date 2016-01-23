package io.github.mhoffmann98.openarena;

import io.github.mhoffmann98.openarena.generators.ChestGenerator;
import io.github.mhoffmann98.openarena.listeners.LobbyEventListener;

import java.util.HashMap;

import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class OpenArena extends JavaPlugin {
    private ArenaGame ag;
    private ChestGenerator cg;
    private Inventory lobbyMenu, kitSelector;
    private HashMap<String, Kit> kits;

    @Override
    public void onEnable() {
	this.getCommand("oa").setExecutor(new OACommandExecutor(this));
	new LobbyEventListener(this);

	ag = new ArenaGame();
    }

    // loads all kits that are marked as active from the config file
    private void loadKits() {

    }

    // generates the Inventory GUI for the players in the lobby
    private void generateLobbyMenu() {

    }

    // returns the arena game that is currently active/in lobby mode.
    public ArenaGame getCurrentArenaGame() {
	return ag;
    }

}
