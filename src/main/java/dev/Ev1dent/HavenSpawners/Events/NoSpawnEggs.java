package dev.Ev1dent.HavenSpawners.Events;

import com.destroystokyo.paper.MaterialTags;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import dev.Ev1dent.HavenSpawners.Utilities.Utils;

public class NoSpawnEggs implements Listener {
    Utils Utils = new Utils();

    @EventHandler
    public void onEggUsage(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(event.getClickedBlock() == null) return;
        if(!event.getClickedBlock().getType().equals(Material.SPAWNER)) return;
        if(MaterialTags.SPAWN_EGGS.isTagged(player.getInventory().getItemInOffHand())
            || MaterialTags.SPAWN_EGGS.isTagged(player.getInventory().getItemInMainHand())){
            event.setCancelled(true);
        }
    }
}
