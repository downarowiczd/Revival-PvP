package de.gamenetz.revival.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;

import de.gamenetz.revival.RevivalData;

public class VoteGiveCommand implements CommandExecutor{
	

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender.hasPermission("revival.Votegive")){
			if (args.length == 0){
				sender.sendMessage(ChatColor.AQUA + " /votegive <Spieler>");
			}else if (args.length == 1){
				if(RevivalData.isInReg(args[0])){
				Player p2 = Bukkit.getPlayer(RevivalData.getUUIDbyUsername(args[0]));
				if (p2 != null){
					Vote vote = new Vote();
			          vote.setUsername(args[0]);
			          Bukkit.getServer().getPluginManager().callEvent(new VotifierEvent(vote));
			          sender.sendMessage(ChatColor.YELLOW + "Du hast dem Spieler " + ChatColor.GREEN  + args[0] + ChatColor.YELLOW +" einen Vote hinzugefügt.");
					}else{
						sender.sendMessage(ChatColor.RED + "Der Spieler ist nicht Online!");
					}
				}else{
					sender.sendMessage(ChatColor.RED + "Der Spieler ist nicht in der Datenbank!");
				}
				}else{
					sender.sendMessage(ChatColor.RED + "Verwendung: /votegive [Spielername]");	
				}
		}else{
			sender.sendMessage(RevivalData.noPerm);
		}
		return true;
	}
}
