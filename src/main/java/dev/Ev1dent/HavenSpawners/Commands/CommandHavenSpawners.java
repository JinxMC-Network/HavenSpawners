package dev.Ev1dent.HavenSpawners.Commands;

import dev.Ev1dent.HavenSpawners.HSMain;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import dev.Ev1dent.HavenSpawners.Utilities.Utils;
import org.bukkit.plugin.PluginDescriptionFile;

public class CommandHavenSpawners implements CommandExecutor {

    Utils Utils = new Utils();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("havenspawners.havenspawners")){
            sender.sendMessage(Utils.Color(Utils.Config().getString("Messages.No-Perms")));
        }

        if(args.length == 0){
            sender.sendMessage(Utils.Color("            &6&lHavenSpawners            "));
            sender.sendMessage(Utils.Color("&m                                       "));
            sender.sendMessage(Utils.Color("&eThis server is running &6&nHavenSpawners &av" + HSMain.plugin.getDescription().getVersion()));
            sender.sendMessage(Utils.Color("&e- &6Bukkit Version: " + Bukkit.getVersion()));
            return true;
        }

        switch (args[0].toLowerCase()){
            case "reload":
                HSMain.plugin.saveDefaultConfig();
                HSMain.plugin.reloadConfig();
                sender.sendMessage(Utils.Color("&2Config reloaded"));
                HSMain.plugin.addTabCompletion();
                break;

            case "version":
                sender.sendMessage("v" + HSMain.plugin.getDescription().getVersion());
                break;

            default:
                sender.sendMessage("/<command> [reload/version]");
                break;
        }
        return true;
    }
}
