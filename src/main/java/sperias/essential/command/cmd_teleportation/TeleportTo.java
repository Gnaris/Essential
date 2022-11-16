package sperias.essential.command.cmd_teleportation;

import SPEssential.SPEssential;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sperias.essential.command.CommandFactory;
import sperias.essential.command.controller.TeleportationController;

public class TeleportTo extends CommandFactory implements CommandExecutor {


    public TeleportTo(SPEssential plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 1 && !(sender instanceof Player))
        {
            Player player = (Player) sender;
            TeleportationController teleportationController = new TeleportationController(player, plugin);
            Player target = Bukkit.getPlayer(args[0]);
            if(!teleportationController.existingTarget(target)) return false;
            plugin.getPlayerTpRequest().put(target, player);
            player.sendMessage("§aVous avez fait une demande de téléportation sur " + target.getName());
            target.sendMessage("§a" + player.getName() + " vous demande de se téléporter à vous. /tpyes pour acceptée ou /tpno pour refusée");
            return true;
        }
        return false;
    }
}
