/*
 * Copyright @RoyalByte 2018
 */

package net.royalbyte.skypvp;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class News {

    public static File file = new File("plugins/SkyPvP/", "news-3.1.4.yml");
    @SuppressWarnings("static-access")
    public static YamlConfiguration cfg = new YamlConfiguration().loadConfiguration(file);

    public static void createFile() {
        new File("plugins/SkyPvP/").mkdir();
        if (!file.exists()) {
            try {
                file.createNewFile();
                cfg.options().copyDefaults(true);

                cfg.options().header("Die Neusten Updates des Plugins! [3.1.4]");

                set("news.1", " Es wurden Aliases zu den Befehlen /teamchat [tc] ; /workbench [wb] ; /teamspeak [ts] ; /broadcast [bc] und /enderchest [ec] ");
                set("news.2", " Es wurde ein Warp System hinzugefügt");
                set("news.3", " Es wurde ein /teamspeak Befehlt hinzugefügt");
                set("news.4", " Alle neuen Permissions und neu einzutragenen Werte in der Config:");
                set("#","");

                set("permssion.1"," skypvp.setwarp ; skypvp.delwarp");
                set("p#","");
                set("Teamspeak-Domain" ,"DeinServer.DE");


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
                public static long getLong(String path) {
                    return Long.parseLong(cfg.getString(path));
                }

                public static String getString(String path) {
                    return cfg.getString(path).replaceAll("&", "§").replaceAll("%PREFIX%", Data.prefix);
                }

                public static Boolean getBoolean(String path) {
                    return cfg.contains(path) ? cfg.getBoolean(path) : null;
                }

                public static Integer getInt(String path) {
                    return cfg.contains(path) ? cfg.getInt(path) : null;
                }

                public static void set(String path, Object o) {
                    cfg.set(path, o);
                    save();
                }

                public static Object get(String path) {
                    return cfg.contains(path) ? cfg.get(path) : null;
                }

                public static void save() {
                    try {
                        cfg.save(file);
                    } catch (IOException e) {
                        SkyPvP.getInstance().sendConsoleError("Konnte §4NEWS.YML §cnicht speichern.");
                    }
                }
}