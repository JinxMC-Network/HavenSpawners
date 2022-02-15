package me.Ev1dent.HavenSpawners.Utilities;

import me.Ev1dent.HavenSpawners.HSMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class Utils {

    // Allows me to be lazy, and use colors cleanly. :)
    public String Color(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    // Logging
    public void LogInfo(String log){
        Bukkit.getLogger().info(log);
    }

    public void LogWarn(String log){
        Bukkit.getLogger().warning(log);
    }

    public void LogSevere(String log){
        Bukkit.getLogger().severe(log);
    }

    public FileConfiguration Config(){
        return HSMain.plugin.getConfig();
    }
}



