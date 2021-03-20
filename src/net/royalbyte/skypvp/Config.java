/*
 * ---------------------------------------
 * Copyright @RoyalByte | Adrian Schiel
 * Youtube : RoyalByte Developer
 * Skype: RoyalByte
 * Website: RoyalByte.net
 * ---------------------------------------
 */

package net.royalbyte.skypvp;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class Config {

	public static File file = new File("plugins/SkyPvP/", "config.yml");
	@SuppressWarnings("static-access")
	public static YamlConfiguration cfg = new YamlConfiguration().loadConfiguration(file);

	public static void createFile() {
		new File("plugins/SkyPvP/").mkdir();
		if (!file.exists()) {
			try {
				file.createNewFile();
				cfg.options().copyDefaults(true);

				cfg.options().header("Dass ist die Config von dem SkyPvP v3 Plugin by RoyalByte");

				// STANDART STRINGS

				set("prefix", "&b&lSkyPvP &7&l×&f ");
				set("noperm", "%PREFIX% &cDu hast dazu keine Rechte.");
				set("player_not_online", "%PREFIX% &cDer Spieler ist nicht online.");
				set("must_a_player", "%PREFIX% &cDu musst ein Spieler sein.");
				set("header", "&7&M&l-------------------------------------------------");

				// LIZENZ
				set("Lizenz", "DEINELIZENZ");
				set("KundenEmail", "DEINE_KUNDEN_EMAIL");
				// MYSQL
				set("MySQL.host", "localhost");
				set("MySQL.password", "passwd");
				set("MySQL.database", "SkyPvP");
				set("MySQL.username", "root");
				set("MySQL.port", 3306);

				// CLEARLAG

				set("ClearLag.enable" , true);
				set("ClearLag.MSG-1", "&aDer Clearlag wurde ausgeführt!");
				set("ClearLag.MSG-2", "&2       Es wurden &b %ENTITYS% &2 Entitys entfernt");
				set("ClearLag.Time", 300);

				//Coins
				set("Death.Coins" , 20);

				//Teamspeak
				set("Teamspeak-Domain" ,"DeinServer.DE");

				// Tablist

				set("tablist.enabled", true);

				set("tablist.header",
						"&7&M&l-------------------------------------------------&r"
								+ "\n\n &7Spieler &9Online&7 >&b %ONLINE%" + "\n &7Dein &9Rang &7>&b %RANG%"
								+ "\n &7Deine &9Punkte &7>&b %POINTS%" + "\n &7Deine &9Tode &7>&b %DEATHS%" + "\n");
				set("tablist.footer",
						"\n" + " &7Deine &9Cookies &7>&b %COOKIES%" + "\n &7Deine &9Coins &7>&b %COINS%"
								+ "\n &7Unser &9TeamSpeak &7>&b DeinServer.de" + "\n &7Deine &9Kills &7>&b %KILLS%"
								+ "\n\n&7&M&l-------------------------------------------------");
				// SCOREBOARD
				set("Scoreboard.name", "&b&lSkyPvP v3");

				// WARTUNG
				set("wartung-boolean", false);
				set("wartung.broadcast", "%PREFIX% &7Die Wartungen wurden&b %MODE% &7gestellt.");
				set("wartung.motd-1", "&CWARTUNG MOTD LINE1");
				set("wartung.motd-2", "&CWARTUNG MOTD LINE2");
				set("wartung.kick",
						"&7&M&l-------------------------------------------------\n\n" + "&7Du wurdest &cgekickt&7.\n"
								+ "&7Grund:&b Wartungen&7.\n" + "\n"
								+ "&7&M&l-------------------------------------------------");

				//CRATE
				set("crate.notExist", "%PREFIX% &cDas Crate gibt es noch nicht");
				set("crate.create", "%PREFIX% &7Crate:&b %NAME% &7wurde erstellt.");
				set("crate.delete", "%PREFIX% &7Crate:&b %NAME% &7wurde gelöscht.");
				set("crate.addItem", "%PREFIX% &7Zu Crate:&b %NAME% &7wurde ein Item hinzugefügt.");
				set("crate.alreadyExists", "%PREFIX% &cDas Crate gibt es schon.");
				set("crate.must_hold_item_in_hand", "%PREFIX% &cBitte halte ein Item in der Hand.");
				set("crate.help.create", "%PREFIX% &7/crate create <Name>");
				set("crate.help.delete", "%PREFIX% &7/crate delete <Name>");
				set("crate.help.addItem", "%PREFIX% &7/crate addItem <Name>");
				set("crate.help.get", "%PREFIX% &7/crate get <Name> <Amount> <Spieler>");
				set("crate.help.line-1", "%PREFIX% &7/crate create");
				set("crate.help.line-2", "%PREFIX% &7/crate delete");
				set("crate.help.line-3", "%PREFIX% &7/crate addItem");
				set("crate.help.line-4", "%PREFIX% &7/crate get");
				set("crate.help.line-5", "%PREFIX% &7/crate help");
				set("crate.getCrate", "%PREFIX% &7Du hast das Crate:&b %NAME% &7bekommen.");
				set("crate.setCrate", "%PREFIX% &7Du hast das Crate:&b %NAME% &7vergeben.");
				
				//SHOP
				set("shop.not_enough_coins", "%PREFIX% &cDu hast zu wenig Coins.");
				set("shop.help.line-1", "%PREFIX% &7/shop create");
				set("shop.help.line-2", "%PREFIX% &7/shop delete");
				set("shop.help.line-3", "%PREFIX% &7/shop addItem");
				set("shop.help.create", "%PREFIX% &7/shop create <Name> <Displayname>.");
				set("shop.help.delete", "%PREFIX% &7/shop delete <Name>.");
				set("shop.help.additem", "%PREFIX% &7/shop addItem <Name> <Price> <Displayname>");
				set("shop.categorie_not_exists", "%PREFIX% &cDie Categorie gibt es nicht.");
				set("shop.categorie_already_exists", "%PREFIX% &cDie Categorie gibt es schon.");
				set("shop.categorie_deletet", "%PREFIX% &7Die Categorie:&b %NAME% &7wurde gelöscht.");
				set("shop.must_hold_item_in_Hand", "%PREFIX% &cBitte halte ein Item in der Hand.");
				set("shop.createCategorie", "%PREFIX% &7Categorie:&b %DISPLAYNAME% &7wurde erstellt.");
				set("shop.addItem", "%PREFIX% &7Zu Categorie:&b %NAME% &7wurde das Item: &b%DISPLAYNAME% &7für &b%PRICE%-Coins &7hinzugefügt.");
				
				// UMFRAGE
				set("umfrage.ja", 0);
				set("umfrage.nein", 0);
				set("umfrage.frage", "Frage...");
				set("umfrage.cuurent", false);
				set("umfrage.line-1", "&7&M&l-------------------------------------------------");
				set("umfrage.line-2", "%PREFIX%");
				set("umfrage.line-3", "%PREFIX% &7Frage:&b %FRAGE%");
				set("umfrage.line-4", "%PREFIX% &7Mache &a/ja &7für &aJa&7.");
				set("umfrage.line-5", "%PREFIX% &7Mache &c/Nein &7für &cNein&7.");
				set("umfrage.line-6", "%PREFIX%");
				set("umfrage.line-7", "&7&M&l-------------------------------------------------");
				set("umfrage.iscuurent", "%PREFIX% &cMomentan läuft schon eine Umfrage.");
				set("umfrage.nofrage", "%PREFIX% &cMomentan läuft keine Umfrage.");
				set("umfrage.has_answered", "%PREFIX% &cDu hast schon auf die Frage geantwortet.");
				set("umfrage.answer_msg", "%PREFIX% &7Du hast auf die &bFrage &7geantwortet.");
				set("umfrage.endsin.msg", "%PREFIX% &7Die &bUmfrage &7endet in&a %SEK% Sekunden&7.");
				set("umfrage.answer.line-1", "&7&M&l-------------------------------------------------");
				set("umfrage.answer.line-2", "%PREFIX%");
				set("umfrage.answer.line-3", "%PREFIX% &7Die Frage:&b %FRAGE%");
				set("umfrage.answer.line-4",
						"%PREFIX% &7wurde mit&9 %ANSWER% &7(&9 %STIMMEN% -Stimmen&7) beantwortet.");
				set("umfrage.answer.line-5", "%PREFIX%");
				set("umfrage.answer.line-6", "&7&M&l-------------------------------------------------");

				// SKYBLOCK
				set("skyblock.enabled", true);

				// KITS
				set("Kit-1", "&b&lKit-1");
				set("Kit-2", "&b&lKit-2");
				set("Kit-3", "&b&lKit-3");
				set("Kit-4", "&b&lKit-4");
				set("Kit-5", "&b&lKit-5");
				set("Kit-6", "&b&lKit-6");
				set("Kit-7", "&b&lKit-7");
				set("Kit.Delay", 10);
				set("Kit.Delay-Enable", true);

				set("Kits.setKit", "%PREFIX% &7Das Kit&b %KITNAME% &7wurde gesetzt.");
				set("Kits.getKit", "%PREFIX% &7Du hast das Kit&b %KITNAME% &7bekommen.");
				set("Kits.notExist", "%PREFIX% &cDas Kit&b %KITNAME% &c wurde nicht gefunden.");
				// CMDWATCH
				set("cmdwatch.command.msg.own.on", "%PREFIX% &7Du bist im &bCMDWATCH-Modus&7.");
				set("cmdwatch.command.msg.own.off", "%PREFIX% &7Du bist nicht mehr im &bCMDWATCH-Modus&7.");
				set("cmdwatch.command.msg.other.on",
						"%PREFIX% &7Der Spieler&9 %PLAYER% ist nun im &bCMDWATCH-Modus&7.");
				set("cmdwatch.command.msg.other.off",
						"%PREFIX% &7Der Spieler&9 %PLAYER% ist nun nicht mehr im &bCMDWATCH-Modus&7.");
				set("cmdwatch.msg", "%PREFIX% &9%PLAYER% &7»&f %CMD%");
				// PAY
				set("command.pay.not_enough_coins", "%PREFIX% &cDu hast zu wenig Coins.");
				set("command.pay.other", "%PREFIX% &7Du hast von &9%PLAYER% &b%COINS%-Coins &7bekommen.");
				set("command.pay.own", "%PREFIX% &7Du hast &9%PLAYER% &b%COINS%-Coins &7gegeben.");
				// TEAMCHAT
				set("command.teamchat", "&b&lTeamChat &7&l×&b %PLAYER% &7&l×&f %MSG%");

				// CMDWATCHLIST
				set("command.cmdwatchlist.msg", "%PREFIX% &b&lCMDWATCH &7&l×&f %PLAYER%");
				set("command.cmdwatchlist.no_in_list", "%PREFIX% &cKein Spieler ist im &cCMDWATCH-Modus&c.");

				// FLYLIST
				set("command.flylist.msg", "%PREFIX% &b&lFLY &7&l×&f %PLAYER%");
				set("command.flylist.no_in_list", "%PREFIX% &cKein Spieler ist im &cFLY-Modus&c.");
				// VanishLIST
				set("command.vanishlist.msg", "%PREFIX% &b&lVANISH &7&l×&f %PLAYER%");
				set("command.vanishlist.no_in_list", "%PREFIX% &cKein Spieler ist im &cVANISH-Modus&c.");
				// COMMAND COINS
				set("command.coins.info", "%PREFIX% &7Du hast&b %COINS% -Coins&7.");
				set("command.coins.update", "%PREFIX% &7Du hast die &bCoins&7 von&9 %PLAYER% &7geupdatet.");

				// COMMAND COOKIES
				set("command.cookies.info", "%PREFIX% &7Du hast&b %COOKIES% -Cookies&7.");
				set("command.cookies.update", "%PREFIX% &7Du hast die &bCoins&7 von&9 %PLAYER% &7geupdatet.");

				// COMMAD RAIN
				set("command.rain", "Das Wetter wurde umgestellt");


				// COMMAND KILLS
				set("command.kills.info", "%PREFIX% &7Du hast&b %KILLS% -Kills&7.");
				set("command.kills.update", "%PREFIX% &7Du hast die &bKills&7 von&9 %PLAYER% &7geupdatet.");

				// COMMAND TODE
				set("command.tode.info", "%PREFIX% &7Du hast&b %TODE% -Tode&7.");
				set("command.tode.update", "%PREFIX% &7Du hast die &bTode&7 von&9 %PLAYER% &7geupdatet.");

				// COMMAND POINTS
				set("command.points.info", "%PREFIX% &7Du hast&b %POINTS% -Points&7.");
				set("command.points.update", "%PREFIX% &7Du hast die &bPoints&7 von&9 %PLAYER% &7geupdatet.");

				// DAY + NIGHT
				set("command.day", "%PREFIX% &7Es ist nun &bTag&7.");
				set("command.night", "%PREFIX% &7Es ist nun &bNacht&7.");

				// FEED
				set("command.feed.own", "%PREFIX% &7Du hast dein &bHunger &7aufgefüllt.");
				set("command.feed.other", "%PREFIX% &7Du hast den &bHunger &7von&9 %PLAYER% &7aufgefüllt.");
				// HEAL
				set("command.heal.own", "%PREFIX% &7Du hast dein &bLeben  &7aufgefüllt.");
				set("command.heal.other", "%PREFIX% &7Du hast den &bHunger &7von&9 %PLAYER% &7aufgefüllt.");
				// REPAIR
				set("command.repair.own", "%PREFIX% &7Du hast das Item repariert.");
				set("command.repair.other", "%PREFIX% &7Du hast alle deine Items repariert.");
				// PING
				set("command.ping.own", "%PREFIX% &7Dein Ping:&b %PING% &7.");
				set("command.ping.other", "%PREFIX% &9 %PLAYER% 's &7Ping:&b %PING% &7.");
				// NAMEITEM
				set("command.nameitem.must_hold_item_in_hand", "%PREFIX% &cDu musst ein Item in der Hand haben.");
				set("command.nameitem.msg", "%PREFIX% &7Dein Item heißt nun&b %NAME% &7.");
				// SKULL
				set("command.skull", "%PREFIX% &7Du hast den Kopf von&b %NAME% &7bekommen.");
				// DISCORD
				set("command.discord", "%PREFIX% &7Unser &9Discord&7: &bhttps://discordapp.com/invite/8Ae8jWc");
				// Server
				set("getrankingshards", "%PREFIX% &7Du hast die &bRanking-Shards&7 bekommen.");
				set("getwand", "%PREFIX% &7Du hast den &bWand&7 bekommen.");
				set("getCookie", "%PREFIX% &7Du hast den &bCookie&7 bekommen.");
				// CHATCLEAR
				set("command.chatclear", "%PREFIX% &7Der Chat wurde von&b %PLAYER% &9geleert&7.");
				// CHATMUTE
				set("command.chatmute.enabled", false);
				set("command.chatmute.ismutet",
						"%PREFIX% &7Du kannst &cnicht &7schreiben, weil der &bChat &causgestellt &7ist.");
				set("command.chatmute.mute", "%PREFIX% &7Der Chat wurde von&b %PLAYER% &causgestellt&7.");
				set("command.chatmute.unmute", "%PREFIX% &7Der Chat wurde von&b %PLAYER% &aangestellt&7.");
				// COOKIE
				set("cookie.actionbar", "&b+ %CS% -Cookies &7| Deine Cookies&9 %COOKIES%");

				set("cookie.cost.1", 100);
				set("coins.become.1", 1);
				set("cookie.cost.2", 1000);
				set("coins.become.2", 100);
				set("cookie.cost.3", 10000);
				set("coins.become.3", 1000);
				// BROADCAST
				set("command.broadcast.line-1", " ");
				set("command.broadcast.line-2", "%PREFIX% %MSG%");
				set("command.broadcast.line-3", " ");

				// STATS
				set("command.stats.line-1", "%PREFIX% &fStats von &b&l%PLAYER% &7&l×");
				set("command.stats.line-2", "%PREFIX% &fKills &7&l×&b&l %KILLS%");
				set("command.stats.line-3", "%PREFIX% &fTode &7&l×&b&l %TODE%");
				set("command.stats.line-4", "%PREFIX% &fK/D &7&l×&b&l %KD%");
				set("command.stats.line-5", "%PREFIX% &fPoints &7&l×&b&l %POINTS%");
				set("command.stats.line-6", "%PREFIX% &fCoins &7&l×&b&l %COINS%");
				//WORKBENCH
				set("command.workbench", "%PREFIX% &7Du öffnest eine &bWorkbench&7.");
				//ANVIL
				set("command.anvil", "%PREFIX% &7Du öffnest einen &bAnvil&7.");
				
				//ENDERCHEST
				set("command.enderchest.own", "%PREFIX% &7Du bist nun in deiner &bEnderchest&7.");
				set("command.enderchest.other", "%PREFIX% &7Du bist nun in einer &bEnderchest&7.");
				// MOTD

				set("motd-1", "&CMOTD LINE1");
				set("motd-2", "&CMOTD LINE2");

				// REPORT
				set("report.help.line-1", "&7&M&l-------------------------------------------------");
				set("report.help.line-2", "%PREFIX% &7/report &8<&blogin&7,&blogout&8>");
				set("report.help.line-3", "%PREFIX% &7/report &8<&bend&8>");
				set("report.help.line-4", "%PREFIX% &7/report &8<&bget> &8<&bPlayer&8>");
				set("report.help.line-5", "%PREFIX% &7/report &8<&bPlayer> &8<&bReason&8>");
				set("report.help.line-6", "%PREFIX% &7/report &8<&blist> ");
				set("report.help.line-7", "%PREFIX% &7/report &8<&binfo> &8<&bPlayer&8>");
				set("report.help.line-8", "%PREFIX% &7/report &8<&bclearlist>");
				set("report.help.line-9", "&7&M&l-------------------------------------------------");
				set("report.login.is_always", "%PREFIX% &cDu bist schon eingeloggt.");
				set("report.login.msg", "%PREFIX% &7Du bist nun im &bReport-System &aeingeloggt&7.");
				set("report.logout.is_not", "%PREFIX% &cDu bist nicht eingeloggt.");
				set("report.logout.msg", "%PREFIX% &7Du hast dich aus dem &bReport-System &causgeloggt&7.");
				set("report.end.not_in_work", "%PREFIX% &cDu bearbeitetst keinen Spieler.");
				set("report.end.msg", "%PREFIX% &7Du bearbeitetst nun &b%PLAYER% &7nicht mehr.");
				set("report.create.has_already_reportet",
						"%PREFIX% &cBitte warte einen moment bis du einen Spieler wieder reportest.");
				set("report.create.is_already_reportet", "%PREFIX% &cDer Spieler &b%PLAYER% &cwurde schon reportet.");
				set("report.create.player_msg",
						"%PREFIX% &7Du hast den Spieler &b%PLAYER% &7für den Grund &9%REASON% &7reportet.");
				set("report.notify.line-1", "&7&M&l-------------------------------------------------");
				set("report.notify.line-2", "%PREFIX% &9%REPORTER% &7hat &c%PLAYER% &7reportet.");
				set("report.notify.line-3", "%PREFIX% &7Der Grund beträgt: §b%REASON%&7.");
				set("report.notify.line-4", "&7&M&l-------------------------------------------------");
				set("report.can_now_report", "%PREFIX% &7Du kannst wieder einen Spieler &breporten&7.");
				set("report.is_in_work", "%PREFIX% &cDu bearbeitest schon einen Spieler");
				set("report.is_not_reportet", "%PREFIX% &cDer Spieler wurde nicht reportet.");
				set("report.get", "%PREFIX% &7Du bearbeitest nun &b%PLAYER%&7.");
				set("report.list", "%PREFIX% &b%PLAYER%&7, Grund:&9 %REASON%&7, von: &a%REPORTER%");
				set("report.clearlist", "%PREFIX% &7Du hast die &bReport-Liste &7geleert.");
				set("report.no_body_is_reportet", "%PREFIX% &cEs wurde kein Spieler reportet.");

				// EVENT
				set("event.help.line-1", "%PREFIX% &7/event &8<&bkit&8> &bKit-ID&7.");
				set("event.help.line-2", "%PREFIX% &7/event &8<&bitem&8>&7.");
				set("event.help.line-3", "%PREFIX% &7/event &8<&bcoins&8> &8<&bCoins-High&8>&7.");
				set("event.help.line-4", "%PREFIX% &7/event &8<&brang&8> &8<&bRang-Name&8>&7.");
				set("event.item.must_hold_item_in_hand", "%PREFIX% &cHalte ein Item in der Hand.");
				set("event.item.msg", "%PREFIX% &7Alle Spieler haben ein &9Item &7von &b%PLAYER% &7bekommen.");
				set("event.coins.boolean", false);
				set("event.coins.is_current", "%PREFIX% &7Momentan werden schon &bCoins &7verlost.");
				set("event.coins.coins_must_over_null", "%PREFIX% &cDie angegeben Coins müssen über 0 sein.");
				set("event.coins.timer",
						"%PREFIX% &7Es werden &b%COINS%-Coins &7in &9%TIME%-Sekunde&7(&9n&7) verlost.");
				set("event.coins.winner", "%PREFIX% &b%PLAYER% &7hat &9%COINS%-Coins &agewonnen.");
				set("event.rang.boolean", false);
				set("event.rang.is_current", "%PREFIX% &7Momentan werden schon &bCoins &7verlost.");
				set("event.rang.timer",
						"%PREFIX% &7Es wird der Rang: &b%RANG% &7in &9%TIME%-Sekunde&7(&9n&7) verlost.");
				set("event.rang.winner", "%PREFIX% &b%PLAYER% &7hat den Rang : %RANG% &agewonnen.");

				// RANGGUTSCHEIn
				set("command.ranggutschein.get",
						"%PREFIX% &7Du hast einen &9Rang-Gutschein &7für den &b%RANG%-Rang &7bekommen.");
				set("command.ranggutschein.is_in_group", "%PREFIX% &cDu hast schon den Rang.");
				set("command.ranggutschein.use", "%PREFIX% &9%PLAYER% &7hat nun den Rang: &b%RANG%");

				// Join-Event

				set("join.teammsg", "%PREFIX% &7Das &9Team-Mitglied&b %PLAYER% &7hat den Server &abetreten &7.");
				set("join.title.header", "&9&lWillkommen");
				set("join.title.footer", "&7Auf &bMeinserver.de");
				set("join.newplayer", "%PREFIX% &7Der Spieler&b %PLAYER% &7ist neu auf dem Server. [&b#%ID%&7]");
				set("join.chatclear", true);

				// VOTE-NACHRICHTEN
				set("Vote.link-1", "%PREFIX% &cKOMMT BALD");
				set("Vote.link-2", "%PREFIX% &cKOMMT BALD");
				set("Vote.link-3", "%PREFIX% &cKOMMT BALD");

				// IP-COMMAND
				set("Command.ip", "%PREFIX% &7Die IP von &b%PLAYER% ist &9%IP% &7.");

				// AUTO MSG
				set("automsg.time", 300);
				set("automsg.msg-1", "%PREFIX% &cKOMMT BALD");
				set("automsg.msg-2", "%PREFIX% &cKOMMT BALD");
				set("automsg.msg-3", "%PREFIX% &cKOMMT BALD");

				// YOUTUBER-RANG
				set("youtuber.msg-1", "%PREFIX% &7Vorraussetzungen fÃ¼r den &5Youtuber-Rang:");
				set("youtuber.msg-2", "%PREFIX% &7Dein &bKanal &7muss mindestens &b500 Abonenten&7 besitzen.");
				set("youtuber.msg-3", "%PREFIX% &7Du benÃ¶tigst eine angemessene &bKlickzahl&7.");
				set("youtuber.msg-4", "%PREFIX% &7Ebenfalls musst du mindestens &b1 Video &7aufnehmen.");
				set("youtuber.msg-5", "%PREFIX% &7Dieses &bVideo &7muss in angemessener &bQualitÃ¤t &7sein.");
				set("youtuber.msg-6",
						"%PREFIX% &7Alles erfÃ¼llt? Beantrage den &5YouTuber-Rang &7im Forum &bwww.DeinServer.de");

				// ONLINE-COMMAND
				set("Command.online", "%PREFIX% &7Es sind gerade&b %ONLINE% Spieler &aonline&7.");

				// GAMEMODE-COMMAND
				set("Command.gamemode", "%PREFIX% &7Du bist nun im Gamemode:&b %GAMEMODE% &7.");

				// FlY-COMMAND
				set("Command.fly", "%PREFIX% &7Dein &9Fly-Modus&7:&b %MODE% &7.");

				// VANISH-COMMAND
				set("Command.vanish", "%PREFIX% &7Dein &9VANISH-Modus&7:&b %MODE% &7.");

				//MOBS COMMAND
				set("command.mobs.on", "%PREFIX% &7Du kannst nun die &bMobs &7töten.");
				set("command.mobs.off", "%PREFIX% &7Du kannst nun nicht mehr die &bMobs &7töten.");
				
				// QUIT
				set("Quit", "%PREFIX% &7Das &9Team-Mitglied&b %PLAYER% &7hat den Server &cverlassen &7.");

				// Location
				set("Location.setbc", "%PREFIX% &7Die Location&b %locname% &7wurde &agesetzt&7.");
				set("Location.notfound", "%PREFIX% &7Die Location&b %locname% &7wurde &cnicht&7 gefunden.");

				// CHAT
				set("chat", "%LISTNAME% %PLAYERNAME% &7» %CHATCOLOR%%MSG%");

				// Mobs
				set("Mob.name.cookie", "&7<< &b&lCookie&7 >>");
				set("Mob.name.Shop", "&7<< &b&lShop&7 >>");
				set("Mob.name.Kits", "&7<< &b&lKits&7 >>");
				set("Mob.name.Rewards", "&7<< &b&lRewards&7 >>");
				set("Mob.set", "%PREFIX% &7Der Mob &b %mobname% &7wurde &agesetzt&7.");

				// Kick
				set("Kick.authormsg", "&7Du hast&b %PLAYER% &agekickt&7.");
				set("Kick.line-1", "&7 ");
				set("Kick.line-2", "%PREFIX%&b %PLAYER% &7wurde von &b%AUTHOR% &agekickt&7.");
				set("Kick.line-3", "&7 ");
				set("Kick.message",
						"&7&M&l-------------------------------------------------\n\n" + "&7Du wurdest &cgekickt&7.\n"
								+ "&7Grund:&b %REASON%&7.\n\n"
								+ "&7&M&l-------------------------------------------------");

				// Player-Manager
				set("Player.manager.p_not_found", "%PREFIX% &cDer Spieler wurde nicht gefunden.");
				set("Player.manager.tpmsg", "%PREFIX% &7Du wurdest zu&9 %PLAYER% &bteleportiert&7,");

				// BAN
				set("Ban.message",
						"&7&M&l-------------------------------------------------\n\n" + "&7Du wurdest &cgebannt&7.\n"
								+ "&7Grund:&b %REASON%&7.\n" + "&7Bis:&b %ENDDATE%&7.\n" + "&7Von:&b %AUTHOR%&7.\n\n"
								+ "&7&M&l-------------------------------------------------");
				set("Ban.banpointsmsg.own", "%PREFIX%&7 Du hast&b %BP%-BanPoints&7.");
				set("Ban.banpointsmsg.other", "%PREFIX%&a %PLAYER% &7hat&b %BP%-BanPoints&7.");
				set("Ban.authormsg", "&7Du hast&b %PLAYER% &cgebannt&7.");
				set("Ban.line-1", "&7 ");
				set("Ban.line-2", "%PREFIX%&b %PLAYER% &7wurde von &b%AUTHOR% &cgebannt&7.");
				set("Ban.line-3", "%PREFIX%&b Grund:&c %REASON% &7| &bbis:&c %BANNEDBIS% &7.");
				set("Ban.line-4", "&7 ");
				set("Ban.one.reason", "Hacking");
				set("Ban.one.time", "2592000000");
				set("Ban.one.banpoints", 50);
				set("Ban.two.reason", "Teaming");
				set("Ban.two.time", "86400000");
				set("Ban.two.banpoints", 50);
				set("Ban.three.reason", "Bugusing");
				set("Ban.three.time", "3600000");
				set("Ban.three.banpoints", 50);
				set("Ban.four.reason", "Extrem");
				set("Ban.four.time", "-1");
				set("Ban.four.banpoints", 50);
				set("Ban.five.reason", "Skin/Name");
				set("Ban.five.time", "60000");
				set("Ban.five.banpoints", 50);
				set("Ban.six.reason", "Grund-6");
				set("Ban.six.time", "604800000");
				set("Ban.six.banpoints", 50);
				set("Ban.seven.reason", "Grund-7");
				set("Ban.seven.time", "2592000000");
				set("Ban.seven.banpoints", 50);
				set("Ban.eight.reason", "Grund-8");
				set("Ban.eight.time", "2592000000");
				set("Ban.eight.banpoints", 50);
				set("Ban.nine.reason", "Grund-9");
				set("Ban.nine.time", "2592000000");
				set("Ban.nine.banpoints", 50);
				set("Ban.ten.reason", "Grund-10");
				set("Ban.ten.time", "2592000000");
				set("Ban.ten.banpoints", 50);

				set("Ban.help.line-1", "&7&M&l-------------------------------------------------");
				set("Ban.help.line-2", "&7");
				set("Ban.help.line-3", "&b/ban <Spieler> <ID>");
				set("Ban.help.line-4", "&bID-1 : &cHacking &7(30-Tage)/(50-BP)");
				set("Ban.help.line-5", "&bID-2 : &cTeaming &7(1-Tag)/(50-BP)");
				set("Ban.help.line-6", "&bID-3 : &cBugusing &7(1-Stunde)");
				set("Ban.help.line-7", "&bID-4 : &cExtrem &7(Permanent)");
				set("Ban.help.line-8", "&bID-5 : &cSkin/Name &7(1-Minute)");
				set("Ban.help.line-9", "&bID-6 : &cGrund-6 &7(1-Woche)");
				set("Ban.help.line-10", "&bID-7 : &cGrund-7 &7(30-Tage)");
				set("Ban.help.line-11", "&bID-8 : &cGrund-8 &7(30-Tage)");
				set("Ban.help.line-12", "&bID-9 : &cGrund-9 &7(30-Tage)");
				set("Ban.help.line-13", "&bID-10 : &cGrund-10 &7(30-Tage)");
				set("Ban.help.line-14", "&7");
				set("Ban.help.line-15", "&7&M&l-------------------------------------------------");
				set("UnBan.notbanned", "&7Der Spieler ist nicht &cgebannt&7.");
				set("UnBan.authormsg", "&7Du hast&b %PLAYER% &aentbannt&7.");
				set("UnBan.line-1", "&7 ");
				set("UnBan.line-2", "%PREFIX%&b %PLAYER% &7wurde von &b%AUTHOR% &aentbannt&7.");
				set("UnBan.line-3", "&7 ");
				set("UnBan.banpoints.remove", true);
				set("UnBan.banpoints.size", 10);

				// COMMAND-MUTE

				set("Unmute.notbanned", "&7Der Spieler ist nicht &cgemutet&7.");
				set("Unmute.authormsg", "&7Du hast&b %PLAYER% &aentmutet&7.");
				set("Unmute.line-1", "&7 ");
				set("Unmute.line-2", "%PREFIX%&b %PLAYER% &7wurde von &b%AUTHOR% &aentbannt&7.");
				set("Unmute.line-3", "&7 ");
				set("Unmute.banpoints.remove", true);
				set("Unmute.banpoints.size", 10);

				set("Mute.help.line-1", "&7&M&l-------------------------------------------------");
				set("Mute.help.line-2", "&7");
				set("Mute.help.line-3", "&b/mute <Spieler> <ID>");
				set("Mute.help.line-4", "&bID-1 : &cBeleidgung &7(30-Tage)/(50-BP)");
				set("Mute.help.line-5", "&bID-2 : &cSpam &7(1-Tag)/(50-BP)");
				set("Mute.help.line-6", "&bID-3 : &cChatverhalten &7(1-Stunde)");
				set("Mute.help.line-7", "&bID-4 : &cExtrem &7(Permanent)");
				set("Mute.help.line-8", "&bID-5 : &cWerbung &7(1-Minute)");
				set("Mute.help.line-9", "&7");
				set("Mute.help.line-10", "&7&M&l-------------------------------------------------");

				set("Mute.one.reason", "Beleidgung");
				set("Mute.one.time", "2592000000");
				set("Mute.one.banpoints", 25);
				set("Mute.two.reason", "Spam");
				set("Mute.two.time", "86400000");
				set("Mute.two.banpoints", 25);
				set("Mute.three.reason", "Chatverhalten");
				set("Mute.three.time", "3600000");
				set("Mute.three.banpoints", 25);
				set("Mute.four.reason", "Extrem");
				set("Mute.four.time", "-1");
				set("Mute.four.banpoints", 25);
				set("Mute.five.reason", "Werbung");
				set("Mute.five.time", "60000");
				set("Mute.five.banpoints", 25);

				set("Mute.authormsg", "&7Du hast&b %PLAYER% &cgemutet&7.");
				set("Mute.line-1", "&7 ");
				set("Mute.line-2", "%PREFIX%&b %PLAYER% &7wurde von &b%AUTHOR% &cgemutet&7.");
				set("Mute.line-3", "%PREFIX%&b Grund:&c %REASON% &7| &bbis:&c %BANNEDBIS% &7.");
				set("Mute.line-4", "&7 ");

				set("Mute.authormsg", "&7Du hast&b %PLAYER% &cgemutet&7.");
				set("Mute.msg.line-1", "&7 ");
				set("Mute.msg.line-2", "%PREFIX% &7Du wurdest &cgemutet&7.");
				set("Mute.msg.line-3", "%PREFIX%&b Grund:&c %REASON% &7|&bbis:&c%BANNEDBIS%&7.");
				set("Mute.msg.line-4", "&7 ");

				set("banpoints.max", 1000);
				set("banpoints.banreason", "BAN-POINTS");
				set("banpoints.time", -1);


				// RANG

				set("rang.isinGroupmsg", "%PREFIX% &cDer Spieler ist schon in der Gruppe");

				set("rang.rang-1.chatcolor", "&7");
				set("rang.rang-1.permissionsexrang", "Owner");
				set("rang.rang-1.prefix", "&cOwner");
				set("rang.rang-1.tag", "&cO&7:&c");

				set("rang.rang-2.chatcolor", "&7");
				set("rang.rang-2.permissionsexrang", "Admin");
				set("rang.rang-2.prefix", "&cAdmin");
				set("rang.rang-2.tag", "&cA&7:&c");

				set("rang.rang-3.chatcolor", "&7");
				set("rang.rang-3.permissionsexrang", "SrMod");
				set("rang.rang-3.prefix", "&9SrMod");
				set("rang.rang-3.tag", "&9SRM&7:&9");

				set("rang.rang-4.chatcolor", "&7");
				set("rang.rang-4.permissionsexrang", "Mod");
				set("rang.rang-4.prefix", "&9Mod");
				set("rang.rang-4.tag", "&9Mod&7:&9");

				set("rang.rang-5.chatcolor", "&7");
				set("rang.rang-5.permissionsexrang", "Sup");
				set("rang.rang-5.prefix", "&aSup");
				set("rang.rang-5.tag", "&aSup&7:&a");

				set("rang.rang-6.chatcolor", "&7");
				set("rang.rang-6.permissionsexrang", "T-Sup");
				set("rang.rang-6.prefix", "&aT-Sup");
				set("rang.rang-6.tag", "&aT-Sup&7:&a");

				set("rang.rang-7.chatcolor", "&7");
				set("rang.rang-7.permissionsexrang", "Dev");
				set("rang.rang-7.prefix", "&bDev");
				set("rang.rang-7.tag", "&bDev&7:&b");

				set("rang.rang-8.chatcolor", "&7");
				set("rang.rang-8.permissionsexrang", "Builder");
				set("rang.rang-8.prefix", "&bBuilder");
				set("rang.rang-8.tag", "&bB&7:&b");

				set("rang.rang-9.chatcolor", "&7");
				set("rang.rang-9.permissionsexrang", "Champion");
				set("rang.rang-9.prefix", "&4Champion");
				set("rang.rang-9.tag", "&4C&7:&4");

				set("rang.rang-10.chatcolor", "&7");
				set("rang.rang-10.permissionsexrang", "Titan");
				set("rang.rang-10.prefix", "&eTitan");
				set("rang.rang-10.tag", "&eT&7:&e");

				set("rang.rang-11.chatcolor", "&7");
				set("rang.rang-11.permissionsexrang", "Hero");
				set("rang.rang-11.prefix", "&aHero");
				set("rang.rang-11.tag", "&aH&7:&a");

				set("rang.rang-12.chatcolor", "&7");
				set("rang.rang-12.permissionsexrang", "Master");
				set("rang.rang-12.prefix", "&dMaster");
				set("rang.rang-12.tag", "&dM&7:&d");

				set("rang.rang-13.chatcolor", "&7");
				set("rang.rang-13.permissionsexrang", "Legend");
				set("rang.rang-13.prefix", "&9Legend");
				set("rang.rang-13.tag", "&9L&7:&9");

				set("rang.rang-14.chatcolor", "&7");
				set("rang.rang-14.permissionsexrang", "Youtuber");
				set("rang.rang-14.prefix", "&5Youtuber");
				set("rang.rang-14.tag", "&5YT&7:&5");

				set("rang.rang-15.chatcolor", "&7");
				set("rang.rang-15.permissionsexrang", "default");
				set("rang.rang-15.prefix", "&7Spieler");
				set("rang.rang-15.tag", "&7S&7:&7");

				set("rewards.getCoins", "%PREFIX% &7Du hast &b%COINS%-Coins &7bekommen.");
				set("rewards.getReward.stuendlich", "%PREFIX% &7Du hast deinen &bStuendlichen-Reward &7bekommen.");
				set("rewards.getReward.taeglich", "%PREFIX% &7Du hast deinen &bTaeglichen-Reward &7bekommen.");
				set("rewards.getReward.woechentlich", "%PREFIX% &7Du hast deinen &bWoechentlichen-Reward &7bekommen.");
				set("rewards.not_alowed.stuendlich",
						"%PREFIX% &7Bitte warte noch bis: &b%TIME% &7für den &9Stuendlichen-Reward&7.");
				set("rewards.not_alowed.taeglich",
						"%PREFIX% &7Bitte warte noch bis: &b%TIME% &7für den &9Taeglichen-Reward&7.");
				set("rewards.not_alowed.woechentlich",
						"%PREFIX% &7Bitte warte noch bis: &b%TIME% &7für den &9Woechentlichen-Reward&7.");
				set("rewards.add.no_item_in_hand", "%PREFIX% &cBitte halte ein Item in der Hand.");
				set("rewards.addItem", "%PREFIX% &7Du hast ein &bItem &7zu einem &9Reward &7hinzugefügt.");
				set("rewards.clearlist", "%PREFIX% &7Du hast die &bReward-Liste &7geleert.");
				set("rewards.updateCoins", "%PREFIX% &7Du hast die &bReward-Coins &7geändert.");

				set("msg.to", "&b&lNachricht &7&l×&f &7Du zu &b%PLAYER% &7➜ &8%MSG%");
				set("msg.from", "&b&lNachricht &7&l×&f &b%PLAYER% &7zu dir ➜ &8%MSG%");

				set("instantkill", "null");

				set("command.invsee.msg", "%PREFIX% &7Du siehst nun das &bInventar &7von &9%PLAYER%&7.");
				set("cookie-shop.notenoughcookies", "%PREFIX% &7Du hast zuwenig Cokkies.");
				set("cookie-shop.changecookies", "%PREFIX% &7Du hast Cokkies eingetauscht.");

				set("enablemsg", true);

				save();
			} catch (IOException e) {
				SkyPvP.getInstance().sendConsoleError("Konnte §4Config §cnicht erstellen.");
			}
		}
	}

	public static long getLong(String path) {
		return Long.parseLong(cfg.getString(path));
	}

	public static String getString(String path) {
		return cfg.getString(path).replaceAll("&", "§").replaceAll("%PREFIX%", SkyPvP.getInstance().getPrefix());
	}

	public static Boolean getBoolean(String path) {
		return cfg.contains(path) ? cfg.getBoolean(path) : false;
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
			SkyPvP.getInstance().sendConsoleError("Konnte §4Config §cnicht speichern.");
		}
	}

}
