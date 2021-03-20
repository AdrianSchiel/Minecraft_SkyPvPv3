package net.royalbyte.skypvp.manager;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Manager_ItemBuilder {
	public static ItemStack lore(String name,Material mat,int anzahl,int subid , String[] lore) {
		ItemStack s = new ItemStack(mat,anzahl,(short) subid);
		ItemMeta m = s.getItemMeta();
		m.setDisplayName(name);
		m.setLore(Arrays.asList(lore));
		s.setDurability(s.getDurability());
		s.setItemMeta(m);
		return s;
	}
	public static ItemStack enchant(String name,String lore,List<String> list,Material mat , int subid, int anzahl,Enchantment ench , int enchhigh) {
		ItemStack s = new ItemStack(mat ,anzahl , (short) subid);
		ItemMeta m = s.getItemMeta();
		m.setDisplayName(name);
		list.clear();
		list.add(lore);
		m.setLore(list);
		m.addEnchant(ench, enchhigh, true);
		m.addEnchant(Enchantment.DURABILITY, 3, true);
		s.setItemMeta(m);
		return s;
	}
	public static ItemStack item(String name,Material mat, int subid , int anzahl) {
		ItemStack s = new ItemStack(mat,anzahl , (short) subid);
		ItemMeta m = s.getItemMeta();
		m.setDisplayName(name);
		s.setItemMeta(m);
		return s;
	}	
	public static ItemStack Skull_lore(String name,String owner,int amount, String[] lore) {
		ItemStack s = new ItemStack(Material.SKULL_ITEM , amount , (byte) 3);
		SkullMeta m = (SkullMeta) s.getItemMeta();
		m.setDisplayName(name);
		m.setOwner(owner);
		m.setLore(Arrays.asList(lore));
		s.setItemMeta(m);
		return s;
	}
	public static ItemStack Skull(String name,String owner,int amount) {
		ItemStack s = new ItemStack(Material.SKULL_ITEM , amount , (byte) 3);
		SkullMeta m = (SkullMeta) s.getItemMeta();
		m.setDisplayName(name);
		m.setOwner(owner);
		s.setItemMeta(m);
		return s;
	}
}
