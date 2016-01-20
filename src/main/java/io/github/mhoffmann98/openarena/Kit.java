package io.github.mhoffmann98.openarena;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class Kit {
	private Material repr;
	private String name, description;
	private HashMap<Material, Integer> startingItems;

	public Kit(Material repr, String name, String description) {
		this.repr = repr;
		this.name = name;
		this.description = description;
		
		this.startingItems = new HashMap<>();
	}

	public Material getRepr() {
		return repr;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	public void getItemsFromConfig() {
	}
	
	public void addStartingItem(Material item, int amount){
		this.startingItems.put(item, amount);
	}

	public List<String> getFormattedLore() {

		List<String> lore = new ArrayList<>();
			
		lore.add(ChatColor.BOLD + description);
		if(startingItems.isEmpty()){
			lore.add("No starting Items!");
			return lore;}
		
		lore.add("Start with:");
		
		Iterator it = startingItems.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry startingItem = (Map.Entry)it.next();
			lore.add("> "+startingItem.getValue()+"x "+startingItem.getKey().toString());
		}
		
		return lore;
	}
}
