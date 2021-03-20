package net.royalbyte.skypvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.SkyPvP;

public class Command_Umfrage implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length > 0) {
			
			if(sender.hasPermission("SkyPvP.umfrage")) {

				if(!SkyPvP.getInstance().getUmfrage().isUmfrage()) {
					String frage = "";
					for (int i = 0; i < args.length; i++) {
						frage += args[i] + " ";
					}
					SkyPvP.getInstance().getUmfrage().createNewUmfrage(frage);
					SkyPvP.getInstance().getUmfrage().timer();
					
					
				}else {
					sender.sendMessage(Config.getString("umfrage.iscuurent"));
				}
				
			}else {
				sender.sendMessage(Data.noperm);
			}
			
		}else {
			sender.sendMessage(Data.prefix + "§cUse /umfrage <Frage>");
		}
		
		return true;
	}

}
