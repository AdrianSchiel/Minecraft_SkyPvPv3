package net.royalbyte.skypvp.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;


public class Command_MSG implements CommandExecutor {
	
	
	public static Map<Player, Player> msg = new HashMap<>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length >= 1) {
				Player t = Bukkit.getPlayer(args[0]);
				if (t != null) {
					String text = "";
					for (int i = 1; i != args.length; i++) {
						text += args[i] + " ";
					}
					text = text.replaceAll("&", "§");
					t.sendMessage(Config.getString("msg.from").replaceAll("%PLAYER%", p.getDisplayName())
							.replaceAll("%MSG%", text));
					p.sendMessage(Config.getString("msg.to").replaceAll("%PLAYER%", t.getDisplayName())
							.replaceAll("%MSG%", text));
					if (msg.containsKey(p)) {
						msg.remove(p);
					}
					if (msg.containsKey(t)) {
						msg.remove(t);
					}
					msg.put(p, t);
					msg.put(t, p);

				} else {
					p.sendMessage(Data.player_not_online);
				}
			} else {
				p.sendMessage(Data.prefix + "§cUse /msg <player> <msg>");
			}

		} else {
			sender.sendMessage(Data.must_a_player);
		}
		return true;
	}

}
