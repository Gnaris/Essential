package Event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e)
    {
        e.setDeathMessage("§a" + e.getEntity().getName() + " s'est donné la mort");
    }
}
