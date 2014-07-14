package de.gamenetz.revival.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.gamenetz.revival.User;

public class VoteCommand implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
		Player p = (Player) sender;
		User u = new User(p.getUniqueId());
		u.sendMessage("");
		u.sendMessage("&8&m[&7&m---------------&8[ &6&lVOTE &8]&7&m---------------&8&m]");
		u.sendMessage("");
		u.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD  + "1. " + ChatColor.GRAY + "Klicke auf den Link&8: &c&lhttp://serverliste.org/?s=vote&id=6211");
		u.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "2. " + ChatColor.GRAY + "Gebe den Code/Namen ein.");
		u.sendMessage(ChatColor.YELLOW + "" +ChatColor.BOLD +"3. " + ChatColor.GRAY + "Klicke auf Bewerten!");
		u.sendMessage(ChatColor.YELLOW + "" +ChatColor.BOLD + "4. " + ChatColor.GRAY + "Warte auf deine Belohnung...");
		u.sendMessage("&cAlles &ber die VotePunkte erfahren ?? &4&l/voteinfo");
		u.sendMessage("");
		}
		return true;
	}

}
