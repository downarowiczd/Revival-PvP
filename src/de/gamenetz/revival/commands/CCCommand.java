package de.gamenetz.revival.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.gamenetz.revival.RevivalData;

public class CCCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		String message = "";
		if(sender.hasPermission("revival.cc")){
			if(sender instanceof Player){
				message = ChatColor.GRAY + " Der Chat wurde von " + ChatColor.YELLOW + sender.getName() + ChatColor.GRAY + " gesaeubert!";
			}else{
				message = ChatColor.GRAY + " Der Chat wurde von der " + ChatColor.YELLOW + "Console" + ChatColor.GRAY + "gesaeubert!";
			}
			
			for(Player players : Bukkit.getOnlinePlayers()){
				if(!players.hasPermission("revival.cc.ignore")){
					for(int x = 0; x < 150; x++){
						players.sendMessage("");
					}
				}
			}
			Bukkit.broadcastMessage(message);		
			}else{
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', RevivalData.noPerm));
			}
		return true;
	}

}
