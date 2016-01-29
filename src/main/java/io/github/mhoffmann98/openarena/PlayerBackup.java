package io.github.mhoffmann98.openarena;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class PlayerBackup {
    private Player player;

    private Inventory inventory;
    private Location location;
    private double health;
    private float exp;
    private int foodLvl, lvl, itemSlot;
    private GameMode gm;

    public PlayerBackup(Player player) {
	this.player = player;

	gm = player.getGameMode();
	inventory = player.getInventory();
	location = player.getLocation();
	health = player.getHealth();
	exp = player.getExp();
	foodLvl = player.getFoodLevel();
	lvl = player.getLevel();
	itemSlot = player.getInventory().getHeldItemSlot();

	player.sendMessage("A backup for you was created. (" + lvl
		+ " | " + health + " | " + foodLvl + " | "
		+ itemSlot);
    }

    public void resetPlayer() {
	
	player.setHealth(health);
	player.setFoodLevel(foodLvl);

	player.setGameMode(gm);
	
	// resets the players experience.
	player.setLevel(lvl);
	player.setExp(exp);

	// resets the players Inventory
	player.getInventory().setContents(inventory.getContents());
	player.getInventory().setHeldItemSlot(itemSlot);
	player.updateInventory();
    }
    
    public Location getLocation(){
	return location;
    }
}
