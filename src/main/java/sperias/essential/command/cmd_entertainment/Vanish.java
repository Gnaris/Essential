package sperias.essential.command.cmd_entertainment;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sperias.essential.command.controller.EntertainmentController;

public class Vanish implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        EntertainmentController entertainmentController = new EntertainmentController(player);
        if(args.length == 0 && entertainmentController.canBeVanished())
        {
            if(player.isInvisible())
            {
                player.setInvisible(false);
                player.sendMessage("§aVous n'êtes plus un fantôme");
            }
            else
            {
                player.setInvisible(true);
                player.sendMessage("§aVous êtes maintenant un fantôme");
            }
            return true;
        }
        return false;
    }
}
