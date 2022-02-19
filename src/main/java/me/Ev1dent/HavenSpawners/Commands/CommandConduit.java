package me.Ev1dent.HavenSpawners.Commands;

import me.Ev1dent.HavenSpawners.HSMain;
import me.Ev1dent.HavenSpawners.Utilities.Utils;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import java.util.HashMap;

public class CommandConduit implements CommandExecutor {

    Utils Utils = new Utils();
    private final HSMain HSMain;
    private final NamespacedKey key;


    public CommandConduit(NamespacedKey key, HSMain HSMain) {
        this.key = key;
        this.HSMain = HSMain;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if(!sender.hasPermission("havenspawners.command.conduit")){
            sender.sendMessage(Utils.Color(Utils.Config().getString("Messages.No-Perms")));
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(Utils.Color(Utils.Config().getString("Messages.No-Arguments")));
            return true;
        }

        switch (args[0].toLowerCase()){
            case "give":
                if(args.length < 2){
                    sender.sendMessage(Utils.Color(Utils.Config().getString("Messages.Invalid-Player")));
                    return true;
                }

                ItemStack conduit = HSMain.generateConduit(key);
                if (Bukkit.getPlayer(args[1]) == null) {
                    sender.sendMessage(Utils.Color(Utils.Config().getString("Messages.Invalid-Player")));
                    return true;
                } else {
                    Player otherPlayer = Bukkit.getPlayer(args[1]);

                    // tries adding the item to the players inventory
                    HashMap<Integer, ItemStack> hashMap = otherPlayer.getInventory().addItem(conduit);
                    otherPlayer.sendMessage(Utils.Color(Utils.Config().getString("Messages.Received")));


                    // If inventory is full, drop at player
                    if (!hashMap.isEmpty()) {
                        otherPlayer.getWorld().dropItem(otherPlayer.getLocation(), conduit);
                    }
                }
                break;

            case "reload":
                me.Ev1dent.HavenSpawners.HSMain.plugin.saveDefaultConfig();
                me.Ev1dent.HavenSpawners.HSMain.plugin.reloadConfig();
                sender.sendMessage(Utils.Color("&6Config reloaded"));
                break;

            default:
                sender.sendMessage(Utils.Color(Utils.Color(Utils.Config().getString("Messages.Unknown-Arg")) + " » " + args[0]));
                break;

        }
        return true;
    }

}
