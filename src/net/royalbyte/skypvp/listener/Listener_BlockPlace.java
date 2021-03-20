package net.royalbyte.skypvp.listener;

import net.royalbyte.skypvp.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;


public class Listener_BlockPlace implements Listener {
    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (p.getWorld().getName().equals("world_skyblock")) {
            e.setCancelled(false);
        }else {
            if(!p.hasPermission("SkyPvP.skyblock.safe.bypass")) {
                e.setCancelled(true);
                p.sendMessage( Data.prefix + "§cDu darfst hier nicht bauen.");
            }else {
                e.setCancelled(false);
            }
        }
    }
    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if(!(e.getEntity() instanceof Player)){
            return;
        }else{
            if(e.getCause().equals(EntityDamageEvent.DamageCause.FALL)){
                e.setCancelled(true);
            }
        }
    }
}
