package io.github.mhoffmann98.openarena;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public final class OACommandExecutor implements CommandExecutor {
	private final OpenArena plugin;

	public OACommandExecutor(OpenArena plugin) {
		this.plugin = plugin; // Store the plugin in situations where you need
								// it.
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("oa")) {
			if (args.length < 1)
				return false;
			switch (args[0]) {
			case "join":
				Bukkit.broadcastMessage(ChatColor.GREEN + sender.getName() + " is now ready.");

				Bukkit.broadcastMessage(ChatColor.YELLOW + "Generating new world...");
				plugin.getArenaGame().generateWorld(500);

				Player p = (Player) sender;
				plugin.getArenaGame().addPlayer(new ArenaPlayer(p));
				break;

			default:
				sender.sendMessage(ChatColor.RED + "Unknown Command!");
			}

			return true;
		} else if (cmd.getName().equalsIgnoreCase("back")) {
			Player p = (Player) sender;
			p.sendMessage("Teleporting back...");
			Location loc = new Location(Bukkit.getWorld("world"), 0, 100, 0);
			p.teleport(loc);

			return true;
		}
		return false;
	}
}
