/*
 * Copyright @RoyalByte 2018
 */

package net.royalbyte.skypvp.commands;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Repair implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length == 0) {
                if (p.hasPermission("SkyPvP.repair")) {
                    p.getItemInHand()
                            .setDurability((short) -1);
                    p.sendMessage(Config.getString("command.repair.own"));
                } else {
                    p.sendMessage(Data.noperm);
                }

            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("all")) {
                    if (p.hasPermission("SkyPvP.repair")) {
                        for (int i = 0; i < p.getInventory().getSize(); i++) {
                            if(p.getInventory().getItem(i) == null || p.getInventory().getItem(i).getDurability() == 0) {

                            }else
                            if(!(p.getInventory().getItem(i).getDurability() == 0)){
                                p.getInventory().getItem(i).setDurability((short) -1);
                            }
                        }
                        p.updateInventory();
                        p.sendMessage(Config.getString("command.repair.other"));
                    } else {
                        p.sendMessage(Data.noperm);
                    }
                } else {
                    p.sendMessage(Data.prefix + "§cUse /repair <all>");
                }
            }
        } else {
            sender.sendMessage(Data.must_a_player);
        }
        return false;
    }
}
