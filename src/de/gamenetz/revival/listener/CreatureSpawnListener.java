package de.gamenetz.revival.listener;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import de.gamenetz.revival.EntityManageSystem;
import de.gamenetz.revival.RevivalData;

public class CreatureSpawnListener implements Listener{
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCreatureSpawn(CreatureSpawnEvent event){
		if(event.getSpawnReason()==SpawnReason.NATURAL || event.getSpawnReason()==SpawnReason.SPAWNER || event.getSpawnReason()==SpawnReason.SPAWNER_EGG){
			if(event.getEntityType()==EntityType.ZOMBIE){
				if(event.getLocation().distance(event.getLocation().getWorld().getSpawnLocation()) >= RevivalData.BORDER_RADIUS){
					EntityManageSystem.spawnRandomLevelZombie(RevivalData.getRandomLocation(event.getLocation().getWorld()));
				}else{
					EntityManageSystem.spawnRandomLevelZombie(event.getLocation());
				}
				event.setCancelled(true);
			}
			
			if(event.getEntityType()==EntityType.SKELETON){
				if(event.getLocation().distance(event.getLocation().getWorld().getSpawnLocation()) >= RevivalData.BORDER_RADIUS){
					EntityManageSystem.spawnRandomLevelSkelett(RevivalData.getRandomLocation(event.getLocation().getWorld()));
				}else{
					EntityManageSystem.spawnRandomLevelSkelett(event.getLocation());
				}
				event.setCancelled(true);
			}
		}
	}

}
