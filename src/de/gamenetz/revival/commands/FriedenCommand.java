package de.gamenetz.revival.commands;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import de.gamenetz.revival.RevivalData;
import de.gamenetz.revival.User;
import de.gamenetz.revival.database.MySQL;

public class FriedenCommand implements CommandExecutor {

	public static HashMap<String, String> anfragen=new HashMap<String, String>();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		if(sender instanceof Player){
			Player p=(Player) sender;
			User u = new User(p.getUniqueId());
			if(cmd.getName().equalsIgnoreCase("frieden")){
				if(args.length==0){
					u.sendMessage("&7&m==================================================");
					u.sendMessage("&a/Frieden <Spieler> &7: &7Schickt ein Spieler Frieden/Loest Friede auf");
					u.sendMessage("&a/Frieden list&e: &7Zeigt die Frieden-Liste.");
					u.sendMessage("&a/Annehmen &e:&7 Friede Annehmen");
					u.sendMessage("&a/Ablehnen &e:&7 Friede Ablehnen");
					u.sendMessage("&27&m==================================================");
				}
				else if(args.length==1){
					if(args[0].equalsIgnoreCase("hilfe")){
						u.sendMessage("&7&m==================================================");
						u.sendMessage("&a/Frieden <Spieler> &7: &7Schickt ein Spieler Frieden/Loest Friede auf");
						u.sendMessage("&a/Frieden list&e: &7Zeigt die Frieden-Liste.");
						u.sendMessage("&a/Annehmen &e:&7 Friede Annehmen");
						u.sendMessage("&a/Ablehnen &e:&7 Friede Ablehnen");
						u.sendMessage("&27&m==================================================");
					}
					else if(args[0].equalsIgnoreCase("list")){
						String msg = "&6";
						if(u.loadFrieden().isEmpty()){
							u.sendMessage("&cDu hast noch keine Friedensvertraege abgeschlossen!");
						}else{
							for(String x : u.loadFrieden()){
								User tu = new User(UUID.fromString(x));
								//msg+=s+", ";
								msg = msg + tu.getName() + ", ";
							}
							msg=msg.substring(0,msg.length()-2);
							u.sendMessage("&eDu hast mit folgenden Personen Frieden:");
							u.sendMessage("&7" + msg);
						}
					}
					else{
						if(args[0].equalsIgnoreCase(p.getName())){
							u.sendMessage("&cDu kannst dir selber keine Friedensanfrage senden!");
						}
						else{
							String t_name = args[0];
							if(RevivalData.isInReg(t_name)){
							User tu = new User(RevivalData.getUUIDbyUsername(t_name));
							Player t=tu.getPlayer();
							if(t!=null){
								if(u.isFrieden(t.getUniqueId())){
										MySQL.Update("DELETE FROM revival_frieden WHERE from_uuid='" + p.getUniqueId().toString() + "' AND to_uuid='" + t.getUniqueId().toString() + "'");
										MySQL.Update("DELETE FROM revival_frieden WHERE from_uuid='" + t.getUniqueId().toString() + "' AND to_uuid='" + p.getUniqueId().toString() + "'");
										u.sendMessage("&7Du hast den Frieden mit &b"+t.getName()+" &caufgeloest.");
										tu.sendMessage("&b"+p.getName()+"&7 hat den Frieden mit dir &caufgeloest.");
								}else{
									u.sendMessage("&7Du hast &e" + t.getName() + "&7 eine Friedensanfrage &egesendet.");
									tu.sendMessage("&e"+p.getName()+"&7 hat dir eine Friedensanfrage &7gesendet. &e/annehmen /ablehnen");
									if(anfragen.containsKey(t.getName())){
										anfragen.remove(t.getName());
									}
									anfragen.put(t.getName(), p.getName());
								}
							}
							else{
								u.sendMessage("&cDer Spieler ist nicht online!");
							}
						}else{
							u.sendMessage(ChatColor.RED + "Der Spieler ist nicht im Register!");
						}
					}
					}
				}
			}
			if(cmd.getName().equalsIgnoreCase("annehmen")){
				if(anfragen.containsKey(p.getName())){
					User tu = new User(RevivalData.getUUIDbyUsername(anfragen.get(p.getName())));
					MySQL.Update("INSERT INTO revival_frieden (from_uuid, to_uuid) VALUES ('" + p.getUniqueId().toString() + "', '" + tu.getUUID().toString() + "')");
					MySQL.Update("INSERT INTO revival_frieden (from_uuid, to_uuid) VALUES ('" + tu.getUUID().toString() + "', '" + p.getUniqueId().toString() + "')");
					u.sendMessage("&7Du hast die Friedensanfrage von &a"+tu.getName()+"&e angenommen.");
					tu.sendMessage("&e"+p.getName()+"&7 hat deine Friedensanfrage &eangenommen.");
					anfragen.remove(p.getName());
				}else{
					u.sendMessage("&cDir hat keiner eine Anfrage gesendet!");
				}
			}
			if(cmd.getName().equalsIgnoreCase("ablehnen")){
				if(anfragen.containsKey(p.getName())){
					if(RevivalData.isInReg(anfragen.get(p.getName()))){
							User tu = new User(RevivalData.getUUIDbyUsername(anfragen.get(p.getName())));
							u.sendMessage("&7Du hast die Friedensanfrage von &e"+tu.getName()+" &7abgelehnt.");
							tu.sendMessage("&e"+p.getName()+"&7 hat deine Friedensanfrage &7abgelehnt.");
					}
				anfragen.remove(p.getName());
				}else{
					u.sendMessage("&cDir hat keiner eine Anfrage gesendet!");
				}
			}
		}
		return true;
	}
	
}
