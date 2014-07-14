package de.gamenetz.revival.commands;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.gamenetz.revival.NotMove;
import de.gamenetz.revival.RevivalData;
import de.gamenetz.revival.User;

public class HomeCommand implements CommandExecutor{
	
	private int NORMAL_USER = 1;
	private int PREMIUM_USER = 5;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			User u = new User(p.getUniqueId());
			List<String> homes = u.getHomes();
			if(cmd.getName().equalsIgnoreCase("home")){
				if(args.length == 0){
					if(homes.size() == 0){
						u.sendMessage(RevivalData.MAIN_PREFIX + "&aDu hast keine Homes gesetzt!");
						return true;
					}
					
					if(homes.size() == 1){
						Location loc = u.getHomeLocation(homes.get(0));
						if(loc.distance(loc.getWorld().getSpawnLocation()) > RevivalData.BORDER_RADIUS){
							u.sendMessage("&cDein Home liegt ausserhalb der WeltGrenze!");
						}else{
							NotMove nm = new NotMove(u, loc, "deinem Home");
							nm.go();
						}
						return true;
					}
					
					if(homes.size() > 1){
						String home_msg = "";
						for(String home : homes){
							home_msg = home_msg + home + ", ";
						}
						home_msg = home_msg.substring(0, home_msg.length() - 2);
						u.sendMessage(RevivalData.MAIN_PREFIX + "&aDeine Homes: " + home_msg);
						return true;
					}
					
				}else if(args.length == 1){
					String homeName = args[0];
					homeName = homeName.toLowerCase();
					if(homes.contains(homeName)){
						Location loc = u.getHomeLocation(homeName);
						if((loc.distance(loc.getWorld().getSpawnLocation())) >= RevivalData.BORDER_RADIUS){
							u.sendMessage(RevivalData.MAIN_PREFIX + "&cDein Home liegt ausserhalb der WeltGrenze!");
						}else{
							NotMove nm = new NotMove(u, loc, "deinem Home");
							nm.go();
						}
					}else{
						u.sendMessage(RevivalData.MAIN_PREFIX + "&cDas Home &a" + homeName + "&c ist nicht gesetzt!");
					}
					
				}else{
					u.sendMessage("&cVerwendung: /home [Name]");
				}
			}else if(cmd.getName().equalsIgnoreCase("sethome")){
				if(args.length == 0){
					int howMany = NORMAL_USER;
					if(p.hasPermission("revival.home.normal")){
						howMany = NORMAL_USER;
					}else if(p.hasPermission("revival.home.premium") && p.hasPermission("revival.home.normal")){
						howMany = PREMIUM_USER;
					}else if(p.hasPermission("revival.home.unlimited") && p.hasPermission("revival.home.premium")){
						howMany = 10000;
					}
					if(homes.contains("home")){
						u.setHome(p.getLocation(), "home");
						u.sendMessage(RevivalData.MAIN_PREFIX + "&aDu hast dein Home erfolgreich gesetzt!");
					}else{
						if(homes.size() < howMany){
							u.setHome(p.getLocation(), "home");
							u.sendMessage(RevivalData.MAIN_PREFIX + "&aDu hast dein Home erfolgreich gesetzt!");
						}else{
							u.sendMessage(RevivalData.MAIN_PREFIX + "&cDu hast die max. Anzahl an Homes erreicht!");
						}
					}
				}else if(args.length == 1){
					int howMany = NORMAL_USER;
					if(p.hasPermission("revival.home.normal")){
						howMany = NORMAL_USER;
					}
					if(p.hasPermission("revival.home.premium") && p.hasPermission("revival.home.normal")){
						howMany = PREMIUM_USER;
					}
					if(p.hasPermission("revival.home.unlimited") && p.hasPermission("revival.home.premium") && p.hasPermission("revival.home.normal")){
						howMany = 10000;
					}
					if(homes.contains(args[0].toLowerCase())){
						u.setHome(p.getLocation(), args[0].toLowerCase());
						u.sendMessage(RevivalData.MAIN_PREFIX + "&aDu hast dein Home erfolgreich gesetzt!");
					}else{
						if(homes.size() < howMany){
							u.setHome(p.getLocation(), args[0]);
							u.sendMessage(RevivalData.MAIN_PREFIX + "&aDu hast dein Home erfolgreich gesetzt!");
						}else{
							u.sendMessage(RevivalData.MAIN_PREFIX + "&cDu hast die max. Anzahl an Homes erreicht!");
						}
					}
				}else{
					u.sendMessage("&cVerwendung: /sethome [Name]");
				}
				
			}else if(cmd.getName().equalsIgnoreCase("delhome")){
				if(args.length == 1){
					if(homes.contains(args[0].toLowerCase())){
						u.delHome(args[0].toLowerCase());
						u.sendMessage(RevivalData.MAIN_PREFIX + "&aDu hast dein Home erfolgreich geloescht!");
					}else{
						u.sendMessage(RevivalData.MAIN_PREFIX + "&cDiese Home gibt es nicht!");
					}
				}else{
					u.sendMessage("&cVerwendung: /delhome [Name]");
				}
			}else if(cmd.getName().equalsIgnoreCase("homes")){
				if(homes.size() > 1){
					String home_msg = "";
					for(String home : homes){
						home_msg = home_msg + home + ", ";
					}
					home_msg = home_msg.substring(0, home_msg.length() - 2);
					u.sendMessage(RevivalData.MAIN_PREFIX + "&aDeine Homes: " + home_msg);
					return true;
				}else if(homes.size() == 1){
					u.sendMessage(RevivalData.MAIN_PREFIX + "&aDeine Homes: " + homes.get(0));
				}else if(homes.size() == 0){
					u.sendMessage(RevivalData.MAIN_PREFIX + "&aDeine Homes: ");
				}
		}
	}
		return true;

}

}
