package net.royalbyte.skypvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.mysql.BanPoints;
import net.royalbyte.skypvp.mysql.Mute;

public class Command_unmute implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length == 1) {
            OfflinePlayer p = Bukkit.getOfflinePlayer(args[0]);
            if (sender.hasPermission("SkyPvP.unmute")) {
                if(Mute.isMuted(p.getUniqueId().toString())){
                    Mute.unban(p.getUniqueId().toString());
                    
                    if(Config.getBoolean("Unmute.banpoints.remove")) {
                    	BanPoints.setBP(p, (BanPoints.getBP(p.getUniqueId().toString()) - (Integer)Config.get("Unmute.banpoints.size")));
                    }
                    
                    Bukkit.getOnlinePlayers().forEach((player)->{
                        if(player.hasPermission("SkyPvP.unmute.notify")){
                            player.sendMessage(Config.getString("Unmute.line-1").replaceAll("%PLAYER%", p.getName()).replaceAll("%AUTHOR%", sender.getName()));
                            player.sendMessage(Config.getString("Unmute.line-2").replaceAll("%PLAYER%", p.getName()).replaceAll("%AUTHOR%", sender.getName()));
                            player.sendMessage(Config.getString("Unmute.line-3").replaceAll("%PLAYER%", p.getName()).replaceAll("%AUTHOR%", sender.getName()));
                        }
                    });
                    sender.sendMessage(Config.getString("Unmute.authormsg").replaceAll("%PLAYER%", p.getName()));
                }else{
                    sender.sendMessage(Config.getString("Unmute.notbanned"));
                }
            } else {
                sender.sendMessage(Data.noperm);
            }
			
		}else {
        	sender.sendMessage(Data.prefix + "§cUse /unmute <Player>");
		}
		
		return true;
	}

}
