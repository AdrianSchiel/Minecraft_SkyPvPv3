package net.royalbyte.skypvp.commands;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.SkyPvP;
import net.royalbyte.skypvp.manager.Manager_ItemBuilder;
import net.royalbyte.skypvp.mysql.Stats;

public class Command_event implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.hasPermission("SkyPvP.event")) {
				if (args.length == 0) {
					p.sendMessage(Data.prefix + "§cUse /event <help>.");
				} else if (args.length == 1) {
					if (args[0].equalsIgnoreCase("help")) {
						p.sendMessage(Data.header);
						p.sendMessage(Config.getString("event.help.line-1"));
						p.sendMessage(Config.getString("event.help.line-2"));
						p.sendMessage(Config.getString("event.help.line-3"));
						p.sendMessage(Config.getString("event.help.line-4"));
						p.sendMessage(Data.header);
					} else if (args[0].equalsIgnoreCase("item")) {
						if (p.hasPermission("SkyPvP.event.item")) {
							ItemStack i = p.getItemInHand();
							if (!(p.getItemInHand().getType() == Material.AIR || p.getItemInHand() == null)) {
								for (Player all : Bukkit.getOnlinePlayers()) {
									all.getInventory().addItem(i);
									all.sendMessage(
											Config.getString("event.item.msg").replaceAll("%PLAYER%", p.getName()));
								}
							} else {
								p.sendMessage(Config.getString("event.item.must_hold_item_in_hand"));
							}
						} else {
							p.sendMessage(Data.noperm);
						}
					} else {
						p.sendMessage(Data.prefix + "§cUse /event <help>.");
					}
				} else if (args.length == 2) {
					if (args[0].equalsIgnoreCase("coins")) {
						if (p.hasPermission("SkyPvP.event.coins")) {
							if (Config.getBoolean("event.coins.boolean") == false) {
								Config.set("event.coins.boolean", true);
								Config.save();
								int coins = Integer.parseInt(args[1]);
								if (coins >= 0) {
									new BukkitRunnable() {
										int i = 31;

										@Override
										public void run() {
											i--;
											if (i == 30 || i == 20 || i == 10) {
												Bukkit.getOnlinePlayers().forEach(player -> {
													player.sendMessage(Config.getString("event.coins.timer")
															.replaceAll("%COINS%", String.valueOf(coins))
															.replaceAll("%TIME%", String.valueOf(i)));
												});
											}
											if (i == 5 || i == 4 || i == 3 || i == 2 || i == 1) {
												Bukkit.getOnlinePlayers().forEach(player -> {
													player.playSound(player.getLocation(), Sound.NOTE_BASS, 2, 3);
													player.sendMessage(Config.getString("event.coins.timer")
															.replaceAll("%COINS%", String.valueOf(coins))
															.replaceAll("%TIME%", String.valueOf(i)));
												});
											}
											if (i == 0) {
												@SuppressWarnings("unchecked")
												List<Player> list = (List<Player>) Bukkit.getOnlinePlayers();
												int gezogen = ThreadLocalRandom.current().nextInt(0, list.size());
												Player winner = list.get(gezogen);
												Bukkit.getOnlinePlayers().forEach(player -> {
													player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);
													player.sendMessage(Config.getString("event.coins.winner")
															.replaceAll("%PLAYER%", winner.getName())
															.replaceAll("%COINS%", String.valueOf(coins)));
												});
												Stats.addCoins(winner.getUniqueId().toString(), coins);
												Config.set("event.coins.boolean", false);
												Config.save();
												cancel();
											}
										}
									}.runTaskTimerAsynchronously(SkyPvP.getInstance(), 0, 20);
								} else {
									p.sendMessage(Config.getString("event.coins.coins_must_over_null"));
								}
							} else {
								p.sendMessage(Config.getString("event.coins.is_current"));
							}
						} else {
							p.sendMessage(Data.noperm);
						}

					} else if (args[0].equalsIgnoreCase("rang")) {
						if (p.hasPermission("SkyPvP.event.rang")) {
							if (Config.getBoolean("event.rang.boolean") == false) {
								Config.set("event.rang.boolean", true);
								Config.save();
								String rang = args[1];
								new BukkitRunnable() {
									int i = 31;

									@Override
									public void run() {
										i--;
										if (i == 30 || i == 20 || i == 10) {
											Bukkit.getOnlinePlayers().forEach(player -> {
												player.sendMessage(Config.getString("event.rang.timer")
														.replaceAll("%RANG%", rang)
														.replaceAll("%TIME%", String.valueOf(i)));
											});
										}
										if (i == 5 || i == 4 || i == 3 || i == 2 || i == 1) {
											Bukkit.getOnlinePlayers().forEach(player -> {
												player.playSound(player.getLocation(), Sound.NOTE_BASS, 2, 3);
												player.sendMessage(Config.getString("event.rang.timer")
														.replaceAll("%RANG%", rang)
														.replaceAll("%TIME%", String.valueOf(i)));
											});
										}
										if (i == 0) {
											@SuppressWarnings("unchecked")
											List<Player> list = (List<Player>) Bukkit.getOnlinePlayers();
											int gezogen = ThreadLocalRandom.current().nextInt(0, list.size());
											Player winner = list.get(gezogen);
											Bukkit.getOnlinePlayers().forEach(player -> {
												player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);
												player.sendMessage(Config.getString("event.rang.winner")
														.replaceAll("%PLAYER%", winner.getName())
														.replaceAll("%RANG%", rang));
											});
											Config.set("event.rang.boolean", false);
											Config.save();
											winner.getInventory().addItem(Manager_ItemBuilder.lore("§bRang §7: " + rang, Material.PAPER, 1, 0 , new String[] {"" , "§8§l§ §7§lRechtsklick zum §b§leinl§sen" , ""}));
											cancel();
										}
									}
								}.runTaskTimerAsynchronously(SkyPvP.getInstance(), 0, 20);

							} else {
								p.sendMessage(Config.getString("event.rang.is_current"));
							}
						} else {
							p.sendMessage(Data.noperm);
						}

					}else if(args[0].equalsIgnoreCase("kit")) {
						for (int i = 1; i < 8; i++) {
							if(args[1].equalsIgnoreCase("Kit-" + i)) {
								if(p.hasPermission("SkyPvP.event.kit-" + i)) {
									for(Player all : Bukkit.getOnlinePlayers()) {
										SkyPvP.getInstance().getKits().getKit(all, "Kit-1", Config.getString("Kit-" + i));	
									}
								}else {
									p.sendMessage(Data.noperm);
								}
							}
						}

					}else {
						p.sendMessage(Data.header);
						p.sendMessage(Config.getString("event.help.line-1"));
						p.sendMessage(Config.getString("event.help.line-2"));
						p.sendMessage(Config.getString("event.help.line-3"));
						p.sendMessage(Config.getString("event.help.line-4"));
						p.sendMessage(Data.header);
					}
				} else {

				}
			} else {
				p.sendMessage(Data.noperm);
			}
		} else {
			sender.sendMessage(Data.must_a_player);
		}

		return true;
	}

}
