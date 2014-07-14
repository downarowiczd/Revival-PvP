package de.gamenetz.revival.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;
import de.gamenetz.revival.User;

public class TeamCommand implements CommandExecutor{

	public static String Strich = " " + ChatColor.DARK_AQUA + ChatColor.BOLD + "| ";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			User u = new User(p.getUniqueId());
			String Owner = "";
			String CoOwner = "";
			String Admin = "";
			String Developer = "";
			String Moderator = "";
			String Supporter = ""; 
		    String Helfer = ""; 
		    String Partner = ""; 
		    String TeamFreund = ""; 
			u.sendMessage("&2&m==============================================");
			PermissionManager pex = PermissionsEx.getPermissionManager();
		    for(PermissionUser user : pex.getUsers("Owner")){
				if(user.getPlayer() != null){
					Owner = Owner + ChatColor.YELLOW + user.getName() + Strich;
					}else{
					Owner = Owner + ChatColor.GRAY + user.getName() + Strich;
				}
		    }
		    
		    for(PermissionUser user : pex.getUsers("CoOwner")){
				if(user.getPlayer() != null){
					CoOwner = CoOwner + ChatColor.YELLOW + user.getName() + Strich;
					}else{
					CoOwner = CoOwner + ChatColor.GRAY + user.getName() + Strich;
				}
		    }
		    
		    for(PermissionUser user : pex.getUsers("Admin")){
				if(user.getPlayer() != null){
					Admin = Admin + ChatColor.YELLOW + user.getName() + Strich;
					}else{
					Admin = Admin + ChatColor.GRAY + user.getName() + Strich;
				}
		    }
		    
			for(PermissionUser user : pex.getUsers("Developer")){
					if(user.getPlayer() != null){
						Developer = Developer + ChatColor.YELLOW + user.getName() + Strich;
						}else{
						Developer = Developer + ChatColor.GRAY + user.getName() + Strich;
					}
			}
			
			
			for(PermissionUser user : pex.getUsers("Moderator")){
				if(user.getPlayer() != null){
					Moderator = Moderator + ChatColor.YELLOW + user.getName() + Strich;
					}else{
						Moderator = Moderator + ChatColor.GRAY + user.getName() + Strich;
				}
		    }
			
			for(PermissionUser user : pex.getUsers("Supporter")){
				if(user.getPlayer() != null){
					Supporter = Supporter + ChatColor.YELLOW + user.getName() + Strich;
					}else{
					Supporter = Supporter + ChatColor.GRAY + user.getName() + Strich;
				}
		    }
			
			for(PermissionUser user : pex.getUsers("Helfer")){
				if(user.getPlayer() != null){
					Helfer = Helfer + ChatColor.YELLOW + user.getName() + Strich;
					}else{
					Helfer = Helfer + ChatColor.GRAY + user.getName() + Strich;
				}
		    }
			
			for(PermissionUser user : pex.getUsers("TeamFreund")){
				if(user.getPlayer() != null){
					TeamFreund = TeamFreund + ChatColor.YELLOW + user.getName() + Strich;
					}else{
					TeamFreund = TeamFreund + ChatColor.GRAY + user.getName() + Strich;
				}
		    }
			
			for(PermissionUser user : pex.getUsers("Partner")){
				if(user.getPlayer() != null){
					Partner = Partner + ChatColor.YELLOW + user.getName() + Strich;
					}else{
						Partner = Partner + ChatColor.GRAY + user.getName() + Strich;
				}
		    }
			
			
            
			if (Owner != "")
				u.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Owner &7&l: ") + Owner.substring(0, Owner.length() - 2) + ChatColor.DARK_RED);
			if (CoOwner != "")
				u.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Co&cOwner &7&l: ") + CoOwner.substring(0, CoOwner.length() - 2) + ChatColor.RED);
			if (Admin != "")
			    u.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cAdmin &7&l: ") + Admin.substring(0, Admin.length() - 2));
			if (Developer != "")
			    u.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bDeveloper &7&l: ") + Developer.substring(0, Developer.length() - 2));
			if (Moderator != "")
			    u.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9Moderator &7&l: ") + Moderator.substring(0, Moderator.length() - 2));
			if (Supporter != "")
			    u.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eSupporter &7&l: ") + Supporter.substring(0, Supporter.length() - 2));
			if (Helfer != "")
			    u.sendMessage(ChatColor.translateAlternateColorCodes('&', "&dHelfer &7&l: ") + Helfer.substring(0, Helfer.length() - 2));
			if (Partner != "")
			    u.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bPartner &7&l: ") + Partner.substring(0, Partner.length() - 2));
			if (TeamFreund != "")
			    u.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aTeamFreund &7&l: ") + TeamFreund.substring(0, TeamFreund.length() - 2));
			    u.sendMessage("&2&m==============================================");
			
		    
		}
		return true;
	}

}
