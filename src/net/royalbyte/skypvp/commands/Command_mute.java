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
import net.royalbyte.skypvp.mysql.Mute;

public class Command_mute implements CommandExecutor {

    @SuppressWarnings("deprecation")
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 2) {
            OfflinePlayer p = Bukkit.getOfflinePlayer(args[0]);
                if (args[1].equalsIgnoreCase("1") || args[1].equalsIgnoreCase(Config.getString("Mute.one.reason"))) {
                    if (sender.hasPermission("SkyPvP.mute.one")) {
                    	Mute.mute(p.getUniqueId().toString(), Config.getString("Mute.one.reason"), sender.getName(), Config.getLong("Mute.one.time"), (Integer) Config.get("Mute.one.banpoints"));
                        banbc(p.getUniqueId().toString());
                        sender.sendMessage(Config.getString("Mute.authormsg").replaceAll("%PLAYER%", p.getName()));
                    } else {
                        sender.sendMessage(Data.noperm);
                    }
                } else if (args[1].equalsIgnoreCase("2") || args[1].equalsIgnoreCase(Config.getString("Mute.two.reason"))) {
                    if (sender.hasPermission("SkyPvP.mute.two")) {
                    	Mute.mute(p.getUniqueId().toString(), Config.getString("Mute.two.reason"), sender.getName(), Config.getLong("Mute.two.time"), (Integer) Config.get("Mute.two.banpoints"));
                        banbc(p.getUniqueId().toString());
                        sender.sendMessage(Config.getString("Mute.authormsg").replaceAll("%PLAYER%", p.getName()));
                    } else {
                        sender.sendMessage(Data.noperm);
                    }

                } else if (args[1].equalsIgnoreCase("3") || args[1].equalsIgnoreCase(Config.getString("Mute.three.reason"))) {
                    if (sender.hasPermission("SkyPvP.mute.three")) {
                    	Mute.mute(p.getUniqueId().toString(), Config.getString("Mute.three.reason"), sender.getName(), Config.getLong("Mute.three.time"), (Integer) Config.get("Mute.three.banpoints"));
                        banbc(p.getUniqueId().toString());
                        sender.sendMessage(Config.getString("Mute.authormsg").replaceAll("%PLAYER%", p.getName()));
                    } else {
                        sender.sendMessage(Data.noperm);
                    }
                } else if (args[1].equalsIgnoreCase("4") || args[1].equalsIgnoreCase(Config.getString("Mute.four.reason"))) {
                    if (sender.hasPermission("SkyPvP.mute.four")) {
                    	Mute.mute(p.getUniqueId().toString(), Config.getString("Mute.four.reason"), sender.getName(), Config.getLong("Mute.four.time"), (Integer) Config.get("Mute.four.banpoints"));
                        banbc(p.getUniqueId().toString());
                        sender.sendMessage(Config.getString("Mute.authormsg").replaceAll("%PLAYER%", p.getName()));
                    } else {
                        sender.sendMessage(Data.noperm);
                    }
                } else if (args[1].equalsIgnoreCase("5") || args[1].equalsIgnoreCase(Config.getString("Mute.five.reason"))) {
                    if (sender.hasPermission("SkyPvP.mute.five")) {
                        Mute.mute(p.getUniqueId().toString(), Config.getString("Mute.five.reason"), sender.getName(), Config.getLong("Mute.five.time"), (Integer) Config.get("Mute.five.banpoints"));
                        banbc(p.getUniqueId().toString());
                        sender.sendMessage(Config.getString("Mute.authormsg").replaceAll("%PLAYER%", p.getName()));
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
        sender.sendMessage(Config.getString("Mute.help.line-1"));
        sender.sendMessage(Config.getString("Mute.help.line-2"));
        sender.sendMessage(Config.getString("Mute.help.line-3"));
        sender.sendMessage(Config.getString("Mute.help.line-4"));
        sender.sendMessage(Config.getString("Mute.help.line-5"));
        sender.sendMessage(Config.getString("Mute.help.line-6"));
        sender.sendMessage(Config.getString("Mute.help.line-7"));
        sender.sendMessage(Config.getString("Mute.help.line-8"));
        sender.sendMessage(Config.getString("Mute.help.line-9"));
        sender.sendMessage(Config.getString("Mute.help.line-10"));
    }
    public void banbc(String uuid){
        Bukkit.getOnlinePlayers().forEach((player) ->{
            if(player.hasPermission("SkyPvP.Mute.notify")){
                player.sendMessage(Config.getString("Mute.line-1").replaceAll("%PLAYER%" ,Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName()).replaceAll("%AUTHOR%" , Mute.getAuthor(uuid)).replaceAll("%REASON%", Mute.getReason(uuid)).replaceAll("%BANNEDBIS%", Mute.getEndDate(uuid)));
                player.sendMessage(Config.getString("Mute.line-2").replaceAll("%PLAYER%" ,Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName()).replaceAll("%AUTHOR%" , Mute.getAuthor(uuid)).replaceAll("%REASON%", Mute.getReason(uuid)).replaceAll("%BANNEDBIS%", Mute.getEndDate(uuid)));
                player.sendMessage(Config.getString("Mute.line-3").replaceAll("%PLAYER%" ,Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName()).replaceAll("%AUTHOR%" , Mute.getAuthor(uuid)).replaceAll("%REASON%", Mute.getReason(uuid)).replaceAll("%BANNEDBIS%", Mute.getEndDate(uuid)));
                player.sendMessage(Config.getString("Mute.line-4").replaceAll("%PLAYER%" ,Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName()).replaceAll("%AUTHOR%" , Mute.getAuthor(uuid)).replaceAll("%REASON%", Mute.getReason(uuid)).replaceAll("%BANNEDBIS%", Mute.getEndDate(uuid)));
            }
        });
    }
}
