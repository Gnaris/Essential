package sperias.essential.event;

import sperias.essential.event.Controller.PlayerJoinController;
import SPEssential.SPEssential;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import sperias.essential.event.Controller.PlayerPunishmentController;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerJoin implements Listener {

    private SPEssential plugin;
    private PlayerJoinController playerJoinController;
    private PlayerPunishmentController playerPunishmentController;
    private Map<UUID, Boolean> newPlayer = new HashMap<>();

    public PlayerJoin(SPEssential plugin) {
        this.plugin = plugin;
        this.playerJoinController = new PlayerJoinController(plugin);
        this.playerPunishmentController = new PlayerPunishmentController(plugin);
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onLogin(PlayerLoginEvent e) throws SQLException, ClassNotFoundException {
        if(playerPunishmentController.isBanned(e.getPlayer().getUniqueId()))
        {
            e.setResult(Result.KICK_BANNED);
            e.setKickMessage("§cVous êtes banni du serveur");
            return;
        }
        if(playerJoinController.isNewPlayer(e.getPlayer().getUniqueId(), e.getPlayer().getName()))
        {
            newPlayer.put(e.getPlayer().getUniqueId(), true);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if(newPlayer.containsKey(e.getPlayer().getUniqueId()))
        {
            Bukkit.broadcastMessage("Bienvenue " + e.getPlayer().getName() + " sur le serveur Sperias");
            newPlayer.remove(e.getPlayer().getUniqueId());
        }
    }
}
