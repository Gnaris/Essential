package sperias.essential.event;

import SPEssential.SPEssential;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerTeleport implements Listener {

    private SPEssential plugin;

    public PlayerTeleport(SPEssential plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e)
    {
        plugin.getPlayerLastTeleportationLocation().put(e.getPlayer(), e.getFrom());
    }
}
