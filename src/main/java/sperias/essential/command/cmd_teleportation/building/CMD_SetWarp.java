package sperias.essential.command.cmd_teleportation.building;

import SPEssential.SPEssential;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sperias.essential.command.Command;
import sperias.essential.command.controller.TeleportationController;

public class CMD_SetWarp extends Command implements CommandExecutor {
    public CMD_SetWarp(SPEssential plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        if(args.length > 0 && args.length <= 2 && sender instanceof Player)
        {
            Player player = (Player) sender;
            TeleportationController teleportationController = new TeleportationController(player, plugin);
            if(!teleportationController.canSetWarp(args[0])) return false;
            String permission = null;
            if(args.length == 2) permission = args[1];
            plugin.addWarpLocation(args[0], player.getLocation(), permission);
            player.sendMessage("§aLe warp " + args[0] + " a bien été crée");
            return true;
        }
        return false;
    }
}
