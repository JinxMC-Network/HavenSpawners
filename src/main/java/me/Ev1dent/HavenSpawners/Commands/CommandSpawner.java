package me.Ev1dent.HavenSpawners.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import me.Ev1dent.HavenSpawners.Utilities.Utils;

public class CommandSpawner implements CommandExecutor {

    Utils Utils = new Utils();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!sender.hasPermission("havenspawners.command.give")){
            sender.sendMessage(Utils.Color(Utils.Config().getString("Messages.No-Perms")));
            return true;
        }

        if(args.length == 0){
            sender.sendMessage(Utils.Color(Utils.Config().getString("No-Arguments")));
            return true;
        }

        return false;
    }
}
