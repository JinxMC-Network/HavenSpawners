package me.Ev1dent.HavenSpawners.Events;

import me.Ev1dent.HavenSpawners.Utilities.Utils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import me.Ev1dent.HavenSpawners.HSMain;

import java.util.HashMap;

public class BlockBreak implements Listener {

    private HSMain HSMain;
    private final NamespacedKey key;
    Utils Utils = new Utils();

    public BlockBreak(HSMain HSMain, NamespacedKey key){
        this.HSMain = HSMain;
        this.key = key;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if(e.getBlock().getType().equals(Material.SPAWNER)){
            CreatureSpawner spawner = (CreatureSpawner) e.getBlock().getState();
            EntityType entityType = spawner.getSpawnedType();
            Player player = e.getPlayer();

            if(Utils.Config().getList("DisabledSpawners").contains(entityType.toString())){
                player.sendMessage(Utils.Color(Utils.Config().getString("Messages.Disabled")));
                e.setCancelled(true);
                return;
            }

            if (!e.getPlayer().hasPermission("havenspawners.spawners.mine")){
                e.setCancelled(true);
                player.sendMessage(Utils.Color(Utils.Config().getString("Messages.Cant-Mine")));
                return;
            }


            ItemStack i = e.getPlayer().getInventory().getItemInMainHand();
            for (ItemStack item : player.getInventory().getContents()) {
                if (item == null) {
                    continue;
                }

                if (!item.getType().equals(Material.CONDUIT)) {
                    continue;
                }

                ItemMeta meta = item.getItemMeta();
                if (meta.getPersistentDataContainer().has(key, PersistentDataType.DOUBLE)) {

                    ItemStack DroppedSpawner = new ItemStack(Material.SPAWNER);
                    String entity = Utils.Config().getString("Spawner.Name"), ET = entity.replace("{MOB}", String.valueOf(entityType));
                    ItemMeta SpawnerMeta = DroppedSpawner.getItemMeta();
                    SpawnerMeta.setDisplayName(Utils.Color(ET));
                    DroppedSpawner.setItemMeta(SpawnerMeta);

                    player.getInventory().remove(item);
                    e.setExpToDrop(0);
                    HashMap<Integer, ItemStack> hashMap = player.getInventory().addItem(DroppedSpawner);
                    player.sendMessage(Utils.Color(Utils.Config().getString("Messages.Spawner-Received")));
                    if (!hashMap.isEmpty()) {
                        player.getWorld().dropItem(player.getLocation(), DroppedSpawner);
                    }
                    return;
                }
            }
            e.setCancelled(true);
            player.sendMessage(Utils.Color(Utils.Config().getString("Messages.No-Conduit1")));
            player.sendMessage(Utils.Color(Utils.Config().getString("Messages.No-Conduit2")));
        }
    }
}
