package me.Ev1dent.HavenSpawners.Events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import me.Ev1dent.HavenSpawners.Utilities.Utils;

public class AnvilRename implements Listener {

    Utils Utils = new Utils();

    @EventHandler
    public void onRename(InventoryClickEvent e){
        if (e.getCurrentItem() == null) {
            return;
        }

        if (e.getInventory().getType() != InventoryType.ANVIL || e.getCurrentItem().getType() != Material.SPAWNER) {
            return;
        }

        Player player = (Player) e.getWhoClicked();
        e.setCancelled(true);
        player.sendMessage(Utils.Color(Utils.Config().getString("Messages.Cannot-Rename")));
    }
}
