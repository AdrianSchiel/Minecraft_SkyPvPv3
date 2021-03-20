package net.royalbyte.skypvp.listener;

import net.royalbyte.skypvp.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;


public class Listener_BlockBreak implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (p.getWorld().getName().equals("world_skyblock")) {
            e.setCancelled(false);
        }else {
            if(!p.hasPermission("SkyPvP.skyblock.safe.bypass")) {
                e.setCancelled(true);
                p.sendMessage( Data.prefix + "§cDu darfst nichts  abbauen");
            }else {
                e.setCancelled(false);
            }
        }
    }


}
