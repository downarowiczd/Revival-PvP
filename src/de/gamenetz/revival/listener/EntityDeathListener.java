package de.gamenetz.revival.listener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import de.gamenetz.revival.RevivalData;

public class EntityDeathListener implements Listener{
	
	private Color getColor(int i) {
		Color c = null;
		if (i == 1) {
			c = Color.AQUA;
		}
		if (i == 2) {
			c = Color.BLACK;
		}
		if (i == 3) {
			c = Color.BLUE;
		}
		if (i == 4) {
			c = Color.FUCHSIA;
		}
		if (i == 5) {
			c = Color.GRAY;
		}
		if (i == 6) {
			c = Color.GREEN;
		}
		if (i == 7) {
			c = Color.LIME;
		}
		if (i == 8) {
			c = Color.MAROON;
		}
		if (i == 9) {
			c = Color.NAVY;
		}
		if (i == 10) {
			c = Color.OLIVE;
		}
		if (i == 11) {
			c = Color.ORANGE;
		}
		if (i == 12) {
			c = Color.PURPLE;
		}
		if (i == 13) {
			c = Color.RED;
		}
		if (i == 14) {
			c = Color.SILVER;
		}
		if (i == 15) {
			c = Color.TEAL;
		}
		if (i == 16) {
			c = Color.WHITE;
		}
		if (i == 17) {
			c = Color.YELLOW;
		}

		return c;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDeath(EntityDeathEvent event){
		if(event.getEntityType()==EntityType.ZOMBIE||event.getEntityType()==EntityType.SKELETON){
			Random rnd = new Random();
			int max1 = 10;
			int min1 = 1;
			int y = rnd.nextInt((max1 - min1) + 1) + min1;
			List<ItemStack> drops = new ArrayList<ItemStack>();
			drops.clear();
			drops.add(new ItemStack(Material.SOUL_SAND,y));
			drops.add(new ItemStack(Material.NETHER_STALK,y));
			drops.add(new ItemStack(Material.BLAZE_ROD,y));
			drops.add(new ItemStack(Material.SPECKLED_MELON, y));
			drops.add(new ItemStack(Material.FERMENTED_SPIDER_EYE, y));
			drops.add(new ItemStack(Material.GHAST_TEAR, y));
			drops.add(new ItemStack(Material.GLOWSTONE, y));
			drops.add(new ItemStack(Material.MAGMA_CREAM, y));
			drops.add(new ItemStack(Material.GOLD_INGOT, y));
			drops.add(new ItemStack(Material.GOLD_INGOT, y));
			drops.add(new ItemStack(Material.GOLD_INGOT, y));
			drops.add(new ItemStack(Material.GOLD_INGOT, y));
			String name = event.getEntity().getCustomName();
			drops.add(new ItemStack(Material.IRON_INGOT, y));
			if(event.getEntityType()==EntityType.ZOMBIE){
				
				if(event.getEntity().getEntityId() == RevivalData.BOSS_ZOMBIE_ID){
					event.getEntity().getLocation().getWorld().dropItem(event.getEntity().getLocation().add(0, 2, 0), new ItemStack(Material.DIAMOND, 32));					
					Firework fw = (Firework) event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.FIREWORK);
		            FireworkMeta fwm = fw.getFireworkMeta();
		           
		            //Our random generator
		            Random r = new Random();   
		 
		            //Get the type
		            int rt = r.nextInt(5) + 1;
		            Type type = Type.BALL;       
		            if (rt == 1) type = Type.BALL;
		            if (rt == 2) type = Type.BALL_LARGE;
		            if (rt == 3) type = Type.BURST;
		            if (rt == 4) type = Type.CREEPER;
		            if (rt == 5) type = Type.STAR;
		           
		            //Get our random colours   
		            int r1i = r.nextInt(17) + 1;
		            int r2i = r.nextInt(17) + 1;
		            Color c1 = getColor(r1i);
		            Color c2 = getColor(r2i);
		           
		            //Create our effect with this
		            FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();
		           
		            //Then apply the effect to the meta
		            fwm.addEffect(effect);
		           
		            //Generate some random power and set it
		            int rp = r.nextInt(2) + 1;
		            fwm.setPower(rp);
		           
		            //Then apply this to our rocket
		            fw.setFireworkMeta(fwm);                     
					Bukkit.getScheduler().cancelTask(RevivalData.BOSS_ZOMBIE_SCH_ID);
					RevivalData.BOSS_ZOMBIE_LOCATION = null;
					RevivalData.BOSS_ZOMBIE_ID = 0;
					RevivalData.BOSS_ZOMBIE_SCH_ID = 0;
				}
				
			if(name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&cZombie Lvl 4"))){
				drops.add(new ItemStack(Material.EXP_BOTTLE, y));
				drops.add(new ItemStack(Material.EXP_BOTTLE, y));
				drops.add(new ItemStack(Material.EXP_BOTTLE, y));
				drops.add(new ItemStack(Material.EXP_BOTTLE, y));
				drops.add(new ItemStack(Material.EXP_BOTTLE, y));
				drops.add(new ItemStack(Material.EXP_BOTTLE, y));
		
			}
			
			if(name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&cZombie Lvl 5"))){
				drops.add(new ItemStack(Material.EXP_BOTTLE, y));
				drops.add(new ItemStack(Material.EXP_BOTTLE, y));
				drops.add(new ItemStack(Material.EXP_BOTTLE, y));
				drops.add(new ItemStack(Material.EXP_BOTTLE, y));
				drops.add(new ItemStack(Material.EXP_BOTTLE, y));
				drops.add(new ItemStack(Material.EXP_BOTTLE, y));
				
			}
			
			if(name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&cZombie Lvl 6"))){
				drops.add(new ItemStack(Material.EXP_BOTTLE, y));
				drops.add(new ItemStack(Material.EXP_BOTTLE, y));
				drops.add(new ItemStack(Material.ENDER_PEARL, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.EXP_BOTTLE, y));
				drops.add(new ItemStack(Material.EXP_BOTTLE, y));
				drops.add(new ItemStack(Material.ENDER_PEARL, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
			}
			
			if(name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&cZombie Lvl 7"))){
				drops.add(new ItemStack(Material.EXP_BOTTLE, y));
				drops.add(new ItemStack(Material.EXP_BOTTLE, y));
				drops.add(new ItemStack(Material.ENDER_PEARL, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));

			}
			
			if(name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&cZombie Lvl 8"))){
				drops.add(new ItemStack(Material.EXP_BOTTLE, y));
				drops.add(new ItemStack(Material.EXP_BOTTLE, y));
				drops.add(new ItemStack(Material.ENDER_PEARL, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.EXP_BOTTLE, y));
				drops.add(new ItemStack(Material.EXP_BOTTLE, y));
				drops.add(new ItemStack(Material.ENDER_PEARL, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));


			}
			
			if(name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&cZombie Lvl 9"))){
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.EXP_BOTTLE, y));
				drops.add(new ItemStack(Material.EXP_BOTTLE, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));


			}
			
			if(name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&cZombie Lvl 10"))){
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.EXP_BOTTLE, y));
				drops.add(new ItemStack(Material.EXP_BOTTLE, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.IRON_INGOT, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.LAPIS_BLOCK, y));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.EMERALD, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
				drops.add(new ItemStack(Material.DIAMOND, y));
				drops.add(new ItemStack(Material.DIAMOND, y));
			}
			}
			
			Collections.shuffle(drops);
			int x = rnd.nextInt(drops.size());
			event.getEntity().getLocation().getWorld().dropItem(event.getEntity().getLocation(), drops.get(x));
			drops.clear();
		}
		event.setDroppedExp(event.getDroppedExp() * RevivalData.XP_AMPLIFIER);
	}

}
