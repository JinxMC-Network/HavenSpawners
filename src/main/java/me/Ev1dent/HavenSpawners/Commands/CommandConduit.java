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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommandConduit implements CommandExecutor {

    Utils Utils = new Utils();
    private final HSMain HSMain;
    private final NamespacedKey key;
    private final List<String> result = new ArrayList<>();
    private final List<String> args = new ArrayList<>();


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
            sender.sendMessage(Utils.Color(Utils.Config().getString("Message.No-Arguments")));
            return true;
        }

        ItemStack conduit = HSMain.generateConduit(key);
        if (Bukkit.getPlayer(args[0]) == null) {
            Player otherPlayer = Bukkit.getPlayer(args[0]);
            HashMap<Integer, ItemStack> hashMap = otherPlayer.getInventory().addItem(conduit);
            if (!hashMap.isEmpty()) {
                otherPlayer.getWorld().dropItem(otherPlayer.getLocation(), conduit);
            }
        } else {
            sender.sendMessage(Utils.Color(Utils.Config().getString("Messages.Valid-Player")));
        }
        return true;
    }

}
