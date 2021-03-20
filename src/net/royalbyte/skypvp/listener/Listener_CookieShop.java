/*
 * Copyright @RoyalByte 2018
 */

package net.royalbyte.skypvp.listener;
/*   Copyright © 2018/2019, Adrian Schiel
 *
 *    Datei:          Listener_CookieShop
 *    Developer:      Adrian Schiel
 *    Erstellt am:    21.12.2018
 *    Email:          adrian.schiel@royalbyte.de
 *
 *    Alle Inhalte des Quelltextes sind urheberrechtlich geschützt.
 *    Das Urhheberrecht liegt, soweit nicht ausdrücklich anders gekenntzeichnet
 *    bei Adrian Schiel. Alle Rechte vorbehalten.
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,
 *    öffentlichen Zugänglichmachung oder andere Nutzung
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung vcn Adrian Schiel
 */

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.SkyPvP;
import net.royalbyte.skypvp.mysql.Stats;
import net.royalbyte.skypvp.utils.SBoard;
import net.royalbyte.skypvp.utils.Tablist;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.concurrent.ExecutionException;

public class Listener_CookieShop implements Listener {

    @EventHandler
    public void onEvent(final InventoryClickEvent event) {
            Player player = (Player) event.getWhoClicked();
            if(event.getClickedInventory().getName().equalsIgnoreCase("§b§lCookieShop")){
                event.setCancelled(true);
                if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§b"+ Config.getInt("cookie.cost.1") + "-Cookies §7gegen §9"+ Config.getInt("coins.become.1") +"-Coins")){
                    if(Stats.getCookies(player.getUniqueId().toString()) >= Config.getInt("cookie.cost.1")){
                        Stats.removeCookies(player.getUniqueId().toString(),  Config.getInt("cookie.cost.1"));
                        Stats.addCoins(player.getUniqueId().toString(), Config.getInt("coins.become.1"));
                        SBoard.setBoard(player);
                        Tablist.send(player);
                        player.sendMessage(Config.getString("cookie-shop.changecookies"));
                    }else {
                        player.sendMessage(Config.getString("cookie-shop.notenoughcookies"));
                        player.closeInventory();
                    }
                }else  if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§b"+Config.getInt("cookie.cost.2")+ "-Cookies §7gegen §9"+ Config.getInt("coins.become.2")+ "-Coins")){
                    if(Stats.getCookies(player.getUniqueId().toString()) >= Config.getInt("cookie.cost.2")){
                        Stats.removeCookies(player.getUniqueId().toString(), Config.getInt("cookie.cost.2"));
                        Stats.addCoins(player.getUniqueId().toString(), Config.getInt("coins.become.2"));
                        SBoard.setBoard(player);
                        Tablist.send(player);
                        player.sendMessage(Config.getString("cookie-shop.changecookies"));
                    }else {
                        player.sendMessage(Config.getString("cookie-shop.notenoughcookies"));
                        player.closeInventory();
                    }
                }else  if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§b"+ Config.getInt("cookie.cost.3") +"-Cookies §7gegen §9" + Config.getInt("coins.become.3") +"-Coins")){
                    if(Stats.getCookies(player.getUniqueId().toString()) >= Config.getInt("cookie.cost.3")){
                        Stats.removeCookies(player.getUniqueId().toString(), Config.getInt("cookie.cost.3"));
                        Stats.addCoins(player.getUniqueId().toString(), Config.getInt("coins.become.3"));
                        SBoard.setBoard(player);
                        Tablist.send(player);
                        player.sendMessage(Config.getString("cookie-shop.changecookies"));
                    }else {
                        player.sendMessage(Config.getString("cookie-shop.notenoughcookies"));
                        player.closeInventory();
                    }
                }else {

                }
            }
    }
}