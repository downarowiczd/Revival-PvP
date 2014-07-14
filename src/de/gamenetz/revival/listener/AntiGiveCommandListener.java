package de.gamenetz.revival.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class AntiGiveCommandListener implements Listener{
	
	@EventHandler
	public void onGive(PlayerCommandPreprocessEvent event){
		String msg = event.getMessage();
		msg = msg.toLowerCase();
		if(msg.startsWith("/give")){
			event.setCancelled(true);
		}
	}

}
