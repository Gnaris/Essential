package sperias.essential.event;

import SPEssential.SPEssential;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {

    private SPEssential plugin;

    public PlayerDeath(SPEssential plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e)
    {
        e.setDeathMessage("§a" + e.getEntity().getName() + " s'est donné la mort");
        plugin.getPlayerLastTeleportationLocation().put(e.getEntity(), e.getEntity().getLocation());
    }
}
