package io.github.mhoffmann98.openarena;

import io.github.mhoffmann98.openarena.generators.ChestGenerator;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class OpenArena extends JavaPlugin {
	private ChestGenerator cg;
	private ArenaGame ag;
	private ArrayList<Kit> activeKits;
	private Inventory kitGUI;
	
	@Override
	public void onEnable() {
		new EventListener(this);
		this.ag = new ArenaGame();
		
		Kit archer = new Kit(Material.BOW, "Archer", "Get an early advantage over your foes with a set of a bow and arrows.");
		Kit lumberjack = new Kit(Material.IRON_AXE, "Lumberjack", "Waste less time gathering wood.");
		Kit cultivist = new Kit(Material.WHEAT, "Cultivist", "Easily provide food for yourself and your team.");
		Kit demolitionist =new Kit(Material.TNT, "Demolitionist", "Wreak havoc with explosives.");
		Kit knight = new Kit(Material.SADDLE, "Knight", "Ride horses!");
		Kit alchemist = new Kit(Material.BREWING_STAND_ITEM, "Alchemist", "Use your brewing stand to create powerful potions.");
		Kit enchanter = new Kit(Material.ENCHANTMENT_TABLE, "Enchanter", "Enchant your items.");
		Kit spy = new Kit(Material.COMPASS, "Spy", "Your compass gives you more information about your foes.");
		Kit berserker = new Kit(Material.POTION, "Berserker", "Killing mobs and players gives you extra strength.");

		archer.addStartingItem(Material.BOW, 1);
		archer.addStartingItem(Material.ARROW, 24);
		lumberjack.addStartingItem(Material.IRON_AXE, 1);
		demolitionist.addStartingItem(Material.TNT, 16);
		demolitionist.addStartingItem(Material.FLINT_AND_STEEL, 1);
		knight.addStartingItem(Material.SADDLE, 1);
		alchemist.addStartingItem(Material.BREWING_STAND_ITEM, 1);
		enchanter.addStartingItem(Material.ENCHANTMENT_TABLE, 1);
		
		activeKits = new ArrayList<Kit>();
		activeKits.add(archer);
		activeKits.add(lumberjack);
		activeKits.add(cultivist);
		activeKits.add(demolitionist);
		activeKits.add(knight);
		activeKits.add(alchemist);
		activeKits.add(enchanter);
		activeKits.add(spy);
		activeKits.add(berserker);

		this.getCommand("oa").setExecutor(new OACommandExecutor(this));
		this.getCommand("back").setExecutor(new OACommandExecutor(this));
		
		Inventory kitGUI = Bukkit.createInventory(null, 9, "Select a kit");
		for (Kit kit : activeKits) {
			ItemStack kitItem = new ItemStack(kit.getRepr(), 1);
			ItemMeta kitItemMeta = kitItem.getItemMeta();

			kitItemMeta.setDisplayName(kit.getName());
			
			List<String> lore = kit.getFormattedLore();
			lore.add(kit.getDescription());

			kitItemMeta.setLore(lore);

			kitItem.setItemMeta(kitItemMeta);
			kitGUI.addItem(kitItem);
		}
		
	}

	public ArrayList<Kit> getActiveKits() {
		return activeKits;
	}

	@Override
	public void onDisable() {
	}
	
	public ArenaGame getArenaGame() {
		return ag;
	}

	public Inventory getKitGUI() {
		return kitGUI;
	}
}
