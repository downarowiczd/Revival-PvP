package de.gamenetz.revival.listener;

import java.util.Collection;

import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.gamenetz.revival.CombatChecker;
import de.gamenetz.revival.RevivalData;
import de.gamenetz.revival.User;

public class DamageListener implements Listener{
	
	private String KNOW_IN_COMBAT = RevivalData.MAIN_PREFIX + ChatColor.translateAlternateColorCodes('&', "&aDu bist nun im Kampf!");
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onDamageListener(EntityDamageByEntityEvent event){
		if(event.getEntity() instanceof Player){
			Player p = (Player)event.getEntity();
			User u = new User(p.getUniqueId());
			if(event.getDamager() instanceof Player){
				Player dmg = (Player)event.getDamager();
				if(u.isFrieden(dmg.getUniqueId())){
					event.setCancelled(true);
				}else{
				if(!CombatChecker.isInCombat(p.getName())){
					u.sendMessage(KNOW_IN_COMBAT);
				}
				
				if(!CombatChecker.isInCombat(dmg.getName())){
					dmg.sendMessage(KNOW_IN_COMBAT);
				}
				CombatChecker.addToCombat(p.getName());
				CombatChecker.addToCombat(dmg.getName());
				}
			}else if(event.getDamager() instanceof Arrow){
				if(((Projectile)event.getDamager()).getShooter() instanceof Player){
					Player dmg = (Player)((Projectile)event.getDamager()).getShooter();
					if(!p.equals(dmg)){
					if(u.isFrieden(dmg.getUniqueId())){
						event.setCancelled(true);
					}else{
					if(!CombatChecker.isInCombat(p.getName())){
						u.sendMessage(KNOW_IN_COMBAT);
					}
					
					if(!CombatChecker.isInCombat(dmg.getName())){
						dmg.sendMessage(KNOW_IN_COMBAT);
					}
					CombatChecker.addToCombat(p.getName());
					CombatChecker.addToCombat(dmg.getName());
					}
				}
			}
		}else if(event.getDamager() instanceof Egg){
			if(((Projectile)event.getDamager()).getShooter() instanceof Player){
				Player dmg = (Player)((Projectile)event.getDamager()).getShooter();
				if(!p.equals(dmg)){
				if(u.isFrieden(dmg.getUniqueId())){
					event.setCancelled(true);
				}else{
				if(!CombatChecker.isInCombat(p.getName())){
					u.sendMessage(KNOW_IN_COMBAT);
				}
				
				if(!CombatChecker.isInCombat(dmg.getName())){
					dmg.sendMessage(KNOW_IN_COMBAT);
				}
				CombatChecker.addToCombat(p.getName());
				CombatChecker.addToCombat(dmg.getName());
				}
			}
		}
	}else if(event.getDamager() instanceof Snowball){
		if(((Projectile)event.getDamager()).getShooter() instanceof Player){
			Player dmg = (Player)((Projectile)event.getDamager()).getShooter();
			if(!p.equals(dmg)){
			if(u.isFrieden(dmg.getUniqueId())){
				event.setCancelled(true);
			}else{
			if(!CombatChecker.isInCombat(p.getName())){
				u.sendMessage(KNOW_IN_COMBAT);
			}
			if(!CombatChecker.isInCombat(dmg.getName())){
				dmg.sendMessage(KNOW_IN_COMBAT);
			}
			CombatChecker.addToCombat(p.getName());
			CombatChecker.addToCombat(dmg.getName());
			}
		}
	}
}
	}
  }
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPotionSplash(PotionSplashEvent e){
		ThrownPotion pot = e.getPotion();
		Collection<LivingEntity> le = e.getAffectedEntities();
		for(LivingEntity le1:le){
			if(le1 instanceof Player){
				Player t = (Player) le1;
				if(pot.getShooter() instanceof Player){
				Player p = (Player) pot.getShooter();
				User o = new User(t.getUniqueId());
				User s = new User(p.getUniqueId());
						if(pot.getEffects().contains(new PotionEffect(PotionEffectType.HARM, 1, 0))){
							if(o.isFrieden(s.getUUID())){
								e.setIntensity(le1, 0);
								e.setCancelled(true);
							}else{
								if(!CombatChecker.isInCombat(p.getName())){
									s.sendMessage(KNOW_IN_COMBAT);
								}
								if(!CombatChecker.isInCombat(t.getName())){
									o.sendMessage(KNOW_IN_COMBAT);
								}
								CombatChecker.addToCombat(p.getName());
								CombatChecker.addToCombat(t.getName());
							}
						}
						if(pot.getEffects().contains(new PotionEffect(PotionEffectType.HARM, 1, 1))){
							if(o.isFrieden(s.getUUID())){
								e.setIntensity(le1, 0);
								e.setCancelled(true);
							}else{
								if(!CombatChecker.isInCombat(p.getName())){
									s.sendMessage(KNOW_IN_COMBAT);
								}
								if(!CombatChecker.isInCombat(t.getName())){
									o.sendMessage(KNOW_IN_COMBAT);
								}
								CombatChecker.addToCombat(p.getName());
								CombatChecker.addToCombat(t.getName());
								}						}
						if(pot.getEffects().contains(new PotionEffect(PotionEffectType.POISON, 676, 0))){
							if(o.isFrieden(s.getUUID())){
								e.setIntensity(le1, 0);
								e.setCancelled(true);
							}else{
								if(!CombatChecker.isInCombat(p.getName())){
									s.sendMessage(KNOW_IN_COMBAT);
								}
								if(!CombatChecker.isInCombat(t.getName())){
									o.sendMessage(KNOW_IN_COMBAT);
								}
								CombatChecker.addToCombat(p.getName());
								CombatChecker.addToCombat(t.getName());
								}						}
						if(pot.getEffects().contains(new PotionEffect(PotionEffectType.POISON, 338, 1))){
							if(o.isFrieden(s.getUUID())){
								e.setIntensity(le1, 0);
								e.setCancelled(true);
							}else{
								if(!CombatChecker.isInCombat(p.getName())){
									s.sendMessage(KNOW_IN_COMBAT);
								}
								if(!CombatChecker.isInCombat(t.getName())){
									o.sendMessage(KNOW_IN_COMBAT);
								}
								CombatChecker.addToCombat(p.getName());
								CombatChecker.addToCombat(t.getName());
								}						}
						if(pot.getEffects().contains(new PotionEffect(PotionEffectType.POISON, 1801, 0))){
							if(o.isFrieden(s.getUUID())){
								e.setIntensity(le1, 0);
								e.setCancelled(true);
							}else{
								if(!CombatChecker.isInCombat(p.getName())){
									s.sendMessage(KNOW_IN_COMBAT);
								}
								if(!CombatChecker.isInCombat(t.getName())){
									o.sendMessage(KNOW_IN_COMBAT);
								}
								CombatChecker.addToCombat(p.getName());
								CombatChecker.addToCombat(t.getName());
								}						}
						if(pot.getEffects().contains(new PotionEffect(PotionEffectType.SLOW, 3601, 0))){
							if(o.isFrieden(s.getUUID())){
								e.setIntensity(le1, 0);
								e.setCancelled(true);
							}else{
								if(!CombatChecker.isInCombat(p.getName())){
									s.sendMessage(KNOW_IN_COMBAT);
								}
								if(!CombatChecker.isInCombat(t.getName())){
									o.sendMessage(KNOW_IN_COMBAT);
								}
								CombatChecker.addToCombat(p.getName());
								CombatChecker.addToCombat(t.getName());
								}						}
						if(pot.getEffects().contains(new PotionEffect(PotionEffectType.SLOW, 1351, 0))){
							if(o.isFrieden(s.getUUID())){
								e.setIntensity(le1, 0);
								e.setCancelled(true);
							}else{
								if(!CombatChecker.isInCombat(p.getName())){
									s.sendMessage(KNOW_IN_COMBAT);
								}
								if(!CombatChecker.isInCombat(t.getName())){
									o.sendMessage(KNOW_IN_COMBAT);
								}
								CombatChecker.addToCombat(p.getName());
								CombatChecker.addToCombat(t.getName());
								}						}
						if(pot.getEffects().contains(new PotionEffect(PotionEffectType.WEAKNESS, 3601, 0))){
							if(o.isFrieden(s.getUUID())){
								e.setIntensity(le1, 0);
								e.setCancelled(true);
							}else{
								if(!CombatChecker.isInCombat(p.getName())){
									s.sendMessage(KNOW_IN_COMBAT);
								}
								if(!CombatChecker.isInCombat(t.getName())){
									o.sendMessage(KNOW_IN_COMBAT);
								}
								CombatChecker.addToCombat(p.getName());
								CombatChecker.addToCombat(t.getName());
								}						}
						if(pot.getEffects().contains(new PotionEffect(PotionEffectType.WEAKNESS, 1351, 0))){
							if(o.isFrieden(s.getUUID())){
								e.setIntensity(le1, 0);
								e.setCancelled(true);
							}else{
								if(!CombatChecker.isInCombat(p.getName())){
									s.sendMessage(KNOW_IN_COMBAT);
								}
								if(!CombatChecker.isInCombat(t.getName())){
									o.sendMessage(KNOW_IN_COMBAT);
								}
								CombatChecker.addToCombat(p.getName());
								CombatChecker.addToCombat(t.getName());
								}
					}
				}
			}
		}
	}
	
}
