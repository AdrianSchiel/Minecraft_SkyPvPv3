package net.royalbyte.skypvp.listener;

import net.royalbyte.skypvp.timer.kitdelay;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowman;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.SkyPvP;
import net.royalbyte.skypvp.commands.Command_mobs;


public class Listener_Mobs implements Listener {

	@EventHandler
	public void onDMG2(EntityDamageEvent e) {
		try {
		if (!e.getCause().equals(DamageCause.ENTITY_ATTACK)) {

			if (e.getEntity() instanceof Zombie) {
				Zombie v = (Zombie) e.getEntity();
				if (v.getCustomName().equalsIgnoreCase(Config.getString("Mob.name.Shop"))) {
					e.setCancelled(true);
				}
			} else if (e.getEntity() instanceof IronGolem) {
				IronGolem g = (IronGolem) e.getEntity();
				if (g.getCustomName().equalsIgnoreCase(Config.getString("Mob.name.Rewards"))) {
					e.setCancelled(true);
				}
			} else if (e.getEntity() instanceof Snowman) {
				Snowman s = (Snowman) e.getEntity();
				if (s.getCustomName().equalsIgnoreCase(Config.getString("Mob.name.Kits"))) {
					e.setCancelled(true);
				}

			} else if (e.getEntity() instanceof Blaze) {
				Blaze s = (Blaze) e.getEntity();
				if (s.getCustomName().equalsIgnoreCase(Config.getString("Mob.name.cookie"))) {
					e.setCancelled(true);
				}
			}
		}
		}catch (Exception ex){

		}
	}
	@EventHandler
	public void onInterract(PlayerInteractAtEntityEvent e) {
		try {
		Player p = e.getPlayer();
		if(e.getRightClicked() instanceof Zombie) {
			Zombie v = (Zombie) e.getRightClicked();
			
			if(v.getCustomName().equalsIgnoreCase(Config.getString("Mob.name.Shop"))) {
				e.setCancelled(true);
				SkyPvP.getInstance().getShop().openMainInv(p);
				return;
			}else {
				
			}
		}else if(e.getRightClicked() instanceof IronGolem) {
			IronGolem g = (IronGolem) e.getRightClicked();
			if(g.getCustomName().equalsIgnoreCase(Config.getString("Mob.name.Rewards"))) {
				SkyPvP.getInstance().getInventories().openRewards(p);
			}
		}else if(e.getRightClicked() instanceof Snowman) {
				Snowman s = (Snowman) e.getRightClicked();
						p.chat("/kits");
		}else if(e.getRightClicked() instanceof Blaze) {
			Blaze s = (Blaze) e.getRightClicked();
			if(s.getCustomName().equalsIgnoreCase(Config.getString("Mob.name.cookie"))) {
				SkyPvP.getInstance().getInventories().openCookieShop(p);
			}
		}
	} catch (Exception e2) {
		// TODO: handle exception
	
	}
	}
	@EventHandler
	public void onDMG(EntityDamageByEntityEvent e) {
		try {
			if (e.getEntity() instanceof Zombie) {
				Zombie v = (Zombie) e.getEntity();

				if (v.getCustomName().equalsIgnoreCase(Config.getString("Mob.name.Shop"))) {
					if (Command_mobs.list.contains(e.getDamager().getUniqueId().toString())) {
						e.setCancelled(false);
					} else {
						e.setCancelled(true);
					}
					return;
				} else {

				}
			} else if (e.getEntity() instanceof IronGolem) {
				IronGolem g = (IronGolem) e.getEntity();
				if (g.getCustomName().equalsIgnoreCase(Config.getString("Mob.name.Rewards"))) {
					if (Command_mobs.list.contains(e.getDamager().getUniqueId().toString())) {
						e.setCancelled(false);
					} else {
						e.setCancelled(true);
					}
				}
			} else if (e.getEntity() instanceof Snowman) {
				Snowman s = (Snowman) e.getEntity();
				if (s.getCustomName().equalsIgnoreCase(Config.getString("Mob.name.Kits"))) {
					if (Command_mobs.list.contains(e.getDamager().getUniqueId().toString())) {
						e.setCancelled(false);
					} else {
						e.setCancelled(true);
					}
				}
			} else if (e.getEntity() instanceof Blaze) {
				Blaze s = (Blaze) e.getEntity();
				if (s.getCustomName().equalsIgnoreCase(Config.getString("Mob.name.cookie"))) {
					if (Command_mobs.list.contains(e.getDamager().getUniqueId().toString())) {
						e.setCancelled(false);
					} else {
						e.setCancelled(true);
					}
				}
			}
		} catch (Exception e2) {
		}

	}
}
