package net.royalbyte.skypvp.utils;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.SkyPvP;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Rang {

	public Rang() {
	}

	public void setrang(Player target, Player p, String pexrang, String perm) {
		String group = Config.getString(pexrang);
		if (p.hasPermission(perm)) {
			if (SkyPvP.getInstance().playermanager.containsKey(p)) {
				if (PermissionsEx.getUser(target).inGroup(group)) {
					p.sendMessage(Config.getString("rang.isinGroupmsg"));

				} else {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
							"pex user " + target.getName() + " group set " + group);
					setprefix(p);
					p.closeInventory();
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
					if (SkyPvP.getInstance().playermanager.containsKey(p)) {
						SkyPvP.getInstance().playermanager.remove(p);
					}
				}
			} else {
				p.sendMessage(Config.getString("Player.manager.p_not_found"));
				p.closeInventory();
			}
		} else {
			p.sendMessage(Data.noperm);
			p.closeInventory();
			p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
			if (SkyPvP.getInstance().playermanager.containsKey(p)) {
				SkyPvP.getInstance().playermanager.remove(p);
			}
		}

	}


	public Scoreboard sb;
	public void createScoreboard() {
		sb = Bukkit.getScoreboardManager().getNewScoreboard();

		sb.registerNewTeam("00Rang");
		sb.registerNewTeam("01Rang");
		sb.registerNewTeam("02Rang");
		sb.registerNewTeam("03Rang");
		sb.registerNewTeam("04Rang");
		sb.registerNewTeam("05Rang");
		sb.registerNewTeam("06Rang");
		sb.registerNewTeam("07Rang");
		sb.registerNewTeam("08Rang");
		sb.registerNewTeam("09Rang");
		sb.registerNewTeam("10Rang");
		sb.registerNewTeam("11Rang");
		sb.registerNewTeam("12Rang");
		sb.registerNewTeam("13Rang");
		sb.registerNewTeam("14Rang");

		
		
		
		sb.getTeam("00Rang").setPrefix(Config.getString("rang.rang-1.tag"));
		sb.getTeam("01Rang").setPrefix(Config.getString("rang.rang-2.tag"));
		sb.getTeam("02Rang").setPrefix(Config.getString("rang.rang-3.tag"));
		sb.getTeam("03Rang").setPrefix(Config.getString("rang.rang-4.tag"));
		sb.getTeam("04Rang").setPrefix(Config.getString("rang.rang-5.tag"));
		sb.getTeam("05Rang").setPrefix(Config.getString("rang.rang-6.tag"));
		sb.getTeam("06Rang").setPrefix(Config.getString("rang.rang-7.tag"));
		sb.getTeam("07Rang").setPrefix(Config.getString("rang.rang-8.tag"));
		sb.getTeam("08Rang").setPrefix(Config.getString("rang.rang-9.tag"));
		sb.getTeam("09Rang").setPrefix(Config.getString("rang.rang-10.tag"));
		sb.getTeam("10Rang").setPrefix(Config.getString("rang.rang-11.tag"));
		sb.getTeam("11Rang").setPrefix(Config.getString("rang.rang-12.tag"));
		sb.getTeam("12Rang").setPrefix(Config.getString("rang.rang-13.tag"));
		sb.getTeam("13Rang").setPrefix(Config.getString("rang.rang-14.tag"));
		sb.getTeam("14Rang").setPrefix(Config.getString("rang.rang-15.tag"));
	}
	@SuppressWarnings({ "deprecation" })
	public void setprefix(Player p) {
		String team = "";

		if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-1.permissionsexrang"))) {
			team = "00Rang";
		}else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-2.permissionsexrang"))) {
			team = "01Rang";
		}else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-3.permissionsexrang"))) {
			team = "02Rang";
		}else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-4.permissionsexrang"))) {
			team = "03Rang";
		}else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-5.permissionsexrang"))) {
			team = "04Rang";
		}else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-6.permissionsexrang"))) {
			team = "05Rang";
		}else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-7.permissionsexrang"))) {
			team = "06Rang";
		}else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-8.permissionsexrang"))) {
			team = "07Rang";
		}else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-9.permissionsexrang"))) {
			team = "08Rang";
		}else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-10.permissionsexrang"))) {
			team = "09Rang";
		}else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-11.permissionsexrang"))) {
			team = "10Rang";
		}else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-12.permissionsexrang"))) {
			team = "11Rang";
		}else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-13.permissionsexrang"))) {
			team = "12Rang";
		}else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-14.permissionsexrang"))) {
			team = "13Rang";
		}else team = "14Rang";
		sb.getTeam(team).addPlayer(p);
		p.setDisplayName(sb.getTeam(team).getPrefix() + p.getName() + sb.getTeam(team).getSuffix());

		for (Player all : Bukkit.getOnlinePlayers()) {
			all.setScoreboard(sb);
		}
	}
	
	
	public String getChatColor(String uuid) {
		String rang = "";
		Player p = Bukkit.getPlayer(UUID.fromString(uuid));
		if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-1.permissionsexrang"))) {
			rang = Config.getString("rang.rang-1.chatcolor");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-2.permissionsexrang"))) {
			rang = Config.getString("rang.rang-2.chatcolor");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-3.permissionsexrang"))) {
			rang = Config.getString("rang.rang-3.chatcolor");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-4.permissionsexrang"))) {
			rang = Config.getString("rang.rang-4.chatcolor");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-5.permissionsexrang"))) {
			rang = Config.getString("rang.rang-5.chatcolor");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-6.permissionsexrang"))) {
			rang = Config.getString("rang.rang-6.chatcolor");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-7.permissionsexrang"))) {
			rang = Config.getString("rang.rang-7.chatcolor");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-8.permissionsexrang"))) {
			rang = Config.getString("rang.rang-8.chatcolor");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-9.permissionsexrang"))) {
			rang = Config.getString("rang.rang-9.chatcolor");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-10.permissionsexrang"))) {
			rang = Config.getString("rang.rang-10.chatcolor");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-11.permissionsexrang"))) {
			rang = Config.getString("rang.rang-11.chatcolor");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-12.permissionsexrang"))) {
			rang = Config.getString("rang.rang-12.chatcolor");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-13.permissionsexrang"))) {
			rang = Config.getString("rang.rang-13.chatcolor");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-14.permissionsexrang"))) {
			rang = Config.getString("rang.rang-14.chatcolor");
		} else {
			rang = Config.getString("rang.rang-15.chatcolor");
		}
		return rang;
	}
	
	public String getTag(String uuid) {
		String rang = "";
		Player p = Bukkit.getPlayer(UUID.fromString(uuid));
		if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-1.permissionsexrang"))) {
			rang = Config.getString("rang.rang-1.tag");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-2.permissionsexrang"))) {
			rang = Config.getString("rang.rang-2.tag");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-3.permissionsexrang"))) {
			rang = Config.getString("rang.rang-3.tag");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-4.permissionsexrang"))) {
			rang = Config.getString("rang.rang-4.tag");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-5.permissionsexrang"))) {
			rang = Config.getString("rang.rang-5.tag");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-6.permissionsexrang"))) {
			rang = Config.getString("rang.rang-6.tag");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-7.permissionsexrang"))) {
			rang = Config.getString("rang.rang-7.tag");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-8.permissionsexrang"))) {
			rang = Config.getString("rang.rang-8.tag");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-9.permissionsexrang"))) {
			rang = Config.getString("rang.rang-9.tag");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-10.permissionsexrang"))) {
			rang = Config.getString("rang.rang-10.tag");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-11.permissionsexrang"))) {
			rang = Config.getString("rang.rang-11.tag");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-12.permissionsexrang"))) {
			rang = Config.getString("rang.rang-12.tag");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-13.permissionsexrang"))) {
			rang = Config.getString("rang.rang-13.tag");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-14.permissionsexrang"))) {
			rang = Config.getString("rang.rang-14.tag");
		} else {
			rang = Config.getString("rang.rang-15.tag");
		}
		return rang;
	}


	
	public String getPrefix(String uuid) {
		String rang = "";
		Player p = Bukkit.getPlayer(UUID.fromString(uuid));
		if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-1.permissionsexrang"))) {
			rang = Config.getString("rang.rang-1.prefix");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-2.permissionsexrang"))) {
			rang = Config.getString("rang.rang-2.prefix");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-3.permissionsexrang"))) {
			rang = Config.getString("rang.rang-3.prefix");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-4.permissionsexrang"))) {
			rang = Config.getString("rang.rang-4.prefix");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-5.permissionsexrang"))) {
			rang = Config.getString("rang.rang-5.prefix");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-6.permissionsexrang"))) {
			rang = Config.getString("rang.rang-6.prefix");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-7.permissionsexrang"))) {
			rang = Config.getString("rang.rang-7.prefix");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-8.permissionsexrang"))) {
			rang = Config.getString("rang.rang-8.prefix");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-9.permissionsexrang"))) {
			rang = Config.getString("rang.rang-9.prefix");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-10.permissionsexrang"))) {
			rang = Config.getString("rang.rang-10.prefix");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-11.permissionsexrang"))) {
			rang = Config.getString("rang.rang-11.prefix");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-12.permissionsexrang"))) {
			rang = Config.getString("rang.rang-12.prefix");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-13.permissionsexrang"))) {
			rang = Config.getString("rang.rang-13.prefix");
		} else if (PermissionsEx.getUser(p).inGroup(Config.getString("rang.rang-14.permissionsexrang"))) {
			rang = Config.getString("rang.rang-14.prefix");
		} else {
			rang = Config.getString("rang.rang-15.prefix");
		}
		return rang;
	}
}
