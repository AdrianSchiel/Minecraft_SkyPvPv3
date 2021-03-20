package net.royalbyte.skypvp.commands;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.SkyPvP;
import net.royalbyte.skypvp.manager.Manager_ItemBuilder;

public class Command_rewards implements CommandExecutor {

	// rewards stuendlich add
	// rewards stuendlich clear
	// rewards stuendlich coins

	@SuppressWarnings("unchecked")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;

			if (args.length == 0) {
				SkyPvP.getInstance().getInventories().openRewards(p);
			} else if (args.length == 2) {
				if (args[0].equalsIgnoreCase("stuendlich")) {
					if (args[1].equalsIgnoreCase("add")) {
						if (p.hasPermission("SkyPvP.rewards.stuendlich.add")) {
							if (!(p.getItemInHand().getType() == Material.AIR || p.getItemInHand() == null)) {
								List<ItemStack> list = (List<ItemStack>) SkyPvP.getInstance().getRewards().cfg
										.get("stuendlich.list");
								list.add(p.getItemInHand());
								SkyPvP.getInstance().getRewards().cfg.set("stuendlich.list", list);
								SkyPvP.getInstance().getRewards().saveFile();
								p.sendMessage(Config.getString("rewards.addItem"));
							} else {
								p.sendMessage(Config.getString("rewards.add.no_item_in_hand"));
							}
						} else {
							p.sendMessage(Data.noperm);
						}
					} else if (args[1].equalsIgnoreCase("clear")) {
						if (p.hasPermission("SkyPvP.rewards.stuendlich.clear")) {
							List<ItemStack> list = SkyPvP.getInstance().getRewards().getList("stuendlich.list");
							list.clear();
							list.add(0, Manager_ItemBuilder.item("Standart-Reward", Material.IRON_INGOT, 0, 1));
							SkyPvP.getInstance().getRewards().cfg.set("stuendlich.list", list);
							SkyPvP.getInstance().getRewards().saveFile();
							p.sendMessage(Config.getString("rewards.clearlist"));
						} else {
							p.sendMessage(Data.noperm);
						}
					}
				} else if (args[0].equalsIgnoreCase("Taeglich")) {
					if (args[1].equalsIgnoreCase("add")) {
						if (p.hasPermission("SkyPvP.rewards.taeglich.add")) {
							if (!(p.getItemInHand().getType() == Material.AIR || p.getItemInHand() == null)) {
								List<ItemStack> list = (List<ItemStack>) SkyPvP.getInstance().getRewards().cfg
										.get("taeglich.list");
								list.add(p.getItemInHand());
								SkyPvP.getInstance().getRewards().cfg.set("taeglich.list", list);
								SkyPvP.getInstance().getRewards().saveFile();
								p.sendMessage(Config.getString("rewards.addItem"));
							} else {
								p.sendMessage(Config.getString("rewards.add.no_item_in_hand"));
							}
						} else {
							p.sendMessage(Data.noperm);
						}
					} else if (args[1].equalsIgnoreCase("clear")) {
						if (p.hasPermission("SkyPvP.rewards.taeglich.clear")) {
							List<ItemStack> list = SkyPvP.getInstance().getRewards().getList("taeglich.list");
							list.clear();
							list.add(0, Manager_ItemBuilder.item("Standart-Reward", Material.IRON_INGOT, 0, 1));
							SkyPvP.getInstance().getRewards().cfg.set("taeglich.list", list);
							SkyPvP.getInstance().getRewards().saveFile();
							p.sendMessage(Config.getString("rewards.clearlist"));
						} else {
							p.sendMessage(Data.noperm);
						}
					}
				} else if (args[0].equalsIgnoreCase("woechentlich")) {
					if (args[1].equalsIgnoreCase("add")) {
						if (p.hasPermission("SkyPvP.rewards.woechentlich.add")) {
							if (!(p.getItemInHand().getType() == Material.AIR || p.getItemInHand() == null)) {
								List<ItemStack> list = (List<ItemStack>) SkyPvP.getInstance().getRewards().cfg
										.get("woechentlich.list");
								list.add(p.getItemInHand());
								SkyPvP.getInstance().getRewards().cfg.set("woechentlich.list", list);
								SkyPvP.getInstance().getRewards().saveFile();
								p.sendMessage(Config.getString("rewards.addItem"));
							} else {
								p.sendMessage(Config.getString("rewards.add.no_item_in_hand"));
							}
						} else {
							p.sendMessage(Data.noperm);
						}
					} else if (args[1].equalsIgnoreCase("clear")) {
						if (p.hasPermission("SkyPvP.rewards.woechentlich.clear")) {
							List<ItemStack> list = SkyPvP.getInstance().getRewards().getList("woechentlich.list");
							list.clear();
							list.add(0, Manager_ItemBuilder.item("Standart-Reward", Material.IRON_INGOT, 0, 1));
							SkyPvP.getInstance().getRewards().cfg.set("woechentlich.list", list);
							SkyPvP.getInstance().getRewards().saveFile();
							p.sendMessage(Config.getString("rewards.clearlist"));
						} else {
							p.sendMessage(Data.noperm);
						}
					}
				} else {
					sender.sendMessage(Data.prefix
							+ "§cUse /rewards <Stuendlich,Taeglich,woechentlich> <add,clear,coins> <Coins>");
				}
			} else if (args.length == 3) {
				if (args[0].equalsIgnoreCase("stuendlich")) {
					if (args[1].equalsIgnoreCase("coins")) {
						if (p.hasPermission("SkyPvP.rewards.stuendlich.coins")) {
							int coins = Integer.parseInt(args[2]);
							SkyPvP.getInstance().getRewards().cfg.set("stuendlich.coins.amount", coins);
							SkyPvP.getInstance().getRewards().saveFile();
							p.sendMessage(Config.getString("rewards.updateCoins"));
						} else {
							p.sendMessage(Data.noperm);
						}
					} else {
						sender.sendMessage(Data.prefix
								+ "§cUse /rewards <Stuendlich,Taeglich,woechentlich> <add,clear,coins> <Coins>");
					}
				} else if (args[0].equalsIgnoreCase("taeglich")) {
					if (args[1].equalsIgnoreCase("coins")) {
						if (p.hasPermission("SkyPvP.rewards.taeglich.coins")) {
							int coins = Integer.parseInt(args[2]);
							SkyPvP.getInstance().getRewards().cfg.set("taeglich.coins.amount", coins);
							SkyPvP.getInstance().getRewards().saveFile();
							p.sendMessage(Config.getString("rewards.updateCoins"));
						} else {
							p.sendMessage(Data.noperm);
						}
					} else {
						sender.sendMessage(Data.prefix
								+ "§cUse /rewards <Stuendlich,Taeglich,woechentlich> <add,clear,coins> <Coins>");
					}
				} else if (args[0].equalsIgnoreCase("woechentlich")) {
					if (args[1].equalsIgnoreCase("coins")) {
						if (p.hasPermission("SkyPvP.rewards.woechentlich.coins")) {
							int coins = Integer.parseInt(args[2]);
							SkyPvP.getInstance().getRewards().cfg.set("woechentlich.coins.amount", coins);
							SkyPvP.getInstance().getRewards().saveFile();
							p.sendMessage(Config.getString("rewards.updateCoins"));
						} else {
							p.sendMessage(Data.noperm);
						}
					} else {
						sender.sendMessage(Data.prefix
								+ "§cUse /rewards <Stuendlich,Taeglich,woechentlich> <add,clear,coins> <Coins>");
					}
				}
			} else {
				sender.sendMessage(
						Data.prefix + "§cUse /rewards <Stuendlich,Taeglich,woechentlich> <add,clear,coins> <Coins>");
			}
		} else {
			sender.sendMessage(Data.must_a_player);
		}

		return true;
	}

}
