package de.gamenetz.revival.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import de.gamenetz.revival.RevivalData;

public class RespawnListener implements Listener{
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerRespawn(PlayerRespawnEvent event){
		Location loc = RevivalData.getRandomLocation(event.getPlayer().getWorld());
		event.setRespawnLocation(loc);
		final Player p = event.getPlayer();
		Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Revival-PvP"), new Runnable() {
			@Override
			public void run() {
				RevivalData.setPlayerInventory(p);
			}
		}, 5L);
		
		
	}

}
