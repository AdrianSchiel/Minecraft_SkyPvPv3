package net.royalbyte.skypvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;

public class Command_Youtuber implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		sender.sendMessage(Data.header);
		sender.sendMessage(Config.getString("youtuber.msg-1"));
		sender.sendMessage(Config.getString("youtuber.msg-2"));
		sender.sendMessage(Config.getString("youtuber.msg-3"));
		sender.sendMessage(Config.getString("youtuber.msg-4"));
		sender.sendMessage(Config.getString("youtuber.msg-5"));
		sender.sendMessage(Config.getString("youtuber.msg-6"));
		sender.sendMessage(Data.header);
		
		return true;
	}

}
