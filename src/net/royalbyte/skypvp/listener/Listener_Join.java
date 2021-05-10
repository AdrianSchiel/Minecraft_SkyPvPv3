package net.royalbyte.skypvp.listener;

import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.mysql.Ban;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.SkyPvP;
import net.royalbyte.skypvp.commands.Command_Vanish;
import net.royalbyte.skypvp.mysql.Datas;
import net.royalbyte.skypvp.mysql.Rewards;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Listener_Join implements Listener {

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        //DATAS REGISTRIERUNG
        if (Datas.getfirstjoin(p.getUniqueId().toString()) == 0) {
            long joined = System.currentTimeMillis();
            Datas.setfirstjoin(p.getUniqueId().toString(), joined);
        }

        // CHATCLEAR
        if (Config.getBoolean("join.chatclear")) {
            for (int i = 0; i < 128; i++) {
                p.sendMessage(" ");
            }
        }
        //TAG
        SkyPvP.getInstance().getRang().setprefix(p);
        //REWARDS
        Rewards.addPlayer(p.getUniqueId().toString(), p.getName());

        //VANISH
        Command_Vanish.vanish.forEach(player -> {
            p.hidePlayer(player);
        });

        //Tablist-Scoreboard
        new BukkitRunnable() {

            @Override
            public void run() {
                SkyPvP.getInstance().getScoreboard().setBoard(p);
                if (Config.getBoolean("tablist.enabled").equals(true)) {
                    SkyPvP.getInstance().getTablist().send(p);
                } else {
                }
            }
        }.runTaskLaterAsynchronously(SkyPvP.getInstance(), 3);

        if (Config.getBoolean("tablist.enabled").equals(true)) {
            SkyPvP.getInstance().getTablist().send(p);
        } else {
        }

        p.sendTitle(Config.getString("join.title.header"), Config.getString("join.title.footer"));

        if (!p.hasPlayedBefore()) {
            SkyPvP.getInstance().getCounts().addPlayers(1);
            Bukkit.broadcastMessage(Config.getString("join.newplayer").replaceAll("%PLAYER%", p.getDisplayName())
                    .replaceAll("%ID%", "" + SkyPvP.getInstance().getCounts().getPlayers()));
        }
        if (p.hasPermission("SkyPvP.team")) {
            e.setJoinMessage(Config.getString("join.teammsg").replaceAll("%PLAYER%", p.getDisplayName()));
        } else {
            e.setJoinMessage(null);
        }


    }

}
