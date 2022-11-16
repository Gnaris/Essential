package sperias.essential.event;

import SPEssential.SPEssential;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class PlayerInvsee implements Listener {

    private final SPEssential plugin;

    public PlayerInvsee(SPEssential plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onSee(InventoryClickEvent e)
    {
        if(plugin.getPlayerInvsee().contains(e.getWhoClicked()) && !e.getWhoClicked().hasPermission("sperias.essential.invsee.takeitem") && !e.getWhoClicked().isOp())
        {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e)
    {
        if(plugin.getPlayerInvsee().contains(e.getPlayer()))
        {
            plugin.getPlayerInvsee().remove(e.getPlayer());
        }
    }
}
