package net.royalbyte.skypvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;

public class Command_remsg implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length >= 1) {
				if (Command_MSG.msg.containsKey(p)) {
					Player t = Command_MSG.msg.get(p);
					String text = "";
					for (int i = 0; i != args.length; i++) {
						text += args[i] + " ";
					}
					text = text.replaceAll("&", "§");
					t.sendMessage(Config.getString("msg.from").replaceAll("%PLAYER%", p.getDisplayName())
							.replaceAll("%MSG%", text));
					p.sendMessage(Config.getString("msg.to").replaceAll("%PLAYER%", t.getDisplayName())
							.replaceAll("%MSG%", text));

				} else {
					p.sendMessage(Data.prefix + "§cDu musst noch einen Spieler anschreiben §7[§3/msg <spieler> <msg>");
				}
			} else {
				p.sendMessage(Data.prefix + "§cUse /remsg <msg>");
			}

		} else {
			sender.sendMessage(Data.must_a_player);
		}

		return true;
	}

}
