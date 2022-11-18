package sperias.essential.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;

public class PlayerChatTab implements Listener {

    @EventHandler
    public void onTab(PlayerChatTabCompleteEvent e)
    {
        e.getPlayer().sendMessage("Oui");
    }
}
