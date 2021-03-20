package net.royalbyte.skypvp.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.SkyPvP;

public class Umfrage {

	public Umfrage() {
	}

	private int ja, nein;
	private String frage;
	
				//UUID
	public List<String> player = new ArrayList<>();
	
	
	
	public void addJa() {
		ja++;
		Config.set("umfrage.ja", ja);

	}

	public void addNein() {
		nein++;
		Config.set("umfrage.nein", nein);
	}

	public void createNewUmfrage(String frage) {
		this.frage = frage;
		Config.set("umfrage.frage", frage);
	}

	public boolean isUmfrage() {
		return Config.getBoolean("umfrage.cuurent");
	}

	public void timer() {
		new BukkitRunnable() {
			int i = 31;

			@Override
			public void run() {
				i--;
				if (i == 30) {
					broadcastFrage();
					Config.set("umfrage.cuurent", true);
				}
				if (i == 20 || i == 10 || i == 5 || i == 4 || i == 3 || i == 2) {
					Bukkit.broadcastMessage(Config.getString("umfrage.endsin.msg").replaceAll("%SEK%", "" + i));
				}
				if (i == 1) {
					broadcastAnswer();
					resetFrage();
					Config.set("umfrage.cuurent", false);
					player.clear();
					cancel();
				}
			}
		}.runTaskTimerAsynchronously(SkyPvP.getInstance(), 0, 20);

	}

	public void resetFrage() {
		Config.set("umfrage.ja", 0);
		Config.set("umfrage.nein", 0);
		Config.set("umfrage.frage", "Frage...");
		this.ja = 0;
		this.nein = 0;
		this.frage = null;

	}

	public String getErgebnis() {
		if (ja > nein) {
			return "ja";
		} else {
			return "nein";
		}
	}

	public int getErgebnisInt() {
		if (ja > nein) {
			return ja;
		} else {
			return nein;
		}
	}

	public void broadcastFrage() {
		Bukkit.broadcastMessage(replaceAnswer("umfrage.line-1"));
		Bukkit.broadcastMessage(replaceAnswer("umfrage.line-2"));
		Bukkit.broadcastMessage(replaceAnswer("umfrage.line-3"));
		Bukkit.broadcastMessage(replaceAnswer("umfrage.line-4"));
		Bukkit.broadcastMessage(replaceAnswer("umfrage.line-5"));
		Bukkit.broadcastMessage(replaceAnswer("umfrage.line-6"));
		Bukkit.broadcastMessage(replaceAnswer("umfrage.line-7"));

	}

	public String replaceFrage(String path) {
		return Config.getString(path).replaceAll("%FRAGE%", frage);
	}

	public void broadcastAnswer() {
		Bukkit.broadcastMessage(replaceAnswer("umfrage.answer.line-1"));
		Bukkit.broadcastMessage(replaceAnswer("umfrage.answer.line-2"));
		Bukkit.broadcastMessage(replaceAnswer("umfrage.answer.line-3"));
		Bukkit.broadcastMessage(replaceAnswer("umfrage.answer.line-4"));
		Bukkit.broadcastMessage(replaceAnswer("umfrage.answer.line-5"));
		Bukkit.broadcastMessage(replaceAnswer("umfrage.answer.line-6"));
	}

	private String replaceAnswer(String path) {
		return Config.getString(path).replaceAll("%FRAGE%", frage).replaceAll("%ANSWER%",
				getErgebnis()).replaceAll("%STIMMEN%", "" + getErgebnisInt());
	}

}
