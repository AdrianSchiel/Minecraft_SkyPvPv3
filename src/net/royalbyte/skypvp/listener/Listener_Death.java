package net.royalbyte.skypvp.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.SkyPvP;
import net.royalbyte.skypvp.commands.Command_night;
import net.royalbyte.skypvp.manager.Manager_Location;
import net.royalbyte.skypvp.mysql.Stats;
import net.royalbyte.skypvp.utils.SBoard;
import net.royalbyte.skypvp.utils.Tablist;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;


public class Listener_Death implements Listener {

    Map<UUID, Integer> killstreak = new HashMap<>();

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        try {
            e.setDeathMessage(null);
            Player p = e.getEntity();
            Player t = p.getKiller();

            new BukkitRunnable() {

                @Override
                public void run() {
                    Manager_Location.tpLocation(p, "spawn");
                }
            }.runTaskLaterAsynchronously(SkyPvP.getInstance(), 5);

            if (t == null) {
                p.sendMessage(Data.prefix + "§6Du bist gestorben");
                Stats.addTode(p.getUniqueId().toString(), 1);

                if (!(Stats.getPoints(p.getUniqueId().toString()) <= 5)) {

                    Stats.removePoints(p.getUniqueId().toString(), 5);
                }


                SBoard.setBoard(t);
                SBoard.setBoard(p);
                Stats.addPoints(t.getUniqueId().toString(), 10);
                return;
            }
            if (t != null) {

                double health = t.getHealth();
                health = Math.round(health * 10) / 10 + 0.25;
                health = health / 2;
                health = health - 0.125;
                p.sendMessage(Data.prefix + "§6Du wurdest von §3" + t.getDisplayName() + "§6 getötet. §8[§3" + health
                        + "§c❤§8]");
                t.sendMessage(Data.prefix + "§6Du hast §3" + p.getDisplayName() + "§6 getötet.");
                p.sendTitle("§c§lx", "§6" + t.getDisplayName());
                Stats.addTode(p.getUniqueId().toString(), 1);
                if (!(Stats.getPoints(p.getUniqueId().toString()) <= 5)) {

                    Stats.removePoints(p.getUniqueId().toString(), 5);
                }

                Stats.addKills(t.getUniqueId().toString(), 1);
                    Stats.addCoins(t.getUniqueId().toString(), Config.getInt("Death.Coins"));


                Stats.addPoints(t.getUniqueId().toString(), 10);

                SBoard.setBoard(t);
                SBoard.setBoard(p);

                if (!killstreak.containsKey(t.getUniqueId())) {
                    killstreak.put(t.getUniqueId(), 1);
                } else {
                    int current = killstreak.get(t.getUniqueId());
                    killstreak.put(t.getUniqueId(), current + 1);
                }
                if (killstreak.get(t.getUniqueId()).equals(5) || killstreak.get(t.getUniqueId()).equals(10)
                        || killstreak.get(t.getUniqueId()).equals(15) || killstreak.get(t.getUniqueId()).equals(20)
                        || killstreak.get(t.getUniqueId()).equals(25)) {
                    int cuurent = killstreak.get(t.getUniqueId());
                    Stats.addCoins(t.getUniqueId().toString(), cuurent);
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        all.sendMessage(Data.prefix + "§3" + t.getName() + "§6 hat eine §3KillStreak §6von §3" + cuurent
                                + " Kills§6.");
                    }
                    t.sendMessage(Data.prefix + "§6Da du eine §3KillStreak §6von §3" + cuurent + "§6 bekommst du §3"
                            + cuurent + " Coins§6.");
                }

                if (killstreak.get(p.getUniqueId()).equals(5) || killstreak.get(p.getUniqueId()).equals(10)
                        || killstreak.get(p.getUniqueId()).equals(15) || killstreak.get(p.getUniqueId()).equals(20)
                        || killstreak.get(p.getUniqueId()).equals(25)) {
                    int cuurent = killstreak.get(p.getUniqueId());
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        all.sendMessage(Data.prefix + "§3" + t.getName() + "§6 hat die §3KillStreak §6von §3"
                                + p.getName() + "§6 gebrochen. §7[§3" + cuurent + "§7]");
                    }
                }
                if (killstreak.containsKey(p.getUniqueId())) {
                    killstreak.remove(p.getUniqueId());
                }

            }
            if(Config.getBoolean("tablist.enabled").equals(true)){
                Tablist.send(p);
                Tablist.send(t);
            }else{ }


        } catch (Exception e2) {
        }
    }

}
