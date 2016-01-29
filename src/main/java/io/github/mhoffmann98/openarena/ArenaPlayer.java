package io.github.mhoffmann98.openarena;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ArenaPlayer {
    private Player bukkitPlayer;
    private boolean active, ready, inLobbyMode;
    private Kit kit;
    private int kills;
    private PlayerBackup backup;
    private ArenaGame ag;

    public ArenaPlayer(Player bukkitPlayer) {
	this.bukkitPlayer = bukkitPlayer;
	this.backup = new PlayerBackup(bukkitPlayer);
	this.ag = OpenArena.getCurrentArenaGame();

	active = true;
	ready = false;
	inLobbyMode = true;
    }

    // returns the bukkit player object
    public Player getBukkitPlayer() {
	return bukkitPlayer;
    }

    // resets Inventory, hunger, exp and health of the player
    public void reset() {
    }

    // equips the player with the tools for the lobby
    public void equipTools() {
	bukkitPlayer.getInventory().setContents(
		OpenArena.lobbyMenu.getContents());
    }

    // equips the player with his kit items at the start of the game
    public void equipKitItems() {
    }

    // sets the kit for this player
    public void setKit() {
    }
    
    public void ready() {
	ready = true;
	ag.checkIfAllReady();
    }
    
    public void notReady() {
	ready = false;
    }
    
    public void teleportBack() {
	// resets the player's values.
	backup.resetPlayer();

	bukkitPlayer.teleport(backup.getLocation());
    }

    public boolean isReady(){
	return ready;
    }
}
