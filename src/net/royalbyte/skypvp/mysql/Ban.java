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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.bukkit.Bukkit;

import net.royalbyte.skypvp.Config;

public class Ban {
    public static boolean isBanned(String uuid) {
        ResultSet rs = MySQL.getResult("SELECT * FROM Ban_bans WHERE UUID='" + uuid + "'");
        try {
            if(rs.next()){
                return rs.getString("UUID") !=null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void ban(String uuid, String reason, String author_name, long time, int banpoints) {
        long banned_bis = (System.currentTimeMillis() + time);
        if(time == -1){
            banned_bis = -1;
        }
        if(!isBanned(uuid)) {
            MySQL.update("INSERT INTO Ban_bans (Name, UUID, reason, bis , author_name) VALUES ('" + Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName() + "' , '"+ uuid+"','" + reason+"','"+ banned_bis+"','"
                    +author_name+ "')");
            BanPoints.addBP(uuid, banpoints);


            if(Bukkit.getOfflinePlayer(UUID.fromString(uuid)).isOnline()){
                Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getPlayer().kickPlayer(getBanMSG(uuid));
            }

        }
    }
    public static void unban(String uuid) {
        MySQL.update("DELETE FROM Ban_bans WHERE UUID='" + uuid + "'");
    }
    public static String getEndDate(String uuid){
        long millis = getEnd(uuid);
        SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy, HH:mm");
        Date date = new Date(millis);
        if(millis == -1){
            return "Permanent";
        }
        return "" + sdf.format(date);
    }
    public static long getEnd(String uuid){
        if (isBanned(uuid)) {
            ResultSet rs = MySQL.getResult("SELECT * FROM Ban_bans WHERE UUID='" + uuid + "'");
            try{
                while(rs.next()){
                    return rs.getLong("bis");
                }
            }catch (SQLException e){
            }
        }
        return 0;
    }
    public static String getReason(String uuid){
        if (isBanned(uuid)) {
            ResultSet rs = MySQL.getResult("SELECT * FROM Ban_bans WHERE UUID='" + uuid + "'");
            try{
                while(rs.next()){
                    return rs.getString("reason");
                }
            }catch (SQLException e){
            }
        }
        return "";
    }
    public static String getAuthor(String uuid){
        if (isBanned(uuid)) {
            ResultSet rs = MySQL.getResult("SELECT * FROM Ban_bans WHERE UUID='" + uuid + "'");
            try{
                while(rs.next()){
                    return rs.getString("author_name");
                }
            }catch (SQLException e){
            }
        }
        return "";
    }

    public static String getBanMSG(String uuid){
        String string = Config.getString("Ban.message");
        string = string.replaceAll("%REASON%", getReason(uuid));
        string = string.replaceAll("%ENDDATE%", getEndDate(uuid));
        string = string.replaceAll("%AUTHOR%", getAuthor(uuid));
        return string;
    }
}
