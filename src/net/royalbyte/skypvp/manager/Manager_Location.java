package net.royalbyte.skypvp.manager;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import net.royalbyte.skypvp.Config;


public class Manager_Location {
	private static File file = new File("plugins//SkyPvP//Datas//", "locations.yml");
	@SuppressWarnings("static-access")
	private static YamlConfiguration cfg = new YamlConfiguration().loadConfiguration(file);

	public static void File() {
		new File("plugins//SkyPvP//Datas//").mkdir();
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {

		}
	}

	public static void setLocation(Location loc, String locname) {
		File();
		cfg.set(locname, loc);
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Bukkit.broadcastMessage(Config.getString("Location.setbc").replaceAll("%locname%", locname));
	}

	public static Location getLocation(String locname) {
		return cfg.contains(locname) ? (Location) cfg.get(locname) : null;
	}

	public static void tpLocation(Player p, String locname) {
		if (getLocation(locname) == null) {
			p.sendMessage(Config.getString("Location.notfound").replaceAll("%locname%", locname));
		} else if (getLocation(locname) == getLocation(locname)) {
			World w = getLocation(locname).getWorld();
			p.teleport(w.getSpawnLocation());
			p.teleport(getLocation(locname));
		}
	}

	public static void delLocation(String locname) {
		cfg.set(locname, null);
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
