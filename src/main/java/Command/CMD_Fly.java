package Command;

import Command.Controller.EssentialController;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Fly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        EssentialController essentialController = new EssentialController(player);
        if(args.length == 0 && essentialController.canFly())
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
