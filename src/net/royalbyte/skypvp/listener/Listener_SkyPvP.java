package net.royalbyte.skypvp.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Entity;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowman;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.SkyPvP;
import net.royalbyte.skypvp.manager.Manager_ItemBuilder;
import net.royalbyte.skypvp.manager.Manager_Location;
import net.royalbyte.skypvp.mysql.Stats;

public class Listener_SkyPvP implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		try {
			Player p = (Player) e.getWhoClicked();
			if (e.getClickedInventory().getName() == "§b§lSkyPvP") {
				e.setCancelled(true);
				if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §b§lSozial") {
					SkyPvP.getInstance().getInventories().openSkyPvPSozials(p);
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §b§lPlayer") {
					SkyPvP.getInstance().getInventories().openSkyPvPPlayerAuswahl(p);
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §b§lServer") {
					SkyPvP.getInstance().getInventories().openSkyPvPServer(p);
				}
			} else if (e.getClickedInventory().getName() == "§b§lSozials") {
				e.setCancelled(true);
				if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §c§lZurück") {
					SkyPvP.getInstance().getInventories().openSkyPvPMain(p);
				}
			} else if (e.getClickedInventory().getName() == "§b§lPlayer-Auswahl") {
				e.setCancelled(true);
				Player target = Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName());
				if (target != null) {
					if (!SkyPvP.getInstance().playermanager.containsKey(p)) {
						SkyPvP.getInstance().getInventories().openSkyPvPPlayer(p, target);
						SkyPvP.getInstance().playermanager.put(p, target);
					} else {
						SkyPvP.getInstance().playermanager.remove(p);
						p.closeInventory();
						SkyPvP.getInstance().getInventories().openSkyPvPPlayer(p, target);
						SkyPvP.getInstance().playermanager.put(p, target);
					}
				} else {
					p.sendMessage(Data.player_not_online);
				}

			} else if (e.getClickedInventory().getName() == "§b§lServer") {

				e.setCancelled(true);

				if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §c§lZurück") {
					SkyPvP.getInstance().getInventories().openSkyPvPMain(p);
				} else

				if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §b§lWartung") {
					if (p.hasPermission("SkyPvP.wartung.manage")) {
						SkyPvP.getInstance().getWartung().wartung();
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
					} else {
						p.sendMessage(Data.noperm);
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §b§lRanking") {
					if (p.hasPermission("SkyPvP.ranking")) {
						p.sendMessage(Config.getString("getrankingshards"));
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
						p.getInventory()
								.addItem(Manager_ItemBuilder.item("§bRanking-1", Material.PRISMARINE_SHARD, 0, 1));
						p.getInventory()
								.addItem(Manager_ItemBuilder.item("§bRanking-2", Material.PRISMARINE_SHARD, 0, 1));
						p.getInventory()
								.addItem(Manager_ItemBuilder.item("§bRanking-3", Material.PRISMARINE_SHARD, 0, 1));
					} else {
						p.sendMessage(Data.noperm);
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §b§lWand") {
					if (p.hasPermission("SkyPvP.wand")) {
						p.sendMessage(Config.getString("getwand"));
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
						p.getInventory()
								.addItem(Manager_ItemBuilder.item("§bWand", Material.PRISMARINE_CRYSTALS, 0, 1));
					} else {
						p.sendMessage(Data.noperm);
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §b§lCookie") {
					if (p.hasPermission("SkyPvP.wand")) {
						p.sendMessage(Config.getString("getCookie"));
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
						p.closeInventory();
						p.getInventory().addItem(Manager_ItemBuilder.Skull_lore("§8§l§ §b§lCookie", "QuadratCookie", 1,
								new String[] { "", "§8§l§ §7Setzte den §bCookie", "" }));
					} else {
						p.sendMessage(Data.noperm);
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §b§lWarp §8§l§ "
						+ "§9§lTeam-Halle") {
					if (p.hasPermission("SkyPvP.setloc.teamhalle")) {
						Manager_Location.setLocation(p.getLocation(), "teamhalle");
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
						p.closeInventory();
					} else {
						p.sendMessage(Data.noperm);
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §b§lWarp §8§l§ " + "§9§lShop") {
					if (p.hasPermission("SkyPvP.setloc.shop")) {
						Manager_Location.setLocation(p.getLocation(), "shop");
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
						p.closeInventory();
					} else {
						p.sendMessage(Data.noperm);
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §b§lWarp §8§l§ " + "§9§lSpawn") {
					if (p.hasPermission("SkyPvP.setloc.Spawn")) {
						Manager_Location.setLocation(p.getLocation(), "spawn");
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
						p.closeInventory();
					} else {
						p.sendMessage(Data.noperm);
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §b§lWarp §8§l§ "
						+ "§9§lVerzaubern") {
					if (p.hasPermission("SkyPvP.setloc.Verzaubern")) {
						Manager_Location.setLocation(p.getLocation(), "Verzaubern");
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
						p.closeInventory();
					} else {
						p.sendMessage(Data.noperm);
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §b§lWarp §8§l§ "
						+ "§9§lCookie-Clicker") {
					if (p.hasPermission("SkyPvP.setloc.Cookie")) {
						Manager_Location.setLocation(p.getLocation(), "Cookie");
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
						p.closeInventory();
					} else {
						p.sendMessage(Data.noperm);
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §b§lMob §8§l§ §9§lCookie") {
					if (p.hasPermission("SkyPvP.setmob.Cookie")) {
						Entity entity = p.getWorld().spawn(p.getLocation(), Blaze.class);
						SkyPvP.getInstance().disableEntityAI(entity);
						entity.setCustomName(Config.getString("Mob.name.cookie"));
						entity.setCustomNameVisible(true);
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
						p.closeInventory();
						Bukkit.broadcastMessage(Config.getString("Mob.set").replaceAll("%mobname%",
								Config.getString("Mob.name.cookie")));
					} else {
						p.sendMessage(Data.noperm);
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §b§lMob §8§l§ §9§lShop") {
					if (p.hasPermission("SkyPvP.setmob.Shop")) {
						Entity entity = p.getWorld().spawn(p.getLocation(), Zombie.class);
						SkyPvP.getInstance().disableEntityAI(entity);
						entity.setCustomName(Config.getString("Mob.name.Shop"));
						entity.setCustomNameVisible(true);
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
						p.closeInventory();
						Bukkit.broadcastMessage(
								Config.getString("Mob.set").replaceAll("%mobname%", Config.getString("Mob.name.Shop")));
					} else {
						p.sendMessage(Data.noperm);
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §b§lMob §8§l§ §9§lKits") {
					if (p.hasPermission("SkyPvP.setmob.Kits")) {
						Entity entity = p.getWorld().spawn(p.getLocation(), Snowman.class);
						SkyPvP.getInstance().disableEntityAI(entity);
						entity.setCustomName(Config.getString("Mob.name.Kits"));
						entity.setCustomNameVisible(true);
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
						p.closeInventory();
						Bukkit.broadcastMessage(
								Config.getString("Mob.set").replaceAll("%mobname%", Config.getString("Mob.name.Kits")));
					} else {
						p.sendMessage(Data.noperm);
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §b§lMob §8§l§ §9§lRewards") {
					if (p.hasPermission("SkyPvP.setmob.Rewards")) {
						Entity entity = p.getWorld().spawn(p.getLocation(), IronGolem.class);
						SkyPvP.getInstance().disableEntityAI(entity);
						entity.setCustomName(Config.getString("Mob.name.Rewards"));
						entity.setCustomNameVisible(true);
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
						p.closeInventory();
						Bukkit.broadcastMessage(Config.getString("Mob.set").replaceAll("%mobname%",
								Config.getString("Mob.name.Rewards")));
					} else {
						p.sendMessage(Data.noperm);
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				}
			} else if (e.getClickedInventory().getName() == "§b§lPlayer") {
				e.setCancelled(true);
				if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §c§lZurück") {
					SkyPvP.getInstance().getInventories().openSkyPvPMain(p);
					if (SkyPvP.getInstance().playermanager.containsKey(p)) {
						SkyPvP.getInstance().playermanager.remove(p);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §b§lKick") {
					if (p.hasPermission("SkyPvP.player.manage.kick")) {
						if (SkyPvP.getInstance().playermanager.containsKey(p)) {
							Bukkit.dispatchCommand(p,
									"kick " + SkyPvP.getInstance().playermanager.get(p).getName() + " OTHER");
							SkyPvP.getInstance().playermanager.remove(p);
							p.closeInventory();
							p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
						} else {
							p.sendMessage(Config.getString("Player.manager.p_not_found"));
							p.closeInventory();
							p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
						}
					} else {
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
						p.sendMessage(Data.noperm);
						p.closeInventory();
						SkyPvP.getInstance().playermanager.remove(p);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §b§lStats-Manager") {
					if (p.hasPermission("SkyPvP.player.manage.statsmanager")) {
						if (SkyPvP.getInstance().playermanager.containsKey(p)) {
							SkyPvP.getInstance().getInventories().openStatsManager(p,
									SkyPvP.getInstance().playermanager.get(p));
						} else {
							p.sendMessage(Config.getString("Player.manager.p_not_found"));
							p.closeInventory();
							p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
						}
					} else {
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
						p.sendMessage(Data.noperm);
						p.closeInventory();
						SkyPvP.getInstance().playermanager.remove(p);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §b§lMute") {
					if (p.hasPermission("SkyPvP.player.manage.mute")) {
						if (SkyPvP.getInstance().playermanager.containsKey(p)) {
							SkyPvP.getInstance().getInventories().openMutePlayer(p,
									SkyPvP.getInstance().playermanager.get(p));
						} else {
							p.sendMessage(Config.getString("Player.manager.p_not_found"));
							p.closeInventory();
							p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
						}
					} else {
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
						p.sendMessage(Data.noperm);
						p.closeInventory();
						SkyPvP.getInstance().playermanager.remove(p);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §b§lBan") {
					if (p.hasPermission("SkyPvP.player.manage.ban")) {
						if (SkyPvP.getInstance().playermanager.containsKey(p)) {
							SkyPvP.getInstance().getInventories().openBanPlayer(p,
									SkyPvP.getInstance().playermanager.get(p));
						} else {
							p.sendMessage(Config.getString("Player.manager.p_not_found"));
							p.closeInventory();
							p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
						}
					} else {
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
						p.sendMessage(Data.noperm);
						p.closeInventory();
						SkyPvP.getInstance().playermanager.remove(p);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §b§lDatas") {
					if (p.hasPermission("SkyPvP.player.manage.data")) {
						if (SkyPvP.getInstance().playermanager.containsKey(p)) {
							SkyPvP.getInstance().getInventories().openData(p,
									SkyPvP.getInstance().playermanager.get(p).getUniqueId().toString());
							SkyPvP.getInstance().playermanager.remove(p);
						} else {
							p.sendMessage(Config.getString("Player.manager.p_not_found"));
							p.closeInventory();
							p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
						}
					} else {
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
						p.sendMessage(Data.noperm);
						p.closeInventory();
						SkyPvP.getInstance().playermanager.remove(p);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §b§lRang") {
					if (p.hasPermission("SkyPvP.player.manage.rang")) {
						if (SkyPvP.getInstance().playermanager.containsKey(p)) {
							SkyPvP.getInstance().getInventories().openRangManager(p,
									SkyPvP.getInstance().playermanager.get(p));
						} else {
							p.sendMessage(Config.getString("Player.manager.p_not_found"));
							p.closeInventory();
							p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
						}
					} else {
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
						p.sendMessage(Data.noperm);
						p.closeInventory();
						SkyPvP.getInstance().playermanager.remove(p);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §b§lTeleport") {
					if (p.hasPermission("SkyPvP.player.manage.tp")) {
						if (SkyPvP.getInstance().playermanager.containsKey(p)) {
							p.teleport(SkyPvP.getInstance().playermanager.get(p));
							p.sendMessage(Config.getString("Player.manager.tpmsg").replaceAll("%PLAYER%",
									SkyPvP.getInstance().playermanager.get(p).getName()));
							SkyPvP.getInstance().playermanager.remove(p);
							p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
						} else {
							p.sendMessage(Config.getString("Player.manager.p_not_found"));
							p.closeInventory();
							p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
						}
					} else {
						p.sendMessage(Data.noperm);
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				}
			} else if (e.getClickedInventory().getName() == "§b§lMute-Player") {
				e.setCancelled(true);
				if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §c§lZurück") {
					SkyPvP.getInstance().getInventories().openSkyPvPPlayer(p,
							SkyPvP.getInstance().playermanager.get(p));
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == Config.getString("Mute.one.reason")) {
					if (SkyPvP.getInstance().playermanager.containsKey(p)) {
						Bukkit.dispatchCommand(p, "mute " + SkyPvP.getInstance().playermanager.get(p).getName() + " 1");
						SkyPvP.getInstance().playermanager.remove(p);
						p.closeInventory();
					} else {
						p.sendMessage(Config.getString("Player.manager.p_not_found"));
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == Config.getString("Mute.two.reason")) {
					if (SkyPvP.getInstance().playermanager.containsKey(p)) {
						Bukkit.dispatchCommand(p, "mute " + SkyPvP.getInstance().playermanager.get(p).getName() + " 2");
						SkyPvP.getInstance().playermanager.remove(p);
						p.closeInventory();
					} else {
						p.sendMessage(Config.getString("Player.manager.p_not_found"));
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == Config.getString("Mute.three.reason")) {
					if (SkyPvP.getInstance().playermanager.containsKey(p)) {
						Bukkit.dispatchCommand(p, "mute " + SkyPvP.getInstance().playermanager.get(p).getName() + " 3");
						SkyPvP.getInstance().playermanager.remove(p);
						p.closeInventory();
					} else {
						p.sendMessage(Config.getString("Player.manager.p_not_found"));
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == Config.getString("Mute.four.reason")) {
					if (SkyPvP.getInstance().playermanager.containsKey(p)) {
						Bukkit.dispatchCommand(p, "mute " + SkyPvP.getInstance().playermanager.get(p).getName() + " 4");
						SkyPvP.getInstance().playermanager.remove(p);
						p.closeInventory();
					} else {
						p.sendMessage(Config.getString("Player.manager.p_not_found"));
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == Config.getString("Mute.five.reason")) {
					if (SkyPvP.getInstance().playermanager.containsKey(p)) {
						Bukkit.dispatchCommand(p, "mute " + SkyPvP.getInstance().playermanager.get(p).getName() + " 5");
						SkyPvP.getInstance().playermanager.remove(p);
						p.closeInventory();
					} else {
						p.sendMessage(Config.getString("Player.manager.p_not_found"));
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§b§lUnMute") {
					if (SkyPvP.getInstance().playermanager.containsKey(p)) {
						Bukkit.dispatchCommand(p, "unmute " + SkyPvP.getInstance().playermanager.get(p).getName());
						SkyPvP.getInstance().playermanager.remove(p);
						p.closeInventory();
					} else {
						p.sendMessage(Config.getString("Player.manager.p_not_found"));
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				}
			} else if (e.getClickedInventory().getName() == "§b§lBan-Player") {
				e.setCancelled(true);
				if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §c§lZurück") {
					SkyPvP.getInstance().getInventories().openSkyPvPPlayer(p,
							SkyPvP.getInstance().playermanager.get(p));
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == Config.getString("Ban.one.reason")) {
					if (SkyPvP.getInstance().playermanager.containsKey(p)) {
						Bukkit.dispatchCommand(p, "ban " + SkyPvP.getInstance().playermanager.get(p).getName() + " 1");
						SkyPvP.getInstance().playermanager.remove(p);
						p.closeInventory();
					} else {
						p.sendMessage(Config.getString("Player.manager.p_not_found"));
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == Config.getString("Ban.two.reason")) {
					if (SkyPvP.getInstance().playermanager.containsKey(p)) {
						Bukkit.dispatchCommand(p, "ban " + SkyPvP.getInstance().playermanager.get(p).getName() + " 2");
						SkyPvP.getInstance().playermanager.remove(p);
						p.closeInventory();
					} else {
						p.sendMessage(Config.getString("Player.manager.p_not_found"));
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == Config.getString("Ban.three.reason")) {
					if (SkyPvP.getInstance().playermanager.containsKey(p)) {
						Bukkit.dispatchCommand(p, "ban " + SkyPvP.getInstance().playermanager.get(p).getName() + " 3");
						SkyPvP.getInstance().playermanager.remove(p);
						p.closeInventory();
					} else {
						p.sendMessage(Config.getString("Player.manager.p_not_found"));
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == Config.getString("Ban.four.reason")) {
					if (SkyPvP.getInstance().playermanager.containsKey(p)) {
						Bukkit.dispatchCommand(p, "ban " + SkyPvP.getInstance().playermanager.get(p).getName() + " 4");
						SkyPvP.getInstance().playermanager.remove(p);
						p.closeInventory();
					} else {
						p.sendMessage(Config.getString("Player.manager.p_not_found"));
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == Config.getString("Ban.five.reason")) {
					if (SkyPvP.getInstance().playermanager.containsKey(p)) {
						Bukkit.dispatchCommand(p, "ban " + SkyPvP.getInstance().playermanager.get(p).getName() + " 5");
						SkyPvP.getInstance().playermanager.remove(p);
						p.closeInventory();
					} else {
						p.sendMessage(Config.getString("Player.manager.p_not_found"));
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == Config.getString("Ban.six.reason")) {
					if (SkyPvP.getInstance().playermanager.containsKey(p)) {
						Bukkit.dispatchCommand(p, "ban " + SkyPvP.getInstance().playermanager.get(p).getName() + " 6");
						SkyPvP.getInstance().playermanager.remove(p);
						p.closeInventory();
					} else {
						p.sendMessage(Config.getString("Player.manager.p_not_found"));
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == Config.getString("Ban.seven.reason")) {
					if (SkyPvP.getInstance().playermanager.containsKey(p)) {
						Bukkit.dispatchCommand(p, "ban " + SkyPvP.getInstance().playermanager.get(p).getName() + " 7");
						SkyPvP.getInstance().playermanager.remove(p);
						p.closeInventory();
					} else {
						p.sendMessage(Config.getString("Player.manager.p_not_found"));
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == Config.getString("Ban.eight.reason")) {
					if (SkyPvP.getInstance().playermanager.containsKey(p)) {
						Bukkit.dispatchCommand(p, "ban " + SkyPvP.getInstance().playermanager.get(p).getName() + " 8");
						SkyPvP.getInstance().playermanager.remove(p);
						p.closeInventory();
					} else {
						p.sendMessage(Config.getString("Player.manager.p_not_found"));
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == Config.getString("Ban.nine.reason")) {
					if (SkyPvP.getInstance().playermanager.containsKey(p)) {
						Bukkit.dispatchCommand(p, "ban " + SkyPvP.getInstance().playermanager.get(p).getName() + " 9");
						SkyPvP.getInstance().playermanager.remove(p);
						p.closeInventory();
					} else {
						p.sendMessage(Config.getString("Player.manager.p_not_found"));
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == Config.getString("Ban.ten.reason")) {
					if (SkyPvP.getInstance().playermanager.containsKey(p)) {
						Bukkit.dispatchCommand(p, "ban " + SkyPvP.getInstance().playermanager.get(p).getName() + " 10");
						SkyPvP.getInstance().playermanager.remove(p);
						p.closeInventory();
					} else {
						p.sendMessage(Config.getString("Player.manager.p_not_found"));
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				}
			} else if (e.getClickedInventory().getName() == "§b§lStats-Manager") {
				e.setCancelled(true);
				if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §c§lZurück") {
					SkyPvP.getInstance().getInventories().openSkyPvPPlayer(p,
							SkyPvP.getInstance().playermanager.get(p));
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §b§l+1 Kill") {
					if (p.hasPermission("SkyPvP.player.manage.stats.addkill")) {
						if (SkyPvP.getInstance().playermanager.containsKey(p)) {
							Stats.addKills(SkyPvP.getInstance().playermanager.get(p).getUniqueId().toString(), 1);
							p.closeInventory();
							SkyPvP.getInstance().getInventories().openStatsManager(p,
									SkyPvP.getInstance().playermanager.get(p));
							p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
						} else {
							p.sendMessage(Config.getString("Player.manager.p_not_found"));
							p.closeInventory();
							p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
						}
					} else {
						p.sendMessage(Data.noperm);
						SkyPvP.getInstance().playermanager.remove(p);
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}

				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §b§l-1 Kill") {
					if (p.hasPermission("SkyPvP.player.manage.stats.removekill")) {
						if (SkyPvP.getInstance().playermanager.containsKey(p)) {
							Stats.removeKills(SkyPvP.getInstance().playermanager.get(p).getUniqueId().toString(), 1);
							p.closeInventory();
							SkyPvP.getInstance().getInventories().openStatsManager(p,
									SkyPvP.getInstance().playermanager.get(p));
							p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
						} else {
							p.sendMessage(Config.getString("Player.manager.p_not_found"));
							p.closeInventory();
							p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
						}
					} else {
						p.sendMessage(Data.noperm);
						SkyPvP.getInstance().playermanager.remove(p);
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}

				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §b§l+1 Tod") {
					if (p.hasPermission("SkyPvP.player.manage.stats.adddeath")) {
						if (SkyPvP.getInstance().playermanager.containsKey(p)) {
							Stats.addTode(SkyPvP.getInstance().playermanager.get(p).getUniqueId().toString(), 1);
							p.closeInventory();
							SkyPvP.getInstance().getInventories().openStatsManager(p,
									SkyPvP.getInstance().playermanager.get(p));
							p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
						} else {
							p.sendMessage(Config.getString("Player.manager.p_not_found"));
							p.closeInventory();
							p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
						}
					} else {
						p.sendMessage(Data.noperm);
						SkyPvP.getInstance().playermanager.remove(p);
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}

				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §b§l-1 Tod") {
					if (p.hasPermission("SkyPvP.player.manage.stats.removedeath")) {
						if (SkyPvP.getInstance().playermanager.containsKey(p)) {
							Stats.removeTode(SkyPvP.getInstance().playermanager.get(p).getUniqueId().toString(), 1);
							p.closeInventory();
							SkyPvP.getInstance().getInventories().openStatsManager(p,
									SkyPvP.getInstance().playermanager.get(p));
							p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
						} else {
							p.sendMessage(Config.getString("Player.manager.p_not_found"));
							p.closeInventory();
							p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
						}
					} else {
						p.sendMessage(Data.noperm);
						SkyPvP.getInstance().playermanager.remove(p);
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}

				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §b§l+50 Coins") {
					if (p.hasPermission("SkyPvP.player.manage.stats.addcoins")) {
						if (SkyPvP.getInstance().playermanager.containsKey(p)) {
							Stats.addCoins(SkyPvP.getInstance().playermanager.get(p).getUniqueId().toString(), 50);
							p.closeInventory();
							SkyPvP.getInstance().getInventories().openStatsManager(p,
									SkyPvP.getInstance().playermanager.get(p));
							p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
						} else {
							p.sendMessage(Config.getString("Player.manager.p_not_found"));
							p.closeInventory();
							p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
						}
					} else {
						p.sendMessage(Data.noperm);
						SkyPvP.getInstance().playermanager.remove(p);
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}

				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §b§l-50 Coins") {
					if (p.hasPermission("SkyPvP.player.manage.stats.removecoins")) {
						if (SkyPvP.getInstance().playermanager.containsKey(p)) {
							Stats.removeCoins(SkyPvP.getInstance().playermanager.get(p).getUniqueId().toString(), 50);
							p.closeInventory();
							SkyPvP.getInstance().getInventories().openStatsManager(p,
									SkyPvP.getInstance().playermanager.get(p));
							p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
						} else {
							p.sendMessage(Config.getString("Player.manager.p_not_found"));
							p.closeInventory();
							p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
						}
					} else {
						p.sendMessage(Data.noperm);
						SkyPvP.getInstance().playermanager.remove(p);
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}

				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §b§l+10 Points") {
					if (p.hasPermission("SkyPvP.player.manage.stats.addPoints")) {
						if (SkyPvP.getInstance().playermanager.containsKey(p)) {
							Stats.addPoints(SkyPvP.getInstance().playermanager.get(p).getUniqueId().toString(), 10);
							p.closeInventory();
							SkyPvP.getInstance().getInventories().openStatsManager(p,
									SkyPvP.getInstance().playermanager.get(p));
							p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
						} else {
							p.sendMessage(Config.getString("Player.manager.p_not_found"));
							p.closeInventory();
							p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
						}
					} else {
						p.sendMessage(Data.noperm);
						SkyPvP.getInstance().playermanager.remove(p);
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}

				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §b§l-10 Points") {
					if (p.hasPermission("SkyPvP.player.manage.stats.removePoints")) {
						if (SkyPvP.getInstance().playermanager.containsKey(p)) {
							Stats.removePoints(SkyPvP.getInstance().playermanager.get(p).getUniqueId().toString(), 10);
							p.closeInventory();
							SkyPvP.getInstance().getInventories().openStatsManager(p,
									SkyPvP.getInstance().playermanager.get(p));
							p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
						} else {
							p.sendMessage(Config.getString("Player.manager.p_not_found"));
							p.closeInventory();
							p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
						}
					} else {
						p.sendMessage(Data.noperm);
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
						SkyPvP.getInstance().playermanager.remove(p);
						p.closeInventory();
					}

				}
			} else if (e.getClickedInventory().getName() == "§b§lRang-Manager") {
				e.setCancelled(true);
				if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §c§lZurück") {
					SkyPvP.getInstance().getInventories().openSkyPvPPlayer(p,
							SkyPvP.getInstance().playermanager.get(p));
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == Config
						.getString("rang.rang-1.permissionsexrang")) {
					Player target = SkyPvP.getInstance().playermanager.get(p);
					SkyPvP.getInstance().getRang().setrang(target, p, "rang.rang-1.permissionsexrang",
							"SkyPvP.player.manage.rang.rang-1");
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == Config
						.getString("rang.rang-2.permissionsexrang")) {
					Player target = SkyPvP.getInstance().playermanager.get(p);
					SkyPvP.getInstance().getRang().setrang(target, p, "rang.rang-2.permissionsexrang",
							"SkyPvP.player.manage.rang.rang-2");
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == Config
						.getString("rang.rang-3.permissionsexrang")) {
					Player target = SkyPvP.getInstance().playermanager.get(p);
					SkyPvP.getInstance().getRang().setrang(target, p, "rang.rang-3.permissionsexrang",
							"SkyPvP.player.manage.rang.rang-3");
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == Config
						.getString("rang.rang-4.permissionsexrang")) {
					Player target = SkyPvP.getInstance().playermanager.get(p);
					SkyPvP.getInstance().getRang().setrang(target, p, "rang.rang-4.permissionsexrang",
							"SkyPvP.player.manage.rang.rang-4");
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == Config
						.getString("rang.rang-5.permissionsexrang")) {
					Player target = SkyPvP.getInstance().playermanager.get(p);
					SkyPvP.getInstance().getRang().setrang(target, p, "rang.rang-5.permissionsexrang",
							"SkyPvP.player.manage.rang.rang-5");
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == Config
						.getString("rang.rang-6.permissionsexrang")) {
					Player target = SkyPvP.getInstance().playermanager.get(p);
					SkyPvP.getInstance().getRang().setrang(target, p, "rang.rang-6.permissionsexrang",
							"SkyPvP.player.manage.rang.rang-6");
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == Config
						.getString("rang.rang-7.permissionsexrang")) {
					Player target = SkyPvP.getInstance().playermanager.get(p);
					SkyPvP.getInstance().getRang().setrang(target, p, "rang.rang-7.permissionsexrang",
							"SkyPvP.player.manage.rang.rang-7");
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == Config
						.getString("rang.rang-8.permissionsexrang")) {
					Player target = SkyPvP.getInstance().playermanager.get(p);
					SkyPvP.getInstance().getRang().setrang(target, p, "rang.rang-8.permissionsexrang",
							"SkyPvP.player.manage.rang.rang-8");
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == Config
						.getString("rang.rang-9.permissionsexrang")) {
					Player target = SkyPvP.getInstance().playermanager.get(p);
					SkyPvP.getInstance().getRang().setrang(target, p, "rang.rang-9.permissionsexrang",
							"SkyPvP.player.manage.rang.rang-9");
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == Config
						.getString("rang.rang-10.permissionsexrang")) {
					Player target = SkyPvP.getInstance().playermanager.get(p);
					SkyPvP.getInstance().getRang().setrang(target, p, "rang.rang-10.permissionsexrang",
							"SkyPvP.player.manage.rang.rang-10");
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == Config
						.getString("rang.rang-11.permissionsexrang")) {
					Player target = SkyPvP.getInstance().playermanager.get(p);
					SkyPvP.getInstance().getRang().setrang(target, p, "rang.rang-11.permissionsexrang",
							"SkyPvP.player.manage.rang.rang-11");
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == Config
						.getString("rang.rang-12.permissionsexrang")) {
					Player target = SkyPvP.getInstance().playermanager.get(p);
					SkyPvP.getInstance().getRang().setrang(target, p, "rang.rang-12.permissionsexrang",
							"SkyPvP.player.manage.rang.rang-12");
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == Config
						.getString("rang.rang-13.permissionsexrang")) {
					Player target = SkyPvP.getInstance().playermanager.get(p);
					SkyPvP.getInstance().getRang().setrang(target, p, "rang.rang-13.permissionsexrang",
							"SkyPvP.player.manage.rang.rang-13");
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == Config
						.getString("rang.rang-14.permissionsexrang")) {
					Player target = SkyPvP.getInstance().playermanager.get(p);
					SkyPvP.getInstance().getRang().setrang(target, p, "rang.rang-14.permissionsexrang",
							"SkyPvP.player.manage.rang.rang-14");
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == Config
						.getString("rang.rang-15.permissionsexrang")) {
					Player target = SkyPvP.getInstance().playermanager.get(p);
					SkyPvP.getInstance().getRang().setrang(target, p, "rang.rang-15.permissionsexrang",
							"SkyPvP.player.manage.rang.rang-15");
				}
			}

		} catch (Exception e2) {
			// TODO: handle exception
		}
	}

}
