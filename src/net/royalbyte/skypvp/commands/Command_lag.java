/*
 * Copyright @RoyalByte 2018
 */

package net.royalbyte.skypvp.commands;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.clearlag.Lag;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

import java.text.DecimalFormat;

public class Command_lag implements CommandExecutor {




    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        int i =0;
        if(label.equalsIgnoreCase("clearlag")){
            if(sender.hasPermission("skypvp.clearlag")){
                if(args.length == 0){
                    for (World world : Bukkit.getWorlds()){
                        for(Entity entity : world.getEntities()) {
                            if(entity instanceof Item || entity instanceof Animals || entity instanceof Monster){
                                if (entity instanceof Zombie) {
                                    Zombie v = (Zombie) entity;
                                    if (v.getType().equals(EntityType.ZOMBIE)) {

                                    }
                                } else if (entity instanceof IronGolem) {
                                    IronGolem g = (IronGolem) entity;
                                    if (g.getType().equals(EntityType.IRON_GOLEM)) {

                                    }
                                } else if (entity instanceof Snowman) {
                                    Snowman s = (Snowman) entity;
                                    if (s.getType().equals(EntityType.SNOWMAN)) {

                                    }

                                } else if (entity instanceof Blaze) {
                                    Blaze s = (Blaze) entity;
                                    if (s.getType().equals(EntityType.BLAZE)) {

                                    }
                                }else {
                                    i++;
                                    entity.remove();

                                }

                            }
                        }
                    }
                    for(Player all : Bukkit.getOnlinePlayers()){

                        all.sendMessage("");
                        all.sendMessage(Data.prefix + Config.getString("ClearLag.MSG-1"));
                        all.sendMessage(Data.prefix +  Config.getString("ClearLag.MSG-2").replaceAll("%ENTITYS%" ,""+i));
                        all.sendMessage("");
                        i=0;

                    }

                }}}


        if(label.equalsIgnoreCase("lag")){
            if(sender.hasPermission("skypvp.clearlag")){
                if(args.length == 0){
                    double TPS = Lag.getTPS();
                    DecimalFormat TpsFormat = new DecimalFormat("#.##");

                    if(TPS > 20){
                        sender.sendMessage(Data.prefix + "§aTPS : " + TpsFormat.format(TPS));
                        return true;
                    }

                    if(TPS > 19){
                        sender.sendMessage(Data.prefix +"§aTPS : " + TpsFormat.format(TPS));
                        return true;
                    }

                    if(TPS > 14){
                        sender.sendMessage(Data.prefix +"§eTPS : " + TpsFormat.format(TPS));
                        return true;
                    }

                    if(TPS > 9){
                        sender.sendMessage(Data.prefix +"§cTPS : " + TpsFormat.format(TPS));
                        return true;
                    }

                    if(TPS < 9){
                        sender.sendMessage(Data.prefix +"§4TPS : " + TpsFormat.format(TPS));
                        return true;
                    }

                }
            }

        }
        return false;
    }

}
