package de.gamenetz.revival.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.gamenetz.revival.User;

public class TSCommand implements CommandExecutor{
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
		Player p = (Player) sender;
		User u = new User(p.getUniqueId());
	    u.sendMessage("&2&m==============================================");
		u.sendMessage(ChatColor.GRAY + "IP &7» " + ChatColor.GREEN + "ts.revival-pvp.de");
	    u.sendMessage("&2&m==============================================");
		}
		return true;
	}
}
