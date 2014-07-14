package de.gamenetz.revival.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.gamenetz.revival.HeadsUpDisplay;
import de.gamenetz.revival.RevivalData;
import de.gamenetz.revival.User;

public class SpawnZombieBossCommand implements CommandExecutor, Listener{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			User u = new User(p.getUniqueId());
			if(p.hasPermission("revival.spawnzombieboss")){
				if(RevivalData.BOSS_ZOMBIE_LOCATION == null){
					Location loc = RevivalData.getRandomLocation(p.getWorld());
					RevivalData.BOSS_ZOMBIE_LOCATION = loc;
					Entity e = loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
					Zombie z = (Zombie)e;
					z.setCustomName(ChatColor.translateAlternateColorCodes('&', "&cBoss-Zombie"));
					z.setCustomNameVisible(true);
					z.setRemoveWhenFarAway(false);
					Damageable d = (Damageable)e;
					d.setMaxHealth(100.00);
					d.setHealth(100.00);
					z.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
					z.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
					z.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
					z.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
					z.getEquipment().setItemInHand(new ItemStack(Material.DIAMOND_SWORD));
					z.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1));
					z.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100000, 0));
					RevivalData.BOSS_ZOMBIE_ID = e.getEntityId();
					Bukkit.broadcastMessage(ChatColor.YELLOW + "Es wurde nun ein Zombie-Boss in der Welt gespawnt!");
					RevivalData.BOSS_ZOMBIE_SCH_ID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("Revival-PvP"), new Runnable() {
						@Override
						public void run() {
							Entity bz = null;
							for(Entity et : RevivalData.BOSS_ZOMBIE_LOCATION.getWorld().getEntities()){
								if(et.getEntityId() == RevivalData.BOSS_ZOMBIE_ID){
									bz = et;
									break;
								}
							}
							
							if(bz != null){
								RevivalData.BOSS_ZOMBIE_LOCATION = bz.getLocation();
								for(Player players : Bukkit.getOnlinePlayers()){
									HeadsUpDisplay.displayTextBar("X: " + bz.getLocation().getBlockX() + " Y: " + bz.getLocation().getBlockY() + " Z: " + bz.getLocation().getBlockZ(), players);
								}
							}else{
								Bukkit.getScheduler().cancelTask(RevivalData.BOSS_ZOMBIE_SCH_ID);
								RevivalData.BOSS_ZOMBIE_LOCATION = null;
								RevivalData.BOSS_ZOMBIE_ID = 0;
							}
							
						}
					}, 20L, 20L);
				}else{
					u.sendMessage("&cEin BossZombie ist schon auf der Welt!");
				}
			}else{
				u.sendMessage("&cDu darfst das nicht!");
			}
			
		}
		return true;
	}
	

}
