package de.gamenetz.revival.commands;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.gamenetz.revival.Revival;
import de.gamenetz.revival.User;

public class EnderChestCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		if(sender instanceof Player){
			Player p = (Player)sender;
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
			if(uses <= 0){
				u.sendMessage("&cDu hast nicht genug VotePunkte. Mach /vote und vote dann!");
				return true;
			}else{
				p.openInventory(p.getEnderChest());
				Revival.votes_points_cfg.set("Vote." + p.getUniqueId().toString() + ".Uses", uses - 1);
				try {
					Revival.votes_points_cfg.save(Revival.votes_points);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return true;
	}

}
