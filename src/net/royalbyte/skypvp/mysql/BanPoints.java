/*
 * ---------------------------------------
 * Copyright @RoyalByte | Adrian Schiel
 * Youtube : RoyalByte Developer
 * Skype: RoyalByte
 * Website: RoyalByte.net
 * ---------------------------------------
 */

package net.royalbyte.skypvp.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class BanPoints {
    public static boolean inList(String uuid) {
        ResultSet rs = MySQL.getResult("SELECT * FROM Ban_Points WHERE UUID='" + uuid + "'");
        try {
            if(rs.next()){
                return rs.getString("UUID") !=null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void addPlayer(String uuid) {
        if(!inList(uuid)) {

            MySQL.update("INSERT INTO Ban_Points (Name , UUID , banpoints) VALUES ('" + Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName() + "', '" + uuid + "' ,'0')");
        }
    }
    public static Integer getBP(String uuid) {
        if (inList(uuid)) {
            ResultSet rs = MySQL.getResult("SELECT * FROM Ban_Points WHERE UUID='" + uuid + "'");
            try{
                while(rs.next()){
                    return rs.getInt("banpoints");
                }
            }catch (SQLException e){
            }
        } else {
            addPlayer(uuid);
        }
        return 0;
    }
    public static void setBP(OfflinePlayer p, int bp){
        String uuid = p.getUniqueId().toString();
        if(inList(uuid)){
            MySQL.update("UPDATE Ban_Points SET banpoints='" + bp +"' WHERE UUID='"+uuid+"'");
        }else{
            addPlayer(uuid);
        }
    }
    public static void addBP(String uuid, int bp){
        int current = getBP(uuid);
        int now = current + bp;
        if(inList(uuid)){
            MySQL.update("UPDATE Ban_Points SET banpoints='" + now +"' WHERE UUID='"+uuid+"'");
        }else{
            addPlayer(uuid);
        }
    }
}
