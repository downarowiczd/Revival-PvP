package de.gamenetz.revival;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

public class NotMove implements Runnable {

    private User user;
    private int tpID = -1;
    private long started;
    private long delay;
    private long initX;
    private long initY;
    private long initZ;
    private double health;
    private Location loc;
    private String ortname;

    public NotMove(User user, Location location, String ortname) {
	this.ortname = ortname;
	this.loc = location;
	this.user = user;
    }

    public NotMove(User user) {
	this.user = user;
    }

    private void initTimer(long delay) {
	this.started = System.currentTimeMillis();
	this.delay = delay;
	this.health = user.getPlayer().getHealthScale();
	this.initX = Math.round(user.getPlayer().getLocation().getX() * 0.3);
	this.initY = Math.round(user.getPlayer().getLocation().getY() * 0.3);
	this.initZ = Math.round(user.getPlayer().getLocation().getZ() * 0.3);
    }

    @Override
    public void run() {

	if (user == null || !user.isOnline() || user.getPlayer().getLocation() == null) {
	    cancel(false);
	    return;
	}
	if (Math.round(user.getPlayer().getLocation().getX() * 0.3) != initX
		|| Math.round(user.getPlayer().getLocation().getY() * 0.3) != initY
		|| Math.round(user.getPlayer().getLocation().getZ() * 0.3) != initZ || user.getPlayer().getHealthScale() < health) {
	    cancel(true);
	    return;
	}

	health = user.getPlayer().getHealthScale();

	long now = System.currentTimeMillis();
	if (now > started + delay) {
		user.getPlayer().teleport(this.loc);
	    cancel(false);
	}

    }

    public void go() {
	cancel(false);
	    user.sendMessage(ChatColor.GRAY + "Bewege dich nicht, denn du wirst in fünf Sekunden zu " + this.ortname + " &7teleportiert!");
	initTimer(5000L);

	tpID = Bukkit.getServer().getScheduler()
		.scheduleSyncRepeatingTask(Bukkit.getServer().getPluginManager().getPlugin("Revival-PvP"), this, 10, 10);
    }

    public void cancel(boolean notifyUser) {
	if (tpID == -1) {
	    return;
	}
	try {
	    Bukkit.getServer().getScheduler().cancelTask(tpID);
	    if (notifyUser) {
		    user.getPlayer().sendMessage(ChatColor.RED + "Du hast dich bewegt, Teleportvorgang abgebrochen!");
	    }
	} finally {
	    tpID = -1;
	}
    }

}
