/*
 * ---------------------------------------
 * Copyright @RoyalByte | Adrian Schiel
 * Youtube : RoyalByte Developer
 * Skype: RoyalByte
 * Website: RoyalByte.net
 * ---------------------------------------
 */

package net.royalbyte.skypvp.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.mysql.Ban;

public class Command_Ban implements CommandExecutor {

    @SuppressWarnings("deprecation")
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 2) {
            OfflinePlayer p = Bukkit.getOfflinePlayer(args[0]);
                if (args[1].equalsIgnoreCase("1") || args[1].equalsIgnoreCase(Config.getString("Ban.one.reason"))) {
                    if (sender.hasPermission("SkyPvP.ban.one")) {
                        Ban.ban(p.getUniqueId().toString(), Config.getString("Ban.one.reason"), sender.getName(), Config.getLong("Ban.one.time"), (Integer) Config.get("Ban.one.banpoints"));
                        banbc(p.getUniqueId().toString());
                        sender.sendMessage(Config.getString("Ban.authormsg").replaceAll("%PLAYER%", p.getName()));
                    } else {
                        sender.sendMessage(Data.noperm);
                    }
                } else if (args[1].equalsIgnoreCase("2") || args[1].equalsIgnoreCase(Config.getString("Ban.two.reason"))) {
                    if (sender.hasPermission("SkyPvP.ban.two")) {
                        Ban.ban(p.getUniqueId().toString(), Config.getString("Ban.two.reason"), sender.getName(), Config.getLong("Ban.two.time"), (Integer) Config.get("Ban.two.banpoints"));
                        banbc(p.getUniqueId().toString());
                        sender.sendMessage(Config.getString("Ban.authormsg").replaceAll("%PLAYER%", p.getName()));
                    } else {
                        sender.sendMessage(Data.noperm);
                    }

                } else if (args[1].equalsIgnoreCase("3") || args[1].equalsIgnoreCase(Config.getString("Ban.three.reason"))) {
                    if (sender.hasPermission("SkyPvP.ban.three")) {
                        Ban.ban(p.getUniqueId().toString(), Config.getString("Ban.three.reason"), sender.getName(), Config.getLong("Ban.three.time"), (Integer) Config.get("Ban.three.banpoints"));
                        banbc(p.getUniqueId().toString());
                        sender.sendMessage(Config.getString("Ban.authormsg").replaceAll("%PLAYER%", p.getName()));
                    } else {
                        sender.sendMessage(Data.noperm);
                    }
                } else if (args[1].equalsIgnoreCase("4") || args[1].equalsIgnoreCase(Config.getString("Ban.four.reason"))) {
                    if (sender.hasPermission("SkyPvP.ban.four")) {
                        Ban.ban(p.getUniqueId().toString(), Config.getString("Ban.four.reason"), sender.getName(), Config.getLong("Ban.four.time"), (Integer) Config.get("Ban.four.banpoints"));
                        banbc(p.getUniqueId().toString());
                        sender.sendMessage(Config.getString("Ban.authormsg").replaceAll("%PLAYER%", p.getName()));
                    } else {
                        sender.sendMessage(Data.noperm);
                    }
                } else if (args[1].equalsIgnoreCase("5") || args[1].equalsIgnoreCase(Config.getString("Ban.five.reason"))) {
                    if (sender.hasPermission("SkyPvP.ban.five")) {
                        Ban.ban(p.getUniqueId().toString(), Config.getString("Ban.five.reason"), sender.getName(), Config.getLong("Ban.five.time"), (Integer) Config.get("Ban.five.banpoints"));
                        banbc(p.getUniqueId().toString());
                        sender.sendMessage(Config.getString("Ban.authormsg").replaceAll("%PLAYER%", p.getName()));
                    } else {
                        sender.sendMessage(Data.noperm);
                    }
                } else if (args[1].equalsIgnoreCase("6") || args[1].equalsIgnoreCase(Config.getString("Ban.six.reason"))) {
                    if (sender.hasPermission("SkyPvP.ban.six")) {
                        Ban.ban(p.getUniqueId().toString(), Config.getString("Ban.six.reason"), sender.getName(), Config.getLong("Ban.six.time"), (Integer) Config.get("Ban.six.banpoints"));
                        banbc(p.getUniqueId().toString());
                        sender.sendMessage(Config.getString("Ban.authormsg").replaceAll("%PLAYER%", p.getName()));
                    } else {
                        sender.sendMessage(Data.noperm);
                    }
                } else if (args[1].equalsIgnoreCase("7") || args[1].equalsIgnoreCase(Config.getString("Ban.seven.reason"))) {
                    if (sender.hasPermission("SkyPvP.ban.seven")) {
                        Ban.ban(p.getUniqueId().toString(), Config.getString("Ban.seven.reason"), sender.getName(), Config.getLong("Ban.seven.time"), (Integer) Config.get("Ban.seven.banpoints"));
                        banbc(p.getUniqueId().toString());
                        sender.sendMessage(Config.getString("Ban.authormsg").replaceAll("%PLAYER%", p.getName()));
                    } else {
                        sender.sendMessage(Data.noperm);
                    }
                } else if (args[1].equalsIgnoreCase("8") || args[1].equalsIgnoreCase(Config.getString("Ban.eight.reason"))) {
                    if (sender.hasPermission("SkyPvP.ban.eight")) {
                        Ban.ban(p.getUniqueId().toString(), Config.getString("Ban.eight.reason"), sender.getName(), Config.getLong("Ban.eight.time"), (Integer) Config.get("Ban.eight.banpoints"));
                        banbc(p.getUniqueId().toString());
                        sender.sendMessage(Config.getString("Ban.authormsg").replaceAll("%PLAYER%", p.getName()));
                    } else {
                        sender.sendMessage(Data.noperm);
                    }
                } else if (args[1].equalsIgnoreCase("9") || args[1].equalsIgnoreCase(Config.getString("Ban.nine.reason"))) {
                    if (sender.hasPermission("SkyPvP.ban.nine")) {
                        Ban.ban(p.getUniqueId().toString(), Config.getString("Ban.nine.reason"), sender.getName(), Config.getLong("Ban.nine.time"), (Integer) Config.get("Ban.nine.banpoints"));
                        banbc(p.getUniqueId().toString());
                        sender.sendMessage(Config.getString("Ban.authormsg").replaceAll("%PLAYER%", p.getName()));
                    } else {
                        sender.sendMessage(Data.noperm);
                    }
                } else if (args[1].equalsIgnoreCase("10") || args[1].equalsIgnoreCase(Config.getString("Ban.ten.reason"))) {
                    if (sender.hasPermission("SkyPvP.ban.ten")) {
                        Ban.ban(p.getUniqueId().toString(), Config.getString("Ban.ten.reason"), sender.getName(), Config.getLong("Ban.ten.time"), (Integer) Config.get("Ban.ten.banpoints"));
                        banbc(p.getUniqueId().toString());
                        sender.sendMessage(Config.getString("Ban.authormsg").replaceAll("%PLAYER%", p.getName()));
                    } else {
                        sender.sendMessage(Data.noperm);
                    }
                } else {
                    sendhelp(sender);
                }

        }else{
            sendhelp(sender);
        }
        return true;
    }
    public void sendhelp(CommandSender sender){
        sender.sendMessage(Config.getString("Ban.help.line-1"));
        sender.sendMessage(Config.getString("Ban.help.line-2"));
        sender.sendMessage(Config.getString("Ban.help.line-3"));
        sender.sendMessage(Config.getString("Ban.help.line-4"));
        sender.sendMessage(Config.getString("Ban.help.line-5"));
        sender.sendMessage(Config.getString("Ban.help.line-6"));
        sender.sendMessage(Config.getString("Ban.help.line-7"));
        sender.sendMessage(Config.getString("Ban.help.line-8"));
        sender.sendMessage(Config.getString("Ban.help.line-9"));
        sender.sendMessage(Config.getString("Ban.help.line-10"));
        sender.sendMessage(Config.getString("Ban.help.line-11"));
        sender.sendMessage(Config.getString("Ban.help.line-12"));
        sender.sendMessage(Config.getString("Ban.help.line-13"));
        sender.sendMessage(Config.getString("Ban.help.line-14"));
        sender.sendMessage(Config.getString("Ban.help.line-15"));
    }
    public void banbc(String uuid){
        Bukkit.getOnlinePlayers().forEach((player) ->{
            if(player.hasPermission("SkyPvP.ban.notify")){
                player.sendMessage(Config.getString("Ban.line-1").replaceAll("%PLAYER%" ,Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName()).replaceAll("%AUTHOR%" , Ban.getAuthor(uuid)).replaceAll("%REASON%", Ban.getReason(uuid)).replaceAll("%BANNEDBIS%", Ban.getEndDate(uuid)));
                player.sendMessage(Config.getString("Ban.line-2").replaceAll("%PLAYER%" ,Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName()).replaceAll("%AUTHOR%" , Ban.getAuthor(uuid)).replaceAll("%REASON%", Ban.getReason(uuid)).replaceAll("%BANNEDBIS%", Ban.getEndDate(uuid)));
                player.sendMessage(Config.getString("Ban.line-3").replaceAll("%PLAYER%" ,Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName()).replaceAll("%AUTHOR%" , Ban.getAuthor(uuid)).replaceAll("%REASON%", Ban.getReason(uuid)).replaceAll("%BANNEDBIS%", Ban.getEndDate(uuid)));
                player.sendMessage(Config.getString("Ban.line-4").replaceAll("%PLAYER%" ,Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName()).replaceAll("%AUTHOR%" , Ban.getAuthor(uuid)).replaceAll("%REASON%", Ban.getReason(uuid)).replaceAll("%BANNEDBIS%", Ban.getEndDate(uuid)));
            }
        });
    }
}
