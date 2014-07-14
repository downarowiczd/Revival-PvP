package de.gamenetz.revival.commands;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.gamenetz.revival.Revival;
import de.gamenetz.revival.User;


public class VoteInfoCommand implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
		Player p = (Player) sender;
		User u = new User(p.getUniqueId());
		if(!Revival.votes_points_cfg.contains("Vote." + p.getUniqueId().toString() + ".Uses")){
			Revival.votes_points_cfg.set("Vote." + p.getUniqueId().toString() + ".Uses", 0);
			try {
				Revival.votes_points_cfg.save(Revival.votes_points);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		int uses = Revival.votes_points_cfg.getInt("Vote." + p.getUniqueId().toString() + ".Uses");
		u.sendMessage("&2&m=====================================================");
		u.sendMessage("&7Du hast jetzt &a" + uses + "&7 VotePunkte.");
		u.sendMessage("&7Du kannst damit &e/tpa, /ec, /tpahere");
		u.sendMessage("");
		u.sendMessage("&7Pro Vote bekommst du &e2 VotePunkte ausser du hast einen Premium-Rang, dann bekommst du 3 VotePunkte!");
		u.sendMessage("&7Wenn du einen Command machst, &cverlierst du &e1 VotePunkt.");
		u.sendMessage("&2&m=====================================================");
		}
		return true;
	}

}
