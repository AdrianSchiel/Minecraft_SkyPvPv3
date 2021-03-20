/*
 * ---------------------------------------
 * Copyright @RoyalByte | Adrian Schiel
 * Youtube : RoyalByte Developer
 * Skype: RoyalByte
 * Website: RoyalByte.net
 * ---------------------------------------
 */

package net.royalbyte.skypvp.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.SkyPvP;

public class Mute {
	public static boolean isMuted(String uuid) {
		ResultSet rs = MySQL.getResult("SELECT * FROM Mute WHERE UUID='" + uuid + "'");
		try {
			if (rs.next()) {
				return rs.getString("UUID") != null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void mute(String uuid, String reason, String author_name, long time, int banpoints) {
		long banned_bis = (System.currentTimeMillis() + time);
		if (time == -1) {
			banned_bis = -1;
		}
		if (!isMuted(uuid)) {
			MySQL.update("INSERT INTO Mute (Name, UUID, reason, bis , author_name) VALUES ('"
					+ Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName() + "' , '" + uuid + "','" + reason + "','"
					+ banned_bis + "','" + author_name + "')");
			BanPoints.addBP(uuid, banpoints);

			if (Bukkit.getOfflinePlayer(UUID.fromString(uuid)).isOnline()) {
				new BukkitRunnable() {

					@Override
					public void run() {
						Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getPlayer()
								.sendMessage(getBanMSG(uuid, "Mute.msg.line-1"));
						Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getPlayer()
								.sendMessage(getBanMSG(uuid, "Mute.msg.line-2"));
						Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getPlayer()
								.sendMessage(getBanMSG(uuid, "Mute.msg.line-3"));
						Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getPlayer()
								.sendMessage(getBanMSG(uuid, "Mute.msg.line-4"));

					}
				}.runTaskLaterAsynchronously(SkyPvP.getInstance(), 20 * 2);

			}

		}
	}

	public static void unban(String uuid) {
		MySQL.update("DELETE FROM Mute WHERE UUID='" + uuid + "'");
	}

	public static String getEndDate(String uuid) {
		long millis = getEnd(uuid);
		SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy, HH:mm");
		Date date = new Date(millis);
		if (millis == -1) {
			return "Permanent";
		}
		return "" + sdf.format(date);
	}

	public static long getEnd(String uuid) {
		if (isMuted(uuid)) {
			ResultSet rs = MySQL.getResult("SELECT * FROM Mute WHERE UUID='" + uuid + "'");
			try {
				while (rs.next()) {
					return rs.getLong("bis");
				}
			} catch (SQLException e) {
			}
		}
		return 0;
	}

	public static String getReason(String uuid) {
		if (isMuted(uuid)) {
			ResultSet rs = MySQL.getResult("SELECT * FROM Mute WHERE UUID='" + uuid + "'");
			try {
				while (rs.next()) {
					return rs.getString("reason");
				}
			} catch (SQLException e) {
			}
		}
		return "";
	}

	public static String getAuthor(String uuid) {
		if (isMuted(uuid)) {
			ResultSet rs = MySQL.getResult("SELECT * FROM Mute WHERE UUID='" + uuid + "'");
			try {
				while (rs.next()) {
					return rs.getString("author_name");
				}
			} catch (SQLException e) {
			}
		}
		return "";
	}

	public static String getBanMSG(String uuid, String path) {
		String string = Config.getString(path);
		string = string.replaceAll("%REASON%", getReason(uuid));
		string = string.replaceAll("%BANNEDBIS%", getEndDate(uuid));
		string = string.replaceAll("%AUTHOR%", getAuthor(uuid));
		return string;
	}
}
