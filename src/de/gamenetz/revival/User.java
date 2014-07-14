package de.gamenetz.revival;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import de.gamenetz.revival.database.MySQL;


public class User {
	
	UUID uuid;
	
	public User(UUID uuid){
		this.uuid = uuid;
	}
	
	public Player getPlayer(){
		return Bukkit.getServer().getPlayer(this.uuid);
	}
	
	public UUID getUUID(){
		return this.uuid;
	}
	
	public boolean isOnline(){
		boolean online = false;
		if(getPlayer() != null){
			online = true;
		}
		return online;
	}
	public String getName(){
		String name = "";
		try{
			ResultSet rs = MySQL.Query("SELECT username FROM revival_user WHERE uuid='" + this.uuid.toString() + "'");
			while(rs.next()){
				name = rs.getString(1);
			}
			rs.close();
		}catch(Exception e){
			System.err.println(e);
		}
		
		return name;
	}
	
	
	public void sendMessage(String msg){
		getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
	}
	
	public void updateName(String name){
		if(!(getName().equals(name)))
			MySQL.Update("UPDATE revival_user SET username='" + name + "' WHERE uuid='" + this.uuid.toString() + "'");
	}
	
	public int getKills(){
		int kills = 0;
		try{
			ResultSet rs = MySQL.Query("SELECT kills FROM revival_user WHERE uuid= '" + this.uuid.toString() + "';");
			while(rs.next()){
				kills = rs.getInt(1);
			}
			rs.close();
		}catch(Exception e){
			System.err.println(e);
		}
		
		return kills;
	}
	
	public int getDeaths(){
		int deaths = 0;
		try{
			ResultSet rs = MySQL.Query("SELECT deaths FROM revival_user WHERE uuid= '" + this.uuid.toString() + "';");
			while(rs.next()){
				deaths = rs.getInt(1);
			}
			rs.close();
		}catch(Exception e){
			System.err.println(e);
		}
		
		return deaths;
	}
	
	public void addKills(int kills){
		int newkills = getKills() + kills;
		MySQL.Update("UPDATE revival_user SET kills= '" + newkills + "' WHERE uuid= '" + this.uuid.toString() + "';");
	}
	
	public void removeKills(int kills){
		int newkills = getKills() - kills;
		MySQL.Update("UPDATE revival_user SET kills= '" + newkills + "' WHERE uuid= '" + this.uuid.toString() + "';");
	}
	
	public void setKills(int kills){
		MySQL.Update("UPDATE revival_user SET kills= '" + kills + "' WHERE uuid= '" + this.uuid.toString() + "';");
	}
	
	public void addDeaths(int deaths){
		int newdeaths = getDeaths() + deaths;
		MySQL.Update("UPDATE revival_user SET deaths= '" + newdeaths + "' WHERE uuid= '" + this.uuid.toString() + "';");
	}
	
	public void removeDeaths(int deaths){
		int newdeaths = getDeaths() - deaths;
		MySQL.Update("UPDATE revival_user SET deaths= '" + newdeaths + "' WHERE uuid= '" + this.uuid.toString() + "';");
	}
	
	public void setDeaths(int deaths){
		MySQL.Update("UPDATE revival_user SET deaths= '" + deaths + "' WHERE uuid= '" + this.uuid.toString() + "';");
	}
	
	public boolean isRegistered(){
		boolean is = false;
		try{
			ResultSet rs = MySQL.Query("SELECT id FROM revival_user WHERE uuid= '" + this.uuid.toString() + "';");
			while(rs.next()){
				is = true;
			}
			rs.close();
		}catch(Exception e){
			System.err.println(e);
		}
		return is;
	}
	
	public void register(String username){
		MySQL.Update("INSERT INTO revival_user (username, uuid, kills, deaths, online, randomTP) VALUES ('" + username + "', '" + this.uuid.toString() + "', '0', '0', '1', '0')");
	}
	
	public boolean wasRandomTP(){
		boolean was = false;
		int x = 0;
		try{
			ResultSet rs = MySQL.Query("SELECT randomTP FROM revival_user WHERE uuid= '" + this.uuid.toString() + "';");
			while(rs.next()){
				x = rs.getInt(1);
			}
			rs.close();
		}catch(Exception e){
			System.err.println(e);
		}
		
		if(x == 1){
			was = true;
		}else{
			was = false;
		}
		return was;
	}
	
	public void setRandomTP(boolean set){
		int x = 0;
		if(set){
			x = 1;
		}else{
			x = 0;
		}
		MySQL.Update("UPDATE revival_user SET randomTP= '" + x + "' WHERE uuid= '" + this.uuid.toString() + "';");
	}
	
	public void setStatus(boolean online){
		int x = 0;
		if(online){
			x = 1;
		}else{
			x = 0;
		}
		
		MySQL.Update("UPDATE revival_user SET online='" + x + "' WHERE uuid='" + this.uuid.toString() + "'");
		
	}
	
	public List<String> getHomes(){
		List<String> homes = new ArrayList<String>();
		try{
			ResultSet rs = MySQL.Query("SELECT homeName FROM revival_homes WHERE uuid='" + this.uuid.toString() + "'");
			while(rs.next()){
				homes.add(rs.getString(1).toLowerCase());
			}
			rs.close();
		}catch(Exception e){
			System.err.println(e);
		}
		
		return homes;
	}
	
	public Location getHomeLocation(String homeName){
		homeName = homeName.toLowerCase();
		Location loc = null;
		try{
			ResultSet rs = MySQL.Query("SELECT x, y, z, worldName FROM revival_homes WHERE uuid='" + this.uuid.toString() + "' AND homeName='" + homeName + "'");
			while(rs.next()){
				loc = new Location(Bukkit.getWorld(rs.getString(4)), rs.getDouble(1), rs.getDouble(2), rs.getDouble(3));
			}
			rs.close();
		}catch(Exception e){
			System.err.println(e);
		}
		
		return loc;
	}
	
	public void setHome(Location loc, String homeName){
		homeName = homeName.toLowerCase();
		List<String> homes = getHomes();
		if(homes.isEmpty()){
			MySQL.Update("INSERT INTO revival_homes (uuid, homeName, x, y, z, worldName) VALUES ('" + this.uuid.toString() + "', '" + homeName + "', '" + loc.getX() + "', '" + loc.getY() + "', '" + loc.getZ() + "', '" + loc.getWorld().getName() + "')");
		}else{
			if(homes.contains(homeName)){
				MySQL.Update("UPDATE revival_homes SET x='" + loc.getX() + "', y='" + loc.getY() + "', z='" + loc.getZ() + "', worldName='" + loc.getWorld().getName() + "' WHERE uuid='" + this.uuid.toString() + "' AND homeName='" + homeName + "'");
			}else{
				MySQL.Update("INSERT INTO revival_homes (uuid, homeName, x, y, z, worldName) VALUES ('" + this.uuid.toString() + "', '" + homeName + "', '" + loc.getX() + "', '" + loc.getY() + "', '" + loc.getZ() + "', '" + loc.getWorld().getName() + "')");	
			}
		}
	}
	
	public void delHome(String homeName){
		homeName = homeName.toLowerCase();
		MySQL.Update("DELETE FROM revival_homes WHERE uuid='" + this.uuid.toString() + "' AND homeName='" + homeName + "'");
	}
	
	public List<String> loadFrieden(){
		List<String> frieden = new ArrayList<String>();
		try{
			ResultSet rs = MySQL.Query("SELECT to_uuid FROM revival_frieden WHERE from_uuid='" + this.uuid.toString() + "'");
			while(rs.next()){
				frieden.add(rs.getString(1));
			}
			rs.close();
		}catch(Exception e){
			System.err.println(e);
		}
		return frieden;
	}
	
	public boolean isFrieden(UUID SecondUUID){
		boolean is = false;
		try{
			ResultSet rs = MySQL.Query("SELECT * FROM revival_frieden WHERE from_uuid='" + this.uuid.toString() + "' AND to_uuid='" + SecondUUID.toString() + "'");
			while(rs.next()){
				is = true;
			}
			rs.close();
		}catch(Exception e){
			System.err.println(e);
		}
		return is;
	}

}
