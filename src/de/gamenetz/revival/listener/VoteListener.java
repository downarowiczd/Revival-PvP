package de.gamenetz.revival.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;

import de.gamenetz.revival.RevivalData;
import de.gamenetz.revival.Revival;
import de.gamenetz.revival.User;


public class VoteListener implements Listener{
	
	@EventHandler
	public void onVotifier(VotifierEvent event) {
		Vote v = event.getVote();
		String user = v.getUsername();
		if(RevivalData.isInReg(user)){
			User u = new User(RevivalData.getUUIDbyUsername(user));
			Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7[&aVote&7] " + ChatColor.GRAY + "Der Spieler " + ChatColor.YELLOW + user + ChatColor.GRAY + " hat gevotet! &e(/voteinfo)"));
			Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7[&aVote&7] " + ChatColor.GRAY + "Der Spieler " + ChatColor.YELLOW + user + ChatColor.GRAY + " hat jetzt &e+2 VotePunkte"));
			Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7[&aVote&7] &7Auch Gratis Items? " + ChatColor.YELLOW + "/vote"));
			if(u.isOnline()){
				u.sendMessage("&7[&aBelohnung&7]" + ChatColor.GRAY + "" + ChatColor.BOLD + " > " + ChatColor.YELLOW + "32x Steaks");
				u.sendMessage("&7[&aBelohnung&7]" + ChatColor.GRAY + "" + ChatColor.BOLD + " > " + ChatColor.YELLOW + "1x Goldener-Apfel");
				u.sendMessage("&7[&aBelohnung&7]" + ChatColor.GRAY + "" + ChatColor.BOLD + " > " + ChatColor.YELLOW + "10x Eisen");
				u.sendMessage("&7[&aBelohnung&7]" + ChatColor.GRAY + "" + ChatColor.BOLD + " > " + ChatColor.YELLOW + "Du kannst +2 VotePunkte");
				Bukkit.getPlayer(u.getUUID()).getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 32));
				Bukkit.getPlayer(u.getUUID()).getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 1));
				Bukkit.getPlayer(u.getUUID()).getInventory().addItem(new ItemStack(Material.IRON_INGOT, 10));
			}
			
			if(Revival.votes_points_cfg.contains("Vote." + u.getUUID().toString())){
				int uses = Revival.votes_points_cfg.getInt("Vote." + u.getUUID().toString() + ".Uses");
				if(u.getPlayer().hasPermission("revival.vote.more")){
					uses = uses + 3;
				}else{
					uses = uses + 2;
				}
				Revival.votes_points_cfg.set("Vote." + u.getUUID().toString() + ".Uses", uses);
			}else{
				if(u.getPlayer().hasPermission("revival.vote.more")){
					Revival.votes_points_cfg.set("Vote." + u.getUUID().toString() + ".Uses", 3);
				}else{
					Revival.votes_points_cfg.set("Vote." + u.getUUID().toString() + ".Uses", 2);
				}
			}
			try{
				Revival.votes_points_cfg.save(Revival.votes_points);
			}catch(Exception e){
				System.err.println(e);
			}
			
		}
	}
}
