/*
 * ---------------------------------------
 * Copyright @RoyalByte | Adrian Schiel
 * Youtube : RoyalByte Developer
 * Skype: RoyalByte
 * Website: RoyalByte.net
 * ---------------------------------------
 */

package net.royalbyte.skypvp.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;

public class Command_Fly implements CommandExecutor {

    public static List<Player> fly = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(args.length == 0){
                if (p.hasPermission("SkyPvP.fly")) {

                    if (fly.contains(p)) {
                        fly.remove(p);
                        p.setAllowFlight(false);
                        p.setFlying(false);
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
                        p.sendMessage(Config.getString("Command.fly").replaceAll("%MODE%", "OFF"));
                    } else {
                        fly.add(p);
                        p.setAllowFlight(true);
                        p.setFlying(true);
                        p.sendMessage(Config.getString("Command.fly").replaceAll("%MODE%", "ON"));
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
                    }

                } else {
                    p.sendMessage(Data.noperm);
    				p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
                }
            }else if(args.length==1){
                Player t = Bukkit.getPlayer(args[0]);
                if (p.hasPermission("SkyPvP.fly.other")) {

                    if (t != null) {
                        if (fly.contains(t)) {
                            fly.remove(t);
                            t.setAllowFlight(false);
                            t.setFlying(false);
                            t.sendMessage(Config.getString("Command.fly").replaceAll("%MODE%", "OFF"));
                            t.playSound(t.getLocation(), Sound.LEVEL_UP, 10F, 10F);
                        } else {
                            fly.add(t);
                            t.setAllowFlight(true);
                            t.setFlying(true);
                            t.sendMessage(Config.getString("Command.fly").replaceAll("%MODE%", "ON"));
                            t.playSound(t.getLocation(), Sound.LEVEL_UP, 10F, 10F);
                        }
                    } else {
                        p.sendMessage(Data.player_not_online);
                    }

                } else {
                    p.sendMessage(Data.noperm);
    				p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
                }
            }


        } else {
            sender.sendMessage(Data.must_a_player);
            return true;
        }


        return true;
    }

}
