package dev.Ev1dent.HavenSpawners;

import dev.Ev1dent.HavenSpawners.Commands.CommandGiveSpawner;
import dev.Ev1dent.HavenSpawners.Commands.CommandHavenSpawners;
import dev.Ev1dent.HavenSpawners.Events.AnvilRename;
import dev.Ev1dent.HavenSpawners.Events.BlockPlace;
import dev.Ev1dent.HavenSpawners.Events.NoSpawnEggs;
import dev.Ev1dent.HavenSpawners.Utilities.TabCompleter;
import dev.Ev1dent.HavenSpawners.Utilities.Utils;
import dev.Ev1dent.HavenSpawners.Events.BlockBreak;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class HSMain extends JavaPlugin implements Listener {

    public static HSMain plugin;
    Utils Utils = new Utils();

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        registerCommands();
        addTabCompletion();
        registerEvents();
    }

    public void registerCommands(){
        this.getCommand("givespawner").setExecutor(new CommandGiveSpawner());
        this.getCommand("havenspawners").setExecutor(new CommandHavenSpawners());
    }

    public void registerEvents(){
        this.getServer().getPluginManager().registerEvents(new BlockBreak(), this);
        this.getServer().getPluginManager().registerEvents(new BlockPlace(), this);
        this.getServer().getPluginManager().registerEvents(new AnvilRename(), this);
        this.getServer().getPluginManager().registerEvents(new NoSpawnEggs(), this);
    }

    public void addTabCompletion() {
        this.getCommand("givespawner").setTabCompleter(new TabCompleter());
        this.getCommand("havenspawners").setTabCompleter(new TabCompleter());
    }
}
