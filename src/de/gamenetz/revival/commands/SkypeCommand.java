package de.gamenetz.revival.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.gamenetz.revival.User;

public class SkypeCommand implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
		Player p = (Player) sender;
		User u = new User(p.getUniqueId());
	    u.sendMessage("&2&m==============================================");
		u.sendMessage(ChatColor.GRAY + "Gamesuchtie &7&l->" + ChatColor.GREEN + " cedrik.raabe");
		u.sendMessage(ChatColor.GRAY + "_R3DShUiiN_ &7&l->" + ChatColor.GREEN + " crazy.zocken1");
		u.sendMessage(ChatColor.GRAY + "DOWNARDO &7&l->" + ChatColor.GREEN + " dominik.downarowicz1200");
	    u.sendMessage("&2&m==============================================");
		}
		return true;
	}

}
