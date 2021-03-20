package net.royalbyte.skypvp.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.minecraft.server.v1_8_R3.IScoreboardCriteria;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardDisplayObjective;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardObjective;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardScore;
import net.minecraft.server.v1_8_R3.Scoreboard;
import net.minecraft.server.v1_8_R3.ScoreboardObjective;
import net.minecraft.server.v1_8_R3.ScoreboardScore;
import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.SkyPvP;
import net.royalbyte.skypvp.mysql.Stats;

public class SBoard {

	public SBoard() {

	}

	public static List<String> scores = new ArrayList<>();
	public static List<String> defaultscores = new ArrayList<>();

	public static void setBoard(Player p) {

		Scoreboard sb = new Scoreboard();
		ScoreboardObjective obj = sb.registerObjective(Config.getString("Scoreboard.name"), IScoreboardCriteria.b);

		PacketPlayOutScoreboardObjective createpacket = new PacketPlayOutScoreboardObjective(obj, 0);

		PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1, obj);

		obj.setDisplayName("§RoyalByte");

		PacketPlayOutScoreboardObjective removePacket = new PacketPlayOutScoreboardObjective(obj, 1);

		SkyPvP.getInstance().sendPacket(p, removePacket);
		SkyPvP.getInstance().sendPacket(p, createpacket);
		SkyPvP.getInstance().sendPacket(p, display);

		String s;
		for (int i = 0; i < scores.size(); i++) {
			s = scores.get(i);
			s = s.replaceAll("&", "§");
			s = s.replaceAll("%ONLINE%", "" + Bukkit.getOnlinePlayers().size());
			s = s.replaceAll("%RANG%", SkyPvP.getInstance().getRang().getPrefix(p.getUniqueId().toString()));
			s = s.replaceAll("%POINTS%", "" + Stats.getPoints(p.getUniqueId().toString()));
			s = s.replaceAll("%COINS%", "" + Stats.getCoins(p.getUniqueId().toString()));
			s = s.replaceAll("%KILLS%", "" + Stats.getKills(p.getUniqueId().toString()));
			s = s.replaceAll("%DEATHS%", "" + Stats.getTode(p.getUniqueId().toString()));

			ScoreboardScore a = new ScoreboardScore(sb, obj, s);
			a.setScore(i);

			PacketPlayOutScoreboardScore pa = new PacketPlayOutScoreboardScore(a);
			SkyPvP.getInstance().sendPacket(p, pa);

		}

	}

	public void setDefaultScores() {
		defaultscores.add("&9Stats &7:");
		defaultscores.add("  &7Kills &7:&b %KILLS%");
		defaultscores.add("  &7Tode &7:&b %DEATHS%");
		defaultscores.add("  &7Coins &7:&b %COINS%");
		defaultscores.add("  &7Punkte &7:&b %POINTS%");
		defaultscores.add(" ");
		defaultscores.add("  &7Online &7:&b %ONLINE%");
		defaultscores.add("&9Rang &7:");
		defaultscores.add("  %RANG%");
		if (!Config.cfg.contains("Scoreboard.lines")) {
			Config.cfg.set("Scoreboard.lines", defaultscores);
			Config.save();
		}
	}

	@SuppressWarnings("unchecked")
	public void registerList() {
		try {
			scores.addAll((Collection<? extends String>) Config.cfg.getList("Scoreboard.lines"));
			Collections.reverse(scores);
		} catch (Exception e) {
		}
	}

	public List<String> getScores() {
		return scores;
	}

	public void setScores(List<String> scores) {
		this.scores = scores;
	}

	public void addScore(String score) {
		scores.add(score);
	}

	public void time() {
		new BukkitRunnable() {

			@Override
			public void run() {
				Bukkit.getOnlinePlayers().forEach(all -> {
					setBoard(all);
				});

			}
		}.runTaskTimer(SkyPvP.getInstance(), 1, 10 * 20);
	}

}
