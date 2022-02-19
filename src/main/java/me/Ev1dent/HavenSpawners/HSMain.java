package me.Ev1dent.HavenSpawners;

import me.Ev1dent.HavenSpawners.Commands.CommandGiveConduit;
import me.Ev1dent.HavenSpawners.Commands.CommandGiveSpawner;
import me.Ev1dent.HavenSpawners.Commands.CommandHavenSpawners;
import me.Ev1dent.HavenSpawners.Events.AnvilRename;
import me.Ev1dent.HavenSpawners.Events.BlockPlace;
import me.Ev1dent.HavenSpawners.Utilities.Utils;
import me.Ev1dent.HavenSpawners.Events.BlockBreak;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Objects;

public final class HSMain extends JavaPlugin implements Listener {

    private NamespacedKey key;
    public static HSMain plugin;
    Utils Utils = new Utils();

    @Override
    public void onEnable() {
        key = new NamespacedKey(this, "conduit");
        plugin = this;
        saveDefaultConfig();
        registerCommands();
        registerEvents();
    }

    public void registerCommands(){
        Objects.requireNonNull(this.getCommand("giveconduit")).setExecutor(new CommandGiveConduit(key, this));
        Objects.requireNonNull(this.getCommand("givespawner")).setExecutor(new CommandGiveSpawner());
        Objects.requireNonNull(this.getCommand("havenspawners")).setExecutor(new CommandHavenSpawners());
    }

    public void registerEvents(){
        this.getServer().getPluginManager().registerEvents(new BlockBreak(this, key), this);
        this.getServer().getPluginManager().registerEvents(new BlockPlace(key), this);
        this.getServer().getPluginManager().registerEvents(new AnvilRename(), this);
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
