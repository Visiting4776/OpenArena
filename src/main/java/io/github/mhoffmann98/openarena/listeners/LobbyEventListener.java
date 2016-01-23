package io.github.mhoffmann98.openarena.listeners;

import io.github.mhoffmann98.openarena.OpenArena;

import org.bukkit.event.Listener;

public final class LobbyEventListener implements Listener{
    private OpenArena plugin;

    public LobbyEventListener(OpenArena plugin) {
	this.plugin = plugin;
	plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

}
