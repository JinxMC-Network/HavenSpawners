package me.Ev1dent.HavenSpawners.Commands;

import me.Ev1dent.HavenSpawners.HSMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import me.Ev1dent.HavenSpawners.Utilities.Utils;

public class CommandHavenSpawners implements CommandExecutor {

    Utils Utils = new Utils();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("havenspawners.havenspawners")){
            sender.sendMessage(Utils.Color(Utils.Config().getString("Messages.No-Perms")));
        }

        if(args.length == 0){
            sender.sendMessage(Utils.Color(Utils.Config().getString("Messages.No-Arguments")));
            return true;
        }

        switch (args[0].toLowerCase()){
            case "reload":
                HSMain.plugin.saveDefaultConfig();
                HSMain.plugin.reloadConfig();
                sender.sendMessage(Utils.Color("&2Config reloaded"));
                break;

            case "version":
                sender.sendMessage("1.0.3 (Stable)");
                break;

            default:
                sender.sendMessage("/<command> [reload/version]");
                break;
        }
        return true;
    }
}
