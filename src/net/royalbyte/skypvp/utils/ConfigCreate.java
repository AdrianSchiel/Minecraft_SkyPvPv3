/*
 * Copyright @RoyalByte 2018
 */

package net.royalbyte.skypvp.utils;

import java.util.HashMap;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.SkyPvP;
import net.royalbyte.skypvp.manager.Manager_Location;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class ConfigCreate {

    private static HashMap<String,BukkitTask> countdown;

    static {
        countdown = new HashMap<>();
    }

    private static String p;
    private static int count;

    public ConfigCreate() {

        this.count = 5;
    }

    public static void start() {
        if (countdown.containsKey(p)) {
            return;
        }

        BukkitTask task = Bukkit.getScheduler().runTaskTimer(SkyPvP.getInstance(), new Runnable() {
            public void run() {

                if (count == 0) {

                    Bukkit.getConsoleSender().sendMessage(Data.prefix + "§2Die Config wurde erstellt");

                    return;
                }

                count--;

            }
        }, 0, 20 * 5);

        countdown.put(p,task);

    }

    public void stop() {
        if (!countdown.containsKey(p)) {
            return;
        }

        countdown.get(this.p).cancel();
        Bukkit.getConsoleSender().sendMessage(Data.prefix +"§cFehler beim erstellen der Config!");

        countdown.remove(this.p);
    }


}