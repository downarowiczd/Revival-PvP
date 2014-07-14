package de.gamenetz.revival.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.gamenetz.revival.User;

public class BewerbenCommand implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			User u = new User(p.getUniqueId());
			u.sendMessage("&2&m==============================================");
			u.sendMessage("&7Du möchtest in unser &aTeam &7?");
			u.sendMessage("&7Um ins &aTeam &7befolge folgende Schritte...");
			u.sendMessage("&2&l1. &7Du musst mindestens &a14+ &7sein!");
			u.sendMessage("&2&l2. &7Du musst gute Kentnisse in Minecraft haben!");
			u.sendMessage("&2&l3. &7Vorteile hast du, wenn du jemanden aus dem &aTeam &7kennst!");
			u.sendMessage("&2&l4. &7Die Bewerbung schickst du uns in einer Text-Datei per Skype &a(/skype)");
			u.sendMessage(" ");
			u.sendMessage("&7 ");
			u.sendMessage("&7Wir w�nschen dir viel Gl�ck bei deiner Bewerbung!");
			u.sendMessage("&7Vielleicht werden wir einen von euch auch bald im &aTeam &7sehen.");
			u.sendMessage("&2&m==============================================");
			
		}
		return true;
	}

}
