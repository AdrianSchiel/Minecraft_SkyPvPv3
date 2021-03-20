package net.royalbyte.skypvp.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.SkyPvP;
import net.royalbyte.skypvp.manager.Manager_Messages;
import net.royalbyte.skypvp.mysql.Stats;

public class Tablist {

	
	public Tablist() {
	}

	public static void send(Player p) {
		Manager_Messages.sendTablistHeaderAndFooter(p, getHeader(p), getFooter(p));
	}
	
	public static String getFooter(Player p) {
		String s = Config.getString("tablist.footer");
		s = s.replaceAll("%ONLINE%", "" + Bukkit.getOnlinePlayers().size());
		s = s.replaceAll("%RANG%", SkyPvP.getInstance().getRang().getPrefix(p.getUniqueId().toString()));
		s = s.replaceAll("%POINTS%", ""+ Stats.getPoints(p.getUniqueId().toString()));
		s = s.replaceAll("%COOKIES%", "" + Stats.getCookies(p.getUniqueId().toString()));
		s = s.replaceAll("%COINS%", "" + Stats.getCoins(p.getUniqueId().toString()));
		s = s.replaceAll("%KILLS%", ""+Stats.getKills(p.getUniqueId().toString()));
		s = s.replaceAll("%DEATHS%", ""+ Stats.getTode(p.getUniqueId().toString()));
		return s;
	}
	public static String getHeader(Player p) {
		String s = Config.getString("tablist.header");
		s = s.replaceAll("%ONLINE%", "" + Bukkit.getOnlinePlayers().size());
		s = s.replaceAll("%RANG%", SkyPvP.getInstance().getRang().getPrefix(p.getUniqueId().toString()));
		s = s.replaceAll("%POINTS%", ""+ Stats.getPoints(p.getUniqueId().toString()));
		s = s.replaceAll("%COOKIES%", "" + Stats.getCookies(p.getUniqueId().toString()));
		s = s.replaceAll("%COINS%", "" + Stats.getCoins(p.getUniqueId().toString()));
		s = s.replaceAll("%KILLS%", ""+Stats.getKills(p.getUniqueId().toString()));
		s = s.replaceAll("%DEATHS%", ""+ Stats.getTode(p.getUniqueId().toString()));
		return s;
	}


}
