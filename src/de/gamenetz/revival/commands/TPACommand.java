package de.gamenetz.revival.commands;

import java.io.IOException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.gamenetz.revival.NotMove;
import de.gamenetz.revival.Revival;
import de.gamenetz.revival.RevivalData;
import de.gamenetz.revival.User;

public class TPACommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			User u = new User(p.getUniqueId());
			if(cmd.getName().equalsIgnoreCase("tpa")){
				if(args.length == 0 || args.length > 1){
					u.sendMessage("&cVerwendung: /tpa [Spieler]");
				}else{
					if(!Revival.votes_points_cfg.contains("Vote." + u.getUUID().toString() + ".Uses")){
						Revival.votes_points_cfg.set("Vote." + u.getUUID().toString() + ".Uses", 0);
						try {
							Revival.votes_points_cfg.save(Revival.votes_points);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
					if(Revival.votes_points_cfg.getInt("Vote." + u.getUUID().toString() + ".Uses") <= 0){
						u.sendMessage("&cDu hast nicht genug Vote-Punkte! Mach /vote und vote dann!");
					}else{
						String t_name = args[0];
						if(RevivalData.isInReg(t_name)){
							User t = new User(RevivalData.getUUIDbyUsername(t_name));
							if(t.isOnline()){
								if(t.getPlayer().getName().equalsIgnoreCase(u.getPlayer().getName())){
									u.sendMessage("&cDu kannst nicht dich selber teleportieren!");
								}else{
									u.sendMessage("&aDu hast dem Spieler erfolgreich eine Teleport-Anfrage geschickt!");
									t.sendMessage("&aDer Spieler &2" + u.getPlayer().getName() + " &awill sich zu dir teleportieren!");
									t.sendMessage("&aMit &c/tpaccept &aakzeptierst du die Teleport-Anfrage!");
									t.sendMessage("&aMit &c/tpadeny &abrichst du die Teleport-Anfrage ab!");
									RevivalData.tpa_request.put(t.getPlayer().getName(), u.getPlayer().getName());
									Revival.votes_points_cfg.set("Vote." + u.getUUID().toString() + ".Uses", Revival.votes_points_cfg.getInt("Vote." + u.getUUID().toString() + ".Uses") - 1);
								}
							}else{
								u.sendMessage("&cDer Spieler ist nicht online!");
							}
						}else{
							u.sendMessage("&cDer Spieler ist nicht im Register!");
						}
					}
				}
			}else if(cmd.getName().equalsIgnoreCase("tpahere")){
				if(args.length == 0 || args.length > 1){
					u.sendMessage("&cVerwendung: /tpahere [Spieler]");
				}else{
					if(!Revival.votes_points_cfg.contains("Vote." + u.getUUID().toString() + ".Uses")){
						Revival.votes_points_cfg.set("Vote." + u.getUUID().toString() + ".Uses", 0);
						try {
							Revival.votes_points_cfg.save(Revival.votes_points);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
					if(Revival.votes_points_cfg.getInt("Vote." + u.getUUID().toString() + ".Uses") <= 0){
						u.sendMessage("&cDu hast nicht genug Vote-Punkte! Mach /vote und vote dann!");
					}else{
						String t_name = args[0];
						if(RevivalData.isInReg(t_name)){
							User t = new User(RevivalData.getUUIDbyUsername(t_name));
							if(t.isOnline()){
								if(t.getPlayer().getName().equalsIgnoreCase(u.getPlayer().getName())){
									u.sendMessage("&cDu kannst nicht dich selber teleportieren!");
								}else{
									u.sendMessage("&aDu hast dem Spieler erfolgreich eine Teleport-Anfrage geschickt!");
									t.sendMessage("&aDer Spieler &2" + u.getPlayer().getName() + " &adich sich zu sich teleportieren!");
									t.sendMessage("&aMit &c/tpaccept &aakzeptierst du die Teleport-Anfrage!");
									t.sendMessage("&aMit &c/tpadeny &abrichst du die Teleport-Anfrage ab!");
									RevivalData.tpahere_request.put(t.getPlayer().getName(), u.getPlayer().getName());
									Revival.votes_points_cfg.set("Vote." + u.getUUID().toString() + ".Uses", Revival.votes_points_cfg.getInt("Vote." + u.getUUID().toString() + ".Uses") - 1);
								}
							}else{
								u.sendMessage("&cDer Spieler ist nicht online!");
							}
						}else{
							u.sendMessage("&cDer Spieler ist nicht im Register!");
						}
					}
				}
			}else if(cmd.getName().equalsIgnoreCase("tpaccept")){
				if(RevivalData.tpa_request.containsKey(u.getPlayer().getName())){
					if(RevivalData.isInReg(RevivalData.tpa_request.get(u.getPlayer().getName()))){
						User t = new User(RevivalData.getUUIDbyUsername(RevivalData.tpa_request.get(u.getPlayer().getName())));
						if(t.isOnline()){
							NotMove nm = new NotMove(t, u.getPlayer().getLocation(), u.getPlayer().getName());
							nm.go();
							RevivalData.tpa_request.remove(u.getPlayer().getName());							u.sendMessage("&aDu hast die Anfrage angenommen!");
						}else{
							u.sendMessage("&cDer GegenSpieler ist nicht online!");
							RevivalData.tpa_request.remove(u.getPlayer().getName());						}
					}else{
						u.sendMessage("&cDer Spieler ist nicht im Register!");
						RevivalData.tpa_request.remove(u.getPlayer().getName());
						}
					return true;
				}
				
				if(RevivalData.tpahere_request.containsKey(u.getPlayer().getName())){
					if(RevivalData.isInReg(RevivalData.tpahere_request.get(u.getPlayer().getName()))){
						User t = new User(RevivalData.getUUIDbyUsername(RevivalData.tpahere_request.get(u.getPlayer().getName())));
						if(t.isOnline()){
							NotMove nm = new NotMove(u, t.getPlayer().getLocation(), t.getPlayer().getName());
							nm.go();
							RevivalData.tpahere_request.remove(u.getPlayer().getName());
							u.sendMessage("&aDu hast die Anfrage angenommen!");
						}else{
							u.sendMessage("&cDer GegenSpieler ist nicht online!");
							RevivalData.tpahere_request.remove(u.getPlayer().getName());
						}
					}else{
						u.sendMessage("&cDer Spieler ist nicht im Register!");
						RevivalData.tpahere_request.remove(u.getPlayer().getName());
					}
					return true;
				}
					u.sendMessage("&cDu hast keine Teleportanfragen!");
				
			}else if(cmd.getName().equalsIgnoreCase("tpadeny")){
				RevivalData.tpa_request.remove(u.getPlayer().getName());
				RevivalData.tpahere_request.remove(u.getPlayer().getName());
				u.sendMessage("&cDu hast alle Teleportieranfragen gelöscht!");
			}
			
		}
		return true;
	}

}
