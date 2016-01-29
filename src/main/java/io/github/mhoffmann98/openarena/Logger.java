package io.github.mhoffmann98.openarena;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Logger {
    private ChatColor color;
    private String prefix;

    public Logger(ChatColor color, String prefix) {
	this.color = color;
	this.prefix = prefix;
    }

    public void log(String msg) {
	Bukkit.broadcastMessage(color + prefix + ChatColor.stripColor(msg));
    }
}
