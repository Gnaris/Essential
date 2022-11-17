package sperias.essential.command.cmd_entertainment;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sperias.essential.command.controller.EntertainmentController;

public class Kill implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        EntertainmentController entertainmentController = new EntertainmentController(player);
        if(args.length == 0 && entertainmentController.canKill())
        {
            player.setHealth(0);
            player.sendMessage("§aVous vous êtes donné la mort");
            return true;
        }

        if(args.length == 1 && entertainmentController.canKillPlayer())
        {
            Player target = Bukkit.getPlayer(args[0]);
            if(!entertainmentController.existingTarget(target)) return false;
            target.setHealth(0);
            player.sendMessage("§aVous avez tuer " + target.getName() + " gratuitement");
            target.sendMessage("§a" + player.getName() + " vous demande de suivre le chemin de Dieu");
            return true;
        }
        return false;
    }
}
