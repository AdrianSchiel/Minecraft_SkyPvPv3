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
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.mysql.BanPoints;

public class Command_BanPoints implements CommandExecutor {

    @SuppressWarnings("deprecation")
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(args.length == 1){
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            if(sender.hasPermission("SkyPvP.banpoints.other")){
                sender.sendMessage(Config.getString("Ban.banpointsmsg.other").replaceAll("%BP%",""+ BanPoints.getBP(target.getUniqueId().toString())).replaceAll("%PLAYER%" , target.getName()));
            }else{
                sender.sendMessage(Data.noperm);
            }
        }else{
            if(sender instanceof  Player){
                Player p =(Player) sender;
				p.playSound(p.getLocation(), Sound.CLICK, 1F, 1F);
                p.sendMessage(Config.getString("Ban.banpointsmsg.own").replaceAll("%BP%", "" + BanPoints.getBP(p.getUniqueId().toString())));
            }else{
                sender.sendMessage(Data.must_a_player);
            }
        }
        return true;
    }

}
