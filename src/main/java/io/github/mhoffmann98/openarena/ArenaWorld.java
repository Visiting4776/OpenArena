package io.github.mhoffmann98.openarena;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public final class ArenaWorld {
	private WorldCreator wc;
	private String name;
	private int mapSize, playerCount;
	private World world;
	private Location mapSpawn;
	
	public String generate(int mapSize){
		Bukkit.broadcastMessage(ChatColor.YELLOW + "called: WorldGenerator.generateWorld()");
		this.mapSize = mapSize;
		this.playerCount = 0;

		name = "arena";
		while (Bukkit.getWorld(name) != null) {
			name += "_";
		}

		wc = new WorldCreator(name);
		world = wc.createWorld();
		world.getWorldBorder().setCenter(0,0);
		world.getWorldBorder().setSize(10);
		
		mapSpawn = new Location(world, 0, world.getHighestBlockYAt(0, 0), 0);
		
		Bukkit.broadcastMessage(ChatColor.GREEN + "Temporary world created as "+name);
		return name;
	}
	
	public boolean destroy(){
		return true;
	}
	
	public Location generateSpawn(int index) {
		Bukkit.broadcastMessage(ChatColor.YELLOW + "called: WorldGenerator.generateSpawn()");
		if(world==null)
			return null;

		double angle = 360 / playerCount * (float) index + 0.5;

		int spawnX = (int) (Math.cos(Math.toRadians(angle)) * mapSize);
		int spawnZ = (int) (Math.sin(Math.toRadians(angle)) * mapSize);
		int spawnY = world.getHighestBlockYAt((int) spawnX, (int) spawnZ) + 1;
		
		return new Location(world, spawnX, spawnY, spawnZ);
	}
	
	public Location getMapSpawn(){
		return mapSpawn; //pre-game spawn point
	}

	public String getWorldName() {
		return name;
	}
	
	public void setPlayerNumbers(int playerCount) {
		this.playerCount = playerCount;
		Bukkit.broadcastMessage(ChatColor.YELLOW+"Set total player count to "+playerCount);
	}

	public int getMapSize() {
		return mapSize;
	}
}
