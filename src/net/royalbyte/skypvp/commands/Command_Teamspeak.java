package net.royalbyte.skypvp.commands;

import java.awt.Desktop;
import java.net.URI;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.SkyPvP;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Command_Teamspeak implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;
        player.sendMessage(Data.prefix + "§7Unser Teamspeak-Server : §b" + Config.getString("Teamspeak-Domain"));

        return false;
    }

}
