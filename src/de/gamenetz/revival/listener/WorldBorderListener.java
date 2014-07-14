package de.gamenetz.revival.listener;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import de.gamenetz.revival.RevivalData;
import de.gamenetz.revival.User;

public class WorldBorderListener implements Listener{
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerMove(PlayerMoveEvent event){
		Player p = event.getPlayer();
		User u = new User(p.getUniqueId());
		if(event.getTo().distance(p.getWorld().getSpawnLocation()) >= RevivalData.BORDER_RADIUS){
			p.teleport(event.getFrom());
			u.sendMessage(RevivalData.MAIN_PREFIX + "&aDu hast die Welt-Grenze erreicht!");
			p.playSound(p.getEyeLocation(), Sound.ANVIL_USE, 1.0F, 1.0F);
			event.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerTeleport(PlayerTeleportEvent event){
		Player p = event.getPlayer();
		User u = new User(p.getUniqueId());
		if(event.getTo().distance(p.getWorld().getSpawnLocation()) >= RevivalData.BORDER_RADIUS){
			u.sendMessage(RevivalData.MAIN_PREFIX + "&aDu wolltest dich aus der Welt-Grenze teleportieren!");
			u.sendMessage(RevivalData.MAIN_PREFIX + "&aDeshalb wurde der Teleport abgebrochen!");
			p.playSound(p.getEyeLocation(), Sound.ANVIL_USE, 1.0F, 1.0F);
			event.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPortalEnter(PlayerPortalEvent event){
		Player p = event.getPlayer();
		User u = new User(p.getUniqueId());
		u.sendMessage(RevivalData.MAIN_PREFIX + "&aDie Portale sind zurzeit deaktiviert!");
		u.sendMessage(RevivalData.MAIN_PREFIX + "&aAlle Brausachen bekommst du durch Mob-Drops von Zombies und Skeletten!");
		event.setCancelled(true);
	}

}
