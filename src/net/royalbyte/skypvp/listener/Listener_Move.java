/*
 * Copyright @RoyalByte 2018
 */

package net.royalbyte.skypvp.listener;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.SkyPvP;
import net.royalbyte.skypvp.utils.SBoard;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Listener_Move implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if(p.getLocation().getY() <= Config.cfg.getInt("instantkill")) {
            p.getInventory().clear();
            p.getInventory().setArmorContents(null);
            p.setHealth(0);
            p.spigot().respawn();
            SBoard.setBoard(p);

        }
    }

}
