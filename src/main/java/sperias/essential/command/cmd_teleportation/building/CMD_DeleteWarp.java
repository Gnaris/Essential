package sperias.essential.command.cmd_teleportation.building;

import SPEssential.SPEssential;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sperias.essential.command.CommandFactory;
import sperias.essential.command.controller.TeleportationController;

public class CMD_DeleteWarp extends CommandFactory implements CommandExecutor {
    public CMD_DeleteWarp(SPEssential plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 1 && sender instanceof Player)
        {
            Player player = (Player) sender;
            TeleportationController teleportationController = new TeleportationController(player, plugin);
            if(!teleportationController.canDeleteWarp(args[0])) return false;
            plugin.deleteWarp(args[0]);
            player.sendMessage("§aLe warp " + args[0] + " a bien été supprimé");
            return true;
        }
        return false;
    }
}
