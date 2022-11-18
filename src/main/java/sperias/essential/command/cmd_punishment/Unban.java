package sperias.essential.command.cmd_punishment;

import SPEssential.SPEssential;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import sperias.essential.command.Command;
import sperias.essential.command.controller.PunishmentController;
import sperias.essential.entity.PlayerApi;

import java.io.IOException;

public class Unban extends Command implements CommandExecutor {

    public Unban(SPEssential plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        if(args.length == 1)
        {
            try {
                String playerName = args[0];
                PlayerApi playerApi = PlayerApi.fetchPlayerAPI(playerName);
                PunishmentController punishmentController = new PunishmentController(sender, plugin);
                if(!punishmentController.canUnban(playerApi)) return false;
                plugin.getPlayerBanned().remove(playerApi.getUUID());
                sender.sendMessage("§a" + playerApi.getName() + " vient d'être débanni du serveur");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }
}
