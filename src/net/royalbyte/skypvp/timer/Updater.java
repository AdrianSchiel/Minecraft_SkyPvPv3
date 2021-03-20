package net.royalbyte.skypvp.timer;

import net.royalbyte.skypvp.Config;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import net.royalbyte.skypvp.SkyPvP;

public class Updater {

	public Updater() {
	}
	
	public void updater() {
		new BukkitRunnable() {

			@Override
			public void run() {
				SkyPvP.getInstance().getRanking().set();
				Bukkit.getOnlinePlayers().forEach(player ->{
					if(Config.getBoolean("tablist.enabled").equals(true)){
						SkyPvP.getInstance().getTablist().send(player);
					}else{ }
					SkyPvP.getInstance().getRang().setprefix(player);
					SkyPvP.getInstance().getScoreboard().setBoard(player);
				});
			}
		}.runTaskTimerAsynchronously(SkyPvP.getInstance(), 0, 20 * 20);
	}
}
