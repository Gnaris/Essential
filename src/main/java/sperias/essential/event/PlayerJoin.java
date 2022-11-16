package sperias.essential.event;

import sperias.essential.event.Controller.PlayerJoinController;
import SPEssential.SPEssential;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import sperias.essential.event.model.PlayerJoinModel;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerJoin implements Listener {

    private SPEssential plugin;
    private Map<UUID, Boolean> isNewPlayer = new HashMap<>();

    public PlayerJoin(SPEssential plugin) {
        this.plugin = plugin;
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onLogin(PlayerLoginEvent e) throws SQLException, ClassNotFoundException {
        if(plugin.getPlayerBanned().containsKey(e.getPlayer().getUniqueId()))
        {
            Timestamp time = plugin.getPlayerBanned().get(e.getPlayer().getUniqueId());
            e.setResult(Result.KICK_BANNED);
            e.setKickMessage("§cVous êtes bannis du serveur ! \n\n\n\n\n §cVous êtes bannis du serveurs !");
            return;
        }
        if(!new PlayerJoinController().isNewPlayer(e.getPlayer().getUniqueId().toString())) return;
        new PlayerJoinModel().insertNewPlayer(e.getPlayer().getUniqueId().toString(), e.getPlayer().getName());
        isNewPlayer.put(e.getPlayer().getUniqueId(), true);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if(isNewPlayer.get(e.getPlayer().getUniqueId()) == null)  return;
        Bukkit.broadcastMessage("Bienvenue " + e.getPlayer().getName() + " sur le serveur Sperias");
        isNewPlayer.remove(e.getPlayer().getUniqueId());
    }
}
