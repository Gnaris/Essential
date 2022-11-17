package sperias.essential.command.cmd_teleportation;

import SPEssential.SPEssential;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sperias.essential.command.Command;
import sperias.essential.command.controller.TeleportationController;

public class TeleportDeny extends Command implements CommandExecutor {


    public TeleportDeny(SPEssential plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        if(args.length == 0 && !(sender instanceof Player))
        {
            Player player = (Player) sender;
            TeleportationController teleportationController = new TeleportationController(player, plugin);
            if(!teleportationController.haveTpRequest()) return false;
            Player target =  plugin.getPlayerTpRequest().get(player);
            target.sendMessage("§a" + player.getName() + " a nier votre demande");
            player.sendMessage("§a Vous avez refusé la demande");
            plugin.getPlayerTpRequest().remove(player);
        }
        return false;
    }
}
