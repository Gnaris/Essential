package sperias.essential.event.Controller;

import SPEssential.SPEssential;

import java.util.UUID;

public class PlayerPunishmentController extends EventController{
    public PlayerPunishmentController(SPEssential plugin) {
        super(plugin);
    }

    public boolean isBanned(UUID uuid)
    {
        return plugin.getPlayerBanned().containsKey(uuid);
    }
}
