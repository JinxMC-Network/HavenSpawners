package me.Ev1dent.HavenSpawners.Commands;

import me.Ev1dent.HavenSpawners.HSMain;
import me.Ev1dent.HavenSpawners.Utilities.Utils;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommandConduit implements TabExecutor {

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
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("havenspawners.conduit")) {
            sender.sendMessage(Utils.Color("&4You are not permitted to use that command."));
            return true;
        } else {
            ItemStack item = HSMain.generateConduit(key);
            if (!(sender instanceof Player)) {
                if (args.length > 0 && Bukkit.getPlayer(args[0]) == null) {
                    Player otherPlayer = Bukkit.getPlayer(args[0]);
                    HashMap<Integer, ItemStack> hashMap = otherPlayer.getInventory().addItem(item);
                    if (!hashMap.isEmpty()) {
                        otherPlayer.getWorld().dropItem(otherPlayer.getLocation(), item);
                    }
                } else {
                    sender.sendMessage(Utils.Color("&4Please specify a player"));
                }
                return true;
            }

            Player player = (Player) sender;
            if (args.length == 0 || Bukkit.getPlayer(args[0]) == null) {

                HashMap<Integer, ItemStack> hashMap = player.getInventory().addItem(item);
                if (!hashMap.isEmpty()) {
                    player.getWorld().dropItem(player.getLocation(), item);
                }
                return true;

            }

            Player otherPlayer = Bukkit.getPlayer(args[0]);

            HashMap<Integer, ItemStack> hashMap = otherPlayer.getInventory().addItem(item);
            if (!hashMap.isEmpty()) {

                otherPlayer.getWorld().dropItem(player.getLocation(), item);
                Utils.LogInfo(otherPlayer.getName() + " Has been given a conduit.");
                otherPlayer.sendMessage(Utils.Color("&6You have been given a Spawner Conduit."));
            }

            return true;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (sender.hasPermission("havenspawners.conduit")) this.args.add("give");

        if (args.length == 1) {
            for (String ar : this.args) {
                if (ar.toLowerCase().startsWith(args[0].toLowerCase())) result.add(ar);
            }
            return result;
        }

        return null;
    }
}
