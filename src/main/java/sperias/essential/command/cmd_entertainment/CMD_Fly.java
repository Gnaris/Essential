package sperias.essential.command.cmd_entertainment;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sperias.essential.command.controller.EntertainmentController;

public class CMD_Fly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        EntertainmentController entertainmentController = new EntertainmentController(player);
        if(args.length == 0 && entertainmentController.canFly())
        {
            if(player.isFlying())
            {
                player.setAllowFlight(false);
                player.setFlying(false);
                player.sendMessage("§aVous ne pouvez plus voler dans les airs");
            }
            else
            {
                player.setAllowFlight(true);
                player.setFlying(true);
                player.sendMessage("§aVous pouvez à présent voler dans les airs");
            }
        }

        return false;
    }
}
