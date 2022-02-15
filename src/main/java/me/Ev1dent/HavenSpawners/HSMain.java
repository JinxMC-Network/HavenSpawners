package me.Ev1dent.HavenSpawners;

import me.Ev1dent.HavenSpawners.Commands.CommandConduit;
import me.Ev1dent.HavenSpawners.Utilities.Utils;
import me.Ev1dent.HavenSpawners.Events.BlockBreak;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class HSMain extends JavaPlugin implements Listener {

    private NamespacedKey key;
    public static HSMain plugin;
    Utils Utils = new Utils();

    @Override
    public void onEnable() {
        key = new NamespacedKey(this, "gem");
        plugin = this;
        saveDefaultConfig();
        registerCommands();
        registerEvents();
    }

    public void registerCommands(){
        this.getCommand("conduit").setExecutor(new CommandConduit(key, this));
    }
    public void registerEvents(){
        this.getServer().getPluginManager().registerEvents(new BlockBreak(this, key), this);
    }

    public ItemStack generateConduit(NamespacedKey key) {
        ItemStack sConduit = new ItemStack(Material.CONDUIT);
        ItemMeta meta = sConduit.getItemMeta();
        meta.getPersistentDataContainer().set(key, PersistentDataType.DOUBLE, Math.random());
        meta.setDisplayName(Utils.Color("&6Spawner Conduit"));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.Color("&cConduit that allows you"));
        lore.add(Utils.Color("&cto mine a spawner!"));
        meta.setLore(lore);
        sConduit.setItemMeta(meta);
        return sConduit;
    }

}
