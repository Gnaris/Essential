package sperias.essential.command.cmd_teleportation.building;

import SPEssential.SPEssential;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sperias.essential.command.Command;
import sperias.essential.command.controller.TeleportationController;

public class CMD_Spawn extends Command implements CommandExecutor {

    public CMD_Spawn(SPEssential plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        if(args.length == 0 && sender instanceof Player)
        {
            Player player = (Player) sender;
            TeleportationController teleportationController = new TeleportationController(player, plugin);
            if(!teleportationController.haveSpawnLocation()) return false;
            player.teleport(plugin.getSpawnLocation());
            player.sendMessage("§aVous vous êtes téléporté au spawn");
            return true;
        }

        return false;
    }
}
