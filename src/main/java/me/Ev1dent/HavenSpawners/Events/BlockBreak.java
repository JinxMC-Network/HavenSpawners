package me.Ev1dent.HavenSpawners.Events;

import me.Ev1dent.HavenSpawners.Utilities.Utils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import me.Ev1dent.HavenSpawners.HSMain;

public class BlockBreak implements Listener {

    private HSMain HSMain;
    private NamespacedKey key;
    Utils Utils = new Utils();

    public BlockBreak(HSMain HSMain, NamespacedKey key){
        this.HSMain = HSMain;
        this.key = key;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Utils.LogInfo("Spawner Broken");
        if(e.getBlock().getType().equals(Material.SPAWNER)){
            Player player = e.getPlayer();
            for (ItemStack item : player.getInventory().getContents()) {
                if (item == null) {
                    continue;
                }

                if (!item.getType().equals(Material.CONDUIT)) {
                    continue;
                }
                ItemMeta meta = item.getItemMeta();
                if (meta.getPersistentDataContainer().has(key, PersistentDataType.DOUBLE)) {
                    if (e.getPlayer().hasPermission("havenspawners.conduit.mine")) ;
                    player.getInventory().remove(item);
                    return;
                }
            }
            e.setCancelled(true);
            e.getPlayer().sendMessage(Utils.Color("&6You need a SpawnerConduit"));
        }
    }
}
