package net.royalbyte.skypvp.utils;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.SkyPvP;
import net.royalbyte.skypvp.manager.Manager_ItemBuilder;
import net.royalbyte.skypvp.mysql.Ban;
import net.royalbyte.skypvp.mysql.BanPoints;
import net.royalbyte.skypvp.mysql.Datas;
import net.royalbyte.skypvp.mysql.Mute;
import net.royalbyte.skypvp.mysql.Stats;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Items {

	SkyPvP instance;

	public Items(SkyPvP instance) {
		this.instance = instance;
	}

	public ItemStack getPluginInfo() {
		return Manager_ItemBuilder.Skull_lore("§8§l§ §b§lPlugin-Info", "RoyalByte", 1,
				new String[] { "",
						"§8§l§ §b§lPlugin-Version§7: §9§l" + SkyPvP.getInstance().getDescription().getVersion(),
						"§8§l§ §b§lPlugin-Author§7: §9§l" + SkyPvP.getInstance().getDescription().getAuthors(),
						"§8§l§ §b§lPlugin-Name§7: §9§l" + SkyPvP.getInstance().getDescription().getName(),
						"§8§l§ §b§lPlugin-Website§7: §9§l" + SkyPvP.getInstance().getDescription().getWebsite(), "" });
	}

	public ItemStack getOnlinePlayerItem(Player p) {
		if (Bukkit.getOnlinePlayers().contains(p)) {
			return Manager_ItemBuilder.lore("§8§l§ §a§lOnline", Material.INK_SACK, 1, 10,
					new String[] { "", "§8§l§ §9" + p.getName() + "§7 ist gerade §a§lOnline", "" });
		} else {
			return Manager_ItemBuilder.lore("§8§l§ §c§lOnline", Material.INK_SACK, 1, 1,
					new String[] { "", "§8§l§ §9" + p.getName() + "§7 ist gerade §a§lOnline", "" });
		}
	}

	public ItemStack getOnlineModeItem() {
		if (Bukkit.getServer().getOnlineMode() == true) {
			return Manager_ItemBuilder.lore("§8§l§ §b§lOnline-Mode", Material.INK_SACK, 1, 10,
					new String[] { "", "§8§l§ §7Momentan ist der Server auf §a§lOnline §7eingestellt", "" });
		} else {
			return Manager_ItemBuilder.lore("§8§l§ §b§lWartung", Material.INK_SACK, 1, 1,
					new String[] { "", "§8§l§ §7Momentan ist der Server auf §c§lOffline §7eingestellt", "" });
		}
	}

	public ItemStack getPluginStatusItem() {
		if (SkyPvP.getInstance().getStatus()) {
			return Manager_ItemBuilder.lore("§8§l§ §b§lPlugin-Status", Material.INK_SACK, 1, 10,
					new String[] { "", "§8§l§ §7Momentan ist das Plugin §a§lAktiv", "" });
		} else {
			return Manager_ItemBuilder.lore("§8§l§ §b§lWartung", Material.INK_SACK, 1, 1,
					new String[] { "", "§8§l§ §7Momentan ist das Plugin §c§lInAktiv", "" });
		}
	}

	public ItemStack getWartungItem() {
		if (SkyPvP.getInstance().getWartung().isWartung()) {
			return Manager_ItemBuilder.lore("§8§l§ §b§lWartung", Material.INK_SACK, 1, 10,
					new String[] { "", "§8§l§ §7Momentan sind Wartungen §a§lan", "" });
		} else {

			return Manager_ItemBuilder.lore("§8§l§ §b§lWartung", Material.INK_SACK, 1, 1,
					new String[] { "", "§8§l§ §7Momentan sind Wartungen §c§laus", "" });
		}
	}

	public ItemStack coins(String uuid) {
		ItemStack s = Manager_ItemBuilder.lore("§a§lCoins", Material.GLOWSTONE_DUST, 1, 0,
				new String[] { " ", "§7§l§ §a" + Stats.getCoins(uuid), "" });
		return s;
	}

	public ItemStack kd(String uuid) {
		ItemStack s = Manager_ItemBuilder.lore("§d§lKD", Material.BONE, 1, 0,
				new String[] { " ", "§7§l§ §d" + Stats.getKD(uuid), "" });
		return s;
	}

	public ItemStack kills(String uuid) {
		ItemStack s = Manager_ItemBuilder.lore("§b§lKills", Material.DIAMOND_SWORD, 1, 0,
				new String[] { " ", "§7§l§ §b" + Stats.getKills(uuid), "" });
		return s;
	}

	public ItemStack tode(String uuid) {
		ItemStack s = Manager_ItemBuilder.lore("§c§lDeaths", Material.SKULL_ITEM, 1, 0,
				new String[] { " ", "§7§l§ §c" + Stats.getTode(uuid), "" });
		return s;
	}

	public ItemStack points(String uuid) {
		ItemStack s = Manager_ItemBuilder.lore("§9§lPoints", Material.PAPER, 1, 0,
				new String[] { " ", "§7§l§ §9" + Stats.getPoints(uuid), "" });
		return s;
	}

	public ItemStack firstjoin(String uuid) {
		ItemStack s = Manager_ItemBuilder.lore("§a§lFirst-Join", Material.BOOK, 1, 0,
				new String[] { " ", "§7§l§ §a" + instance.getEndfrommillis(Datas.getfirstjoin(uuid)), "" });
		return s;
	}

	public ItemStack lastonline(String uuid) {
		ItemStack s = Manager_ItemBuilder.lore("§c§lLast-Online", Material.BOOK, 1, 0,
				new String[] { " ", "§7§l§ §c" + instance.getEndfrommillis(Datas.getlastonline(uuid)), "" });
		return s;
	}

	public ItemStack player(String uuid) {
		String name = Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName();
		if (Bukkit.getOfflinePlayer(UUID.fromString(uuid)).isOnline()) {
			return Manager_ItemBuilder.Skull_lore("§b§l" + name, name, 1, new String[] { "", "§7§l§ §a§lOnline", "" });
		} else {
			return Manager_ItemBuilder.Skull_lore("§b§l" + name, name, 1, new String[] { "", "§7§l§ §c§lOffline", "" });
		}
	}

	public ItemStack banpoints(String uuid) {
		ItemStack s = Manager_ItemBuilder.lore("§c§lBan-Points", Material.BOOK, 1, 0,
				new String[] { " ", "§7§l§ §c" + BanPoints.getBP(uuid), "" });
		return s;
	}

	public ItemStack ban(String uuid) {
		if (Ban.isBanned(uuid)) {
			return Manager_ItemBuilder.item("§7§l§ §c§lBanned", Material.INK_SACK, 1, 1);
		} else {

			return Manager_ItemBuilder.item("§7§l§ §a§lNot-Banned", Material.INK_SACK, 10, 1);
		}
	}

	public ItemStack rang_player(Player p, String rangpath, String rangprefixpath) {
		if (PermissionsEx.getUser(p).inGroup(Config.getString(rangpath))) {
			return Manager_ItemBuilder.lore(Config.getString(rangpath), Material.INK_SACK, 1, 10,
					new String[] { "", "§7§l§ §9" + p.getName() + "§7 ist schon",
							"§7§l§ in der Gruppe: §b" + Config.getString(rangprefixpath), ""

					});
		} else {
			return Manager_ItemBuilder.lore(Config.getString(rangpath), Material.INK_SACK, 1, 8,
					new String[] { "", "§7§l§ Klick im §9" + p.getName(),
							"§7§l§ in die Gruppe: §b" + Config.getString(rangprefixpath), "§7§l§ zu setzen.", ""

					});
		}
	}

	public ItemStack mute(String uuid) {
		if (Mute.isMuted(uuid)) {
			return Manager_ItemBuilder.item("§7§l§ §c§lMuted", Material.INK_SACK, 1, 1);
		} else {

			return Manager_ItemBuilder.item("§7§l§ §a§lNot-Muted", Material.INK_SACK, 10, 1);
		}
	}

}
