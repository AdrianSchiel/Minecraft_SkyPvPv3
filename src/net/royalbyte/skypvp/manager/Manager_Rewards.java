package net.royalbyte.skypvp.manager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.mysql.Rewards;
import net.royalbyte.skypvp.mysql.Stats;

public class Manager_Rewards {

	public File file = new File("plugins//SkyPvP//Datas", "rewards.yml");
	@SuppressWarnings("static-access")
	public YamlConfiguration cfg = new YamlConfiguration().loadConfiguration(file);

	public Manager_Rewards() {

	}

	public boolean isAllowedstuendlich(Player p) {
		long his = Rewards.getstuendlich(p.getUniqueId().toString());
		long current = System.currentTimeMillis();
		return current > his;
	}

	public boolean isAllowedtaeglich(Player p) {
		long his = Rewards.getteaglich(p.getUniqueId().toString());
		long current = System.currentTimeMillis();
		return current > his;
	}

	public boolean isAllowedwoechentlich(Player p) {
		long his = Rewards.getwoechentlich(p.getUniqueId().toString());
		long current = System.currentTimeMillis();
		return current > his;
	}

	@SuppressWarnings("unchecked")
	public void setwoechentlich(Player p) {
		Rewards.setwoechentlich(p.getUniqueId().toString(), (System.currentTimeMillis() + 604800000));
		if (cfg.getBoolean("woechentlich.coins.boolean")) {
			Stats.addCoins(p.getUniqueId().toString(), cfg.getInt("woechentlich.coins.amount"));
			p.sendMessage(Config.getString("rewards.getCoins").replaceAll("%COINS%",
					String.valueOf(cfg.getInt("woechentlich.coins.amount"))));
		}
		List<ItemStack> list = (List<ItemStack>) cfg.getList("woechentlich.list");
		for (int i = 0; i < list.size(); i++) {
			p.getInventory().addItem(list.get(i));
		}
		p.sendMessage(Config.getString("rewards.getReward.woechentlich"));
	}

	@SuppressWarnings("unchecked")
	public void settaeglich(Player p) {
		Rewards.setteaglich(p.getUniqueId().toString(), (System.currentTimeMillis() + 86400000));
		if (cfg.getBoolean("taeglich.coins.boolean")) {
			Stats.addCoins(p.getUniqueId().toString(), cfg.getInt("taeglich.coins.amount"));
			p.sendMessage(Config.getString("rewards.getCoins").replaceAll("%COINS%",
					String.valueOf(cfg.getInt("taeglich.coins.amount"))));
		}
		List<ItemStack> list = (List<ItemStack>) cfg.getList("taeglich.list");
		for (int i = 0; i < list.size(); i++) {
			p.getInventory().addItem(list.get(i));
		}
		p.sendMessage(Config.getString("rewards.getReward.taeglich"));
	
	}

	@SuppressWarnings("unchecked")
	public void setstuendlich(Player p) {
			Rewards.setstuendlich(p.getUniqueId().toString(), (System.currentTimeMillis() + 3600000));
			if (cfg.getBoolean("stuendlich.coins.boolean")) {
				Stats.addCoins(p.getUniqueId().toString(), cfg.getInt("stuendlich.coins.amount"));
				p.sendMessage(Config.getString("rewards.getCoins").replaceAll("%COINS%",
						String.valueOf(cfg.getInt("stuendlich.coins.amount"))));
			}
			List<ItemStack> list = (List<ItemStack>) cfg.getList("stuendlich.list");
			for (int i = 0; i < list.size(); i++) {
				p.getInventory().addItem(list.get(i));
			}
			p.sendMessage(Config.getString("rewards.getReward.stuendlich"));
	}

	public String getReamingTimeMSG(Player p, long millis) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy, HH:mm");
		Date date = new Date(millis);
		return "" + sdf.format(date);
	}

	@SuppressWarnings("unchecked")
	public List<ItemStack> getList(String path) {
		List<ItemStack> list = (List<ItemStack>) cfg.getList(path);
		return list;
	}

	public void addList(String path, ItemStack item) {
		List<ItemStack> list = getList(path);
		list.add(item);
		cfg.set(path, list);
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void clearlist(String path) {
		getList(path).clear();
	}
	
    public static String getEndDate(Player p, long millis){
        SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy, HH:mm");
        Date date = new Date(millis);
        return "" + sdf.format(date);
    }

	public void createRewardFile() {
		if (!file.exists()) {
			new File("plugins//SkyPvP//Datas//").mkdirs();
			List<ItemStack> stuendlich = new ArrayList<>();
			stuendlich.add(Manager_ItemBuilder.item("Reward", Material.IRON_INGOT, 0, 1));
			List<ItemStack> taeglich = new ArrayList<>();
			taeglich.add(Manager_ItemBuilder.item("Reward", Material.GOLD_INGOT, 0, 1));
			List<ItemStack> woechentlich = new ArrayList<>();
			woechentlich.add(Manager_ItemBuilder.item("Reward", Material.DIAMOND, 0, 1));
			cfg.set("stuendlich.coins.boolean", true);
			cfg.set("stuendlich.coins.amount", 10);
			cfg.set("stuendlich.list", stuendlich);

			cfg.set("taeglich.coins.boolean", true);
			cfg.set("taeglich.coins.amount", 20);
			cfg.set("taeglich.list", taeglich);

			cfg.set("woechentlich.coins.boolean", true);
			cfg.set("woechentlich.coins.amount", 30);
			cfg.set("woechentlich.list", woechentlich);

			saveFile();
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void saveFile() {
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
