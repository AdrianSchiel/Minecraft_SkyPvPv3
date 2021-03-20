/*
 * Copyright @RoyalByte 2018
 */

package net.royalbyte.skypvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;

public class Command_GameMode implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {

			Player p = (Player)sender;
            if (args.length == 1) {
                if (p.hasPermission("SkyPvP.gamemode")) {
                    if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
                        p.setGameMode(GameMode.CREATIVE);
                        p.sendMessage(Config.getString("Command.gamemode").replaceAll("%GAMEMODE%" ,"creative"));
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
                    }else if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
                        p.setGameMode(GameMode.SURVIVAL);
                        p.sendMessage(Config.getString("Command.gamemode").replaceAll("%GAMEMODE%" ,"survival"));
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
                    }else if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
                        p.setGameMode(GameMode.ADVENTURE);
                        p.sendMessage(Config.getString("Command.gamemode").replaceAll("%GAMEMODE%" ,"adventure"));
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
                    } else if (args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")) {
                        p.setGameMode(GameMode.SPECTATOR);
                        p.sendMessage(Config.getString("Command.gamemode").replaceAll("%GAMEMODE%", "spectator"));
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
                    } else {
                        p.sendMessage(Data.prefix + "§cUse /gamemode <Gamemode> <Player>");
                    }
                } else {
                    p.sendMessage(Data.noperm);
                    p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
                }
            }else if(args.length == 2){
                if (p.hasPermission("SkyPvP.gamemode.other")) {
                        Player t = Bukkit.getPlayer(args[1]);
                    if (t != null) {
                        if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
                            t.setGameMode(GameMode.CREATIVE);
                            t.sendMessage(Config.getString("Command.gamemode").replaceAll("%GAMEMODE%" ,"creative"));
                            t.playSound(t.getLocation(), Sound.LEVEL_UP, 10F, 10F);
                        }else if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
                            t.setGameMode(GameMode.SURVIVAL);
                            t.sendMessage(Config.getString("Command.gamemode").replaceAll("%GAMEMODE%" ,"survival"));
                            t.playSound(t.getLocation(), Sound.LEVEL_UP, 10F, 10F);
                        }else if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
                            t.setGameMode(GameMode.ADVENTURE);
                            t.sendMessage(Config.getString("Command.gamemode").replaceAll("%GAMEMODE%" ,"adventure"));
                            t.playSound(t.getLocation(), Sound.LEVEL_UP, 10F, 10F);
                        } else if (args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")) {
                            t.setGameMode(GameMode.SPECTATOR);
                            t.sendMessage(Config.getString("Command.gamemode").replaceAll("%GAMEMODE%", "spectator"));
                            t.playSound(t.getLocation(), Sound.LEVEL_UP, 10F, 10F);
                        } else {
                            p.sendMessage(Data.prefix + "§cUse /gamemode <Gamemode> <Player>");
                        }
                    } else {
                        p.sendMessage(Data.player_not_online);
                    }
                } else {
                    p.sendMessage(Data.noperm);
                    p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
                }
            } else {
                p.sendMessage(Data.prefix + "§cUse /gamemode <Gamemode> <Player>");
            }

		} else {
			sender.sendMessage(Data.must_a_player);
			return true;
		}

		
		return true;
	}

}
