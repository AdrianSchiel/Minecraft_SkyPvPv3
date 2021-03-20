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

public class Datas {

    public static boolean inList(String uuid) {
        ResultSet rs = MySQL.getResult("SELECT * FROM Datas WHERE UUID='" + uuid + "'");
        try {
            if(rs.next()){
                return rs.getString("UUID") !=null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void addPlayer(String uuid, String name) {
        if(!inList(uuid)) {
            MySQL.update("INSERT INTO Datas (Name , UUID , firstjoin, lastonline) VALUES ('" + name + "', '" + uuid + "' ,'0', '0')");
        }
    }

    public static long getlastonline(String uuid) {
        if (inList(uuid)) {
            ResultSet rs = MySQL.getResult("SELECT * FROM Datas WHERE UUID='" + uuid + "'");
            try{
                while(rs.next()){
                    return rs.getLong("lastonline");
                }
            }catch (SQLException e){
            }
        } else {
            addPlayer(uuid, Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
        }
        return 0;
    }
    public static void setlastonline(String uuid, long cuurent){
        if(inList(uuid)){
            MySQL.update("UPDATE Datas SET lastonline='" + cuurent +"' WHERE UUID='"+uuid+"'");
        }else{
            addPlayer(uuid ,Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
        }
    }

    public static long getfirstjoin(String uuid) {
        if (inList(uuid)) {
            ResultSet rs = MySQL.getResult("SELECT * FROM Datas WHERE UUID='" + uuid + "'");
            try{
                while(rs.next()){
                    return rs.getLong("firstjoin");
                }
            }catch (SQLException e){
            }
        } else {
            addPlayer(uuid , Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
        }
        return 0;
    }
    public static void setfirstjoin(String uuid, long cuurent){
        if(inList(uuid)){
            MySQL.update("UPDATE Datas SET firstjoin='" + cuurent +"' WHERE UUID='"+uuid+"'");
        }else{
            addPlayer(uuid ,Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
        }
    }
}
