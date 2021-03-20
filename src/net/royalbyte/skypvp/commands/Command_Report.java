package net.royalbyte.skypvp.commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.SkyPvP;
import net.royalbyte.skypvp.manager.Manager_Location;

public class Command_Report implements CommandExecutor {

	// report <Player> <Grund>
	// report get <Player>
	// report <login,logout>
	// Report end
	// report list
	// Report info
	// Report listclear
	// report help
	public static File file = new File("plugins//SkyPvP//Report//", "reports.yml");
	@SuppressWarnings("static-access")
	public static YamlConfiguration cfg = new YamlConfiguration().loadConfiguration(file);
	public static List<Player> login = new ArrayList<>();
	public static List<Player> inwork = new ArrayList<>();
	public static List<String> reportet = new ArrayList<>();
	public static List<Player> reporter = new ArrayList<>();
	public static Map<Player, Player> work_map = new HashMap<>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("get")) {
					if (p.hasPermission("SkyPvP.report.get")) {
						if (!inwork.contains(p)) {
							Player t = Bukkit.getPlayer(args[1]);
							if (t != null) {
								if (reportet.contains(t.getUniqueId().toString())) {
									work_map.put(p, t);
									inwork.add(p);
									reportet.remove(t.getUniqueId().toString());
									Bukkit.getOnlinePlayers().forEach(player -> {
										player.hidePlayer(p);
									});
									p.teleport(t);
									p.sendMessage(Config.getString("report.get").replaceAll("%PLAYER%", t.getName()));
								} else {
									p.sendMessage(Config.getString("report.is_not_reportet"));
								}
							} else {
								p.sendMessage(Data.noperm);
							}
						} else {
							p.sendMessage(Config.getString("report.is_in_work"));
						}
					} else {
						p.sendMessage(Data.noperm);
					}
				} else {
					Player t = Bukkit.getPlayer(args[0]);
					String reason = args[1];
					if (t != null) {
						if (!reporter.contains(p)) {
							if (!reportet.contains(t.getUniqueId().toString())) {
								reportet.add(t.getUniqueId().toString());
								reporter.add(p);
								cfg.set(t.getUniqueId().toString() + ".name", t.getName());
								cfg.set(t.getUniqueId().toString() + ".reason", reason);
								cfg.set(t.getUniqueId().toString() + ".reporter.name", p.getName());
								cfg.set(t.getUniqueId().toString() + ".reporter.uuid", p.getUniqueId().toString());
								cfg.set("reportet_list", reportet);
								saveFile();
								p.sendMessage(Config.getString("report.create.player_msg")
										.replaceAll("%PLAYER%", args[0]).replaceAll("%REASON%", reason));
								IChatBaseComponent chat = ChatSerializer.a("{\"text\":\"" + Data.prefix
										+ "\",\"extra\":[{\"text\":\"§a§lBearbeiten\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"§6Klicke zum §abearbeiten\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/report get "
										+ t.getName() + "\"}}]}");
								PacketPlayOutChat msg = new PacketPlayOutChat(chat);
								for (Player worker : login) {
									worker.sendMessage(Config.getString("report.notify.line-1")
											.replaceAll("%REPORTER%", p.getName()).replaceAll("%PLAYER%", t.getName())
											.replaceAll("%REASON%", reason));
									worker.sendMessage(Config.getString("report.notify.line-2")
											.replaceAll("%REPORTER%", p.getName()).replaceAll("%PLAYER%", t.getName())
											.replaceAll("%REASON%", reason));
									worker.sendMessage(Config.getString("report.notify.line-3")
											.replaceAll("%REPORTER%", p.getName()).replaceAll("%PLAYER%", t.getName())
											.replaceAll("%REASON%", reason));
									((CraftPlayer) worker).getHandle().playerConnection.sendPacket(msg);
									worker.sendMessage(Config.getString("report.notify.line-4")
											.replaceAll("%REPORTER%", p.getName()).replaceAll("%PLAYER%", t.getName())
											.replaceAll("%REASON%", reason));
								}
								new BukkitRunnable() {

									@Override
									public void run() {
										reporter.remove(p);
										p.sendMessage(Config.getString("report.can_now_report"));
									}
								}.runTaskLaterAsynchronously(SkyPvP.getInstance(), 20 * 60);
							} else {
								p.sendMessage(Config.getString("report.create.is_already_reportet")
										.replaceAll("%PLAYER%", args[0]));
							}
						} else {
							p.sendMessage(Config.getString("report.create.has_already_reportet"));
						}
					} else {
						p.sendMessage(Data.player_not_online);
					}

				}
			} else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("help")) {
					for (int i = 1; i < 10; i++) {
						p.sendMessage(Config.getString("report.help.line-" + i));
					}
				} else if (args[0].equalsIgnoreCase("login")) {
					if (p.hasPermission("SkyPvP.report.login")) {
						if (login.contains(p)) {
							p.sendMessage(Config.getString("report.login.is_always"));
						} else {
							login.add(p);
							p.sendMessage(Config.getString("report.login.msg"));
						}
					} else {
						p.sendMessage(Data.noperm);
					}
				} else if (args[0].equalsIgnoreCase("logout")) {
					if (p.hasPermission("SkyPvP.report.logout")) {
						if (login.contains(p)) {
							login.remove(p);
							p.sendMessage(Config.getString("report.logout.msg"));
						} else {
							p.sendMessage(Config.getString("report.logout.is_not"));
						}
					} else {
						p.sendMessage(Data.noperm);
					}
				} else if (args[0].equalsIgnoreCase("end")) {
					if (p.hasPermission("SkyPvP.report.end")) {
						if (inwork.contains(p)) {
							p.sendMessage(Config.getString("report.end.msg").replaceAll("%PLAYER%",
									work_map.get(p).getName()));
							inwork.remove(p);
							work_map.remove(p);
							Manager_Location.tpLocation(p, "Spawn");
							Bukkit.getOnlinePlayers().forEach(player -> {
								player.showPlayer(p);
							});
						} else {
							p.sendMessage(Config.getString("report.end.not_in_work"));
						}
					} else {
						p.sendMessage(Data.noperm);
					}
				} else if (args[0].equalsIgnoreCase("list")) {
					if (p.hasPermission("SkyPvP.report.list")) {
						if(!reportet.isEmpty()) {
						reportet.forEach(uuid -> {
							String name = cfg.getString(uuid + ".name");
							String reason = cfg.getString(uuid + ".reason");
							String reporter = cfg.getString(uuid + ".reporter.name");
							p.sendMessage(Config.getString("report.list").replaceAll("%PLAYER%", name)
									.replaceAll("%REASON%", reason).replaceAll("%REPORTER%", reporter));
						});
						}else {
							p.sendMessage(Config.getString("report.no_body_is_reportet"));
						}
					} else {
						p.sendMessage(Data.noperm);
					}
				}else if(args[0].equalsIgnoreCase("clearlist")) {
					if(p.hasPermission("SkyPvP.report.clearlist")) {
						List<String> clear_list = new ArrayList<>();
						for(String uuid :reportet) {
							cfg.set(uuid, "");
						}
						cfg.set("reportet_list", clear_list);
						reportet.clear();
						saveFile();
						p.sendMessage(Config.getString("report.clearlist"));
					}else {
						p.sendMessage(Data.noperm);
					}
				}else {
					p.sendMessage(Data.prefix + "§cUse /report <help>");
				}
			} else {
				p.sendMessage(Data.prefix + "§cUse /report <help>");
			}
		} else {
			sender.sendMessage(Data.must_a_player);
		}

		return true;
	}

	public static void saveFile() {
		try {
			cfg.save(file);
		} catch (IOException e) {
		}
	}

	public static void registerReportetLIst() {
		reportet = cfg.getStringList("reportet_list");
	}

	public static void createFile() {
		if (!file.exists()) {
			new File("plugins//SkyPvP//Report//").mkdirs();
			List<String> list = new ArrayList<>();
			cfg.set("reportet_list", list);
			saveFile();
			try {
				file.createNewFile();
			} catch (IOException e) {
			}
		}
	}

}
