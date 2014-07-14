package de.gamenetz.revival.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatProtecter implements Listener{


	private Pattern ipPattern = Pattern.compile("((?<![0-9])(?:(?:25[0-5]|2[0-4][0-9]|[0-1]?[0-9]{1,2})[ ]?[.,_ ][ ]?(?:25[0-5]|2[0-4][0-9]|[0-1]?[0-9]{1,2})[ ]?[.,_ ][ ]?(?:25[0-5]|2[0-4][0-9]|[0-1]?[0-9]{1,2})[ ]?[.,_ ][ ]?(?:25[0-5]|2[0-4][0-9]|[0-1]?[0-9]{1,2}))(?![0-9]))");
	private Pattern webPattern = Pattern.compile("(http://)|(https://)?(www)?\\S{2,}((\\.com)|(\\.ru)|(\\.net)|(\\.org)|(\\.co\\.uk)|(\\.tk)|(\\.info)|(\\.es)|(\\.de)|(\\.arpa)|(\\.edu)|(\\.firm)|(\\.int)|(\\.mil)|(\\.mobi)|(\\.nato)|(\\.to)|(\\.fr)|(\\.ms)|(\\.vu)|(\\.eu)|(\\.nl)|(\\.us)|(\\.dk)|(\\,com)|(\\,ru)|(\\,net)|(\\,org)|(\\,co\\,uk)|(\\,tk)|(\\,info)|(\\,es)|(\\,de)|(\\,arpa)|(\\,edu)|(\\,firm)|(\\,int)|(\\,mil)|(\\,mobi)|(\\,nato)|(\\,to)|(\\,fr)|(\\,ms)|(\\,vu)|(\\,eu)|(\\,nl)|(\\,us)|(\\,dk))");
	private static Map<String, Long> cooldown = new HashMap<String, Long>();
	
	@EventHandler
	public void onWerbung(AsyncPlayerChatEvent event){
		String message = event.getMessage();
		Player p = event.getPlayer();
		if(!p.hasPermission("revival.werbung.ignore")){
			if(isIPorURL(message)){
				p.sendMessage(ChatColor.RED + "[Werbung] Auf diesem Server ist Werbung verboten!!!");
				for(Player players : Bukkit.getOnlinePlayers()){
					if(players.hasPermission("revival.werbung")){
						players.sendMessage(ChatColor.RED + "[Werbung]-> " + ChatColor.GRAY + p.getName() + ": " + message);
					}
				}
				event.setCancelled(true);
			}
		}
	
	}
	

	@EventHandler
	public void AntiSpam(AsyncPlayerChatEvent event) {
		Player s = event.getPlayer();
		Long time = Long.valueOf(System.currentTimeMillis());
		if (!s.hasPermission("revival.antispam")) {
			if (ChatProtecter.cooldown.containsKey(s.getName())) {
				Long lastussage = (Long) ChatProtecter.cooldown.get(s.getName());
				if (lastussage.longValue() + 5 * 1000 > time.longValue()) {
					event.setCancelled(true);
					s.sendMessage(ChatColor.RED + "Du kannst nur alle 5 Sekunden schreiben!");
					return;
				}
			}
			ChatProtecter.cooldown.put(s.getName(), time);
		}
	}
	
	
	@EventHandler
	public void onCaps(AsyncPlayerChatEvent event) {
		if (!event.getPlayer().hasPermission("revival.caps")) {
			int spacecount = 0;
			boolean msgbool = false;
			String[] msg = event.getMessage().split(" ");
			for (int i = 0; i < msg.length; i++) {
				if ((msg[i].length() == 1) && (isMostUpper2(msg[i]))) {
					spacecount++;
				}
				if ((isMostUpper(msg[i])) || (spacecount > 2)) {
					msg[i] = msg[i].toLowerCase();
					if (!msgbool) {
						event.getPlayer().sendMessage(ChatColor.RED + "Caps ist schlecht!");
						msgbool = true;
					}
				}
			}
			String newmsg = "";
			for (int i = 0; i < msg.length; i++) {
				newmsg = newmsg + msg[i];
				newmsg = newmsg + " ";
			}
			event.setMessage(newmsg);
			msgbool = false;
		}
	}

	private boolean isIPorURL(String word) {
	    Matcher searchforips = this.ipPattern.matcher(word.toLowerCase());
	    Matcher searchforweb = this.webPattern.matcher(word.toLowerCase());
	    if((searchforips.find()) || (searchforweb.find())) return true;
	    else return false;
	}
	
	private static boolean isMostUpper(String s) {
		int capcount = 0;
		for (char c : s.toCharArray()) {
			if ((Character.isLetter(c)) && (Character.isUpperCase(c))) {
				capcount++;
			}
		}
		return (capcount > s.length() / 2) && (s.length() != 1);
	}

	private static boolean isMostUpper2(String s) {
		for (char c : s.toCharArray()) {
			if ((Character.isLetter(c)) && (Character.isUpperCase(c))) {
				return true;
			}
		}
		return false;
	}
}
