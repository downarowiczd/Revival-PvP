package de.gamenetz.revival.listener;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

public class GameModListener implements Listener{
	
	@EventHandler
	public void onGM(PlayerGameModeChangeEvent event){
		if(event.getNewGameMode() == GameMode.CREATIVE){
			event.setCancelled(true);
		}
	}

}
