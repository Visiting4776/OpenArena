package io.github.mhoffmann98.openarena;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

public class ArenaGame {
    private OpenArena plugin;
    private ArrayList<ArenaPlayer> players;
    private World world;
    private String worldName;
    private Location worldSpawn;
    private int mapSize;
    private boolean active;

    public ArenaGame(OpenArena plugin) {
	this.plugin = plugin;
	worldName = generateWorld();
	players = new ArrayList<ArenaPlayer>();
    }

    // Creates a new temporary world and returns the name.
    private String generateWorld() {
	String name = "arena";
	while (new File(plugin.getServer().getWorldContainer(), name).exists()) { // finds a name for the new world.
	    name += "_";
	}

	WorldCreator wc = new WorldCreator(name);
	world = wc.createWorld();
	world.getWorldBorder().setCenter(0, 0);
	world.getWorldBorder().setSize(10);

	worldSpawn = new Location(world, 0, world.getHighestBlockYAt(0, 0), 0);

	plugin.log("A new World has been created with the name " + name);
	return name;
    }

    // checks if the player is in the arena
    public int hasPlayer(Player p) {
	for (ArenaPlayer player : players) {
	    if (p.getUniqueId().equals(player.getBukkitPlayer().getUniqueId()))
		return players.indexOf(player);
	}
	return -1;
    }

    public ArenaPlayer getArenaPlayer(Player p) {
	for (ArenaPlayer player : players) {
	    if (p.getUniqueId().equals(player.getBukkitPlayer().getUniqueId()))
		return player;
	}
	return null;
    }

    private void deleteWorld() {
	// removes all players from the world first. normally, no players should be in the world anymore
	for (Player player : Bukkit.getServer().getOnlinePlayers()) {
	    if (!player.getWorld().equals(world))
		continue;
	    player.sendMessage("The world you were in has been deleted.");
	    player.teleport(new Location(Bukkit.getWorld("world"), 0, 100, 0));
	}
	deleteDirectory(Bukkit.getServer().getWorld(worldName).getWorldFolder());
	plugin.log("The world " + worldName + " has been deleted.");
    }

    private boolean deleteDirectory(File directory) {
	if (directory.exists()) {
	    File[] files = directory.listFiles();
	    if (null != files) {
		for (int i = 0; i < files.length; i++) {
		    if (files[i].isDirectory()) {
			deleteDirectory(files[i]);
		    } else {
			files[i].delete();
		    }
		}
	    }
	}
	return (directory.delete());
    }

    // adds an ArenaPlayer object to the players HashMap. Returns true if a new element was added, otherwise false.
    public boolean addPlayer(Player bukkitPlayer) {
	if (hasPlayer(bukkitPlayer) != -1) // Player is already in the game!
	    return false;
	players.add(new ArenaPlayer(bukkitPlayer));

	bukkitPlayer.teleport(worldSpawn); // teleports the player to the new map.
	bukkitPlayer.getInventory().setContents(plugin.lobbyMenu.getContents());
	plugin.log("A new player was added to the ArenaGame.");
	return true;
    }

    // removes an ArenaPlayer object from the players HashMap. Returns true if an element was removed, otherwise false.
    public boolean removePlayer(Player bukkitPlayer) {
	int index = hasPlayer(bukkitPlayer);
	if (index == -1) // No such player in the game!
	    return false;
	players.get(index).teleportBack(); // teleports the player back to the old world and loads his values from the backup.
	players.remove(getArenaPlayer(bukkitPlayer));
	plugin.log("A player has left the ArenaGame.");
	return true;
    }

    public boolean isActive() {
	return active;
    }

    private void start() {
	active = true;
	for (ArenaPlayer player : players) {
	    Player bukkitPlayer = player.getBukkitPlayer();
	}
    }

    public void stop() {
	deleteWorld();
	active = false;
    }

    // commented out for testing
    public void checkIfAllReady() {
//	for(ArenaPlayer player : players){
//	    if(!player.isReady())
//		return;
//	}
//	plugin.log("All players are ready. Starting game.");
//	
//	start();
    }
}
