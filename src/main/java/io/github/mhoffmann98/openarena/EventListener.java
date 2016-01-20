package io.github.mhoffmann98.openarena;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

public final class EventListener implements Listener {
	private OpenArena plugin;

	public EventListener(OpenArena plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void playerMove(PlayerMoveEvent e) {
		if (!plugin.getArenaGame().isGameActive()) {
			e.getPlayer().setFoodLevel(20);
		}
		// Location from = e.getFrom();
		// Location to = e.getTo();
		//
		// if (to.getBlockX() == from.getBlockX() && to.getBlockZ() == from.getBlockZ())
		// return; // The player hasn't moved
		//
		// Player player = e.getPlayer();
		// player.sendMessage(ChatColor.RED + "You are not allowed to move!");
		// player.teleport(from);
	}

	@EventHandler
	public void playerInteract(PlayerInteractEvent e) {
		if (!plugin.getArenaGame().isGameActive() && plugin.getArenaGame().playerInArena(e.getPlayer())) { // Game isnt active and player is in the arena game
			ItemStack notReady = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.RED.getData());
			ItemMeta notReadyMeta = notReady.getItemMeta();
			notReadyMeta.setDisplayName("Not Ready");
			notReady.setItemMeta(notReadyMeta);

			ItemStack ready = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GREEN.getData());
			ItemMeta readyMeta = ready.getItemMeta();
			readyMeta.setDisplayName("Ready");
			ready.setItemMeta(readyMeta);

			ItemStack item = e.getItem();
			Material itemMaterial = e.getMaterial();

			ItemMeta itemMeta = e.getPlayer().getItemInHand().getItemMeta();
			if (item.equals(notReady)) {
				e.getPlayer().sendMessage(ChatColor.GREEN + "You are ready!");
				itemMeta.setDisplayName("Ready");
				e.getPlayer().getItemInHand().setItemMeta(itemMeta);

				e.getPlayer().setItemInHand(ready);
				plugin.getArenaGame().getArenaPlayer(e.getPlayer()).setReady(true);

				// see if everybody is ready
				for (ArenaPlayer player : plugin.getArenaGame().getPlayers()) {
					if (!player.isReady())
						return;
				}
				Bukkit.broadcastMessage(ChatColor.GREEN + "Everybody is ready!");
				plugin.getArenaGame().start();

			} else if (item.equals(ready)) {
				e.getPlayer().sendMessage(ChatColor.RED + "You are not ready!");
				itemMeta.setDisplayName("Not Ready");
				e.getPlayer().getItemInHand().setItemMeta(itemMeta);

				e.getPlayer().setItemInHand(notReady);
				plugin.getArenaGame().getArenaPlayer(e.getPlayer()).setReady(false);
			} else if (itemMaterial.equals(itemMaterial.CHEST)) {
				ArrayList<Kit> kits = plugin.getActiveKits();

				e.getPlayer().openInventory(plugin.getKitGUI());
			}

			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if(!plugin.getArenaGame().isGameActive() && e.getInventory().getName().equalsIgnoreCase("Select a kit") && plugin.getArenaGame().playerInArena((Player)e.getWhoClicked())) {
			Player player = (Player) e.getWhoClicked();
			e.setCancelled(true);
			if (!e.getCurrentItem().hasItemMeta()) {
				return;
			}

			ArrayList<String> kitNames = new ArrayList<String>();
			for (Kit kit : plugin.getActiveKits())
				kitNames.add(kit.getName());

			String kitName = e.getCurrentItem().getItemMeta().getDisplayName();

			if (kitNames.contains(kitName)) {
				player.sendMessage(ChatColor.GREEN + "Your kit: " + kitName);

				ArenaPlayer p = plugin.getArenaGame().getArenaPlayer(player);
				p.setKit(kitName);

				player.setMetadata("kit", new FixedMetadataValue(plugin, kitName));
				player.closeInventory();
			}
			return;
		}
	}

	@EventHandler
	public void takeDamage(EntityDamageEvent e) {
		if (!plugin.getArenaGame().isGameActive() && e.getEntity() instanceof Player && plugin.getArenaGame().playerInArena((Player)e.getEntity()))
			e.setCancelled(true);
	}

	@EventHandler
	public void dropItem(PlayerDropItemEvent e) {
		if (!plugin.getArenaGame().isGameActive() && plugin.getArenaGame().playerInArena(e.getPlayer()))
			e.setCancelled(true);
	}
}
