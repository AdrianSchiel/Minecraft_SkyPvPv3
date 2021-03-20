package net.royalbyte.skypvp.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;

public class Rewards {
	public static boolean inList(String uuid) {
		ResultSet rs = MySQL.getResult("SELECT * FROM Rewards WHERE UUID='" + uuid + "'");
		try {
			if (rs.next()) {
				return rs.getString("UUID") != null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void addPlayer(String uuid, String name) {
		if (!inList(uuid)) {
			MySQL.update("INSERT INTO Rewards (Name , UUID , stuendlich, taeglich, woechentlich) VALUES ('" + name + "', '" + uuid
					+ "' ,'"+ System.currentTimeMillis()+"', '"+ System.currentTimeMillis()+"', '"+ System.currentTimeMillis()+"')");
		}
	}

	public static long getstuendlich(String uuid) {
		if (inList(uuid)) {
			ResultSet rs = MySQL.getResult("SELECT * FROM Rewards WHERE UUID='" + uuid + "'");
			try {
				while (rs.next()) {
					return rs.getLong("stuendlich");
				}
			} catch (SQLException e) {
			}
		} else {
			addPlayer(uuid, Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
		}
		return 0;
	}

	public static void setstuendlich(String uuid, long cuurent) {
		if (inList(uuid)) {
			MySQL.update("UPDATE Rewards SET stuendlich='" + cuurent + "' WHERE UUID='" + uuid + "'");
		} else {
			addPlayer(uuid, Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
		}
	}
	public static long getteaglich(String uuid) {
		if (inList(uuid)) {
			ResultSet rs = MySQL.getResult("SELECT * FROM Rewards WHERE UUID='" + uuid + "'");
			try {
				while (rs.next()) {
					return rs.getLong("taeglich");
				}
			} catch (SQLException e) {
			}
		} else {
			addPlayer(uuid, Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
		}
		return 0;
	}

	public static void setteaglich(String uuid, long cuurent) {
		if (inList(uuid)) {
			MySQL.update("UPDATE Rewards SET taeglich='" + cuurent + "' WHERE UUID='" + uuid + "'");
		} else {
			addPlayer(uuid, Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
		}
	}
	public static long getwoechentlich(String uuid) {
		if (inList(uuid)) {
			ResultSet rs = MySQL.getResult("SELECT * FROM Rewards WHERE UUID='" + uuid + "'");
			try {
				while (rs.next()) {
					return rs.getLong("woechentlich");
				}
			} catch (SQLException e) {
			}
		} else {
			addPlayer(uuid, Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
		}
		return 0;
	}

	public static void setwoechentlich(String uuid, long cuurent) {
		if (inList(uuid)) {
			MySQL.update("UPDATE Rewards SET woechentlich='" + cuurent + "' WHERE UUID='" + uuid + "'");
		} else {
			addPlayer(uuid, Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
		}
	}

}
