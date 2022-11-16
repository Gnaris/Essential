package sperias.essential.command.cmd_teleportation.building;

import SPEssential.SPEssential;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sperias.essential.command.CommandFactory;
import sperias.essential.command.controller.TeleportationController;

public class CMD_SetSpawn extends CommandFactory implements CommandExecutor {

    public CMD_SetSpawn(SPEssential plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 0 && sender instanceof Player)
        {
            Player player = (Player) sender;
            TeleportationController teleportationController = new TeleportationController(player, plugin);
            if(!teleportationController.canSetSpawn()) return false;
            plugin.setSpawnLocation(player.getLocation());
            player.sendMessage("§aLe nouveau spawn a bien été défini");
            return true;
        }

        return false;
    }
}
