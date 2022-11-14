package Command;

import Command.Controller.EssentialController;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Kill implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        EssentialController essentialController = new EssentialController(player);
        if(args.length == 0 && essentialController.canKill())
        {
            player.setHealth(0);
            player.sendMessage("§aVous vous êtes donné la mort");
            return true;
        }

        if(args.length == 1 && essentialController.canKillPlayer())
        {
            Player target = Bukkit.getPlayer(args[0]);
            if(!essentialController.existingTarget(target)) return false;
            target.setHealth(0);
            player.sendMessage("§aVous avez tuer " + target.getName() + " gratuitement");
            target.sendMessage("§a" + player.getName() + " vous demande de suivre le chemin de Dieu");
            return true;
        }
        return false;
    }
}
