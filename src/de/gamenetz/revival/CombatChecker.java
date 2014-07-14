package de.gamenetz.revival;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CombatChecker {
	
	public static final int howLongInCombat = 10; //In Sekunden
	
	public static Map<String, Long> inCombat = new HashMap<String, Long>();
	
	public static boolean isInCombat(String name){
		if(inCombat.containsKey(name)){
			return true;
		}
		return false;
	}
	
	public static void removeFromCombat(String name){
		if(CombatChecker.isInCombat(name)){
			CombatChecker.inCombat.remove(name);
		}
	}
	
	public static void addToCombat(String name){
		if(CombatChecker.isInCombat(name)){
			removeFromCombat(name);
		}
		CombatChecker.inCombat.put(name, Revival.getSystemTime());
	}
	
	public static void resetCombat(){
		CombatChecker.inCombat.clear();
	}
	
	public static int startTimer(){
		int id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("Revival-PvP"), new Runnable() {
			@Override
			public void run() {
				for (Iterator<Entry<String, Long>> iter = CombatChecker.inCombat.entrySet().iterator(); iter.hasNext(); ) {
			          Entry<String, Long> c = iter.next();
			          if (Revival.getSystemTime() - ((Long)c.getValue()).longValue() >= CombatChecker.howLongInCombat){
			        	  iter.remove();
							if(RevivalData.isInReg(c.getKey().toString())){
								User u = new User(RevivalData.getUUIDbyUsername(c.getKey().toString()));
								if(u.isOnline()){
									u.sendMessage(RevivalData.MAIN_PREFIX + ChatColor.GOLD + "Du bist nun nicht mehr im Kampf!");
								}
							}
			          }
			        }
				
				for(String key : CombatChecker.inCombat.keySet()){
					long time = CombatChecker.inCombat.get(key);
					if(Revival.getSystemTime() - time >= CombatChecker.howLongInCombat){
						CombatChecker.removeFromCombat(key);
						if(RevivalData.isInReg(key)){
							User u = new User(RevivalData.getUUIDbyUsername(key));
							if(u.isOnline()){
								u.sendMessage(RevivalData.MAIN_PREFIX + ChatColor.GOLD + "Du bist nun nicht mehr im Kampf!");
							}
						}
					}
				}
			}
		}, 20L, 20L);
		return id;
	}
	
	public static void stopTimer(){
		Bukkit.getServer().getScheduler().cancelTask(RevivalData.COMBAT_TIMER_ID);
	}
	
	public static void killPlayer(Player player){
		CombatChecker.removeFromCombat(player.getName());
		Bukkit.broadcastMessage(RevivalData.MAIN_PREFIX + ChatColor.translateAlternateColorCodes('&', "&aDer Spieler " + player.getName() + " ist im Kampf geleftet!"));
		ItemStack[] items = player.getInventory().getContents().clone();
		ItemStack[] armor = player.getInventory().getArmorContents().clone();
		player.getInventory().clear();
		for(ItemStack item : items){
			if(item.getType() != Material.AIR){
			player.getLocation().getWorld().dropItemNaturally(player.getLocation(), item);
			}
		}
		for(ItemStack rus : armor){
			if(rus.getType() != Material.AIR){
			player.getLocation().getWorld().dropItemNaturally(player.getLocation(), rus);
			}
		}
		new User(player.getUniqueId()).setRandomTP(false);
		player.setHealth(0.00);
	}
	
}
