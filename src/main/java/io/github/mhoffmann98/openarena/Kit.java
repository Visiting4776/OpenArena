package io.github.mhoffmann98.openarena;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Kit {
    private Material repr;
    private String name, description;
    private HashMap<ItemStack, Integer> startingItems;

    // returns a formatted lore for the Kit Selector
    public List<String> getLore() {
	return null;
    }

    // loads Kit data from config
    private void loadFromConfig() {

    }
}
