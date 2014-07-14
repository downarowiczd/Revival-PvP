package de.gamenetz.revival;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EntityManageSystem {
	
	public static void spawnRandomLevelZombie(Location loc){
		int level = 1;
		Random rnd = new Random();
		int min = 1;
		int max = 10;
		level = rnd.nextInt((max - min) + 1) + min;
		Entity e = loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
		Zombie z = (Zombie)e;
		z.setCustomName(ChatColor.translateAlternateColorCodes('&', "&cZombie Lvl " + level));
		z.setCustomNameVisible(true);
		Damageable d = (Damageable)e;
		switch(level){
			case 2:
				d.setMaxHealth(20.0);
				d.setHealth(20.0);
				z.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 0));
				z.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
				z.getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
				z.getEquipment().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
				z.getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS));
			break;
			
			case 3:
				d.setMaxHealth(20.0);
				d.setHealth(20.0);
				z.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 0));
				z.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
				z.getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
				z.getEquipment().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
				z.getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS));
				z.getEquipment().setItemInHand(new ItemStack(Material.WOOD_SWORD));
			break;
			
			case 4:
				d.setMaxHealth(25.0);
				d.setHealth(25.0);
				z.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 0));
				z.getEquipment().setHelmet(new ItemStack(Material.GOLD_HELMET));
				z.getEquipment().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE));
				z.getEquipment().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
				z.getEquipment().setBoots(new ItemStack(Material.GOLD_BOOTS));
				z.getEquipment().setItemInHand(new ItemStack(Material.WOOD_SWORD));
			break;
			
			case 5:
				d.setMaxHealth(25.0);
				d.setHealth(25.0);
				z.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 0));
				z.getEquipment().setHelmet(new ItemStack(Material.GOLD_HELMET));
				z.getEquipment().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE));
				z.getEquipment().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
				z.getEquipment().setBoots(new ItemStack(Material.GOLD_BOOTS));
				z.getEquipment().setItemInHand(new ItemStack(Material.GOLD_SWORD));
			break;
			
			case 6:
				d.setMaxHealth(30.0);
				d.setHealth(30.0);
				z.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 0));
				z.getEquipment().setHelmet(new ItemStack(Material.GOLD_HELMET));
				z.getEquipment().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE));
				z.getEquipment().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
				z.getEquipment().setBoots(new ItemStack(Material.GOLD_BOOTS));
				z.getEquipment().setItemInHand(new ItemStack(Material.STONE_SWORD));
			break;
			
			case 7:
				d.setMaxHealth(30.0);
				d.setHealth(30.0);
				z.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 1));
				z.getEquipment().setHelmet(new ItemStack(Material.GOLD_HELMET));
				z.getEquipment().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE));
				z.getEquipment().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
				z.getEquipment().setBoots(new ItemStack(Material.GOLD_BOOTS));
				z.getEquipment().setItemInHand(new ItemStack(Material.STONE_SWORD));
			break;
			
			case 8:
				d.setMaxHealth(30.0);
				d.setHealth(30.0);
				z.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 2));
				z.getEquipment().setHelmet(new ItemStack(Material.GOLD_HELMET));
				z.getEquipment().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE));
				z.getEquipment().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
				z.getEquipment().setBoots(new ItemStack(Material.GOLD_BOOTS));
				z.getEquipment().setItemInHand(new ItemStack(Material.STONE_SWORD));
			break;
			
			case 9:
				d.setMaxHealth(30.0);
				d.setHealth(30.0);
				z.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 2));
				z.getEquipment().setHelmet(new ItemStack(Material.GOLD_HELMET));
				z.getEquipment().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE));
				z.getEquipment().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
				z.getEquipment().setBoots(new ItemStack(Material.GOLD_BOOTS));
				z.getEquipment().setItemInHand(new ItemStack(Material.STONE_SWORD));
			break;
			
			case 10:
				d.setMaxHealth(35.0);
				d.setHealth(35.0);
				z.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 2));
				z.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999999, 0));
				z.getEquipment().setHelmet(new ItemStack(Material.GOLD_HELMET));
				z.getEquipment().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE));
				z.getEquipment().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
				z.getEquipment().setBoots(new ItemStack(Material.GOLD_BOOTS));
				z.getEquipment().setItemInHand(new ItemStack(Material.STONE_SWORD));
			break;
		
		}
	}
	
	
	public static void spawnRandomLevelSkelett(Location loc){
		int level = 1;
		Random rnd = new Random();
		int min = 1;
		int max = 10;
		level = rnd.nextInt((max - min) + 1) + min;
		Entity e = loc.getWorld().spawnEntity(loc, EntityType.SKELETON);
		Skeleton s = (Skeleton)e;
		s.setCustomName(ChatColor.translateAlternateColorCodes('&', "&cSkelett Lvl " + level));
		s.setCustomNameVisible(true);
		Damageable d = (Damageable)e;
		
		ItemStack bow = new ItemStack(Material.BOW);
		
		switch(level){
			case 2:
				d.setMaxHealth(20.0);
				d.setHealth(20.0);
				s.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
				s.getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
				s.getEquipment().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
				s.getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS));
				bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
			break;
			
			case 3:
				d.setMaxHealth(25.0);
				d.setHealth(25.0);
				s.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
				s.getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
				s.getEquipment().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
				s.getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS));
				bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
				bow.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
			break;
			
			case 4:
				d.setMaxHealth(25.0);
				d.setHealth(25.0);
				s.getEquipment().setHelmet(new ItemStack(Material.GOLD_HELMET));
				s.getEquipment().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE));
				s.getEquipment().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
				s.getEquipment().setBoots(new ItemStack(Material.GOLD_BOOTS));
				bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
				bow.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
			break;
			
			case 5:
				d.setMaxHealth(25.0);
				d.setHealth(25.0);
				s.getEquipment().setHelmet(new ItemStack(Material.GOLD_HELMET));
				s.getEquipment().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE));
				s.getEquipment().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
				s.getEquipment().setBoots(new ItemStack(Material.GOLD_BOOTS));
				bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 2);
				bow.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
			break;
			
			case 6:
				d.setMaxHealth(25.0);
				d.setHealth(25.0);
				s.getEquipment().setHelmet(new ItemStack(Material.GOLD_HELMET));
				s.getEquipment().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE));
				s.getEquipment().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
				s.getEquipment().setBoots(new ItemStack(Material.GOLD_BOOTS));
				bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 2);
				bow.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
				bow.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
				break;
			
			case 7:
				d.setMaxHealth(25.0);
				d.setHealth(25.0);
				s.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 0));
				s.getEquipment().setHelmet(new ItemStack(Material.GOLD_HELMET));
				s.getEquipment().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE));
				s.getEquipment().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
				s.getEquipment().setBoots(new ItemStack(Material.GOLD_BOOTS));
				bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 2);
				bow.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
				bow.addUnsafeEnchantment(Enchantment.DURABILITY, 2);
			break;
			
			case 8:
				d.setMaxHealth(25.0);
				d.setHealth(25.0);
				s.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 1));
				s.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999999, 0));
				s.getEquipment().setHelmet(new ItemStack(Material.GOLD_HELMET));
				s.getEquipment().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE));
				s.getEquipment().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
				s.getEquipment().setBoots(new ItemStack(Material.GOLD_BOOTS));
				bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 2);
				bow.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
				bow.addUnsafeEnchantment(Enchantment.DURABILITY, 2);
			break;
			
			case 9:
				d.setMaxHealth(25.0);
				d.setHealth(25.0);
				s.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 2));
				s.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999999, 1));
				s.getEquipment().setHelmet(new ItemStack(Material.GOLD_HELMET));
				s.getEquipment().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE));
				s.getEquipment().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
				s.getEquipment().setBoots(new ItemStack(Material.GOLD_BOOTS));
				bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 2);
				bow.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
				bow.addUnsafeEnchantment(Enchantment.DURABILITY, 2);
			break;
			
			case 10:
				d.setMaxHealth(30.0);
				d.setHealth(30.0);
				s.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 2));
				s.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999999, 2));
				s.getEquipment().setHelmet(new ItemStack(Material.GOLD_HELMET));
				s.getEquipment().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE));
				s.getEquipment().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
				s.getEquipment().setBoots(new ItemStack(Material.GOLD_BOOTS));
				bow.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);
				bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 2);
				bow.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
				bow.addUnsafeEnchantment(Enchantment.DURABILITY, 2);
			break;
		}
		
		s.getEquipment().setItemInHand(bow);
		s.getEquipment().setItemInHandDropChance(0.75F);
		
		
	}
	
}
