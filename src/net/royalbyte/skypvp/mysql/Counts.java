package net.royalbyte.skypvp.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Counts {

	public Counts() {
	}

	public boolean inList() {
		ResultSet rs = MySQL.getResult("SELECT * FROM Server_Counts WHERE Server='" + "SERVER" + "'");
		try {
			if (rs.next()) {
				return rs.getString("SERVER") != null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void create() {
		if (!inList())
			MySQL.update("INSERT INTO Server_Counts (Server, ban , mute , players) VALUES ('SERVER','0', '0', '0')");
	}

	public void setBan(int ban) {
		if (ban < 0) {
			ban = 0;
		}
		if (inList()) {
			MySQL.update("UPDATE Server_Counts SET ban='" + ban + "' WHERE Server='" + "SERVER" + "'");
		} else {
			create();
		}
	}

	public void setMute(int mute) {
		if (mute < 0) {
			mute = 0;
		}
		if (inList()) {
			MySQL.update("UPDATE Server_Counts SET mute='" + mute + "' WHERE Server='" + "SERVER" + "'");
		} else {
			create();
		}
	}

	public void setPlayers(int players) {
		if (players < 0) {
			players = 0;
		}
		if (inList()) {
			MySQL.update("UPDATE Server_Counts SET players='" + players + "' WHERE Server='" + "SERVER" + "'");
		} else {
			create();
		}
	}

	public Integer getPlayers() {
		if (inList()) {
			ResultSet rs = MySQL.getResult("SELECT * FROM Server_Counts WHERE Server='" + "SERVER" + "'");
			try {
				while (rs.next()) {
					return rs.getInt("players");
				}
			} catch (SQLException e) {
			}
		} else {
			create();
		}
		return 0;
	}

	public void addPlayers(int players) {
		int i = getPlayers();
		i += players;
		MySQL.update("UPDATE Server_Counts SET players='" + i + "' WHERE Server='" + "SERVER" + "'");
	}

	public void removePlayers(int players) {
		int i = getBan();
		i -= players;
		MySQL.update("UPDATE Server_Counts SET players='" + i + "' WHERE Server='" + "SERVER" + "'");
	}

	public Integer getMute() {
		if (inList()) {
			ResultSet rs = MySQL.getResult("SELECT * FROM Server_Counts WHERE Server='" + "SERVER" + "'");
			try {
				while (rs.next()) {
					return rs.getInt("mute");
				}
			} catch (SQLException e) {
			}
		} else {
			create();
		}
		return 0;
	}

	public void addMute(int mute) {
		int i = getBan();
		i += mute;
		MySQL.update("UPDATE Server_Counts SET mute='" + i + "' WHERE Server='" + "SERVER" + "'");
	}

	public void removeMute(int mute) {
		int i = getBan();
		i -= mute;
		MySQL.update("UPDATE Server_Counts SET mute='" + i + "' WHERE Server='" + "SERVER" + "'");
	}

	public Integer getBan() {
		if (inList()) {
			ResultSet rs = MySQL.getResult("SELECT * FROM Server_Counts WHERE Server='" + "SERVER" + "'");
			try {
				while (rs.next()) {
					return rs.getInt("ban");
				}
			} catch (SQLException e) {
			}
		} else {
			create();
		}
		return 0;
	}

	public void addBan(int bans) {
		int i = getBan();
		i += bans;
		MySQL.update("UPDATE Server_Counts SET ban='" + i + "' WHERE Server='" + "SERVER" + "'");
	}

	public void removeBan(int bans) {
		int i = getBan();
		i -= bans;
		MySQL.update("UPDATE Server_Counts SET ban='" + i + "' WHERE Server='" + "SERVER" + "'");
	}
}
