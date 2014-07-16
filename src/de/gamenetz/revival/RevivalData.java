package de.gamenetz.revival;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.gamenetz.revival.database.MySQL;

public class RevivalData {
	
	public static boolean reseting = false;
	public static final String MAIN_PREFIX = ChatColor.translateAlternateColorCodes('&', "&7[&aRevival-PvP&7] ");
	public static int BORDER_RADIUS = 2000;
	public static int XP_AMPLIFIER = 1;
	public static int COMBAT_TIMER_ID = 0;
	public static List<String> chat_spy = new ArrayList<String>();
	public static List<String> player_spy = new ArrayList<String>();
	
	public static Map<String, String> tpa_request = new HashMap<String, String>();
	public static Map<String, String> tpahere_request = new HashMap<String, String>();

	public static Map<String, String> conversation = new HashMap<String, String>();

	public static Location BOSS_ZOMBIE_LOCATION = null;
	public static int BOSS_ZOMBIE_ID = 0;
	public static int BOSS_ZOMBIE_SCH_ID = 0;
	
	public static final String noPerm = "&cDu darfst das nicht!!!";
	
	public static UUID getUUIDbyUsername(String username){
		UUID uuid = null;
		try{
		ResultSet rs = MySQL.Query("SELECT uuid FROM revival_user WHERE username='" + username + "';");
		while(rs.next()){
			uuid = UUID.fromString(rs.getString(1));
		}
		rs.close();
		}catch(Exception e){
			System.err.println(e);
		}
		return uuid;
	}
	
	public static boolean isInReg(String username){
		boolean is = false;
		try{
			ResultSet rs = MySQL.Query("SELECT id FROM revival_user WHERE username='" + username + "';");
			while(rs.next()){
				is = true;
			}
			rs.close();
		}catch(Exception e){
			System.err.println(e);
		}
		
		return is;
	}
	
	public static Location getRandomLocation(World world){
		Random rnd = new Random();
		Location loc = world.getSpawnLocation();
		double x = loc.getX();
        double z = loc.getZ();
        x = x - 8 + rnd.nextInt(RevivalData.BORDER_RADIUS/2);
        z = z - 8 + rnd.nextInt(RevivalData.BORDER_RADIUS/2);
        loc.setX(x);
        loc.setZ(z);
        loc.setY(world.getHighestBlockYAt(loc.getBlockX(), loc.getBlockZ()));
        boolean safe = false;
        while(!safe){
        	if(loc.getY() != 0 && loc.getBlock().getType() != Material.WATER && loc.getBlock().getType() != Material.LAVA && loc.getBlock().getType() != Material.STATIONARY_WATER && loc.getBlock().getType() != Material.STATIONARY_LAVA){
        		if(loc.distance(loc.getWorld().getSpawnLocation()) >= RevivalData.BORDER_RADIUS){
        			continue;
        		}else{
        			Location loc2 = loc;
            		loc2.setY(loc2.getY() - 1);
                	if(loc2.getY() != 0 && loc2.getBlock().getType() != Material.WATER && loc2.getBlock().getType() != Material.LAVA && loc2.getBlock().getType() != Material.STATIONARY_WATER && loc2.getBlock().getType() != Material.STATIONARY_LAVA){
        			safe = true;
        			loc.setY(loc.getY() + 1);
        			break;
                	}
        		}
        	}
        		x = loc.getX();
        		z = loc.getZ();
        		x = x - 8 + rnd.nextInt(RevivalData.BORDER_RADIUS/2);
        		z = z - 8 + rnd.nextInt(RevivalData.BORDER_RADIUS/2);
        		loc.setX(x);
        		loc.setZ(z);
                loc.setY(world.getHighestBlockYAt(loc.getBlockX(), loc.getBlockZ()));
        }
        
		return loc;
	}

	
	public static boolean checkDouble(double d) {
		boolean g = false;
		try {
		    if ((String.valueOf(d).length() - String.valueOf(d).indexOf('.')) > 3) {
		    	g = false;
		    }else{
		    	g = true;
		    }

		    if (d < 0) {
			g = false;
		    }
		}catch (Exception e) {
		    System.err.println(e);
		}
		return g;
	}
	
	public static void setPlayerInventory(Player p){
		p.getInventory().setItem(0, new ItemStack(Material.COMPASS, 1));
		p.getInventory().setItem(1, new ItemStack(Material.STONE_SWORD, 1));
		p.getInventory().setItem(2, new ItemStack(Material.STONE_PICKAXE, 1));
		p.getInventory().setItem(3, new ItemStack(Material.BREAD, 16));
		p.getInventory().setItem(4, new ItemStack(Material.GOLDEN_APPLE, 1));
		p.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET));
		p.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
		p.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
		p.getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS));
	}
	
	public static void getNearestPlayer(User u){
		Player target = null;
		Player p = null;
		if(u.isOnline()){
			p = u.getPlayer();
		}else{
			return;
		}
		double dist = 0.0D;
		for(Entity e : p.getNearbyEntities(500.0D, 500.0D, 500.D)){
			if(!(e instanceof Player)) continue;
			Player t = (Player)e;
			dist = p.getLocation().distance(t.getLocation());
			if(dist < 200.0){
				if(new User(t.getUniqueId()).isFrieden(u.getUUID())){
					target = null;
				}else{
					target = t;
					break;
				}
			}else{
				if(p.hasPermission("revival.seemore")){
					if(new User(t.getUniqueId()).isFrieden(u.getUUID())){
						target = null;
					}else{
						target = t;
						break;
					}
				}
			}
		}
		
		if(target == null){
			u.sendMessage(RevivalData.MAIN_PREFIX + "&aEs befindet sich kein Spieler in deiner Nähe");
		}else{
			u.sendMessage(RevivalData.MAIN_PREFIX + "&aDer Spieler &c" + target.getName() + " &aist &c" + (int)dist + "&a Blöcke entfernt!");
			p.setCompassTarget(target.getLocation());
		}
		
		
	}
	
	public static void resetMap(){
		RevivalData.reseting = true;
		for(Player p : Bukkit.getOnlinePlayers()){
			p.kickPlayer(ChatColor.GOLD + "Der Server wird nun zurücksetzt!");
		}
		try {
			copyDir(new File("world/playerdata"), new File(Revival.PLAYER_DATA_SAVE));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Revival-PvP"), new Runnable() {
			
			@Override
			public void run() {
				Bukkit.unloadWorld(Bukkit.getWorld("world"), false);
				RevivalData.deleteDir(new File("world"));
				MySQL.Update("DELETE FROM revival_homes");
				MySQL.Update("UPDATE revival_user SET randomTP='0'");
				WorldCreator wc = new WorldCreator("world");
				wc.generateStructures(true);
				Bukkit.createWorld(wc);
				try {
					RevivalData.copyDir(new File(Revival.PLAYER_DATA_SAVE), new File("world/playerdata"));
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}, 20L*5);
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Revival-PvP"), new Runnable() {
			@Override
			public void run() {
				RevivalData.reseting = false;
				Bukkit.shutdown();
			}
		}, 20L*6);
		
	}
	
	public static void deleteDir(File dir){
		try {
			FileUtils.deleteDirectory(dir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void copyDir(File quelle, File ziel) throws FileNotFoundException, IOException {
		
		File[] files = quelle.listFiles();
		File newFile = null; // in diesem Objekt wird für jedes File der Zielpfad gespeichert.
				     // 1. Der alte Zielpfad
				     // 2. Das systemspezifische Pfadtrennungszeichen
				     // 3. Der Name des aktuellen Ordners/der aktuellen Datei
		ziel.mkdirs();	     // erstellt alle benötigten Ordner
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
					newFile = new File(ziel.getAbsolutePath() + System.getProperty("file.separator") + files[i].getName());
				if (files[i].isDirectory()) {
					RevivalData.copyDir(files[i], newFile);
				}
				else {
					RevivalData.copyFile(files[i], newFile);
				}
			}
		}
	}
	
	public static void copyFile(File file, File ziel) throws FileNotFoundException, IOException {
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(ziel, true));
		int bytes = 0;
		while ((bytes = in.read()) != -1) { // Datei einlesen
			out.write(bytes); // Datei schreiben
		}
		in.close();
		out.close();
	}
	
	public static ItemStack setItemNameAndLore(ItemStack item, String name, String[] lore){
	    ItemMeta im = item.getItemMeta();
	    im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
	    im.setLore(Arrays.asList(lore));
	    item.setItemMeta(im);
	    return item;
	  }
	
	
}

