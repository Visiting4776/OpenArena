package io.github.mhoffmann98.openarena;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class ArenaGame {
    private HashMap<Player, ArenaPlayer> players;
    private World world;
    private String worldName;
    private Location worldSpawn;
    private int mapSize;
    private boolean isActive;

    public ArenaGame() {
	worldName = generateWorld();
	players = new HashMap<Player, ArenaPlayer>();
    }

    // Creates a new temporary world and returns the name.
    private String generateWorld() {
	return null;
    }

    // adds an ArenaPlayer object to the players HashMap. Returns true if a new element was added, otherwise false.
    public boolean addPlayer(Player bukkitPlayer) {
	if (players.containsKey(bukkitPlayer)) // Player is already in the game!
	    return false;
	players.put(bukkitPlayer, new ArenaPlayer(bukkitPlayer));
	Bukkit.broadcastMessage(ChatColor.YELLOW
		+ "A new player was added to the ArenaGame.");
	return true;
    }

    // removes an ArenaPlayer object from the players HashMap. Returns true if an element was removed, otherwise false.
    public boolean removePlayer(Player bukkitPlayer) {
	if (!players.containsKey(bukkitPlayer)) // No such player in the game!
	    return false;
	players.remove(bukkitPlayer);
	Bukkit.broadcastMessage(ChatColor.YELLOW
		+ "A player has left the ArenaGame.");
	return true;
    }
}
