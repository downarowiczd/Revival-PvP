package de.gamenetz.revival.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkUnloadEvent;

import de.gamenetz.revival.RevivalData;

public class UnloadChunkListener implements Listener{
	
	@EventHandler
	public void onChunk(ChunkUnloadEvent event){
		if(RevivalData.BOSS_ZOMBIE_LOCATION != null){
			if(RevivalData.BOSS_ZOMBIE_LOCATION.getChunk() == event.getChunk()){
				event.getChunk().load();
				event.setCancelled(true);
			}
			
		}
	}

}
