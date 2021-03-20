/*
 * ---------------------------------------
 * Copyright @RoyalByte | Adrian Schiel
 * Youtube : RoyalByte Developer
 * Skype: RoyalByte
 * Website: RoyalByte.net
 * ---------------------------------------
 */

package net.royalbyte.skypvp;

import java.io.*;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.royalbyte.skypvp.clearlag.Lag;
import net.royalbyte.skypvp.commands.*;
import net.royalbyte.skypvp.listener.*;
import net.royalbyte.skypvp.timer.ClearLag;
import net.royalbyte.skypvp.utils.*;
import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.royalbyte.skypvp.manager.Manager_Rewards;
import net.royalbyte.skypvp.mysql.Counts;
import net.royalbyte.skypvp.mysql.MySQL;
import net.royalbyte.skypvp.mysql.Ranking;
import net.royalbyte.skypvp.timer.AutoMsg;
import net.royalbyte.skypvp.timer.Updater;
import org.bukkit.scheduler.BukkitRunnable;

public class SkyPvP extends JavaPlugin {

    private static SkyPvP instance;
    SkyPvP plugin;
    private Inventories inventories;
    private AutoMsg automsg;
    private Items items;
    private Wartung wartung;
    private Ranking ranking;
    private Updater updater;
    private ClearLag clearLag;
    private Crate crate;
    private Rang rang;
    private Tablist tablist;
    private SBoard scoreboard;
    private Counts counts;
    private Kits kits;
    private Umfrage umfrage;
    private Manager_Rewards rewards;
    private Shop shop;
//	private SkyBlock skyblock;

    private boolean status;
    private boolean enablemsg;


    private String prefix;
    private String noperm;
    private String must_a_player;
    private String player_not_online;
    private String header;

    public static boolean UpdateV = false;
    public String Version = getDescription().getVersion();

    private static SkyPvP skyBlock;

    public World world;


    public Map<Player, Player> playermanager = new HashMap<>();

    public void createConfig() {
        Config.createFile();
        News.createFile();
    }

    @Override
    public void onEnable() {
		instance = this;
        createConfig();
        skyBlock = this;
        File file = new File("plugins/SkyPvP/", "config.yml");
        YamlConfiguration cfg = new YamlConfiguration().loadConfiguration(file);


		MySQL.host = cfg.getString("MySQL.host");
		MySQL.password = cfg.getString("MySQL.password");
		MySQL.database = cfg.getString("MySQL.database");
		MySQL.username = cfg.getString("MySQL.username");
		MySQL.port = cfg.getInt("MySQL.port");

		MySQL.aktiv = true;
		MySQL.connect();
		MySQL.createTable();

        prefix = cfg.getString("prefix").replaceAll("&", "§");
        noperm = cfg.getString("noperm").replaceAll("&", "§").replaceAll("%PREFIX%", prefix);
        must_a_player = cfg.getString("must_a_player").replaceAll("&", "§").replaceAll("%PREFIX%", prefix);
        player_not_online = cfg.getString("player_not_online").replaceAll("&", "§").replaceAll("%PREFIX%", prefix);
        header = cfg.getString("header").replaceAll("&", "§").replaceAll("%PREFIX%", prefix);

        if (!Manager.datayml.exists()) {
            try {
                Manager.datayml.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Manager.save();

        sendConsoleInfo("§7§M§l-------------------------------------------------");
        sendConsoleInfo("Das §2Plugin §awird gestartet.");
        sendConsoleInfo("...");

        sendConsoleInfo("§7Version: §b" + Version);
        broadcastInfo("§7Version: §b" + Version);


        try {
            inventories = new Inventories(this);
            automsg = new AutoMsg();
            items = new Items(this);
            registerCommands();
            wartung = new Wartung();
            ranking = new Ranking();
            updater = new Updater();
            clearLag = new ClearLag();
            crate = new Crate();
            rang = new Rang();
            tablist = new Tablist();
            scoreboard = new SBoard();
            counts = new Counts();
            kits = new Kits();
            umfrage = new Umfrage();
            rewards = new Manager_Rewards();
            enablemsg = Config.getBoolean("enablemsg");
            // skyblock = newF SkyBlock();
            PluginManager pm = Bukkit.getPluginManager();


            sendConsoleInfo("Alle §2Classen §awurden initialisiert.");
        } catch (Exception e) {
            sendConsoleError("Fehler beim initialisieren der §4Classen §c.");
        }

//		* REPORT

        Command_Report.createFile();
        Command_Report.registerReportetLIst();

//		* RANG
        getRang().createScoreboard();
        Bukkit.getOnlinePlayers().forEach(all -> {
            getRang().setprefix(all);
        });


//		* SHOP

        shop = new Shop();

////		 * MySQL


//		 * Register


        registerCommands();
        new BukkitRunnable(){
            @Override
            public void run() {
                registerEvents();
            }
        }.runTaskLater(this, 20);
        getScoreboard().setDefaultScores();
        getScoreboard().registerList();
        counts.create();

        if (cfg.getBoolean("ClearLag.enable")) {
            ClearLag.clearLag();
            Bukkit.getConsoleSender().sendMessage(Config.getString("prefix") + "§aClearLag ist aktiv");
        } else {
            Bukkit.getConsoleSender().sendMessage(Config.getString("prefix") + "§cClearLag ist deaktiviert");
        }

//		 * Timer

        getAutomsg().start();
        getUpdater().updater();


//		* Status + Enable MSG

        status = true;

        sendEnablemsg();
        sendConsoleInfo("Das §2Plugin §awurde gestartet.");

        getRewards().createRewardFile();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Lag(), 100L, 1L);

    }

    public void sendMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(Config.getString("prefix") + message);


    }

    public static SkyPvP getSkyBlock() {
        return skyBlock;
    }


    @Override
    public void onDisable() {

        sendConsoleInfo(header);
        sendConsoleInfo("Das §2Plugin §awird geschlossen.");
        sendConsoleInfo("...");

//		 * MySQL

        MySQL.close();
        MySQL.aktiv = false;

//		 * Essentials

        removefromMode();

//		Status + Disable MSG

        status = false;
        sendDisEnablemsg();
        sendConsoleInfo("Das §2Plugin §awurde geschlossen.");

    }


    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new Listener_Join(), this);
        pm.registerEvents(new Listener_BlockPlace(), this);
        pm.registerEvents(new Listener_BlockBreak(), this);
        pm.registerEvents(new Listener_Death(), this);
        pm.registerEvents(new Listener_Quit(), this);
        pm.registerEvents(new Listener_FreeFrames(), this);
        pm.registerEvents(new Listener_Login(), this);
        pm.registerEvents(new Listener_Mute(), this);
        pm.registerEvents(new Listener_data(), this);
        pm.registerEvents(new Ranking(), this);
        pm.registerEvents(new Command_SkyPvP(), this);
        pm.registerEvents(new Listener_Move(), this);
        pm.registerEvents(new Listener_ServerPing(), this);
        pm.registerEvents(new Listener_CookieClicker(), this);

        pm.registerEvents(new Listener_SkyPvP(), this);
        pm.registerEvents(new Listener_Warps(), this);
        pm.registerEvents(new Listener_Kits(), this);
        pm.registerEvents(new Listener_ChatMute(), this);
        pm.registerEvents(new Listener_Achievment(), this);
        pm.registerEvents(new Listener_Chat(), this);
        pm.registerEvents(new Listener_CMDWATCH(), this);
        pm.registerEvents(new Listener_Ranggutschein(), this);
        pm.registerEvents(new Listener_Rewards(), this);
        pm.registerEvents(new Listener_invsee(), this);
        pm.registerEvents(new Shop(), this);
        pm.registerEvents(new Crate(), this);
        pm.registerEvents(new Listener_Mobs(), this);
        pm.registerEvents(new Command_enderchest(), this);
        pm.registerEvents(new Listener_CookieShop(), this);
        sendConsoleInfo("Alle §2Events §awurden geladen.");
    }

    private void registerCommands() {
        getCommand("skypvp").setExecutor(new Command_SkyPvP());
        getCommand("vote").setExecutor(new Command_Vote());
        getCommand("ip").setExecutor(new Command_IP());
        getCommand("gamemode").setExecutor(new Command_GameMode());
        getCommand("youtuber").setExecutor(new Command_Youtuber());
        getCommand("online").setExecutor(new Command_Online());
        getCommand("fly").setExecutor(new Command_Fly());
        getCommand("vanish").setExecutor(new Command_Vanish());
        getCommand("ban").setExecutor(new Command_Ban());
        getCommand("unban").setExecutor(new Command_UnBan());
        getCommand("banpoints").setExecutor(new Command_BanPoints());
        getCommand("unmute").setExecutor(new Command_unmute());
        getCommand("mute").setExecutor(new Command_mute());
        getCommand("data").setExecutor(new Command_Data());
        getCommand("top").setExecutor(new Command_TOP());
        getCommand("kick").setExecutor(new Command_Kick());
        getCommand("warps").setExecutor(new Command_Warps());
        getCommand("kits").setExecutor(new Command_kits());
        getCommand("setkit").setExecutor(new Command_setkit());
        getCommand("day").setExecutor(new Command_day());
        getCommand("night").setExecutor(new Command_night());
        getCommand("coins").setExecutor(new Command_coins());
        getCommand("points").setExecutor(new Command_Points());
        getCommand("teamspeak").setExecutor(new Command_Teamspeak());
        getCommand("delwarp").setExecutor(new Command_delwarp());
        getCommand("kills").setExecutor(new Command_kills());
        getCommand("deaths").setExecutor(new Command_deaths());
        getCommand("setwarp").setExecutor(new Command_setwarp());
        getCommand("warp").setExecutor(new Command_Warp());
        getCommand("cookies").setExecutor(new Command_cookies());
        getCommand("umfrage").setExecutor(new Command_Umfrage());
        getCommand("ja").setExecutor(new Command_Ja());
        getCommand("nein").setExecutor(new Command_Nein());
        getCommand("feed").setExecutor(new Command_feed());
        getCommand("heal").setExecutor(new Command_heal());
        getCommand("ping").setExecutor(new Command_ping());
        getCommand("nameitem").setExecutor(new Command_nameitem());
        getCommand("skull").setExecutor(new Command_skull());
        getCommand("discord").setExecutor(new Command_discord());
        getCommand("chatclear").setExecutor(new Command_chatclear());
        getCommand("chatmute").setExecutor(new Command_chatmute());
        getCommand("broadcast").setExecutor(new Command_broadcast());
        getCommand("cmdwatch").setExecutor(new Command_cmdwatch());
        getCommand("cmdwatchlist").setExecutor(new Command_cmdwatchlist());
        getCommand("flylist").setExecutor(new Command_flylist());
        getCommand("vanishlist").setExecutor(new Command_vanishlist());
        getCommand("teamchat").setExecutor(new Command_teamchat());
        getCommand("pay").setExecutor(new Command_pay());
        getCommand("stats").setExecutor(new Command_stats());
        getCommand("event").setExecutor(new Command_event());
        getCommand("ranggutschein").setExecutor(new Command_ranggutschein());
        getCommand("manage").setExecutor(new Command_manage());
        getCommand("report").setExecutor(new Command_Report());
        getCommand("rewards").setExecutor(new Command_rewards());
        getCommand("msg").setExecutor(new Command_MSG());
        getCommand("remsg").setExecutor(new Command_remsg());
        getCommand("invsee").setExecutor(new Command_invsee());
        getCommand("shop").setExecutor(new Shop());
        getCommand("crate").setExecutor(new Crate());
        getCommand("mobs").setExecutor(new Command_mobs());
        getCommand("workbench").setExecutor(new Command_workbench());
        getCommand("anvil").setExecutor(new Command_anvil());
        getCommand("enderchest").setExecutor(new Command_enderchest());
        getCommand("rain").setExecutor(new Command_rain());
        getCommand("setinstantkill").setExecutor(new Command_setInstantKill());
        getCommand("motd").setExecutor(new Command_motd());
        getCommand("spawn").setExecutor(new Command_spawn());
        getCommand("lag").setExecutor(new Command_lag());
        getCommand("clearlag").setExecutor(new Command_lag());
        getCommand("update").setExecutor(new Command_Update());
        getCommand("repair").setExecutor(new Command_Repair());
        getCommand("stack").setExecutor(new Command_stack());
        // MUST TO REGISTER IN PLUGIN.YML

//

        sendConsoleInfo("Alle §2Commands §awurden geladen.");
    }

    public static SkyPvP getInstance() {
        return instance;
    }

//	public SkyBlock getSkyblock() {
//		return skyblock;
//	}

    public Manager_Rewards getRewards() {
        return rewards;
    }

    public Kits getKits() {
        return kits;
    }

    public Umfrage getUmfrage() {
        return umfrage;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getNoperm() {
        return noperm;
    }

    public Counts getCounts() {
        return counts;
    }

    public String getMust_a_player() {
        return must_a_player;
    }

    public Shop getShop() {
        return shop;
    }

    public String getPlayer_not_online() {
        return player_not_online;
    }

    public String dataname = "SkyPvPv3";

    public String getHeader() {
        return header;
    }

    public SBoard getScoreboard() {
        return scoreboard;
    }

    public Rang getRang() {
        return rang;
    }

    public boolean getStatus() {
        return status;
    }

    public Tablist getTablist() {
        return tablist;
    }

    public Crate getCrate() {
        return crate;
    }

    public Wartung getWartung() {
        return wartung;
    }

    public Items getItems() {
        return items;
    }

    public Inventories getInventories() {
        return inventories;
    }

    public AutoMsg getAutomsg() {
        return automsg;
    }

    public Ranking getRanking() {
        return ranking;
    }

    public Updater getUpdater() {
        return updater;
    }

    public ClearLag getLagger() {
        return clearLag;
    }

    public String getEndfrommillis(long millis) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
        Date date = new Date(millis);
        return "" + sdf.format(date);
    }

    public void sendConsoleError(String error) {
        Bukkit.getConsoleSender().sendMessage("§fERROR §7: " + "§c" + error);
    }

    public void sendConsoleInfo(String info) {
        Bukkit.getConsoleSender().sendMessage("§fINFO §7: " + "§a" + info);
    }

    public void broadcastError(String error) {
        Bukkit.broadcastMessage("§fERROR §7: " + "§c" + error);
    }

    public void broadcastInfo(String info) {
        Bukkit.broadcastMessage("§fINFO §7: " + "§a" + info);
    }

    public void disableEntityAI(Entity entity) {
        net.minecraft.server.v1_8_R3.Entity nmsEnt = ((CraftEntity) entity).getHandle();
        NBTTagCompound tag = nmsEnt.getNBTTag();

        if (tag == null) {
            tag = new NBTTagCompound();
        }

        nmsEnt.c(tag);
        tag.setInt("NoAI", 1);
        nmsEnt.f(tag);
    }

    public void sendPacket(Player p, Packet<?> packet) {
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
    }

    private void removefromMode() {
        try {
            Command_Vanish.vanish.forEach((v) -> {
                v.sendMessage(Config.getString("Command.vanish").replaceAll("%MODE%", "OFF"));
                Bukkit.getOnlinePlayers().forEach((online) -> {
                    online.showPlayer(v);
                });
            });

            Command_Fly.fly.forEach((player) -> {
                player.sendMessage(Config.getString("Command.fly").replaceAll("%MODE%", "OFF"));
                player.setFlying(false);
                player.setAllowFlight(false);
            });
            sendConsoleInfo("Alle §2Spieler §awurden aus einem §2Modus §aentfernt.");
        } catch (Exception e) {
            sendConsoleError("Fehler beim enfernen der §4Spieler§c.");
        }
    }

    private String getStatusString() {
        if (status) {
            return "§aAktiviert";
        } else {
            return "§cDeaktiviert";
        }
    }

    private static String ReadURL(String URL) {
        String re = "";
        try {
            java.net.URL url = new URL(URL);
            Reader is = new InputStreamReader(url.openStream());
            BufferedReader in = new BufferedReader(is);
            String s;
            while ((s = in.readLine()) != null) {
                re = re + " " + s;
            }
            in.close();
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException: " + e);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return re;
    }


    private void sendEnablemsg() {
        if (enablemsg) {
            Bukkit.broadcastMessage("§fINFO §7: " + header);
            broadcastInfo("§7Name : §b" + getDescription().getName());
            broadcastInfo("§7Plugin by : §b" + getDescription().getAuthors());
            if (UpdateV == true) {
                broadcastInfo("§4§k-------------------------------------------------");
                broadcastInfo("§cVersion: §b" + Version + " §8[§4Veraltet§8]");
                sendConsoleInfo("§7Lade dir die neuste Version für die weiter Nutzung herunter...");
                sendConsoleInfo("§a§lhttps://www.byte-evolve.de/konto/downloads/");
                broadcastInfo("§4§k-------------------------------------------------");
            } else {

                broadcastInfo("§7Version: §b" + Version + "");

            }


            broadcastInfo("§7MySQL : §b" + MySQL.getMySQLEnabledMSG());
            //broadcastInfo("§7SkyBlock : §b" + getSkyblock().getSkyBlockEnabledMSG());
            broadcastInfo("§7Aktiv : §b" + getStatusString());
            sendInfos();
            Bukkit.broadcastMessage("§fINFO §7: " + header);
        }
    }

    private void sendDisEnablemsg() {
        if (enablemsg) {
            Bukkit.broadcastMessage("§fINFO §7: " + header);
            broadcastInfo("§7Name : §b" + getDescription().getName());
            broadcastInfo("§7Plugin by : §b" + getDescription().getAuthors());
            broadcastInfo("§7Version: §b" + Version + "");

        }


        broadcastInfo("§7MySQL : §b" + MySQL.getMySQLEnabledMSG());
        //broadcastInfo("§7SkyBlock : §b" + getSkyblock().getSkyBlockEnabledMSG());
        broadcastInfo("§7Aktiv : §b" + getStatusString());
        sendInfos();
        Bukkit.broadcastMessage("§fINFO §7: " + header);

    }

    public void sendInfos() {
        IChatBaseComponent Kanal = ChatSerializer.a("{\"text\":\"" + "§fINFO §7: " + "§7Youtube Kanal§7 : "
                + "\",\"extra\":[{\"text\":\"§a§lAnschauen\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"§7Klicke zum §aöffnen\"},\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://www.youtube.com/channel/UCjZg2q-zup95VUg-sk8-Idg?view_as=subscriber\"}}]}");
        PacketPlayOutChat Kanal_msg = new PacketPlayOutChat(Kanal);

        IChatBaseComponent Plugin = ChatSerializer.a("{\"text\":\"" + "§fINFO §7: " + "§7Online-Shop§7 :"
                + "\",\"extra\":[{\"text\":\" §a§lSeite öffnen\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"§7Klicke zum §aöffnen\"},\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://byte-evolve.de\"}}]}");
        PacketPlayOutChat Plugin_Msg = new PacketPlayOutChat(Plugin);
        Bukkit.getOnlinePlayers().forEach(all -> {
            ((CraftPlayer) all).getHandle().playerConnection.sendPacket(Kanal_msg);
            ((CraftPlayer) all).getHandle().playerConnection.sendPacket(Plugin_Msg);
        });
    }


}
