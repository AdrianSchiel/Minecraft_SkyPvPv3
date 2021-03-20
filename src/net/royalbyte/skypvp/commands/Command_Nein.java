package net.royalbyte.skypvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.SkyPvP;

public class Command_Nein implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			
			Player p = (Player)sender;
			
			if(SkyPvP.getInstance().getUmfrage().isUmfrage()) {
				if(!SkyPvP.getInstance().getUmfrage().player.contains(p.getUniqueId().toString())) {
					
					SkyPvP.getInstance().getUmfrage().player.add(p.getUniqueId().toString());
					SkyPvP.getInstance().getUmfrage().addNein();
					p.sendMessage(Config.getString("umfrage.answer_msg"));
					
				}else {
					p.sendMessage(Config.getString("umfrage.has_answered"));
				}
			}else {
				p.sendMessage(Config.getString("umfrage.nofrage"));
			}
		
			
		}else {
			sender.sendMessage(Data.must_a_player);
		}
		
		return true;
	}
}
