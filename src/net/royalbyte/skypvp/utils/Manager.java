/*
 *
 *   David.R | RoyalByteNET (c) 2019.
 *
 *    ______  _______ _    _ _____ ______     ______
 *    |     \ |_____|  \  /    |   |     \   |_____/
 *    |_____/ |     |   \/   __|__ |_____/ . |    \_
 *
 *
 *
 */

package net.royalbyte.skypvp.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;


public class Manager {
    public static File datayml = new File("plugins/SkyPvP/warps.yml");
    public static YamlConfiguration data = YamlConfiguration.loadConfiguration(datayml);


    public  static void save(){
        try {
            data.save(datayml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void setLocation(Player p, String warpName)
    {
        Location playerLoc = p.getLocation();
        warpName = warpName.toLowerCase();
        if (!isWarpExists(warpName))
        {
            data.set(warpName, playerLoc);
            save();
            warpName = warpName.toLowerCase();

        }
        else
        {
            warpName = warpName.toLowerCase();
            p.sendMessage("§cDiesen Warp Punkt gibts es bereits");
        }
    }

    public static void removeLocation(String warpName)
    {
        warpName = warpName.toLowerCase();
        data.set(warpName, null);
        save();
    }

    public static boolean isWarpExists(String warpName)
    {
        warpName = warpName.toLowerCase();
        return data.contains(warpName);
    }

    public static Location getLocation(String warpName)
    {
        warpName = warpName.toLowerCase();
        return (Location)data.get(warpName);
    }

    public static void teleportToWarp(Player p, String warpName)
    {
        warpName = warpName.toLowerCase();
        p.teleport(getLocation(warpName));
    }
}

