package sperias.essential.event.Controller;

import SPEssential.SPEssential;
import sperias.essential.model.PunishmentModel;
import sperias.essential.entity.Ban;

import java.util.Date;
import java.util.UUID;

public class PunishmentController extends EventController{
    public PunishmentController(SPEssential plugin) {
        super(plugin);
    }

    public boolean isBanned(UUID uuid)
    {
        if(plugin.getPlayerBanned().containsKey(uuid))
        {
            Ban ban = plugin.getPlayerBanned().get(uuid);
            if(ban.getExpiration().after(new Date()))return true;
            new PunishmentModel().deletePlayerBanned(uuid.toString());
            plugin.getPlayerBanned().remove(uuid);
        }
        return false;
    }
}
