/*
 * Copyright @RoyalByte 2018
 */

package net.royalbyte.skypvp.listener;

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

public class Spawn {

    private static HashMap<Player, BukkitTask> countdown;

    static {
        countdown = new HashMap<>();
    }

    private Player p;
    private int count;

    public Spawn(Player p) {
        this.p = p;
        this.count = 5;
    }

    public void start() {
        if (countdown.containsKey(p)) {
            return;
        }

        BukkitTask task = Bukkit.getScheduler().runTaskTimer(SkyPvP.getInstance(), new Runnable() {
            public void run() {

                sendBar(count);

                if (count == 0) {
                    Manager_Location.tpLocation(p, "spawn");
                    p.sendMessage(Data.prefix + "§aDu wurdest zum Spawn teleportiert");
                    countdown.get(p).cancel();
                    countdown.remove(p);
                    return;
                }

                count--;

            }
        }, 0, 20);

        countdown.put(this.p, task);

    }

    public void stop() {
        if (!countdown.containsKey(p)) {
            return;
        }

        countdown.get(this.p).cancel();
        this.p.sendMessage(Data.prefix +"§cTeleportation abgebrochen!");
        this.sendBar(this.p, "§8[§cXXXXX§8] §e§lX");
        countdown.remove(this.p);
    }

    private void sendBar(int count) {
        String s = "§8< ";

        for (int i = 0; i < count; i++) {
            s = s + "§a█";
        }

        for (int i = 0; i < (5 - count); i++) {
            s = s + "§7█";
        }

        s = s + "§8 > §2§l" + count;
        this.sendBar(this.p, s);
    }

    private void sendBar(Player p, String msg) {
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + msg + "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte) 2);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(bar);
    }

}