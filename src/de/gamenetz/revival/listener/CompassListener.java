package de.gamenetz.revival.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import de.gamenetz.revival.RevivalData;
import de.gamenetz.revival.User;

public class CompassListener implements Listener{
	
	@EventHandler
	public void onCompassClick(PlayerInteractEvent event){
		if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(event.getPlayer().getItemInHand().getType() == Material.COMPASS){
				User u = new User(event.getPlayer().getUniqueId());
				RevivalData.getNearestPlayer(u);
			}
		}
	}

}
