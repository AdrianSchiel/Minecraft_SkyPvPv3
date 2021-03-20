package net.royalbyte.skypvp.utils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.google.common.collect.Lists;

import io.netty.util.internal.ThreadLocalRandom;
import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;

public class Crate implements Listener, CommandExecutor {

    private File file;
    private YamlConfiguration cfg;
    private List<String> crate_list;

    @SuppressWarnings("static-access")
    public Crate() {
        this.file = new File("plugins/SkyPvP/Datas/", "crates.yml");
        this.cfg = new YamlConfiguration().loadConfiguration(this.file);
        this.crate_list = Lists.newArrayList();
        this.createnewFile();
        this.registerCrateList();
    }

    private void createCrate(final String name, final ItemStack item) {
        if (!existCrate(name)) {
            this.getCrate_list().add(name);
            this.cfg.set("crate_list", this.getCrate_list());
            List<ItemStack> list = Lists.newArrayList();
            this.cfg.set("crate." + name + ".list", list);
            this.cfg.set("crate." + name + ".item", item);
            this.saveCFG();
        }
    }

    private void addItem(final String name, final ItemStack item) {
        if (existCrate(name)) {
            List<ItemStack> items = this.getItems(name);
            items.add(item);
            this.cfg.set("crate." + name + ".list", items);
            this.saveCFG();
        } else
            return;
    }

    private ItemStack getItem(final String name) {
        if (existCrate(name)) {
            return (ItemStack) this.cfg.get("crate." + name + ".item");
        } else
            return null;
    }

    private void deleteCrate(final String name) {
        if (existCrate(name)) {
            this.getCrate_list().remove(name);
            this.cfg.set("crate_list", this.getCrate_list());
            this.cfg.set("crate." + name, null);
            this.saveCFG();
        } else
            return;
    }

    @SuppressWarnings("unchecked")
    private List<ItemStack> getItems(final String name) {
        if (existCrate(name)) {
            return (List<ItemStack>) cfg.get("crate." + name + ".list");
        } else
            return null;
    }

    private void setItem(Player p, final String name) {
        if (existCrate(name)) {
            List<ItemStack> list = getItems(name);
            if (!list.isEmpty()) {
                Collections.shuffle(list);
                int item = ThreadLocalRandom.current().nextInt(0, list.size());
                p.getInventory().addItem(list.get(item));
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
                System.out.println(item);
            } else
                return;
        } else
            return;
    }

    private boolean existCrate(final String name) {
        return getCrate_list().contains(name);
    }

    private void registerCrateList() {
        List<String> cfg_list = this.cfg.getStringList("crate_list");
        cfg_list.forEach(arena -> {
            this.getCrate_list().add(arena);
        });
    }

    public List<String> getCrate_list() {
        return crate_list;
    }

    private void createnewFile() {
        new File("plugins/SkyPvP/Datas").mkdirs();
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
                List<String> list = Lists.newArrayList();
                this.cfg.set("crate_list", list);
                this.saveCFG();
            } catch (IOException e) {
            }
        }

    }

    private void saveCFG() {
        try {
            this.cfg.save(this.file);
        } catch (IOException e) {
        }
    }

    /*
     * LISTENER
     */

    @EventHandler
    public void onInterract(final PlayerInteractEvent e) {
        try {
            final Player p = e.getPlayer();
            if (p.getItemInHand().getItemMeta().getDisplayName().startsWith("§bCrate §7» ")) {
                ItemStack item = p.getItemInHand();
                if (item.getItemMeta().hasLore()) {
                    final String nameDisPlayName = item.getItemMeta().getDisplayName().replaceAll("§bCrate §7» ", "");
                    final String name = nameDisPlayName;
                    if (existCrate(name)) {
                        int amount = p.getItemInHand().getAmount();
                        if (amount == 1) {
                            p.getInventory().removeItem(p.getItemInHand());
                        } else {
                            p.getItemInHand().setAmount(amount - 1);
                        }
                        setItem(p, name);
                        p.updateInventory();
                    }
                } else
                    return;
            }
        } catch (Exception ex) {
        }
    }

    /*
     * COMMAND /crate create <Name> /crate delete <Name> /crate addItem <Name>
     * /crate get <Name> <Amount> <Player>
     */

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("create")) {
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    if (p.hasPermission("SkyPvP.crate.create")) {
                        final String name = args[1];
                        if (!existCrate(name)) {
                            if (!(p.getItemInHand().getType() == Material.AIR || p.getItemInHand() == null)) {
                                createCrate(name, p.getItemInHand());
                                p.sendMessage(Config.getString("crate.create").replaceAll("%NAME%", name));
                            } else
                                p.sendMessage(Config.getString("crate.must_hold_item_in_hand"));
                        } else
                            p.sendMessage(Config.getString("crate.alreadyExists"));
                    } else
                        p.sendMessage(Data.noperm);
                } else
                    sender.sendMessage(Data.must_a_player);
            } else if (args[0].equalsIgnoreCase("delete")) {
                if (sender.hasPermission("SkyPvP.crate.delete")) {
                    final String name = args[1];
                    if (existCrate(name)) {
                        deleteCrate(name);
                        sender.sendMessage(Config.getString("crate.delete").replaceAll("%NAME%", name));
                    } else
                        sender.sendMessage(Config.getString("crate.notExist"));
                } else
                    sender.sendMessage(Data.noperm);
            } else if (args[0].equalsIgnoreCase("addItem")) {
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    if (p.hasPermission("SkyPvP.crate.addItem")) {
                        final String name = args[1];
                        if (existCrate(name)) {
                            if (!(p.getItemInHand().getType() == Material.AIR || p.getItemInHand() == null)) {
                                addItem(name, p.getItemInHand());
                                p.sendMessage(Config.getString("crate.addItem").replaceAll("%NAME%", name));
                            } else
                                p.sendMessage(Config.getString("crate.must_hold_item_in_hand"));

                        } else
                            p.sendMessage(Config.getString("crate.alreadyExists"));
                    } else
                        p.sendMessage(Data.noperm);
                } else
                    sender.sendMessage(Data.must_a_player);
            } else
                sendHelp(sender);
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("create")) {
                sender.sendMessage(Config.getString("crate.help.create"));
            } else if (args[0].equalsIgnoreCase("delete")) {
                sender.sendMessage(Config.getString("crate.help.delete"));
            } else if (args[0].equalsIgnoreCase("addItem")) {
                sender.sendMessage(Config.getString("crate.help.addItem"));
            } else if (args[0].equalsIgnoreCase("get")) {
                sender.sendMessage(Config.getString("crate.help.get"));
            } else if (args[0].equalsIgnoreCase("help")) {
                sendHelp(sender);
            }
        } else if (args.length == 4) {
            if (args[0].equalsIgnoreCase("get")) {
                if (sender.hasPermission("SkyPvP.crate.get")) {
                    final String name = args[1];
                    if (existCrate(name)) {
                        final Integer amount = Integer.parseInt(args[2]);
                        final Player target = Bukkit.getPlayer(args[3]);
                        if (target != null) {
                            ItemStack crate = getItem(name);
                            crate.setAmount(amount);
                            ItemMeta meta = crate.getItemMeta();
                            meta.setDisplayName("§bCrate §7» " + name);
                            meta.setLore(Arrays.asList(new String[]{"§bCrate ID§7: " + name}));
                            crate.setItemMeta(meta);
                            target.getInventory().addItem(crate);
                            target.sendMessage(Config.getString("crate.getCrate").replaceAll("%NAME%", name));
                            sender.sendMessage(Config.getString("crate.setCrate").replaceAll("%NAME%", name));
                        } else
                            sender.sendMessage(Data.player_not_online);
                    } else
                        sender.sendMessage(Config.getString("crate.notExist"));
                } else
                    sender.sendMessage(Data.noperm);
            } else
                sendHelp(sender);
        } else
            sendHelp(sender);

        return true;
    }

    private void sendHelp(CommandSender sender) {
        sender.sendMessage(Data.header);
        sender.sendMessage(Config.getString("crate.help.line-1"));
        sender.sendMessage(Config.getString("crate.help.line-2"));
        sender.sendMessage(Config.getString("crate.help.line-3"));
        sender.sendMessage(Config.getString("crate.help.line-4"));
        sender.sendMessage(Config.getString("crate.help.line-5"));
        sender.sendMessage(Data.header);
    }

}
