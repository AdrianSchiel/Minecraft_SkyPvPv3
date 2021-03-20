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
import java.util.UUID;

import org.bukkit.Bukkit;

import net.royalbyte.skypvp.SkyPvP;

public class Stats {
	public static boolean inList(String uuid) {
		ResultSet rs = MySQL.getResult("SELECT * FROM Stats WHERE UUID='" + uuid + "'");
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
			MySQL.update(
					"INSERT INTO Stats (Name , UUID , kills, tode, points, coins, cookie, cookiescore, PlayerID) VALUES ('"
							+ name + "', '" + uuid + "' ,'0', '0','0','0', '0', '1', '"
							+ (SkyPvP.getInstance().getCounts().getPlayers() + 1) + "')");
		}
	}

	public static Integer getCookieScore(String uuid) {
		if (inList(uuid)) {
			ResultSet rs = MySQL.getResult("SELECT * FROM Stats WHERE UUID='" + uuid + "'");
			try {
				while (rs.next()) {
					return rs.getInt("cookiescore");
				}
			} catch (SQLException e) {
			}
		} else {
			addPlayer(uuid, Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
		}
		return 0;
	}

	public static void setCookieScore(String uuid, int kills) {
		if (kills < 0) {
			kills = 0;
		}
		if (inList(uuid)) {
			MySQL.update("UPDATE Stats SET cookiescore='" + kills + "' WHERE UUID='" + uuid + "'");
		} else {
			addPlayer(uuid, Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
		}
	}

	public static Integer getPlayerID(String uuid) {
		if (inList(uuid)) {
			ResultSet rs = MySQL.getResult("SELECT * FROM Stats WHERE UUID='" + uuid + "'");
			try {
				while (rs.next()) {
					return rs.getInt("PlayerID");
				}
			} catch (SQLException e) {
			}
		} else {
			addPlayer(uuid, Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
		}
		return 0;
	}

	public static void setPlayerID(String uuid, int PlayerID) {
		if (PlayerID < 0) {
			PlayerID = 0;
		}
		if (inList(uuid)) {
			MySQL.update("UPDATE Stats SET PlayerID='" + PlayerID + "' WHERE UUID='" + uuid + "'");
		} else {
			addPlayer(uuid, Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
		}
	}

	public static Integer getCookies(String uuid) {
		if (inList(uuid)) {
			ResultSet rs = MySQL.getResult("SELECT * FROM Stats WHERE UUID='" + uuid + "'");
			try {
				while (rs.next()) {
					return rs.getInt("cookie");
				}
			} catch (SQLException e) {
			}
		} else {
			addPlayer(uuid, Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
		}
		return 0;
	}

	public static void setCookies(String uuid, int kills) {
		if (kills < 0) {
			kills = 0;
		}
		if (inList(uuid)) {
			MySQL.update("UPDATE Stats SET cookie='" + kills + "' WHERE UUID='" + uuid + "'");
		} else {
			addPlayer(uuid, Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
		}
	}

	public static Integer getKills(String uuid) {
		if (inList(uuid)) {
			ResultSet rs = MySQL.getResult("SELECT * FROM Stats WHERE UUID='" + uuid + "'");
			try {
				while (rs.next()) {
					return rs.getInt("kills");
				}
			} catch (SQLException e) {
			}
		} else {
			addPlayer(uuid, Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
		}
		return 0;
	}

	public static void setKills(String uuid, int kills) {
		if (kills < 0) {
			kills = 0;
		}
		if (inList(uuid)) {
			MySQL.update("UPDATE Stats SET kills='" + kills + "' WHERE UUID='" + uuid + "'");
		} else {
			addPlayer(uuid, Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
		}
	}

	public static Integer getTode(String uuid) {
		if (inList(uuid)) {
			ResultSet rs = MySQL.getResult("SELECT * FROM Stats WHERE UUID='" + uuid + "'");
			try {
				while (rs.next()) {
					return rs.getInt("tode");
				}
			} catch (SQLException e) {
			}
		} else {
			addPlayer(uuid, Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
		}
		return 0;
	}

	public static void setTode(String uuid, int tode) {
		if (tode < 0) {
			tode = 0;
		}
		if (inList(uuid)) {
			MySQL.update("UPDATE Stats SET tode='" + tode + "' WHERE UUID='" + uuid + "'");
		} else {
			addPlayer(uuid, Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
		}
	}

	public static Integer getCoins(String uuid) {
		if (inList(uuid)) {
			ResultSet rs = MySQL.getResult("SELECT * FROM Stats WHERE UUID='" + uuid + "'");
			try {
				while (rs.next()) {
					return rs.getInt("coins");
				}
			} catch (SQLException e) {
			}
		} else {
			addPlayer(uuid, Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
		}
		return 0;
	}

	public static void setCoins(String uuid, int coins) {
		if (coins < 0) {
			coins = 0;
		}
		if (inList(uuid)) {
			MySQL.update("UPDATE Stats SET coins='" + coins + "' WHERE UUID='" + uuid + "'");
		} else {
			addPlayer(uuid, Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
		}
	}

	public static Integer getPoints(String uuid) {
		if (inList(uuid)) {
			ResultSet rs = MySQL.getResult("SELECT * FROM Stats WHERE UUID='" + uuid + "'");
			try {
				while (rs.next()) {
					return rs.getInt("points");
				}
			} catch (SQLException e) {
			}
		} else {
			addPlayer(uuid, Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
		}
		return 0;
	}

	public static void setPoints(String uuid, int points) {
		if (points < 0) {
			points = 0;
		}
		if (inList(uuid)) {
			MySQL.update("UPDATE Stats SET points='" + points + "' WHERE UUID='" + uuid + "'");
		} else {
			addPlayer(uuid, Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
		}
	}

	public static void addPoints(String uuid, int points) {
		int i = getPoints(uuid);
		i += points;
		MySQL.update("UPDATE Stats SET points='" + i + "' WHERE `UUID`='" + uuid + "'");
	}

	public static void addCoins(String uuid, int coins) {
		int i = getCoins(uuid);
		i += coins;
		MySQL.update("UPDATE Stats SET coins='" + i + "' WHERE `UUID`='" + uuid + "'");
	}

	public static void addKills(String uuid, int kills) {
		int i = getKills(uuid);
		i += kills;
		MySQL.update("UPDATE Stats SET kills='" + i + "' WHERE `UUID`='" + uuid + "'");
	}

	public static void addTode(String uuid, int tode) {
		int i = getTode(uuid);
		i += tode;
		MySQL.update("UPDATE Stats SET tode='" + i + "' WHERE `UUID`='" + uuid + "'");
	}

	public static void addPlayerID(String uuid, int PlayerID) {
		int i = getPlayerID(uuid);
		i += PlayerID;
		MySQL.update("UPDATE Stats SET PlayerID='" + i + "' WHERE `UUID`='" + uuid + "'");
	}

	public static void removePlayerID(String uuid, int PlayerID) {
		int i = getPlayerID(uuid);
		i -= PlayerID;
		MySQL.update("UPDATE Stats SET PlayerID='" + i + "' WHERE `UUID`='" + uuid + "'");
	}

	public static void removePoints(String uuid, int points) {
		int i = getPoints(uuid);
		i -= points;
		MySQL.update("UPDATE Stats SET points='" + i + "' WHERE `UUID`='" + uuid + "'");
	}

	public static void removeCoins(String uuid, int coins) {
		int i = getCoins(uuid);
		i -= coins;
		MySQL.update("UPDATE Stats SET coins='" + i + "' WHERE `UUID`='" + uuid + "'");
	}

	public static void removeKills(String uuid, int kills) {
		int i = getKills(uuid);
		i -= kills;
		MySQL.update("UPDATE Stats SET kills='" + i + "' WHERE `UUID`='" + uuid + "'");
	}

	public static void removeTode(String uuid, int tode) {
		int i = getTode(uuid);
		i -= tode;
		MySQL.update("UPDATE Stats SET tode='" + i + "' WHERE `UUID`='" + uuid + "'");
	}

	public static void addCookies(String uuid, int tode) {
		int i = getCookies(uuid);
		i += tode;
		MySQL.update("UPDATE Stats SET cookie='" + i + "' WHERE `UUID`='" + uuid + "'");
	}

	public static void removeCookies(String uuid, int points) {
		int i = getCookies(uuid);
		i -= points;
		MySQL.update("UPDATE Stats SET cookie='" + i + "' WHERE `UUID`='" + uuid + "'");
	}

	public static void addCookieScore(String uuid, int tode) {
		int i = getCookieScore(uuid);
		i += tode;
		MySQL.update("UPDATE Stats SET cookiescore='" + i + "' WHERE `UUID`='" + uuid + "'");
	}

	public static void removeCookieScore(String uuid, int points) {
		int i = getCookieScore(uuid);
		i -= points;
		MySQL.update("UPDATE Stats SET cookiescore='" + i + "' WHERE `UUID`='" + uuid + "'");
	}

	public static double getKD(String uuid) {

		double deaths = getTode(uuid);
		double Kills = getKills(uuid);
		double KD = (Kills / deaths);
		KD = (Math.round(KD * 100.0) / 100.0);

		return KD;

	}

}
