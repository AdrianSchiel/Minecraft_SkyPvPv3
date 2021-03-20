package net.royalbyte.skypvp.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.manager.Manager_ItemBuilder;
import net.royalbyte.skypvp.manager.Manager_Location;
import net.royalbyte.skypvp.mysql.MySQL;
import net.royalbyte.skypvp.mysql.Stats;

public class Ranking implements Listener {

	
	public Ranking() {
	}
	
	public void set() {

		HashMap<Integer, String> rang = new HashMap<>();
		try {
		ResultSet rs = MySQL.getResult("SELECT UUID FROM Stats ORDER BY points DESC LIMIT 3");
		
		int in = 0;
		
		try {
			while(rs.next()) {
				in++;
				rang.put(in, rs.getString("UUID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Location loc = Manager_Location.getLocation("Ranking-skull-1");
		Location loc2 = Manager_Location.getLocation("Ranking-skull-2");
		Location loc3 = Manager_Location.getLocation("Ranking-skull-3");
		
		List<Location> LOC = new ArrayList<>();
		LOC.clear();
		LOC.add(loc);
		LOC.add(loc2);
		LOC.add(loc3);
		
		for(int i = 0; i < LOC.size(); i++) {
			
			int id = i+1;
			
			
			Skull s = (Skull) LOC.get(i).getBlock().getState();
			s.setSkullType(SkullType.PLAYER);

			try {
				String name = Bukkit.getOfflinePlayer(UUID.fromString(rang.get(id))).getName();
				s.setOwner(name);
			} catch (Exception e) {
			}
			
			
			s.update();
			
			Location newloc = new Location(LOC.get(i).getWorld(), LOC.get(i).getX(), LOC.get(i).getY() -1, LOC.get(i).getZ());
			
			if(newloc.getBlock().getState() instanceof Sign) {
				BlockState b = newloc.getBlock().getState();
				Sign S = (Sign) b;

						
				try {
					String name = Bukkit.getOfflinePlayer(UUID.fromString(rang.get(id))).getName();
					S.setLine(0, "§b"
							+ "§a#" + id);	
					S.setLine(1,"§8§ §c§l" + name);
				} catch (Exception e) {
				}
				S.setLine(2, getCoins(rang.get(id)));
				S.setLine(3, getPoints(rang.get(id)));
				S.update();
			}
		}
		} catch (Exception e) {
		}
		
	}
	
	public void setTop(Player p) {

		
		HashMap<Integer, String> rang = new HashMap<>();
		try {
			ResultSet rs = MySQL.getResult("SELECT UUID FROM Stats ORDER BY " + "points" + " DESC LIMIT 3");

			int in = 0;

			try {
				while (rs.next()) {
					in++;
					rang.put(in, rs.getString("UUID"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			String name = Bukkit.getOfflinePlayer(UUID.fromString(rang.get(1))).getName();
			String name1 = Bukkit.getOfflinePlayer(UUID.fromString(rang.get(2))).getName();
			String name2 = Bukkit.getOfflinePlayer(UUID.fromString(rang.get(3))).getName();
			String uuid = rang.get(1);
			String uuid1 = rang.get(2);
			String uuid2 = rang.get(3);
			
			Inventory inv = Bukkit.createInventory(null, 9, "§b§lTop-Liste");
			inv.setItem(0, Manager_ItemBuilder.item(" ", Material.STAINED_GLASS_PANE, 7, 1));
			inv.setItem(1, Manager_ItemBuilder.item(" ", Material.STAINED_GLASS_PANE, 7, 1));
			inv.setItem(2, Manager_ItemBuilder.item(" ", Material.STAINED_GLASS_PANE, 7, 1));
			inv.setItem(3, Manager_ItemBuilder.Skull_lore("§b#1 §7: §b" + name, name, 1, new String[] {"", getPoints(uuid),getCoins(uuid), getKills(uuid), getTode(uuid), getKD(uuid),getCookies(uuid),getCookieScore(uuid), ""}));
			inv.setItem(4, Manager_ItemBuilder.Skull_lore("§b#2 §7: §b" + name1, name1, 1, new String[] {"", getPoints(uuid1),getCoins(uuid1), getKills(uuid1), getTode(uuid1), getKD(uuid1),getCookies(uuid1),getCookieScore(uuid1), ""}));
			inv.setItem(5, Manager_ItemBuilder.Skull_lore("§b#3 §7: §b" + name2, name2, 1, new String[] {"", getPoints(uuid2),getCoins(uuid2), getKills(uuid2), getTode(uuid2), getKD(uuid2),getCookies(uuid2),getCookieScore(uuid2), ""}));
			inv.setItem(6, Manager_ItemBuilder.item(" ", Material.STAINED_GLASS_PANE, 7, 1));
			inv.setItem(7, Manager_ItemBuilder.item(" ", Material.STAINED_GLASS_PANE, 7, 1));
			inv.setItem(8, Manager_ItemBuilder.item(" ", Material.STAINED_GLASS_PANE, 7, 1));
			p.openInventory(inv);
			p.updateInventory();

		} catch (Exception e) {
		}

	}

	public static String getKills(String uuid) {
		return "§8§l§ §b§lKills §8: §b§l" + Stats.getKills(uuid);
	}
	public static String getTode(String uuid) {
		return "§8§l§ §c§lDeaths §8: §c§l" + Stats.getTode(uuid);
	}
	public static String getPoints(String uuid) {
		return "§8§l§ §9§lPoints §8: §9§l" + Stats.getPoints(uuid);
	}
	public static String getCoins(String uuid) {
		return "§8§l§ §a§lCoins §8: §a§l" + Stats.getCoins(uuid);
	}
	public static String getKD(String uuid) {
		return "§8§l§ §d§lKD §8: §d§l" + Stats.getKD(uuid);
	}
	public static String getCookies(String uuid) {
		return "§8§l§ §e§lCookies §8: §e§l" + Stats.getCookies(uuid);
	}
	public static String getCookieScore(String uuid) {
		return "§8§l§ §6§lCookie-Score §8: §6§l" + Stats.getCookieScore(uuid);
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		try {
			if(e.getClickedInventory().getName() == "§b§lTop-Liste") {
				e.setCancelled(true);
			}			
		} catch (Exception e2) {
		}

	}
	@EventHandler
	public void onInterract(PlayerInteractEvent e) {
		try {
			Player p = e.getPlayer();
			if(p.getItemInHand().getItemMeta().getDisplayName().equals("§bRanking-1")) {
				if(p.hasPermission("SkyPvP.ranking")) {
					Block b = e.getClickedBlock();
					Manager_Location.setLocation(b.getLocation(), "Ranking-skull-1");
					set();
				}else {
					p.sendMessage(Data.noperm);
				}
			}else if(p.getItemInHand().getItemMeta().getDisplayName().equals("§bRanking-2")) {
				if(p.hasPermission("SkyPvP.ranking")) {
					Block b = e.getClickedBlock();
					Manager_Location.setLocation(b.getLocation(), "Ranking-skull-2");
					set();
				}else {
					p.sendMessage(Data.noperm);
				}
			}else if(p.getItemInHand().getItemMeta().getDisplayName().equals("§bRanking-3")) {
				if(p.hasPermission("SkyPvP.ranking")) {
					Block b = e.getClickedBlock();
					Manager_Location.setLocation(b.getLocation(), "Ranking-skull-3");
					set();
				}else {
					p.sendMessage(Data.noperm);
				}
			}
			
			
			
		} catch (Exception e2) {
		}
	}
}
