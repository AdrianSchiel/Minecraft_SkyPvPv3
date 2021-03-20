/*
 * ---------------------------------------
 * Copyright @RoyalByte | Adrian Schiel
 * Youtube : RoyalByte Developer
 * Skype: RoyalByte
 * Website: RoyalByte.net
 * ---------------------------------------
 */

package net.royalbyte.skypvp.mysql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import net.royalbyte.skypvp.SkyPvP;

public class MySQL {

    public static String host;
    public static String username;
    public static String database;
    public static String password;
    public static int port;
    public static Connection con;

    public static boolean aktiv;
    
    public static String getMySQLEnabledMSG() {
		if(aktiv) {
			return "§aAktiviert";
		}else {
			return "§cDeaktiviert";
		}
    }
    
    public static void connect() {
        if (!isConnected()) {
                    try {
                        con = DriverManager.getConnection(
                                "jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", username,
                                password);

                    	SkyPvP.getInstance().sendConsoleInfo("Die §2MySQL §awurde verbunden.");
                    } catch (SQLException e) {
                    	SkyPvP.getInstance().sendConsoleError("Konnte §4MySQL §cnicht verbinden.");
                    }
        }
    }

    public static void close() {
        if (isConnected()) {
                    try {
                        con.close();
                    	SkyPvP.getInstance().sendConsoleInfo("Die §2MySQL §awurde geschlossen.");

                    } catch (SQLException e) {
                    	SkyPvP.getInstance().sendConsoleError("Konnte §4MySQL §cnicht beenden.");
                    }

        }

    }

    public static boolean isConnected() {
        return con != null;
    }


    public static void update(String qry)
    {
        try
        {
            Statement st = con.createStatement();
            st.executeUpdate(qry);
            st.close();
        }
        catch (SQLException e)
        {
            connect();
            System.err.println(e);
        }
    }


    public static ResultSet getResult(String qry) {
        ResultSet rs = null;
        try
        {
            Statement st = con.createStatement();
            rs = st.executeQuery(qry);
        }
        catch (SQLException e)
        {
            connect();
            System.err.println(e);
        }
        return rs;
    }


    public static void createTable() {
        update("CREATE TABLE IF NOT EXISTS Stats (Name VARCHAR(16), UUID VARCHAR(100), kills INT(100), tode INT(100), points INT(100), coins INT(100) , cookie INT(100), cookiescore INT(100), PlayerID INT(100))");
        update("CREATE TABLE IF NOT EXISTS Datas (Name VARCHAR(16), UUID VARCHAR(100), firstjoin LONG, lastonline LONG)");
        update("CREATE TABLE IF NOT EXISTS Ban_bans (Name VARCHAR(16), UUID VARCHAR(100), reason VARCHAR(100), bis LONG, author_name VARCHAR(16))");
        update("CREATE TABLE IF NOT EXISTS Mute (Name VARCHAR(16), UUID VARCHAR(100), reason VARCHAR(100), bis LONG, author_name VARCHAR(16))");
        update("CREATE TABLE IF NOT EXISTS Ban_Points (Name VARCHAR(16), UUID VARCHAR(100), banpoints INT)");
        update("CREATE TABLE IF NOT EXISTS Server_Counts (Server VARCHAR(100),ban INT(100), mute INT(100), players INT(100))");
        update("CREATE TABLE IF NOT EXISTS Rewards (Name VARCHAR(16), UUID VARCHAR(100), stuendlich LONG, taeglich LONG, woechentlich LONG)");
    }


}
