package io.github.mhoffmann98.openarena.listeners;

import io.github.mhoffmann98.openarena.ArenaGame;
import io.github.mhoffmann98.openarena.ArenaPlayer;
import io.github.mhoffmann98.openarena.OpenArena;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public final class LobbyEventListener implements Listener {
    private OpenArena plugin;
    private ArenaGame ag;

    public LobbyEventListener(OpenArena plugin) {
	this.plugin = plugin;
	ag = plugin.getCurrentArenaGame();
	plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void LobbyItemInteract(PlayerInteractEvent e) {
	Player player = e.getPlayer();
	if (ag.isActive() || ag.hasPlayer(player) == -1)
	    return; // the game is either not in lobby phase or the player is not in the game
	
	ArenaPlayer arenaPlayer = ag.getArenaPlayer(player);
	ItemStack notReady = plugin.getReadyItems()[0];
	ItemStack ready = plugin.getReadyItems()[1];

	ItemStack item = e.getItem();
	
	if(item.equals(notReady)){
	    player.sendMessage("You are ready!");
	    arenaPlayer.ready();
	    player.getInventory().setItemInHand(ready);
	    
	} else if (item.equals(ready)) {
	    player.sendMessage("You are not ready!");
	    arenaPlayer.notReady();
	    player.getInventory().setItemInHand(notReady);
	}

	e.setCancelled(true);

    }
}
