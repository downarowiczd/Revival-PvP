package de.gamenetz.revival.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.gamenetz.revival.RevivalData;
import de.gamenetz.revival.User;
import de.gamenetz.revival.database.MySQL;

public class RevivalCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			User u = new User(p.getUniqueId());
			if(args.length == 0){
				if(!p.hasPermission("revival.admin.help")){
					u.sendMessage("&aRevival-PvP wurde von Game-Netz.de ins Leben gerufen!");
					u.sendMessage("&a Und wird derzeit von DOWNARDO programmiert!");
				}else{
					u.sendMessage("&a---Revival-Befehle---");
					u.sendMessage("&a/revival newstart -> Beginnt eine neue Welt, Stats bleiben gleich");
					u.sendMessage("&a/revival chatspy  -> Spioniert den Chat aus");
					u.sendMessage("&a/revival resetplayer [PlayerName]");
					u.sendMessage("&a/revival setLevelMultiplicator [Multi]");
				}
			}else if(args.length == 1){
				if(args[0].equalsIgnoreCase("newstart")){
					if(p.hasPermission("revival.admin.newstart")){
						if(RevivalData.reseting){
							u.sendMessage("Der Server resetet gerade!");
						}else{
							RevivalData.reseting = true;
							Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', RevivalData.MAIN_PREFIX + "Die Map wird in 60 Sekunden resetet. Bitte verstaut eure Sachen in der EnderChest oder im Inventar!"));
							Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Revival-PvP"), new Runnable() {
								
								@Override
								public void run() {
									RevivalData.resetMap();
								}
							}, 20L*65);
						}
					}else{
						u.sendMessage(RevivalData.noPerm);
					}
				}else if(args[0].equalsIgnoreCase("chatspy")){
					if(p.hasPermission("revival.admin.chatspy")){
						if(RevivalData.chat_spy.contains(p.getName())){
							u.sendMessage(RevivalData.MAIN_PREFIX + "&aDu beobachtest jetzt nicht mehr den privaten Chat!");
							RevivalData.chat_spy.remove(p.getName());
						}else{
							u.sendMessage(RevivalData.MAIN_PREFIX + "&aDu beobachtest nun den privaten Chat!");
							RevivalData.chat_spy.add(p.getName());
						}
					}else{
						u.sendMessage(RevivalData.noPerm);
					}
				}
			}else if(args.length == 2){
				if(args[0].equalsIgnoreCase("resetplayer")){
					if(p.hasPermission("revival.admin.resetplayer")){
						String t_n = args[1];
						if(RevivalData.isInReg(t_n)){
							User tu = new User(RevivalData.getUUIDbyUsername(t_n));
							if(tu.isOnline()){
								tu.getPlayer().kickPlayer(ChatColor.GOLD + "Du wirst nun zurückgesetzt!");
							}
							tu.setRandomTP(false);
							for(String homes : tu.getHomes()){
								tu.delHome(homes);
							}
							tu.setKills(0);
							tu.setDeaths(0);
							MySQL.Update("DELETE FROM revival_frieden WHERE from_uuid='" + tu.getUUID().toString() + "' OR to_uuid='" + tu.getUUID().toString() + "'");
						}else{
							u.sendMessage("&aDer Spieler ist nicht in der Datenbank!");
						}
					}else{
						u.sendMessage(RevivalData.noPerm);
					}
				}else if(args[0].equalsIgnoreCase("setLevelMultiplicator")){
					if(p.hasPermission("revival.admin.setlevelmultiplicator")){
						String amp = args[1];
						int a = 1;
						try{
							a = Integer.valueOf(amp);
						}catch(NumberFormatException e){
							u.sendMessage("&cZahl!!!!!!!!!!!!!!!!");
						}
						
						if(a < 0){
							a = 0;
						}
						
						RevivalData.XP_AMPLIFIER = a;
					}else{
						u.sendMessage(RevivalData.noPerm);
					}
				}
			}
		}
		return true;
	}

}
