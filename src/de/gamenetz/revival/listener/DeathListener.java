package de.gamenetz.revival.listener;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import de.gamenetz.revival.CombatChecker;
import de.gamenetz.revival.RevivalData;
import de.gamenetz.revival.User;

public class DeathListener implements Listener{
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerDeath(PlayerDeathEvent event){
		event.setDeathMessage(null);
		event.setDroppedExp(event.getDroppedExp() * RevivalData.XP_AMPLIFIER);
		Player death = event.getEntity().getPlayer();
		User deathUser = new User(death.getUniqueId());
		deathUser.addDeaths(1);
		if(CombatChecker.isInCombat(death.getName())){
			CombatChecker.removeFromCombat(death.getName());
		}
		if(event.getEntity().getKiller() instanceof Player){
			Player killer = event.getEntity().getKiller();
			if(killer.getUniqueId().toString().equals(death.getUniqueId().toString())){
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8" + death.getName() + " &chat sich aus Dummheit selber getötet!"));
			}else{
			User killerUser = new User(killer.getUniqueId());
			killerUser.addKills(1);
			Random rnd = new Random();
			int z = 1 + rnd.nextInt(8 - 1 + 1);
			 if(z==1){
				 Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8"+ death.getName()+ " &cwurde von &8"+killer.getName()+" &czu Hackfleisch verarbeitet."));
			 }
			 if(z==2){
				 Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8"+killer.getName()+" &chat &8"+death.getName()+" &cgezeigt wer der Babo ist."));
			 }
			 if(z==3){
				 Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8"+killer.getName()+" &chat &8"+death.getName()+" &cgezeigt wo der Hammer hängt."));
			 }
			 if(z==4){
				 Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8"+killer.getName()+" &chat &8"+death.getName()+" &czum Licht geführt."));
			 }
			 if(z==5){
				 Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8"+killer.getName()+" &chat &8"+death.getName()+" &cseine Klinge spüren lassen."));
			 }
			 if(z==6){
				 Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8"+death.getName()+" &cwurde von &8"+killer.getName()+" &czu Brei verarbeitet."));
			 }
			 if(z==7){
				 Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8"+killer.getName()+" &chat &8"+death.getName()+" &cgezeigt wer besser ist."));
			 }
			 if(z==8){
				 Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8"+killer.getName()+" &chat &8"+death.getName()+" &cgezeigt wer den Skill hat."));
			 }
			}
		}else{
			Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8"+death.getName()+" &cstarb tragisch!"));
		}
		
		
	}

}
