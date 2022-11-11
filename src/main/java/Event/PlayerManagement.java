package Event;

import Event.Controller.PlayerManagementController;
import Model.EssentialModel;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManagement implements Listener {

    Map<UUID, Boolean> isNewPlayer = new HashMap<>();

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onLogin(PlayerLoginEvent e) throws SQLException, ClassNotFoundException {
        if(!new PlayerManagementController().isNewPlayer(e.getPlayer().getUniqueId().toString())) return;
        new EssentialModel().insertNewPlayer(e.getPlayer().getUniqueId().toString(), e.getPlayer().getName());
        isNewPlayer.put(e.getPlayer().getUniqueId(), true);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if(isNewPlayer.get(e.getPlayer().getUniqueId()) == null)  return;
        Bukkit.broadcastMessage("Bienvenue " + e.getPlayer().getName() + " sur le serveur Sperias");
        isNewPlayer.remove(e.getPlayer().getUniqueId());
    }
}
