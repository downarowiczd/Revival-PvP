package de.gamenetz.revival.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.gamenetz.revival.User;

public class RangCommand implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String [] args){
		
	     if (sender instanceof Player) {
             Player p = (Player)sender;
             User u = new User(p.getUniqueId());
             	if (cmd.getName().equalsIgnoreCase("rang")) {
             		u.sendMessage("&a---Premium-Rang---");
             		u.sendMessage("&a-Vorteile:");
             		u.sendMessage("&aKann mit dem Kompass Spieler von weiter weg orten.");
             		u.sendMessage("&aKann 5 Homes setzten");
             		u.sendMessage("&aBekommt beim Voten 3 VotePunkte");
             		u.sendMessage("&aaPreis: 20 Euro");
             		u.sendMessage("&a---Premium-Rang---");
                }
	     }
		return true;
	}
}
