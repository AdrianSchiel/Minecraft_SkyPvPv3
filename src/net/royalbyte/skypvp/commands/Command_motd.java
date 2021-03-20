/*
 * Copyright @RoyalByte 2018
 */

package net.royalbyte.skypvp.commands;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_motd implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        Player p = (Player) commandSender;
        if(p.hasPermission("skypvp.motd")){
        if (args.length >= 4) {
            if (args[0].equalsIgnoreCase("set")) {
                if (args[1].equalsIgnoreCase("normal")) {
                    if (args[2].equalsIgnoreCase("1")) {
                        String m1 = "";
                        for (int i = 3; i < args.length; ++i) {
                            m1 = m1 + args[i] + " ";
                        }
                        Config.set("motd-1" , m1.replaceAll("&", "§"));
                        p.sendMessage(Data.prefix + "\u00a77Du hast die NormaleMOTD f\u00fcr Zeile \u00a7e1 \u00a77erfolgreich gesetzt\u00a78.");
                    } else if (args[2].equalsIgnoreCase("2")) {
                        String m2 = "";
                        for (int i = 3; i < args.length; ++i) {
                            m2 = m2 + args[i] + " ";
                        }
                        Config.set("motd-2" , m2.replaceAll("&", "§"));
                        p.sendMessage(Data.prefix + "\u00a77Du hast die NormaleMOTD f\u00fcr Zeile \u00a7e2 \u00a77erfolgreich gesetzt\u00a78.");
                    }
                } else if (args[1].equalsIgnoreCase("wartung")) {
                    if (args[2].equalsIgnoreCase("1")) {
                        String m1 = "";
                        for (int i = 3; i < args.length; ++i) {
                            m1 = m1 + args[i] + " ";
                        }
                        Config.set("wartung.motd-1", m1.replaceAll("&", "§"));
                        p.sendMessage(Data.prefix + "\u00a77Du hast die WartungsMOTD f\u00fcr Zeile \u00a7e1 \u00a77erfolgreich gesetzt\u00a78.");
                    } else if (args[2].equalsIgnoreCase("2")) {
                        String m2 = "";
                        for (int i = 3; i < args.length; ++i) {
                            m2 = m2 + args[i] + " ";
                        }
                        Config.set("wartung.motd-2", m2.replaceAll("&", "§"));
                        p.sendMessage(Data.prefix + "\u00a77Du hast die WartungsMOTD f\u00fcr Zeile \u00a7e2 \u00a77erfolgreich gesetzt\u00a78.");
                    }
                }
            } else {
                p.sendMessage(Data.prefix + "\u00a77Bitte benutze \u00a7e/motd <set> <normal/wartung> <line> <Text>");
            }
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("info")) {
                // load
              p.sendMessage(Data.prefix + "\u00a77Deine normale MOTD : §a"+  Config.getString("motd-1") + "\n" + Config.getString("motd-2"));
              p.sendMessage(Data.prefix + "\u00a77Deine Wartungs MOTD : §a"+  Config.getString("wartung.motd-1") + "\n" + Config.getString("wartung.motd-2"));

            }
        } else {
            p.sendMessage(Data.prefix + "\u00a77Bitte benutze \u00a7e/motd <set> <normal/wartung> <1/2> <Text>");
            p.sendMessage(Data.prefix + "\u00a77oder \u00a7e/motd info");
        }
        }else{
                p.sendMessage(Data.noperm);
            }

        return false;
    }
}
