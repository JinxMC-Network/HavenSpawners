package dev.Ev1dent.HavenSpawners.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import dev.Ev1dent.HavenSpawners.Utilities.Utils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class CommandGiveSpawner implements CommandExecutor {

    Utils Utils = new Utils();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!sender.hasPermission("havenspawners.command.givespawner")){
            sender.sendMessage(Utils.Color(Utils.Config().getString("Messages.No-Perms")));
            return true;
        }

        if(args.length < 2){
            sender.sendMessage(Utils.Color("&fUsage: /<command> <player> <mob>"));
            return true;
        }

        Player player = Bukkit.getPlayer(args[0]);
        String mobType = args[1].toUpperCase();
        if(player == null){
            sender.sendMessage(Utils.Color(Utils.Config().getString("Messages.Invalid-Player")));
            return true;
            }

        if(!Utils.Config().getList("Mobs").contains(mobType)){
            player.sendMessage(Utils.Color(Utils.Config().getString("Messages.Invalid-Mob")));
            return true;

        } else {
            String SpawnerName = Utils.Config().getString("Spawner.Name"), Spawner = SpawnerName.replace("{MOB}", mobType);
            ItemStack spawner = new ItemStack(Material.SPAWNER);
            ItemMeta meta = spawner.getItemMeta();
            meta.setDisplayName(Utils.Color(Spawner));
            spawner.setItemMeta(meta);
            HashMap<Integer, ItemStack> hashMap = player.getInventory().addItem(spawner);
            player.sendMessage(Utils.Color(Utils.Config().getString("Messages.Spawner-Received")));
            // If inventory is full, drop at player
            if (!hashMap.isEmpty()) {
                player.getWorld().dropItem(player.getLocation(), spawner);
            }
            return true;
        }
    }
}
