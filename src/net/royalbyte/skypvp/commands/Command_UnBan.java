/*
 * ---------------------------------------
 * Copyright @RoyalByte | Adrian Schiel
 * Youtube : RoyalByte Developer
 * Skype: RoyalByte
 * Website: RoyalByte.net
 * ---------------------------------------
 */

package net.royalbyte.skypvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.mysql.Ban;
import net.royalbyte.skypvp.mysql.BanPoints;

public class Command_UnBan implements CommandExecutor {

    @SuppressWarnings("deprecation")
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length ==1) {
            OfflinePlayer p = Bukkit.getOfflinePlayer(args[0]);
            if (sender.hasPermission("SkyPvP.unban")) {
                if(Ban.isBanned(p.getUniqueId().toString())){
                    Ban.unban(p.getUniqueId().toString());
                    if(Config.getBoolean("UnBan.banpoints.remove")) {
                    	BanPoints.setBP(p, (BanPoints.getBP(p.getUniqueId().toString()) - (Integer)Config.get("UnBan.banpoints.size")));
                    }
                    Bukkit.getOnlinePlayers().forEach((player)->{
                        if(player.hasPermission("SkyPvP.unban.notify")){
                            player.sendMessage(Config.getString("UnBan.line-1").replaceAll("%PLAYER%", p.getName()).replaceAll("%AUTHOR%", sender.getName()));
                            player.sendMessage(Config.getString("UnBan.line-2").replaceAll("%PLAYER%", p.getName()).replaceAll("%AUTHOR%", sender.getName()));
                            player.sendMessage(Config.getString("UnBan.line-3").replaceAll("%PLAYER%", p.getName()).replaceAll("%AUTHOR%", sender.getName()));
                        }
                    });
                    sender.sendMessage(Config.getString("UnBan.authormsg").replaceAll("%PLAYER%", p.getName()));
                }else{
                    sender.sendMessage(Config.getString("UnBan.notbanned"));
                }
            } else {
                sender.sendMessage(Data.noperm);
            }
        }else {
        	sender.sendMessage(Data.prefix + "§cUse /unban <Player>");
        }
        return true;
    }

}
