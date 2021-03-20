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
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.SkyPvP;

public class Command_SkyPvP implements CommandExecutor, Listener {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.hasPermission("SkyPvP.gui")) {
				SkyPvP.getInstance().getInventories().openSkyPvPMain(p);
			} else {
				SkyPvP.getInstance().getInventories().openSkyPvPSozials(p);
			}
		
		} else {
			sender.sendMessage(Data.must_a_player);
		}
		return true;
	}




}
