package de.gamenetz.revival.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class ChatManager implements Listener{
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent event){
		Player p = event.getPlayer();
		PermissionManager pex = PermissionsEx.getPermissionManager();
		String nameColor = "&a";
		if(pex.getUser(p).inGroup("Spieler")){
			nameColor = "&a";
		}else if(pex.getUser(p).inGroup("Premium")){
			nameColor = "&6";
		}else if(pex.getUser(p).inGroup("YouTuber")){
			nameColor = "&5";
		}else if(pex.getUser(p).inGroup("Helfer")){
			nameColor = "&9";
		}else if(pex.getUser(p).inGroup("Moderator")){
			nameColor = "&1";
		}else if(pex.getUser(p).inGroup("Developer")){
			nameColor = "&3";
		}else if(pex.getUser(p).inGroup("Admin")){
			nameColor = "&c";
		}else if(pex.getUser(p).inGroup("CoOwner")){
			nameColor = "&c";
		}else if(pex.getUser(p).inGroup("Owner")){
			nameColor = "&4";
		}
		nameColor = nameColor + p.getName();
		event.setFormat(ChatColor.translateAlternateColorCodes('&', nameColor) + ChatColor.RESET + ": " + event.getMessage());
	}

}
