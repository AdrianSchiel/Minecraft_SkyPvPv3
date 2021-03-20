/*
 * ---------------------------------------
 * Copyright @RoyalByte | Adrian Schiel
 * Youtube : RoyalByte Developer
 * Skype: RoyalByte
 * Website: RoyalByte.net
 * ---------------------------------------
 */

package net.royalbyte.skypvp.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.SkyPvP;
import net.royalbyte.skypvp.mysql.Ban;
import net.royalbyte.skypvp.mysql.BanPoints;
import net.royalbyte.skypvp.mysql.Datas;
import net.royalbyte.skypvp.mysql.Stats;

public class Listener_Login implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent e){
        Player p = e.getPlayer();
        Stats.addPlayer(p.getUniqueId().toString(), p.getName());
        Datas.addPlayer(p.getUniqueId().toString(), p.getName());
        BanPoints.addPlayer(p.getUniqueId().toString());

        if(BanPoints.getBP(p.getUniqueId().toString()) >= (Integer)Config.get("banpoints.max")) {
        	e.disallow(Result.KICK_BANNED, "");
        	Ban.ban(p.getUniqueId().toString(), Config.getString("banpoints.banreason"), "SERVER", Config.getLong("banpoints.time"), 0);
        	
        }
        
        if(Ban.isBanned(p.getUniqueId().toString())){
            long cuurent = System.currentTimeMillis();
            long banned_bis = Ban.getEnd(p.getUniqueId().toString());
            if(!(cuurent >= banned_bis) || Ban.getEnd(p.getUniqueId().toString()) == -1) {
                e.disallow(PlayerLoginEvent.Result.KICK_BANNED, Ban.getBanMSG(p.getUniqueId().toString()));
            }else{
                Ban.unban(p.getUniqueId().toString());
            }
        }
        
        if(SkyPvP.getInstance().getWartung().isWartung()) {
        	if(!p.hasPermission("SkyPvP.wartung.bypass")) {
			e.disallow(Result.KICK_OTHER, Config.getString("wartung.kick"));	
        	}
        }

    }
}
