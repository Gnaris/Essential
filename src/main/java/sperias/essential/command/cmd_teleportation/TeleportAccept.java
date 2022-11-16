package sperias.essential.command.cmd_teleportation;

import SPEssential.SPEssential;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sperias.essential.command.CommandFactory;
import sperias.essential.command.controller.TeleportationController;

public class TeleportAccept extends CommandFactory implements CommandExecutor {

    public TeleportAccept(SPEssential plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 0 && !(sender instanceof Player))
        {
            Player player = (Player) sender;
            TeleportationController teleportationController = new TeleportationController(player, plugin);
            if(!teleportationController.haveTpRequest()) return false;
            Player target = plugin.getPlayerTpRequest().get(player);
            target.teleport(player);
            target.sendMessage("§a" + player.getName() + " a accepté votre requête de téléportation");
            player.sendMessage("§a" + target.getName() + " s'est téléporté à vous");
        }

        return true;
    }
}
