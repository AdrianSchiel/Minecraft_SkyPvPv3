package net.royalbyte.skypvp.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.manager.Manager_ItemBuilder;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Listener_Ranggutschein implements Listener {

	@EventHandler
	public void onInterract(PlayerInteractEvent e) {
		try {
			Player p = (Player) e.getPlayer();
			if (p.getItemInHand().getType() == Material.PAPER) {
				if (p.getItemInHand().getItemMeta().getDisplayName().startsWith("§bRang §7:")) {
					if (p.getItemInHand().getItemMeta().hasLore()) {
						String itemname = p.getItemInHand().getItemMeta().getDisplayName();
						String[] parts = itemname.split(":");
						String rang = parts[1];
						Inventory inv = Bukkit.createInventory(null, 9, "§bRanggutschein §7: " + rang);
						inv.setItem(1, Manager_ItemBuilder.lore("§aAnnehmen", Material.STAINED_GLASS_PANE, 1, 5,
								new String[] { "", "§8§l§ §7§lRechtsklick zum §a§lAnnehmen§7." , ""}));
						inv.setItem(2, Manager_ItemBuilder.lore("§aAnnehmen", Material.STAINED_GLASS_PANE, 1, 5,
								new String[] { "", "§8§l§ §7§lRechtsklick zum §a§lAnnehmen§7.", "" }));
						inv.setItem(4, Manager_ItemBuilder.lore("§bRanggutschein", Material.PAPER, 1, 0,
								new String[] { "", "§8§l§ §7§lHole dir den Rang: §b§l" + rang, "" }));
						inv.setItem(6, Manager_ItemBuilder.lore("§cAblehnen", Material.STAINED_GLASS_PANE, 1, 14,
								new String[] { "", "§8§l§ §7§lRechtsklick zum §c§lAblehnen§7." ,""}));
						inv.setItem(7, Manager_ItemBuilder.lore("§cAblehnen", Material.STAINED_GLASS_PANE, 1, 14,
								new String[] { "", "§8§l§ §7§lRechtsklick zum §c§lAblehnen§7." ,""}));
						p.openInventory(inv);
					}
				}
			}
		} catch (Exception e2) {
		}
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		try {
			Player p = (Player) e.getWhoClicked();
			if (e.getClickedInventory().getName().startsWith("§bRanggutschein §7:")) {
				String itemname = e.getClickedInventory().getName();
				String[] parts = itemname.split(": ");
				String rang = parts[1].replaceAll(" ", "");
				e.setCancelled(true);
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aAnnehmen")) {
					if(PermissionsEx.getUser(p).inGroup(rang)) {
						p.closeInventory();
						p.sendMessage(Config.getString("command.ranggutschein.is_in_group"));
					}else {
					int amount = p.getItemInHand().getAmount();
					if(amount == 1) {
						p.getInventory().removeItem(p.getItemInHand());
					}else {
						p.getItemInHand().setAmount(amount -1);
					}
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group set " + rang);
					p.closeInventory();
					p.sendMessage(Config.getString("command.ranggutschein.use").replaceAll("%RANG%", rang).replaceAll("%PLAYER%", p.getName()));
					}
				}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cAblehnen")) {
					p.closeInventory();
				}
			}
		} catch (Exception e2) {
		}
	}

}
