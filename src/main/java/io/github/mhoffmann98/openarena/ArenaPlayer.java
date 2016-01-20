package io.github.mhoffmann98.openarena;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public final class ArenaPlayer {
	private String kit;
	private int score;
	private boolean isActive, isReady;
	private Player p;
	
	public ArenaPlayer(Player p){
		Bukkit.broadcastMessage(ChatColor.YELLOW + "New area player object created");
		this.p = p;
		this.kit = kit;
		score = 0;
		isActive = true;
		setReady(false);
	}
	
	public Inventory equipTools(){
		Bukkit.broadcastMessage(ChatColor.YELLOW + "called: ArenaPlayer.equip()");
		Inventory currentInv = p.getInventory();
		
		ItemStack kitSelector = new ItemStack(Material.CHEST, 1);
		ItemStack notReady = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.RED.getData());

		ItemMeta kitSelectorMeta = kitSelector.getItemMeta();
		kitSelectorMeta.setDisplayName("Kit Selector");
		kitSelector.setItemMeta(kitSelectorMeta);

		ItemMeta notReadyMeta = notReady.getItemMeta();
		notReadyMeta.setDisplayName("Not Ready");
		notReady.setItemMeta(notReadyMeta);
		
		this.reset();
		p.getInventory().addItem(kitSelector);
		p.getInventory().addItem(notReady);
		p.setGameMode(GameMode.ADVENTURE);
		
		return currentInv;
	}

	public Player getPlayer() {
		return p;
	}

	public void setKit(String kitName) {
		this.kit = kitName;
		Bukkit.broadcastMessage(ChatColor.GREEN + getPlayer().getName() + " is now a " + kitName + "!");
	}

	public void reset(){
		p.getInventory().clear();
		p.getInventory().setHelmet(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setBoots(null);
		
		p.setGameMode(GameMode.SURVIVAL);
		
		p.setHealth(20);
		p.setSaturation(20);
		p.setExhaustion(0);
		
		p.setExp(0);
		p.setLevel(0);
	}
	
	public void giveKitItems(){
		//Kitname: kit
	}
	
	public void setReady(boolean ready) {
		this.isReady = ready;
	}

	public boolean isReady() {
		return isReady;
	}
}
