package dev.Ev1dent.HavenSpawners.Events;

import com.destroystokyo.paper.MaterialSetTag;
import com.destroystokyo.paper.MaterialTags;
import dev.Ev1dent.HavenSpawners.Utilities.Utils;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import dev.Ev1dent.HavenSpawners.HSMain;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class BlockBreak implements Listener {

    private HSMain HSMain;
    Utils Utils = new Utils();


    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (e.getPlayer().getGameMode().equals(GameMode.CREATIVE) && e.getPlayer().isSneaking()) return;
        if (e.getBlock().getType().equals(Material.SPAWNER)) {
            CreatureSpawner spawner = (CreatureSpawner) e.getBlock().getState();
            EntityType entityType = spawner.getSpawnedType();
            Player player = e.getPlayer();

            if (e.isCancelled()) {
                return;
            }

            ItemStack i = e.getPlayer().getInventory().getItemInMainHand();
            if(i != (ItemStack) MaterialSetTag.MINEABLE_PICKAXE){
                e.setDropItems(false);
            }

            if(!i.containsEnchantment(Enchantment.SILK_TOUCH)){
                e.setCancelled(true);
                player.sendMessage("need silk touch dumb fuck");
                i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                player.sendMessage("I'll fucking add it for you this time..");
                e.setCancelled(false);
            }


            ItemStack DroppedSpawner = new ItemStack(Material.BEDROCK);
            String entity = Utils.Config().getString("Spawner.Name"), ET = entity.replace("{MOB}", String.valueOf(entityType));
            ItemMeta SpawnerMeta = DroppedSpawner.getItemMeta();
            SpawnerMeta.setDisplayName(Utils.Color(ET));
            DroppedSpawner.setItemMeta(SpawnerMeta);

            HashMap<Integer, ItemStack> hashMap = player.getInventory().addItem(DroppedSpawner);
            player.sendMessage(Utils.Color(Utils.Config().getString("Messages.Spawner-Received")));
            if (!hashMap.isEmpty()) {
                player.getWorld().dropItem(player.getLocation(), DroppedSpawner);
            }
        }
    }
}
