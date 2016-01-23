package io.github.mhoffmann98.openarena;

import org.bukkit.entity.Player;

public class ArenaPlayer {
    private Player backup, bukkitPlayer;
    private boolean active, ready;
    private Kit kit;
    private int kills;

    public ArenaPlayer(Player bukkitPlayer) {
	this.bukkitPlayer = bukkitPlayer;
    }

    // resets Inventory, hunger, exp and health of the player
    public void reset() {
    }

    // equips the player with the tools for the lobby
    public void equipTools() {
    }

    // equips the player with his kit items at the start of the game
    public void equipKitItems() {
    }

    // sets the kit for this player
    public void setKit() {
    }
}
