package io.github.mhoffmann98.openarena;

import io.github.mhoffmann98.openarena.generators.ChestGenerator;
import io.github.mhoffmann98.openarena.listeners.LobbyEventListener;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class OpenArena extends JavaPlugin {
    private static ArenaGame ag;
    private ChestGenerator cg;
    private Logger logger;
    public static Inventory kitSelector, lobbyMenu;
    private HashMap<String, Kit> kits;

    @Override
    public void onEnable() {
	this.getCommand("oa").setExecutor(new OACommandExecutor(this));
	new LobbyEventListener(this);

	logger = new Logger(ChatColor.YELLOW, "OpenArena > ");
	ag = new ArenaGame(this);

	this.lobbyMenu = generateLobbyMenu();
    }

    @Override
    public void onDisable() {
	ag.stop();
    }

    // loads all kits that are marked as active from the config file
    private void loadKits() {

    }

    // generates the Inventory GUI for the players in the lobby
    private Inventory generateLobbyMenu() {
	Inventory inv = Bukkit.getServer().createInventory(null,
		InventoryType.PLAYER);

	inv.setItem(0, getKitSelector());
	inv.setItem(8, getReadyItems()[0]);

	return inv;
    }

    public ItemStack getKitSelector() {
	ItemStack kitSelector = new ItemStack(Material.CHEST, 1);

	ItemMeta kitSelectorMeta = kitSelector.getItemMeta();
	kitSelectorMeta.setDisplayName("Kit Selector");
	kitSelector.setItemMeta(kitSelectorMeta);
	return kitSelector;
    }

    //this returns an array of the two items that the user can toggle to be ready or not.
    public ItemStack[] getReadyItems() {
	ItemStack[] readyItems = new ItemStack[2];

	ItemStack notReady = new ItemStack(Material.STAINED_GLASS_PANE, 1,
		DyeColor.RED.getData());
	ItemMeta notReadyMeta = notReady.getItemMeta();
	notReadyMeta.setDisplayName("Not Ready");
	notReady.setItemMeta(notReadyMeta);

	ItemStack ready = new ItemStack(Material.STAINED_GLASS_PANE, 1,
		DyeColor.GREEN.getData());
	ItemMeta readyMeta = ready.getItemMeta();
	readyMeta.setDisplayName("Ready");
	ready.setItemMeta(readyMeta);

	readyItems[0] = notReady;
	readyItems[1] = ready;

	return readyItems;
    }

    // returns the arena game that is currently active/in lobby mode.
    public static ArenaGame getCurrentArenaGame() {
	return ag;
    }

    public void log(String msg) {
	logger.log(msg);
    }
}
