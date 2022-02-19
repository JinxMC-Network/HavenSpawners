package me.Ev1dent.HavenSpawners.Events;

import me.Ev1dent.HavenSpawners.Utilities.Utils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
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

    @EventHandler
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

        if(ItemInHand == Material.SPAWNER){
            String[] name = item.getItemMeta().getDisplayName().split(" ", 2);
            String formatted = name[0].replaceAll("(?i)[§&][0-9A-FK-ORX]", "");
            CreatureSpawner spawner = (CreatureSpawner) e.getBlockPlaced().getState();
            try{
                spawner.setSpawnedType(EntityType.valueOf(formatted));
            }
            catch (Exception err){
                Utils.LogWarn("Unknown Spawner placed. Defaulting to PIG");
            }
            spawner.update();
            p.sendMessage(Utils.Color("&6You have placed a " + name[0] + "&6 Spawner!"));
        }
    }
}