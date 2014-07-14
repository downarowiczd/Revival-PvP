package de.gamenetz.revival.commands;

import java.sql.ResultSet;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.gamenetz.revival.User;
import de.gamenetz.revival.database.MySQL;

public class RankingCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
    	if(sender instanceof Player){
    		Player p = (Player)sender;
    		User u = new User(p.getUniqueId());
    		u.sendMessage("&a----Top 10 | Spieler-----");
    		try{
    			ResultSet rs = MySQL.Query("SELECT username,kills FROM revival_user ORDER BY kills DESC LIMIT 10;");
    			while(rs.next()){
    				u.sendMessage("&a" + rs.getString(1) + " | " + rs.getInt(2));
    			}
    			rs.close();
    		}catch(Exception e){
    			System.err.println(e);
    		}
    	}
    	
    	return true;
    }

}
