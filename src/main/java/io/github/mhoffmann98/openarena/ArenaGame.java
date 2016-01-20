package io.github.mhoffmann98.openarena;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public final class ArenaGame {
	private ArrayList<ArenaPlayer> players = new ArrayList<>();
	private ArenaWorld world;
	private boolean gameActive;
	
	public ArenaGame(){
		this.world = new ArenaWorld();
		gameActive = false;
	}

	public void start(){
		Bukkit.broadcastMessage(ChatColor.YELLOW + "called: ArenaGame.start()");
		world.setPlayerNumbers(players.size());
		for(int i=0; i<players.size(); i++){
			ArenaPlayer player = players.get(i);
			
			player.reset();
			player.giveKitItems();
			player.getPlayer().teleport(world.generateSpawn(i));
			
			Bukkit.broadcastMessage(ChatColor.YELLOW + "Teleported player to spawn");
		}
		
		Bukkit.getWorld(world.getWorldName()).getWorldBorder().setSize(world.getMapSize()*2);
		gameActive = true;
	}
	
	public void stop(){
		gameActive = false;
	}
	
	public void addPlayer(ArenaPlayer player){
		Bukkit.broadcastMessage(ChatColor.YELLOW + "Added player to ArenaGame");
		this.players.add(player);
		Bukkit.broadcastMessage(player.getPlayer().getName() + " is equipped");
		player.equipTools();
		player.getPlayer().teleport(world.getMapSpawn());
	}
	
	public String generateWorld(int mapSize) {
		return world.generate(mapSize);
	}
	
	public boolean isGameActive() {
		return gameActive;
	}

	public ArrayList<ArenaPlayer> getPlayers() {
		return players;
	}
	
	public ArenaPlayer getArenaPlayer(Player player){
		for(ArenaPlayer p : players){
			if(p.getPlayer().equals(player))
				return p;
		}
		return null;
	}
	
	public boolean playerInArena(Player player){
		for(ArenaPlayer p : players){
			if(p.getPlayer().equals(player))
				return true;
		}
		return false;
	}
}
