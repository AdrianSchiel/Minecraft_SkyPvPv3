/*
 * Copyright @RoyalByte 2018
 */

package net.royalbyte.skypvp.timer;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.SkyPvP;
import net.royalbyte.skypvp.clearlag.Lag;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ClearLag {

    public static int task;

    public ClearLag() {
    }

    public static void clearLag() {

        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyPvP.getPlugin(SkyPvP.class), new Runnable() {
            int i =0;
            @Override
            public void run() {

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



                }
                i=0;
            }

        }, 20L,  20 * Config.getInt("ClearLag.Time") );

    }

}