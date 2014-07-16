package de.gamenetz.revival.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;

import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;
import de.gamenetz.revival.CombatChecker;
import de.gamenetz.revival.RevivalData;
import de.gamenetz.revival.User;

public class JoinQuitListener implements Listener{
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		final Player p = event.getPlayer();
		final User u = new User(p.getUniqueId());
		PermissionManager pex = PermissionsEx.getPermissionManager();
		if(u.isRegistered()){
			u.updateName(p.getName());
			if(!u.wasRandomTP()){
				Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Revival-PvP"), new Runnable() {
					
					@Override
					public void run() {
						p.teleport(RevivalData.getRandomLocation(p.getLocation().getWorld()));
						u.setRandomTP(true);
						p.sendMessage(ChatColor.YELLOW + "Du wurdest zu einer zufälligen Postion geportet!");
					}
				}, 2L);
			}
			
			String joinMSG = ChatColor.translateAlternateColorCodes('&', "§8(§a+§8) §7" + p.getName());
			event.setJoinMessage(joinMSG);
			u.sendMessage("&a&lMoooooooooiiiiiiiiiinnnnnnnnn");
			u.sendMessage("&a&lSry wegen der Stoerung. Unsere Kiste wollte nicht gehen!");
			u.sendMessage("&a&lP.S.: Kiste bedeutet bei den Profis vServer :D");
		}else{
			u.register(p.getName());
			Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Revival-PvP"), new Runnable() {
				@Override
				public void run() {
					p.teleport(RevivalData.getRandomLocation(p.getLocation().getWorld()));
					u.setRandomTP(true);
					u.sendMessage(ChatColor.YELLOW + "Du wurdest zu einer zufälligen Postion geportet!");
				}
			}, 2L);
			event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "&8(&a+&8) &7" + p.getName()));
			u.sendMessage("&aWillkommen auf Revival-PvP");
			RevivalData.setPlayerInventory(p);
		}
		
		u.setStatus(true);
		String tabColor = "&a";
		if(pex.getUser(p).inGroup("Spieler")){
			tabColor = "&a";
		}else if(pex.getUser(p).inGroup("Premium")){
			tabColor = "&6";
		}else if(pex.getUser(p).inGroup("YouTuber")){
			tabColor = "&5";
		}else if(pex.getUser(p).inGroup("Helfer")){
			tabColor = "&9";
		}else if(pex.getUser(p).inGroup("Moderator")){
			tabColor = "&1";
		}else if(pex.getUser(p).inGroup("Developer")){
			tabColor = "&3";
		}else if(pex.getUser(p).inGroup("Admin")){
			tabColor = "&c";
		}else if(pex.getUser(p).inGroup("CoOwner")){
			tabColor = "&c";
		}else if(pex.getUser(p).inGroup("Owner")){
			tabColor = "&4";
		}
		String newname = tabColor + p.getName();
		if(newname.length() > 16){
		newname = ChatColor.translateAlternateColorCodes('&', newname.substring(0, 16));
		}else{
			newname = ChatColor.translateAlternateColorCodes('&', newname);
		}
		u.getPlayer().setPlayerListName(ChatColor.translateAlternateColorCodes('&', newname));
		
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event){
		Player p = event.getPlayer();
		User u = new User(p.getUniqueId());
		u.setStatus(false);
		u.getPlayer().setPlayerListName(null);
		String quitMSG = ChatColor.translateAlternateColorCodes('&', "§8(§c-§8) §7" + p.getName());
		event.setQuitMessage(quitMSG);
		if(CombatChecker.isInCombat(p.getName())){
			CombatChecker.killPlayer(event.getPlayer());
		}
	}
	
	@EventHandler
	public void onPlayerKick(PlayerKickEvent event){
		Player p = event.getPlayer();
		if(CombatChecker.isInCombat(p.getName())){
			CombatChecker.removeFromCombat(p.getName());
		}
	}
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent event){
		if(RevivalData.reseting){
			event.setResult(Result.KICK_OTHER);
			event.setKickMessage(ChatColor.translateAlternateColorCodes('&', "&aDer Server resetet gerade!"));
			event.disallow(event.getResult(), event.getKickMessage());
		}
	}
	


}
