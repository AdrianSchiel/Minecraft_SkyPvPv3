
/*
 * ---------------------------------------
 * Copyright @RoyalByte | Adrian Schiel
 * Youtube : RoyalByte Developer
 * Skype: RoyalByte
 * Website: RoyalByte.net
 * ---------------------------------------
 */

package net.royalbyte.skypvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;

public class Command_Vote implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		sender.sendMessage(Data.header);
		sender.sendMessage("§7" + Config.getString("Vote.link-1"));
		sender.sendMessage("§7" + Config.getString("Vote.link-2"));
		sender.sendMessage("§7" + Config.getString("Vote.link-3"));
		sender.sendMessage(Data.header);
		return true;
	}

}
