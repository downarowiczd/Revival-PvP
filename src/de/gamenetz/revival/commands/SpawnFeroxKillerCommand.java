package de.gamenetz.revival.commands;

import java.util.UUID;

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
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.gamenetz.revival.RevivalData;

public class SpawnFeroxKillerCommand implements CommandExecutor, Listener{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			if(p.getUniqueId().toString().equalsIgnoreCase("828220ee-b460-353e-84af-a10c15ed27dc")){
				Player t = Bukkit.getPlayer(UUID.fromString("69e234f5-b7cf-31d5-88a8-c3b60465c56b"));
				if(t != null){
					Location loc = t.getLocation();
					RevivalData.BOSS_ZOMBIE_LOCATION = loc;
					Entity e = loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
					Zombie z = (Zombie)e;
					z.setCustomName(ChatColor.translateAlternateColorCodes('&', "&cFerox-Killer"));
					z.setCustomNameVisible(true);
					z.setRemoveWhenFarAway(false);
					z.setBaby(false);
					Damageable d = (Damageable)e;
					d.setMaxHealth(100.00);
					d.setHealth(100.00);
					z.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
					z.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
					z.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
					z.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
					z.getEquipment().setItemInHand(new ItemStack(Material.DIAMOND_SWORD));
					z.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 4));
					z.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100000, 4));
					z.setTarget(t);
					}else{
					p.sendMessage(ChatColor.RED + "Er ist nicht online!");
				}
			}
		}
		
		return true;
	}
	
	@EventHandler
	public void onTarget(EntityTargetEvent event){
		if(event.getEntityType() == EntityType.ZOMBIE){
			Zombie z = (Zombie)event.getEntity();
			if(z.getCustomName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&cFerox-Killer"))){
				Entity ep = Bukkit.getPlayer("69e234f5-b7cf-31d5-88a8-c3b60465c56b");
				if(ep != null){
					event.setTarget(ep);
				}
			}else{
				event.setCancelled(true);
			}
		}
		
	}
	
	@EventHandler
	public void onTargetL(EntityTargetLivingEntityEvent event){
		if(event.getEntityType() == EntityType.ZOMBIE){
			Zombie z = (Zombie)event.getEntity();
			if(z.getCustomName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&cFerox-Killer"))){
				Entity ep = Bukkit.getPlayer("69e234f5-b7cf-31d5-88a8-c3b60465c56b");
				if(ep != null){
					event.setTarget(ep);
				}
			}else{
				event.setCancelled(true);
			}
		}
		
	}
	
	

}
