package net.royalbyte.skypvp.utils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.common.collect.Lists;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.SkyPvP;
import net.royalbyte.skypvp.manager.Manager_ItemBuilder;
import net.royalbyte.skypvp.mysql.Stats;

public class Shop implements Listener, CommandExecutor {

	/*
	 * API
	 */
	private File file;
	private YamlConfiguration cfg;
	private List<String> categories_list;

	@SuppressWarnings("static-access")
	public Shop() {
		this.file = new File("plugins/SkyPvP/Datas/", "shop.yml");
		this.cfg = new YamlConfiguration().loadConfiguration(this.file);
		this.categories_list = Lists.newArrayList();
		this.createFile();
		this.registeCategories_Names();
		//SkyPvP.getInstance().sendConsoleInfo("Das §2Shop-System §awurde geladen.");
	}

	public void createCategorie(final String name, final String displayname, final ItemStack item) {
		if (!this.existCategorie(name)) {
			this.cfg.set("categorie." + name + ".name", name);
			this.cfg.set("categorie." + name + ".displayname", displayname);
			this.cfg.set("categorie." + name + ".mainitem", item);
			this.cfg.set("categorie." + name + ".item_count", 0);
			this.getCategories_list().add(name);
			this.cfg.set("categories_list", this.getCategories_list());
			this.cfg.set("categories_count", (getCategoriesCount() + 1));
			this.saveCFG();
		}
	}

	public void openInvCategorie(final Player p, final String name) {
		Inventory inv = Bukkit.createInventory(null, 5 * 9, "§bShop§7§§9 " + name);
		for (int i = 0; i < 45; i++) {
			inv.setItem(i, Manager_ItemBuilder.lore(" ", Material.STAINED_GLASS_PANE, 1, 7, new String[] {}));
		}
		inv.setItem(44, Manager_ItemBuilder.lore("§cZurück", Material.IRON_DOOR, 1, 0, new String[] {}));
		final int items = getItemsinCategorieCount(name);
		int slot = 11;

		for (int i = 0; i < items; i++) {
			ItemStack item = getItemsinCategorie(name).get(i);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(getDisplayNameItem(name, (i + 1)).replaceAll("&", "§"));
			meta.setLore(Arrays.asList(new String[] { "§bItem ID§7:" + i, "§bPrice§7:" + getPriceItem(name, i + 1) }));
			item.setItemMeta(meta);
			inv.setItem(slot, item);
			slot++;
			if (slot == 16) {
				slot = 20;
			}
			if (slot == 25) {
				slot = 29;
			}
		}
		p.openInventory(inv);
	}

	public void openMainInv(final Player p) {
		Inventory inv = Bukkit.createInventory(null, 5 * 9, "§bShop");
		for (int i = 0; i < 45; i++) {
			inv.setItem(i, Manager_ItemBuilder.lore(" ", Material.STAINED_GLASS_PANE, 1, 7, new String[] {}));
		}

		final int categories = getCategoriesCount();
		int slot = 11;
		for (int i = 0; i < categories; i++) {
			ItemStack item = getItem(getCategories_list().get(i));
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(getDislayName(getCategories_list().get(i).replaceAll("&", "§")));
			meta.setLore(Arrays.asList(new String[] { "§bCategorie ID§7:" + getCategories_list().get(i) }));
			item.setItemMeta(meta);
			inv.setItem(slot, item);
			slot++;
			if (slot == 16) {
				slot = 20;
			}
			if (slot == 25) {
				slot = 29;
			}

		}

		p.openInventory(inv);
	}

	public List<ItemStack> getItemsinCategorie(final String name) {
		if (existCategorie(name)) {
			List<ItemStack> list = Lists.newArrayList();
			int items = getItemsinCategorieCount(name);
			for (int i = 1; i < (items + 1); i++) {
				list.add((ItemStack) this.cfg.get("categorie." + name + ".item." + i + ".item"));
			}
			return list;
		} else
			return null;
	}

	public void addItem(final String name, final String displayname, final Integer price, final ItemStack item) {
		if (this.existCategorie(name)) {
			if (!(getItemsinCategorieCount(name) >= 15)) {
				int i = getItemsinCategorieCount(name) + 1;
				this.cfg.set("categorie." + name + ".item." + i + ".price", price);
				this.cfg.set("categorie." + name + ".item." + i + ".displayname", displayname);
				this.cfg.set("categorie." + name + ".item." + i + ".item", item);
				this.cfg.set("categorie." + name + ".item_count", i);
				this.saveCFG();
			} else
				return;
		} else
			return;
	}

	public Integer getItemsinCategorieCount(final String name) {
		if (this.existCategorie(name)) {
			return this.cfg.getInt("categorie." + name + ".item_count");
		} else
			return 0;
	}

	public void deleteCategorie(final String name) {
		if (this.existCategorie(name)) {
			this.cfg.set("categorie." + name, null);
			this.getCategories_list().remove(name);
			this.cfg.set("categories_list", this.getCategories_list());
			this.cfg.set("categories_count", (getCategoriesCount() - 1));
			this.saveCFG();
		} else
			return;
	}

	public String getDisplayNameItem(final String name, final int id) {
		return this.cfg.getString("categorie." + name + ".item." + id + ".displayname");
	}

	public Integer getPriceItem(final String name, final int id) {
		return this.cfg.getInt("categorie." + name + ".item." + id + ".price");
	}

	public String getDislayName(final String name) {
		if (this.existCategorie(name)) {
			return this.cfg.getString("categorie." + name + ".displayname").replaceAll("&", "§");
		} else
			return null;
	}

	public ItemStack getItem(final String name) {
		if (this.existCategorie(name)) {
			return (ItemStack) this.cfg.get("categorie." + name + ".mainitem");
		} else
			return null;
	}

	public Integer getCategoriesCount() {
		return this.cfg.getInt("categories_count");
	}

	public boolean existCategorie(final String name) {
		return this.getCategories_list().contains(name);
	}

	public void registeCategories_Names() {
		List<String> cfg_list = cfg.getStringList("categories_list");
		cfg_list.forEach(arena -> {
			this.getCategories_list().add(arena);
		});
	}

	public List<String> getCategories_list() {
		return this.categories_list;
	}

	private void createFile() {
		new File("plugins/SkyPvP/Datas/").mkdirs();
		if (!this.file.exists()) {
			try {
				this.file.createNewFile();
				this.cfg.set("categories_count", 0);
				List<String> list = Lists.newArrayList();
				this.cfg.set("categories_list", list);
				this.saveCFG();
			} catch (IOException e) {
			}
		}
	}

	private void saveCFG() {
		try {
			this.cfg.save(this.file);
		} catch (IOException e) {
		}
	}

	/*
	 * COMMAND
	 */

	/*
	 * /shop create <Name> <Displayname> /shop delete <Name> /shop additem <name>
	 * <Price> <Displayname>
	 */

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("create")) {
				sender.sendMessage(Config.getString("shop.help.create"));
			} else if (args[0].equalsIgnoreCase("delete")) {
				sender.sendMessage(Config.getString("shop.help.delete"));
			} else if (args[0].equalsIgnoreCase("addItem")) {
				sender.sendMessage(Config.getString("shop.help.additem"));
			} else {
				sendHelp(sender);
			}
		} else if (args.length == 2) {
			if (args[0].equalsIgnoreCase("delete")) {
				if (sender.hasPermission("SkyPvP.shop.delete")) {
					final String name = args[1];
					if (existCategorie(name)) {
						deleteCategorie(name);
						sender.sendMessage(Config.getString("shop.categorie_deletet").replaceAll("%NAME%", name));
					} else
						sender.sendMessage(Config.getString("shop.categorie_not_exists"));
				} else
					sender.sendMessage(Data.noperm);
			} else
				sendHelp(sender);
		} else if (args.length >= 4) {
			if (args[0].equalsIgnoreCase("addItem")) {
				if (sender instanceof Player) {
					Player p = (Player) sender;
					final String name = args[1];
					if (existCategorie(name)) {
						if (!(p.getItemInHand().getType() == Material.AIR || p.getItemInHand() == null)) {
							final Integer price = Integer.parseInt(args[2]);
							String displayname = "";
							for (int i = 3; i != args.length; i++) {
								displayname += args[i] + " ";
							}
							addItem(name, displayname, price, p.getItemInHand());
							p.sendMessage(Config.getString("shop.addItem").replaceAll("%NAME%", name)
									.replaceAll("%DISPLAYNAME%", displayname)
									.replaceAll("%PRICE%", String.valueOf(price)));
						} else
							p.sendMessage(Config.getString("shop.must_hold_item_in_Hand"));
					} else
						sender.sendMessage(Config.getString("shop.categorie_not_exists"));
				} else
					sender.sendMessage(Data.must_a_player);
			} else
				sendHelp(sender);
		} else if (args.length >= 3) {
			if (args[0].equalsIgnoreCase("create")) {
				if (sender instanceof Player) {
					final Player p = (Player) sender;
					if (p.hasPermission("SkyPvP.shop.create")) {
						final String name = args[1];
						if (!existCategorie(name)) {
							if (!(p.getItemInHand().getType() == Material.AIR || p.getItemInHand() == null)) {
								String displayname = "";
								for (int i = 2; i != args.length; i++) {
									displayname += args[i] + " ";
								}
								createCategorie(name, displayname, p.getItemInHand());
								p.sendMessage(Config.getString("shop.createCategorie").replaceAll("%DISPLAYNAME%",
										displayname.replaceAll("&", "§")));
							} else
								p.sendMessage(Config.getString("shop.must_hold_item_in_Hand"));
						} else
							p.sendMessage(Config.getString("shop.categorie_already_exists"));

					} else
						p.sendMessage(Data.noperm);
				} else
					sender.sendMessage(Data.must_a_player);
			} else
				sendHelp(sender);
		}else {
			if(sender instanceof Player) {
				Player p =(Player)sender;
				openMainInv(p);
			}else sender.sendMessage(Data.must_a_player);
		}
		return true;
	}

	private void sendHelp(CommandSender sender) {
		sender.sendMessage(Data.header);
		sender.sendMessage(Config.getString("shop.help.line-1"));
		sender.sendMessage(Config.getString("shop.help.line-2"));
		sender.sendMessage(Config.getString("shop.help.line-3"));
		sender.sendMessage(Data.header);
	}

	/*
	 * EVENT
	 */

	@EventHandler
	public void onClick(final InventoryClickEvent e) {
		try {
			final Player p = (Player) e.getWhoClicked();
			if (e.getClickedInventory().getName() == "§bShop") {
				e.setCancelled(true);
				List<String> lore = e.getCurrentItem().getItemMeta().getLore();
				final String name = lore.get(0).replaceAll("§bCategorie ID§7:", "");
				if (existCategorie(name)) {
					openInvCategorie(p, name);
				}
			} else if (e.getClickedInventory().getName().startsWith("§bShop§7§§9 ")) {
				e.setCancelled(true);
				if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cZurück")) {
					p.closeInventory();
					openMainInv(p);
					p.updateInventory();
				} else {
					final String name = e.getInventory().getName().replaceAll("§bShop§7§§9 ", "");
					List<String> lore = e.getCurrentItem().getItemMeta().getLore();
					final Integer item_id = Integer.parseInt(lore.get(0).replaceAll("§bItem ID§7:", ""));
					final int preis = getPriceItem(name, (item_id + 1));
					final ItemStack item = e.getCurrentItem();

					if (Stats.getCoins(p.getUniqueId().toString()) >= preis) {
						Stats.removeCoins(p.getUniqueId().toString(), preis);
						p.getInventory().addItem(item);
						SkyPvP.getInstance().getScoreboard().setBoard(p);
					} else {
						p.sendMessage(Config.getString("shop.not_enough_coins"));
						return;
					}

				}
			}

		} catch (Exception e2) {

		}
	}

}
