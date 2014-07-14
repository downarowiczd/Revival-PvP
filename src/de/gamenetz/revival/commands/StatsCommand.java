package de.gamenetz.revival.commands;

import java.sql.ResultSet;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.gamenetz.revival.RevivalData;
import de.gamenetz.revival.User;
import de.gamenetz.revival.database.MySQL;

public class StatsCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			User u = new User(p.getUniqueId());
			if(args.length == 0){
				u.sendMessage("&a--Deine Stats---");
				int r = 0;
				boolean finish = false;
				ResultSet rs = MySQL.Query("SELECT username FROM revival_user ORDER BY kills DESC");
				try{
					while(rs.next() && !finish){
						r = r + 1;
						if(rs.getString(1).equalsIgnoreCase(p.getName())){
							finish = true;
						}
					}
					rs.close();
				}catch(Exception e){
					System.err.println(e);
				}
				
				u.sendMessage("&aRanking: &2#" + r);
				u.sendMessage("&aKills: &2" + u.getKills());
				u.sendMessage("&aTode: &2" + u.getDeaths());
			}else if(args.length == 1){
				String t_name = args[0];
				if(RevivalData.isInReg(t_name)){
					User t = new User(RevivalData.getUUIDbyUsername(t_name));
					
					u.sendMessage("&a--Stats von " + t_name + " ---");
					int r = 0;
					boolean finish = false;
					ResultSet rs = MySQL.Query("SELECT username FROM revival_user ORDER BY kills DESC");
					try{
						while(rs.next() && !finish){
							r = r + 1;
							if(rs.getString(1).equalsIgnoreCase(t_name)){
								finish = true;
							}
						}
						rs.close();
					}catch(Exception e){
						System.err.println(e);
					}
					u.sendMessage("&aRanking: &2#" + r);
					u.sendMessage("&aKills: &2" + t.getKills());
					u.sendMessage("&aTode: &2" + t.getDeaths());
					
				}else{
					u.sendMessage("&cDer Spieler ist nicht im Register!");
				}
				
			}
			
		}
		
		return true;
	}
	
}
