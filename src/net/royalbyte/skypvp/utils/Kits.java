package net.royalbyte.skypvp.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.SkyPvP;

public class Kits {

	public Kits() {
	}

	public void openInv(Player p) {
		SkyPvP.getInstance().getInventories().openKits(p);
	}

	@SuppressWarnings("static-access")
	public void saveKit(Player p, String name, String kitprefix) {
		if (p != null) {
			File file = new File("plugins//SkyPvP//Kits", name + ".yml");
			YamlConfiguration cfg = new YamlConfiguration().loadConfiguration(file);
			new File("plugins//SkyPvP//Kits").mkdirs();
			if (file.exists()) {
				for (int i = 0; i < 35; i++) {
					if (p.getInventory().getItem(i) != null) {
						cfg.set("slot." + i, p.getInventory().getItem(i));
					}
				}
				if (p.getInventory().getHelmet() != null) {
					cfg.set("helm", p.getInventory().getHelmet());
				}
				if (p.getInventory().getChestplate() != null) {
					cfg.set("chest", p.getInventory().getChestplate());
				}
				if (p.getInventory().getLeggings() != null) {
					cfg.set("leggins", p.getInventory().getLeggings());
				}
				if (p.getInventory().getBoots() != null) {
					cfg.set("boots", p.getInventory().getBoots());
				}
				try {
					cfg.save(file);
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
					Bukkit.broadcastMessage(Config.getString("Kits.setKit").replaceAll("%KITNAME%", kitprefix));
				} catch (IOException e) {
					SkyPvP.getInstance().sendConsoleError("Konnte Kit: §4" + name + "§c nicht erstellen");
				}

			} else {
				try {
					file.createNewFile();
					for (int i = 0; i < 35; i++) {
						if (p.getInventory().getItem(i) != null) {
							cfg.set("slot." + i, p.getInventory().getItem(i));
						}
					}
					if (p.getInventory().getHelmet() != null) {
						cfg.set("helm", p.getInventory().getHelmet());
					}
					if (p.getInventory().getChestplate() != null) {
						cfg.set("chest", p.getInventory().getChestplate());
					}
					if (p.getInventory().getLeggings() != null) {
						cfg.set("leggins", p.getInventory().getLeggings());
					}
					if (p.getInventory().getBoots() != null) {
						cfg.set("boots", p.getInventory().getBoots());
					}
					try {
						cfg.save(file);
						Bukkit.broadcastMessage(Config.getString("Kits.setKit").replaceAll("%KITNAME%", kitprefix));
					} catch (IOException e) {
						SkyPvP.getInstance().sendConsoleError("Konnte Kit: §4" + name + "§c nicht erstellen");
					}
				} catch (IOException e) {
					SkyPvP.getInstance().sendConsoleError("Konnte Kit: §4" + name + "§c nicht erstellen");
				}
			}
		} else {
			SkyPvP.getInstance().sendConsoleError("Konnte Kit: §4" + name + "§c nicht erstellen");
		}
	}

	@SuppressWarnings("static-access")
	public void getKit(Player p, String name, String kitprefix) {
		File file = new File("plugins//SkyPvP//Kits", name + ".yml");
		YamlConfiguration cfg = new YamlConfiguration().loadConfiguration(file);
		if (!file.exists()) {
			p.sendMessage(Config.getString("Kits.notExist").replaceAll("%KITNAME%", kitprefix));
		} else {
			for (int i = 0; i < 35; i++) {
				ItemStack s = cfg.contains("slot." + i) ? (ItemStack) cfg.get("slot." + i) : null;
				if (s != null) {
					p.getInventory().addItem(s);
				}
			}
			p.getInventory().setHelmet((ItemStack) cfg.get("helm"));
			p.getInventory().setChestplate((ItemStack) cfg.get("chest"));
			p.getInventory().setLeggings((ItemStack) cfg.get("leggins"));
			p.getInventory().setBoots((ItemStack) cfg.get("boots"));

			p.sendMessage(Config.getString("Kits.getKit").replaceAll("%KITNAME%", kitprefix));
			p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
		}

	}

}
