package de.gamenetz.revival.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.gamenetz.revival.RevivalData;
import de.gamenetz.revival.User;

public class MSGCommand implements CommandExecutor{

	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			User u = new User(p.getUniqueId());
			if(cmd.getName().equalsIgnoreCase("msg")){
				if(args.length >= 2){
					StringBuilder sb = new StringBuilder();
					for(int i = 1; i < args.length; i++){
						if (i != 0)
							sb.append(" ");
						sb.append(args[i]);
					}
					String message = sb.toString();
					if(RevivalData.isInReg(args[0])){
						User t = new User(RevivalData.getUUIDbyUsername(args[0]));
						if(t.isOnline()){
							sendMSG(u, t, message);
							RevivalData.conversation.put(u.getPlayer().getName(), t.getPlayer().getName());
							RevivalData.conversation.put(t.getPlayer().getName(), u.getPlayer().getName());
						}else{
							u.sendMessage("&cDer Spieler ist nicht online!");
						}
					}else{
						u.sendMessage("&cDer Spieler war nie online!");
					}
				}else{
					u.sendMessage("&cVerwendung: /msg [SpielName] [Nachricht]");
				}
			}else if(cmd.getName().equalsIgnoreCase("r")){
				if(args.length >= 1){
					if(RevivalData.conversation.containsKey(u.getPlayer().getName())){
						String t_name = RevivalData.conversation.get(u.getPlayer().getName());
						if(RevivalData.isInReg(t_name)){
							User t = new User(RevivalData.getUUIDbyUsername(t_name));
							if(t.isOnline()){
							StringBuilder sb = new StringBuilder();
							for(int i = 0; i < args.length; i++){
								if (i != 0)
									sb.append(" ");
								sb.append(args[i]);
							}
							String message = sb.toString();
								sendMSG(u, t, message);
								RevivalData.conversation.put(u.getPlayer().getName(), t.getPlayer().getName());
								RevivalData.conversation.put(t.getPlayer().getName(), u.getPlayer().getName());
							}else{
								u.sendMessage("&cLeider ist dein Gesprächspartner offline!");
							}
						}else{
							u.sendMessage("&aLeider kann die Vermittlung deinen Gesprächspartner nicht finden!");
						}
					}else{
						u.sendMessage("&aDu führst gerade keine Konversation!");
					}
				}else{
					u.sendMessage("&cVerwendung: /r [Nachricht]");
				}
			}
		}
		return true;
	}
	
	private void sendMSG(User sender, User reciver, String message){
		sender.sendMessage("&3[&aich -> &2" + reciver.getPlayer().getName() + "&7]: " + message);
		reciver.sendMessage("&3[&a" + sender.getPlayer().getName() + "-> &2mir&7]: " + message);
		for(Player players : Bukkit.getOnlinePlayers()){
			if(RevivalData.chat_spy.contains(players.getName())){
				players.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[Revival-Chat-Spy]: &3[&a" + sender.getPlayer().getName() + " -> &2" + reciver.getPlayer().getName() + "&7]: " + message));
			}
		}
	}

}
