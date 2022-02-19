package me.Ev1dent.HavenSpawners.Events;

import me.Ev1dent.HavenSpawners.HSMain;
import me.Ev1dent.HavenSpawners.Utilities.Utils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class BlockPlace implements Listener {

    private final NamespacedKey key;
    Utils Utils = new Utils();

    public BlockPlace(NamespacedKey key){
        this.key = key;
    }

    public void onBlockPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();
        Material ItemInHand = item.getType();
        if (ItemInHand == Material.CONDUIT) {
            ItemMeta meta = item.getItemMeta();
            if (meta.getPersistentDataContainer().has(key, PersistentDataType.DOUBLE)) {
                e.setCancelled(true);
                p.sendMessage(Utils.Color(Utils.Config().getString("Messages.Place-Conduit")));
            }
        }
    }
}