package sperias.essential.event.Controller;

import SPEssential.SPEssential;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerLoginEvent;
import sperias.essential.event.model.PlayerJoinModel;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

public class PlayerJoinController extends EventController{


    public PlayerJoinController(SPEssential plugin) {
        super(plugin);
    }

    public boolean isNewPlayer(UUID uuid, String name) throws SQLException, ClassNotFoundException {
        if(playerJoinModel.getPlayer(uuid.toString()) != null)
        {
            return false;
        }
        playerJoinModel.insertNewPlayer(uuid.toString(), name);
        return true;
    }
}
