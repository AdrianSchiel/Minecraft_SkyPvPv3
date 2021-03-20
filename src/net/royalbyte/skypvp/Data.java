/*
 * ---------------------------------------
 * Copyright @RoyalByte | Adrian Schiel
 * Youtube : RoyalByte Developer
 * Skype: RoyalByte
 * Website: RoyalByte.net
 * ---------------------------------------
 */

package net.royalbyte.skypvp;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class Data {

	
	public static String prefix = SkyPvP.getInstance().getPrefix();
	public static String noperm = SkyPvP.getInstance().getNoperm();
	public static String must_a_player =  SkyPvP.getInstance().getMust_a_player();
	public static String player_not_online =  SkyPvP.getInstance().getPlayer_not_online();
	public static String header =  SkyPvP.getInstance().getHeader();

	
	
	public static List<Player> mobs = new ArrayList<>();
	
	
}
