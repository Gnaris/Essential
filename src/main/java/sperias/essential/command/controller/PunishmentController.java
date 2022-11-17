package sperias.essential.command.controller;

import SPEssential.SPEssential;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import sperias.essential.command.model.PunishmentModel;
import sperias.essential.entity.PlayerApi;

import java.io.Console;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class PunishmentController{

    private ConsoleCommandSender console;

    public PunishmentController(ConsoleCommandSender console)
    {
        this.console = console;
    }

    public boolean canBan(Player target, String name, Timestamp date, String reason) throws SQLException, ClassNotFoundException {
        //if(!this.havePermission("sperias.essential.command.ban")) return false;
        UUID targetUUID = null;
        if(target == null)
        {
            try {
                if(PlayerApi.fetchPlayerAPI(name) == null)
                {
                    console.sendMessage("§cCe joueur n'existe pas !");
                    return false;
                }
                else
                {
                    targetUUID = PlayerApi.fetchPlayerAPI(name).getUUID();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        PunishmentModel punishmentModel = new PunishmentModel();
        if(target == null)
        {
            punishmentModel.insertPlayerBanned(targetUUID.toString(), date, reason);
        }
        else
        {
            punishmentModel.insertPlayerBanned(target.getUniqueId().toString(), date, reason);
        }
        return true;
    }
}
