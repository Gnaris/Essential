package sperias.essential.event.Controller;

import SPEssential.SPEssential;

import java.sql.SQLException;
import java.util.UUID;

public class PlayerJoinController extends EventController{


    public PlayerJoinController(SPEssential plugin) {
        super(plugin);
    }

    public boolean isNewPlayer(UUID uuid, String name) throws SQLException, ClassNotFoundException {
        if(playerModel.getPlayer(uuid.toString()) != null)
        {
            return false;
        }
        playerModel.insertNewPlayer(uuid.toString(), name);
        return true;
    }
}
