package net.royalbyte.skypvp.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.SkyPvP;
import net.royalbyte.skypvp.manager.Manager_ItemBuilder;

public class Inventories {

	SkyPvP instance;

	public Inventories(SkyPvP instance) {
		this.instance = instance;
	}

	public void openCookieShop(Player p) {
		Inventory inv = Bukkit.createInventory(null, 3 * 9, "§b§lCookieShop");
		p.openInventory(inv);
		new BukkitRunnable() {
			int i = 0;
			int i2 = 18;

			@Override
			public void run() {
				if (i <= 8) {
					inv.setItem(i, Manager_ItemBuilder.item(" ", Material.STAINED_GLASS_PANE, 15, 1));
					inv.setItem(i2, Manager_ItemBuilder.item(" ", Material.STAINED_GLASS_PANE, 15, 1));
					i++;
					i2++;
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
				} else if (i == 9) {
					inv.setItem(11, Manager_ItemBuilder.lore("§b"+ Config.getInt("cookie.cost.1") + "-Cookies §7gegen §9"+ Config.getInt("coins.become.1") +"-Coins", Material.COOKIE, 1, 0,
							new String[] { "", "§8§l§ §7Rechtsklick zum §bkaufen", "" }));

					inv.setItem(13, Manager_ItemBuilder.lore("§b"+ Config.getInt("cookie.cost.2") + "-Cookies §7gegen §9"+ Config.getInt("coins.become.2") +"-Coins", Material.COOKIE, 1, 0,
							new String[] { "", "§8§l§ §7Rechtsklick zum §bkaufen", "" }));

					inv.setItem(15, Manager_ItemBuilder.lore("§b"+ Config.getInt("cookie.cost.3") + "-Cookies §7gegen §9"+ Config.getInt("coins.become.3") +"-Coins", Material.COOKIE, 1, 0,
							new String[] { "", "§8§l§ §7Rechtsklick zum §bkaufen", "" }));
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
					cancel();
				}

			}
		}.runTaskTimerAsynchronously(instance, 0, (long) 0.5);
	}
	public void openRewards(Player p) {
		Inventory inv = Bukkit.createInventory(null, 3 * 9, "§b§lRewards");
		p.openInventory(inv);
		for (int i = 0; i < inv.getSize(); i++) {
			inv.setItem(i, Manager_ItemBuilder.item(" ", Material.STAINED_GLASS_PANE, 7, 1));
		}

		if (SkyPvP.getInstance().getRewards().isAllowedstuendlich(p)) {
			inv.setItem(11, Manager_ItemBuilder.lore("§bStuendlicher-Reward", Material.STORAGE_MINECART, 1, 0,
					new String[] { "", "§8§l§ §7Rechtsklick zum §berhalten§7.", "" }));
		} else {
			inv.setItem(11, Manager_ItemBuilder.lore("§bStuendlicher-Reward", Material.MINECART, 1, 0,
					new String[] { "", "§8§l§ §7Du hast diesen §bReward", "§8§l§ §7schon erhalten." }));
		}
		
		if (SkyPvP.getInstance().getRewards().isAllowedtaeglich(p)) {
			inv.setItem(13, Manager_ItemBuilder.lore("§bTaeglicher-Reward", Material.STORAGE_MINECART, 1, 0,
					new String[] { "", "§8§l§ §7Rechtsklick zum §berhalten§7.", "" }));
		} else {
			inv.setItem(13, Manager_ItemBuilder.lore("§bTaeglicher-Reward", Material.MINECART, 1, 0,
					new String[] { "", "§8§l§ §7Du hast diesen §bReward", "§8§l§ §7schon erhalten." }));
		}
		if (SkyPvP.getInstance().getRewards().isAllowedwoechentlich(p)) {
			inv.setItem(15, Manager_ItemBuilder.lore("§bWoechentlicher-Reward", Material.STORAGE_MINECART, 1, 0,
					new String[] { "", "§8§l§ §7Rechtsklick zum §berhalten§7.", "" }));
		} else {
			inv.setItem(15, Manager_ItemBuilder.lore("§bWoechentlicher-Reward", Material.MINECART, 1, 0,
					new String[] { "", "§8§l§ §7Du hast diesen §bReward", "§8§l§ §7schon erhalten." }));
		}
	}

	public void openKits(Player p) {
		Inventory inv = Bukkit.createInventory(null, 5 * 9, "§b§lKits");
		p.openInventory(inv);
		for (int i = 9; i < 36; i++) {
			inv.setItem(i, Manager_ItemBuilder.item(" ", Material.STAINED_GLASS_PANE, 7, 1));
		}
		for (int i = 0; i < 9; i++) {
			inv.setItem(i, Manager_ItemBuilder.item(" ", Material.STAINED_GLASS_PANE, 0, 1));
		}
		for (int i = 36; i < inv.getSize(); i++) {
			inv.setItem(i, Manager_ItemBuilder.item(" ", Material.STAINED_GLASS_PANE, 0, 1));
		}

		inv.setItem(11, Manager_ItemBuilder.lore(Config.getString("Kit-1"), Material.WOOD_SWORD, 1, 0,
				new String[] { "", "§8§l§ §7Rechtsklick zum §bauswählen", "" }));
		inv.setItem(15, Manager_ItemBuilder.lore(Config.getString("Kit-2"), Material.STONE_SWORD, 1, 0,
				new String[] { "", "§8§l§ §7Rechtsklick zum §bauswählen", "" }));
		inv.setItem(19, Manager_ItemBuilder.lore(Config.getString("Kit-3"), Material.IRON_CHESTPLATE, 1, 0,
				new String[] { "", "§8§l§ §7Rechtsklick zum §bauswählen", "" }));
		inv.setItem(21, Manager_ItemBuilder.lore(Config.getString("Kit-4"), Material.IRON_SWORD, 1, 0,
				new String[] { "", "§8§l§ §7Rechtsklick zum §bauswählen", "" }));
		inv.setItem(23, Manager_ItemBuilder.lore(Config.getString("Kit-5"), Material.GOLD_SWORD, 1, 0,
				new String[] { "", "§8§l§ §7Rechtsklick zum §bauswählen", "" }));
		inv.setItem(25, Manager_ItemBuilder.lore(Config.getString("Kit-6"), Material.COMMAND, 1, 0,
				new String[] { "", "§8§l§ §7Rechtsklick zum §bauswählen", "" }));
		inv.setItem(31, Manager_ItemBuilder.lore(Config.getString("Kit-7"), Material.DIAMOND_SWORD, 1, 0,
				new String[] { "", "§8§l§ §7Rechtsklick zum §bauswählen", "" }));

	}

	public void openWarps(Player p) {
		Inventory inv = Bukkit.createInventory(null, 3 * 9, "§b§lWarps");
		p.openInventory(inv);
		new BukkitRunnable() {
			int i = 0;
			int i2 = 18;

			@Override
			public void run() {
				if (i <= 8) {
					inv.setItem(i, Manager_ItemBuilder.item(" ", Material.STAINED_GLASS_PANE, 15, 1));
					inv.setItem(i2, Manager_ItemBuilder.item(" ", Material.STAINED_GLASS_PANE, 15, 1));
					i++;
					i2++;
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
				} else if (i == 9) {
					inv.setItem(9, Manager_ItemBuilder.lore("§8§l§ §bShop", Material.CHEST, 1, 0,
							new String[] { "", "§8§l§ §7Rechtsklick zum §bteleportieren", "" }));
					inv.setItem(11, Manager_ItemBuilder.Skull_lore("§8§l§ §bTeam-Halle", p.getName(), 1,
							new String[] { "", "§8§l§ §7Rechtsklick zum §bteleportieren", "" }));
					inv.setItem(13, Manager_ItemBuilder.lore("§8§l§ §bSpawn", Material.MAGMA_CREAM, 1, 0,
							new String[] { "", "§8§l§ §7Rechtsklick zum §bteleportieren", "" }));
					inv.setItem(15, Manager_ItemBuilder.Skull_lore("§8§l§ §bCookie-Clicker", "QuadratCookie", 1,
							new String[] { "", "§8§l§ §7Rechtsklick zum §bteleportieren", "" }));
					inv.setItem(17, Manager_ItemBuilder.lore("§8§l§ §bVerzaubern", Material.CHEST, 1, 0,
							new String[] { "", "§8§l§ §7Rechtsklick zum §bteleportieren", "" }));
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
					cancel();
				}

			}
		}.runTaskTimerAsynchronously(instance, 0, (long) 0.5);
	}

	public void openSkyPvPMain(Player p) {
		Inventory inv = Bukkit.createInventory(null, 27, "§b§lSkyPvP");
		for (int i = 0; i < inv.getSize(); i++) {
			inv.setItem(i, Manager_ItemBuilder.item(" ", Material.STAINED_GLASS_PANE, 7, 1));
		}
		inv.setItem(11, Manager_ItemBuilder.Skull_lore("§8§l§ §b§lServer", "CallTheHendeks", 1,
				new String[] { "", "§8§L§ §7öffne die §b§lServer-Settings", "" }));
		inv.setItem(13, Manager_ItemBuilder.Skull_lore("§8§l§ §b§lSozial", "ExtrayeaMC", 1,
				new String[] { "", "§8§L§ §7öffne die §b§lSozials", "" }));
		inv.setItem(15, Manager_ItemBuilder.Skull_lore("§8§l§ §b§lPlayer", p.getName(), 1,
				new String[] { "", "§8§L§ §7öffne die §b§lPlayer-Settings", "" }));
		p.openInventory(inv);
		p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	}

	public void openSkyPvPSozials(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9, "§b§lSozials");
		for (int i = 0; i < inv.getSize(); i++) {
			inv.setItem(i, Manager_ItemBuilder.item(" ", Material.STAINED_GLASS_PANE, 7, 1));
		}
		inv.setItem(1, Manager_ItemBuilder.Skull_lore("§8§l§ §b§lEmail", "icytouch", 1,
				new String[] { "", "§8§L§ §b§ladrian.schiel@royalbyte.de", "" }));
		inv.setItem(3, Manager_ItemBuilder.Skull_lore("§8§l§ §b§lDiscord", "2amSkypeCall", 1,
				new String[] { "", "§8§L§ §b§lRoyalByte | Adrian#4909", "" }));
		inv.setItem(5, Manager_ItemBuilder.Skull_lore("§8§l§ §b§lYoutube", "MHF_Youtube", 1,
				new String[] { "", "§8§L§ §b§lRoyalByte", "" }));
		inv.setItem(7, Manager_ItemBuilder.Skull_lore("§8§l§ §b§lDiscord-Server", "ExtrayeaMC", 1,
				new String[] { "", "§8§L§ §b§lhttps://discordapp.com/invite/8Ae8jWc", "" }));

		p.openInventory(inv);
		p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
		if (p.hasPermission("SkyPvP.gui")) {
			inv.setItem(4, Manager_ItemBuilder.lore("§8§l§ §c§lZurück", Material.IRON_DOOR, 1, 0,
					new String[] { "", "§8§L§ §7öffne die §b§lVorherige-Seite", "" }));
		}
	}

	public void openSkyPvPPlayerAuswahl(Player p) {
		Inventory inv = Bukkit.createInventory(null, 6 * 9, "§b§lPlayer-Auswahl");
		for (Player player : Bukkit.getOnlinePlayers()) {
			inv.addItem(Manager_ItemBuilder.Skull_lore(player.getName(), player.getName(), 1,
					new String[] { "", "§8§l§ §7Klick um §9" + player.getName() + "§7 zu §bbearbeiten", "" }));
		}
		p.openInventory(inv);
		p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	}

	public void openMutePlayer(Player p, Player target) {
		Inventory inv = Bukkit.createInventory(null, 3 * 9, "§b§lMute-Player");
		for (int i = 0; i < inv.getSize(); i++) {
			inv.setItem(i, Manager_ItemBuilder.item(" ", Material.STAINED_GLASS_PANE, 7, 1));
		}
		inv.setItem(9, SkyPvP.getInstance().getItems().mute(target.getUniqueId().toString()));
		inv.setItem(11, Manager_ItemBuilder.lore(Config.getString("Mute.one.reason"), Material.BARRIER, 1, 0,
				new String[] { "", "§8§l§ §7Klick um §9" + target.getName() + "§7 zu §bmuten", "" }));
		inv.setItem(12, Manager_ItemBuilder.lore(Config.getString("Mute.two.reason"), Material.BARRIER, 1, 0,
				new String[] { "", "§8§l§ §7Klick um §9" + target.getName() + "§7 zu §bmuten", "" }));
		inv.setItem(13, Manager_ItemBuilder.lore(Config.getString("Mute.three.reason"), Material.BARRIER, 1, 0,
				new String[] { "", "§8§l§ §7Klick um §9" + target.getName() + "§7 zu §bmuten", "" }));
		inv.setItem(14, Manager_ItemBuilder.lore(Config.getString("Mute.four.reason"), Material.BARRIER, 1, 0,
				new String[] { "", "§8§l§ §7Klick um §9" + target.getName() + "§7 zu §bmuten", "" }));
		inv.setItem(15, Manager_ItemBuilder.lore(Config.getString("Mute.five.reason"), Material.BARRIER, 1, 0,
				new String[] { "", "§8§l§ §7Klick um §9" + target.getName() + "§7 zu §bmuten", "" }));
		inv.setItem(17, Manager_ItemBuilder.lore("§b§lUnMute", Material.INK_SACK, 1, 10,
				new String[] { "", "§8§l§ §7Klick um §9" + target.getName() + "§7 zu §bentmuten", "" }));

		inv.setItem(26, Manager_ItemBuilder.lore("§8§l§ §c§lZurück", Material.IRON_DOOR, 1, 0,
				new String[] { "", "§8§L§ §7öffne die §b§lVorherige-Seite", "" }));

		p.openInventory(inv);
		p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	}

	public void openBanPlayer(Player p, Player target) {
		Inventory inv = Bukkit.createInventory(null, 4 * 9, "§b§lBan-Player");
		for (int i = 0; i < inv.getSize(); i++) {
			inv.setItem(i, Manager_ItemBuilder.item(" ", Material.STAINED_GLASS_PANE, 7, 1));
		}
		inv.setItem(11, Manager_ItemBuilder.lore(Config.getString("Ban.one.reason"), Material.BARRIER, 1, 0,
				new String[] { "", "§8§l§ §7Klick um §9" + target.getName() + "§7 zu §bbannen", "" }));
		inv.setItem(12, Manager_ItemBuilder.lore(Config.getString("Ban.two.reason"), Material.BARRIER, 1, 0,
				new String[] { "", "§8§l§ §7Klick um §9" + target.getName() + "§7 zu §bbannen", "" }));
		inv.setItem(13, Manager_ItemBuilder.lore(Config.getString("Ban.three.reason"), Material.BARRIER, 1, 0,
				new String[] { "", "§8§l§ §7Klick um §9" + target.getName() + "§7 zu §bbannen", "" }));
		inv.setItem(14, Manager_ItemBuilder.lore(Config.getString("Ban.four.reason"), Material.BARRIER, 1, 0,
				new String[] { "", "§8§l§ §7Klick um §9" + target.getName() + "§7 zu §bbannen", "" }));
		inv.setItem(15, Manager_ItemBuilder.lore(Config.getString("Ban.five.reason"), Material.BARRIER, 1, 0,
				new String[] { "", "§8§l§ §7Klick um §9" + target.getName() + "§7 zu §bbannen", "" }));
		inv.setItem(20, Manager_ItemBuilder.lore(Config.getString("Ban.six.reason"), Material.BARRIER, 1, 0,
				new String[] { "", "§8§l§ §7Klick um §9" + target.getName() + "§7 zu §bbannen", "" }));
		inv.setItem(21, Manager_ItemBuilder.lore(Config.getString("Ban.seven.reason"), Material.BARRIER, 1, 0,
				new String[] { "", "§8§l§ §7Klick um §9" + target.getName() + "§7 zu §bbannen", "" }));
		inv.setItem(22, Manager_ItemBuilder.lore(Config.getString("Ban.eight.reason"), Material.BARRIER, 1, 0,
				new String[] { "", "§8§l§ §7Klick um §9" + target.getName() + "§7 zu §bbannen", "" }));
		inv.setItem(23, Manager_ItemBuilder.lore(Config.getString("Ban.nine.reason"), Material.BARRIER, 1, 0,
				new String[] { "", "§8§l§ §7Klick um §9" + target.getName() + "§7 zu §bbannen", "" }));
		inv.setItem(24, Manager_ItemBuilder.lore(Config.getString("Ban.ten.reason"), Material.BARRIER, 1, 0,
				new String[] { "", "§8§l§ §7Klick um §9" + target.getName() + "§7 zu §bbannen", "" }));

		inv.setItem(35, Manager_ItemBuilder.lore("§8§l§ §c§lZurück", Material.IRON_DOOR, 1, 0,
				new String[] { "", "§8§L§ §7öffne die §b§lVorherige-Seite", "" }));
		p.openInventory(inv);
		p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	}

	public void openRangManager(Player p, Player target) {
		Inventory inv = Bukkit.createInventory(null, 5 * 9, "§b§lRang-Manager");
		Items items = SkyPvP.getInstance().getItems();
		for (int i = 0; i < inv.getSize(); i++) {
			inv.setItem(i, Manager_ItemBuilder.item(" ", Material.STAINED_GLASS_PANE, 7, 1));
		}
		inv.setItem(11, items.rang_player(target, "rang.rang-1.permissionsexrang", "rang.rang-1.prefix"));
		inv.setItem(12, items.rang_player(target, "rang.rang-2.permissionsexrang", "rang.rang-2.prefix"));
		inv.setItem(13, items.rang_player(target, "rang.rang-3.permissionsexrang", "rang.rang-3.prefix"));
		inv.setItem(14, items.rang_player(target, "rang.rang-4.permissionsexrang", "rang.rang-4.prefix"));
		inv.setItem(15, items.rang_player(target, "rang.rang-5.permissionsexrang", "rang.rang-5.prefix"));
		inv.setItem(20, items.rang_player(target, "rang.rang-6.permissionsexrang", "rang.rang-6.prefix"));
		inv.setItem(21, items.rang_player(target, "rang.rang-7.permissionsexrang", "rang.rang-7.prefix"));
		inv.setItem(22, items.rang_player(target, "rang.rang-8.permissionsexrang", "rang.rang-8.prefix"));
		inv.setItem(23, items.rang_player(target, "rang.rang-9.permissionsexrang", "rang.rang-9.prefix"));
		inv.setItem(24, items.rang_player(target, "rang.rang-10.permissionsexrang", "rang.rang-10.prefix"));
		inv.setItem(29, items.rang_player(target, "rang.rang-11.permissionsexrang", "rang.rang-11.prefix"));
		inv.setItem(30, items.rang_player(target, "rang.rang-12.permissionsexrang", "rang.rang-12.prefix"));
		inv.setItem(31, items.rang_player(target, "rang.rang-13.permissionsexrang", "rang.rang-13.prefix"));
		inv.setItem(32, items.rang_player(target, "rang.rang-14.permissionsexrang", "rang.rang-14.prefix"));
		inv.setItem(33, items.rang_player(target, "rang.rang-15.permissionsexrang", "rang.rang-15.prefix"));

		inv.setItem(44, Manager_ItemBuilder.lore("§8§l§ §c§lZurück", Material.IRON_DOOR, 1, 0,
				new String[] { "", "§8§L§ §7öffne die §b§lVorherige-Seite", "" }));

		p.openInventory(inv);
		p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	}

	public void openStatsManager(Player p, Player target) {
		Inventory inv = Bukkit.createInventory(null, 5 * 9, "§b§lStats-Manager");
		for (int i = 0; i < inv.getSize(); i++) {
			inv.setItem(i, Manager_ItemBuilder.item(" ", Material.STAINED_GLASS_PANE, 7, 1));
		}
		inv.setItem(10, Manager_ItemBuilder.lore("§8§l§ §b§l+1 Kill", Material.WOOD_BUTTON, 1, 0, new String[] { "",
				"§8§l§ §7Klick um §9" + target.getName(), "§8§l§ §7einen Kill §bhinzuzuf§gen", "" }));
		inv.setItem(12, Manager_ItemBuilder.lore("§8§l§ §b§l+1 Tod", Material.WOOD_BUTTON, 1, 0,
				new String[] { "", "§8§l§ §7Klick um §9" + target.getName(), "§8§l§ §7einen Tod §bhinzuzuf§gen", "" }));
		inv.setItem(14, Manager_ItemBuilder.lore("§8§l§ §b§l+50 Coins", Material.WOOD_BUTTON, 1, 0,
				new String[] { "", "§8§l§ §7Klick um §9" + target.getName(), "§8§l§ §750 Coins §bhinzuzuf§gen", "" }));
		inv.setItem(16, Manager_ItemBuilder.lore("§8§l§ §b§l+10 Points", Material.WOOD_BUTTON, 1, 0,
				new String[] { "", "§8§l§ §7Klick um §9" + target.getName(), "§8§l§ §710 Points §bhinzuzuf§gen", "" }));
		inv.setItem(28, Manager_ItemBuilder.lore("§8§l§ §b§l-1 Kill", Material.WOOD_BUTTON, 1, 0,
				new String[] { "", "§8§l§ §7Klick um §9" + target.getName(), "§8§l§ §7einen Kill §babzuziehen", "" }));
		inv.setItem(30, Manager_ItemBuilder.lore("§8§l§ §b§l-1 Tod", Material.WOOD_BUTTON, 1, 0,
				new String[] { "", "§8§l§ §7Klick um §9" + target.getName(), "§8§l§ §7einen Tod §babzuziehen", "" }));
		inv.setItem(32, Manager_ItemBuilder.lore("§8§l§ §b§l-50 Coins", Material.WOOD_BUTTON, 1, 0,
				new String[] { "", "§8§l§ §7Klick um §9" + target.getName(), "§8§l§ §750 Coins §babzuziehen", "" }));
		inv.setItem(34, Manager_ItemBuilder.lore("§8§l§ §b§l-10 Points", Material.WOOD_BUTTON, 1, 0,
				new String[] { "", "§8§l§ §7Klick um §9" + target.getName(), "§8§l§ §710 Points §babzuziehen", "" }));
		inv.setItem(19, SkyPvP.getInstance().getItems().kills(target.getUniqueId().toString()));
		inv.setItem(21, SkyPvP.getInstance().getItems().tode(target.getUniqueId().toString()));
		inv.setItem(23, SkyPvP.getInstance().getItems().coins(target.getUniqueId().toString()));
		inv.setItem(25, SkyPvP.getInstance().getItems().points(target.getUniqueId().toString()));

		inv.setItem(44, Manager_ItemBuilder.lore("§8§l§ §c§lZurück", Material.IRON_DOOR, 1, 0,
				new String[] { "", "§8§L§ §7öffne die §b§lVorherige-Seite", "" }));

		p.openInventory(inv);
		p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	}

	public void openSkyPvPPlayer(Player p, Player target) {
		Inventory inv = Bukkit.createInventory(null, 3 * 9, "§b§lPlayer");
		for (int i = 0; i < inv.getSize(); i++) {
			inv.setItem(i, Manager_ItemBuilder.item(" ", Material.STAINED_GLASS_PANE, 7, 1));
		}
		inv.setItem(10, Manager_ItemBuilder.lore("§8§l§ §b§lKick", Material.STICK, 1, 0,
				new String[] { "", "§8§l§ §7Klick um §9" + target.getName() + "§7 zu §bkicken", "" }));
		inv.setItem(11, Manager_ItemBuilder.lore("§8§l§ §b§lMute", Material.BARRIER, 1, 0,
				new String[] { "", "§8§l§ §7Klick um §9" + target.getName() + "§7 zu §bmuten", "" }));
		inv.setItem(12, Manager_ItemBuilder.lore("§8§l§ §b§lStats-Manager", Material.GLOWSTONE_DUST, 1, 0,
				new String[] { "", "§8§l§ §7Klick um §9" + target.getName() + "'s §7Stats zu §bbearbeiten", "" }));
		inv.setItem(13, Manager_ItemBuilder.Skull_lore("§8§l§ §b§lDatas", target.getName(), 1,
				new String[] { "", "§8§l§ §7Klick um §9" + target.getName() + "'s §7Datas zu §bsehen", "" }));
		inv.setItem(14, Manager_ItemBuilder.lore("§8§l§ §b§lRang", Material.NAME_TAG, 1, 0,
				new String[] { "", "§8§l§ §7Klick um §9" + target.getName() + "'s §7Rang zu §bbearbeiten", "" }));
		inv.setItem(15, Manager_ItemBuilder.lore("§8§l§ §b§lBan", Material.BARRIER, 1, 0,
				new String[] { "", "§8§l§ §7Klick um §9" + target.getName() + "§7 zu §bbannen", "" }));
		inv.setItem(16, Manager_ItemBuilder.lore("§8§l§ §b§lTeleport", Material.ENDER_PEARL, 1, 0,
				new String[] { "", "§8§l§ §7Klick um dich zu §9" + target.getName() + " §bteleportieren", "" }));

		inv.setItem(26, Manager_ItemBuilder.lore("§8§l§ §c§lZurück", Material.IRON_DOOR, 1, 0,
				new String[] { "", "§8§L§ §7öffne die §b§lVorherige-Seite", "" }));
		p.openInventory(inv);
		p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	}

	public void openSkyPvPServer(Player p) {
		Inventory inv = Bukkit.createInventory(null, 4 * 9, "§b§lServer");
		for (int i = 0; i < inv.getSize(); i++) {
			inv.setItem(i, Manager_ItemBuilder.item(" ", Material.STAINED_GLASS_PANE, 7, 1));
		}

		inv.setItem(4, Manager_ItemBuilder.lore("§8§l§ §c§lZurück", Material.IRON_DOOR, 1, 0,
				new String[] { "", "§8§L§ §7öffne die §b§lVorherige-Seite", "" }));

		inv.setItem(1, instance.getItems().getPluginInfo());

		inv.setItem(3, Manager_ItemBuilder.lore("§8§l§ §b§lRanking", Material.PRISMARINE_SHARD, 1, 0,
				new String[] { "", "§8§l§ §7Klick für die §bRanking-Shards", "" }));

		inv.setItem(5, Manager_ItemBuilder.lore("§8§l§ §b§lWand", Material.PRISMARINE_CRYSTALS, 1, 0,
				new String[] { "", "§8§l§ §7Klick für den §bWand", "" }));

		inv.setItem(7, Manager_ItemBuilder.Skull_lore("§8§l§ §b§lCookie", "QuadratCookie", 1,
				new String[] { "", "§8§l§ §7Klick für den §bCookie", "" }));

		inv.setItem(9, Manager_ItemBuilder.Skull_lore("§8§l§ §b§lWarp §8§l§ " + "§9§lTeam-Halle", "RoyalByte", 1,
				new String[] { "", "§8§l§ §7Klick zum §b§lSetzen", "" }));

		inv.setItem(11, Manager_ItemBuilder.lore("§8§l§ §b§lWarp §8§l§ " + "§9§lShop", Material.CHEST, 1, 0,
				new String[] { "", "§8§l§ §7Klick zum §b§lSetzen", "" }));

		inv.setItem(13, Manager_ItemBuilder.lore("§8§l§ §b§lWarp §8§l§ " + "§9§lSpawn", Material.MAGMA_CREAM, 1, 0,
				new String[] { "", "§8§l§ §7Klick zum §b§lSetzen", "" }));

		inv.setItem(15, Manager_ItemBuilder.lore("§8§l§ §b§lWarp §8§l§ " + "§9§lVerzaubern", Material.ENCHANTMENT_TABLE,
				1, 0, new String[] { "", "§8§l§ §7Klick zum §b§lSetzen", "" }));

		inv.setItem(17, Manager_ItemBuilder.Skull_lore("§8§l§ §b§lWarp §8§l§ " + "§9§lCookie-Clicker", "QuadratCookie",
				1, new String[] { "", "§8§l§ §7Klick zum §b§lSetzen", "" }));

		inv.setItem(19, Manager_ItemBuilder.lore("§8§l§ §b§lMob §8§l§ §9§lCookie", Material.BLAZE_ROD, 1, 0,
				new String[] { "", "§8§l§ §7Klick zum §b§lSetzen", "" }));

		inv.setItem(21, Manager_ItemBuilder.lore("§8§l§ §b§lMob §8§l§ §9§lShop", Material.EMERALD, 1, 0,
				new String[] { "", "§8§l§ §7Klick zum §b§lSetzen", "" }));

		inv.setItem(23, Manager_ItemBuilder.lore("§8§l§ §b§lMob §8§l§ §9§lKits", Material.SNOW_BALL, 1, 0,
				new String[] { "", "§8§l§ §7Klick zum §b§lSetzen", "" }));

		inv.setItem(25, Manager_ItemBuilder.lore("§8§l§ §b§lMob §8§l§ §9§lRewards", Material.IRON_INGOT, 1, 0,
				new String[] { "", "§8§l§ §7Klick zum §b§lSetzen", "" }));

		inv.setItem(28, SkyPvP.getInstance().getItems().getOnlineModeItem());

		inv.setItem(31, SkyPvP.getInstance().getItems().getWartungItem());

		inv.setItem(34, SkyPvP.getInstance().getItems().getPluginStatusItem());

		p.openInventory(inv);
		p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	}

	public void openData(Player p, String uuid) {
		Inventory inv = Bukkit.createInventory(null, 27, "§bPlayer-Data");
		for (int i = 0; i < inv.getSize(); i++) {
			inv.setItem(i, Manager_ItemBuilder.item("", Material.STAINED_GLASS_PANE, 7, 1));
		}
		inv.setItem(1, instance.getItems().lastonline(uuid));
		inv.setItem(4, instance.getItems().player(uuid));
		inv.setItem(7, instance.getItems().firstjoin(uuid));
		inv.setItem(9, instance.getItems().coins(uuid));
		inv.setItem(11, instance.getItems().kills(uuid));
		inv.setItem(13, instance.getItems().tode(uuid));
		inv.setItem(15, instance.getItems().points(uuid));
		inv.setItem(17, instance.getItems().kd(uuid));
		inv.setItem(19, instance.getItems().ban(uuid));
		inv.setItem(22, instance.getItems().banpoints(uuid));
		inv.setItem(25, instance.getItems().mute(uuid));

		p.openInventory(inv);
		p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	}

	@SuppressWarnings("deprecation")
	public void openFreeFrame(Player p, ItemStack item) {
		if (item.getType() != Material.AIR) {
			Inventory inv = Bukkit.createInventory(null, 9, "§bFree §7 §9" + item.getType().getId());
			for (int i = 0; i < 9; i++) {
				inv.setItem(i, item);
			}
			p.openInventory(inv);
			p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
		}
	}

}
