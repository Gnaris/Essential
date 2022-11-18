package sperias.essential.command.controller;

import SPEssential.SPEssential;
import org.bukkit.command.CommandSender;
import sperias.essential.model.PunishmentModel;
import sperias.essential.entity.PlayerApi;

import java.sql.Timestamp;

public class PunishmentController extends Controller{

    public PunishmentController(CommandSender sender) {
        super(sender);
    }
    public PunishmentController(CommandSender sender, SPEssential plugin) {
        super(sender, plugin);
    }

    public boolean canBan(PlayerApi playerApi, Timestamp date, String reason){
        if(!this.havePermission("sperias.essential.command.bannissement"))
        {
            sender.sendMessage("§cVous n'avez pas les permissions");
            return false;
        }
        if(playerApi == null)
        {
            sender.sendMessage("§cCe joueur n'existe pas");
            return false;
        }
        new PunishmentModel().insertPlayerBanned(sender, playerApi.getUUID().toString(), date, reason);
        return true;
    }

    public boolean canUnban(PlayerApi playerApi)
    {
        if(!this.havePermission("sperias.essential.command.bannissement")) return false;
        if(playerApi == null)
        {
            sender.sendMessage("§cCe joueur n'existe pas");
            return false;
        }

        new PunishmentModel().deletePlayerBanned(playerApi.getUUID().toString());
        return true;
    }
}
