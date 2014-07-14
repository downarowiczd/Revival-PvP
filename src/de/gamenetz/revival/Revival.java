package de.gamenetz.revival;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.gamenetz.revival.commands.BewerbenCommand;
import de.gamenetz.revival.commands.CCCommand;
import de.gamenetz.revival.commands.EnderChestCommand;
import de.gamenetz.revival.commands.FriedenCommand;
import de.gamenetz.revival.commands.HomeCommand;
import de.gamenetz.revival.commands.MSGCommand;
import de.gamenetz.revival.commands.RangCommand;
import de.gamenetz.revival.commands.RankingCommand;
import de.gamenetz.revival.commands.RevivalCommand;
import de.gamenetz.revival.commands.SkypeCommand;
import de.gamenetz.revival.commands.SpawnFeroxKillerCommand;
import de.gamenetz.revival.commands.SpawnZombieBossCommand;
import de.gamenetz.revival.commands.StatsCommand;
import de.gamenetz.revival.commands.TPACommand;
import de.gamenetz.revival.commands.TSCommand;
import de.gamenetz.revival.commands.TeamCommand;
import de.gamenetz.revival.commands.VoteCommand;
import de.gamenetz.revival.commands.VoteGiveCommand;
import de.gamenetz.revival.commands.VoteInfoCommand;
import de.gamenetz.revival.database.MySQL;
import de.gamenetz.revival.listener.AntiGiveCommandListener;
import de.gamenetz.revival.listener.ChatManager;
import de.gamenetz.revival.listener.ChatProtecter;
import de.gamenetz.revival.listener.CompassListener;
import de.gamenetz.revival.listener.CreatureSpawnListener;
import de.gamenetz.revival.listener.DamageListener;
import de.gamenetz.revival.listener.DeathListener;
import de.gamenetz.revival.listener.EntityDeathListener;
import de.gamenetz.revival.listener.GameModListener;
import de.gamenetz.revival.listener.JoinQuitListener;
import de.gamenetz.revival.listener.RespawnListener;
import de.gamenetz.revival.listener.UnloadChunkListener;
import de.gamenetz.revival.listener.VoteListener;
import de.gamenetz.revival.listener.WorldBorderListener;

public class Revival extends JavaPlugin{
	
	public static String host, user, pass, db;
	public static String PLAYER_DATA_SAVE = "plugins/Revival-Data/ClipBoard/playerdata";
	public static String VOTE_POINTS_SAVE = "plugins/Revival-Data/Vote/";
	
	public static File votes_points = new File(Revival.VOTE_POINTS_SAVE, "vote_points.yml");
	public static FileConfiguration votes_points_cfg = YamlConfiguration.loadConfiguration(votes_points);
	
	@Override
	public void onDisable() {
		System.out.println("[Revival-PvP] The Main Plugin is now offline!");
		CombatChecker.stopTimer();
		CombatChecker.resetCombat();
		MySQL.close();
	}
	
	@Override
	public void onEnable() {
		System.out.println("[Revival-PvP] The Main Plugin is now online!");
		this.getConfig().addDefault("Config.Database.MySQL.HostName", "localhost");
		this.getConfig().addDefault("Config.Database.MySQL.UserName", "username");
		this.getConfig().addDefault("Config.Database.MySQL.PassWord", "password");
		this.getConfig().addDefault("Config.Database.MySQL.DatabaseName", "db");
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		Revival.votes_points_cfg.options().copyDefaults(true);
		try {
			Revival.votes_points_cfg.save(Revival.votes_points);
		} catch (IOException e) {
			e.printStackTrace();
		}
		host = this.getConfig().getString("Config.Database.MySQL.HostName");
		user = this.getConfig().getString("Config.Database.MySQL.UserName");
		pass = this.getConfig().getString("Config.Database.MySQL.PassWord");
		db = this.getConfig().getString("Config.Database.MySQL.DatabaseName");
		System.out.println("[Revival-PvP] SettingUp MySQL-Connection...");
		MySQL.connect();
		MySQL.Update("CREATE TABLE IF NOT EXISTS revival_user (id INT AUTO_INCREMENT PRIMARY KEY, uuid VARCHAR(255), username VARCHAR(30), online INT(2), kills INT(255), deaths INT(255), randomTP INT(2))");
		MySQL.Update("CREATE TABLE IF NOT EXISTS revival_homes (id INT AUTO_INCREMENT PRIMARY KEY, uuid VARCHAR(255), homeName VARCHAR(16), x DOUBLE, y DOUBLE, z DOUBLE, worldName VARCHAR(30))");
		MySQL.Update("CREATE TABLE IF NOT EXISTS revival_frieden (id INT AUTO_INCREMENT PRIMARY KEY, from_uuid VARCHAR(255), to_uuid VARCHAR(255))");
		System.out.println("[Revival-PvP] Connected to MySQL!");
		
		/*Commands*/
		this.getCommand("revival").setExecutor(new RevivalCommand());
		this.getCommand("team").setExecutor(new TeamCommand());
		this.getCommand("bewerben").setExecutor(new BewerbenCommand());
		this.getCommand("cc").setExecutor(new CCCommand());
		this.getCommand("frieden").setExecutor(new FriedenCommand());
		this.getCommand("annehmen").setExecutor(new FriedenCommand());
		this.getCommand("ablehnen").setExecutor(new FriedenCommand());
		this.getCommand("home").setExecutor(new HomeCommand());
		this.getCommand("sethome").setExecutor(new HomeCommand());
		this.getCommand("delhome").setExecutor(new HomeCommand());
		this.getCommand("homes").setExecutor(new HomeCommand());
		this.getCommand("rang").setExecutor(new RangCommand());
		this.getCommand("ranking").setExecutor(new RankingCommand());
		this.getCommand("skype").setExecutor(new SkypeCommand());
		this.getCommand("ts").setExecutor(new TSCommand());
		this.getCommand("vote").setExecutor(new VoteCommand());
		this.getCommand("votegive").setExecutor(new VoteGiveCommand());
		this.getCommand("voteinfo").setExecutor(new VoteInfoCommand());
		this.getCommand("msg").setExecutor(new MSGCommand());
		this.getCommand("r").setExecutor(new MSGCommand());
		this.getCommand("tpa").setExecutor(new TPACommand());
		this.getCommand("tpahere").setExecutor(new TPACommand());
		this.getCommand("tpaccept").setExecutor(new TPACommand());
		this.getCommand("tpadeny").setExecutor(new TPACommand());
		this.getCommand("stats").setExecutor(new StatsCommand());
		this.getCommand("enderchest").setExecutor(new EnderChestCommand());
		this.getCommand("spawnzombieboss").setExecutor(new SpawnZombieBossCommand());
		this.getCommand("spawnferoxkiller").setExecutor(new SpawnFeroxKillerCommand());
		/*Listener*/
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new JoinQuitListener(), this);
		pm.registerEvents(new WorldBorderListener(), this);
		pm.registerEvents(new RespawnListener(), this);
		pm.registerEvents(new DeathListener(), this);
		pm.registerEvents(new CreatureSpawnListener(), this);
		pm.registerEvents(new EntityDeathListener(), this);
		pm.registerEvents(new DamageListener(), this);
		pm.registerEvents(new CompassListener(), this);
		pm.registerEvents(new ChatManager(), this);
		pm.registerEvents(new ChatProtecter(), this);
		pm.registerEvents(new VoteListener(), this);
		pm.registerEvents(new GameModListener(), this);
		pm.registerEvents(new AntiGiveCommandListener(), this);
		pm.registerEvents(new UnloadChunkListener(), this);
		pm.registerEvents(new SpawnFeroxKillerCommand(), this);
		RevivalData.COMBAT_TIMER_ID = CombatChecker.startTimer();
		/*Scoreboard*/		
	}
	
	  public static long getSystemTime() {
		  return System.currentTimeMillis() / 1000L;
	  }
	

}
