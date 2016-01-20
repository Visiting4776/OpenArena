package io.github.mhoffmann98.openarena.generators;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public final class ChestGenerator {
	private Location mapCenter;
	private int mapSize;
	private FileConfiguration cfg;
	
	public ChestGenerator(Location mapCenter, int mapSize, FileConfiguration cfg){
		this.mapCenter = mapCenter;
		this.mapSize = mapSize;
		this.cfg = cfg;
	}
	
	public Location generateChest(){
		return null;
	}
}
