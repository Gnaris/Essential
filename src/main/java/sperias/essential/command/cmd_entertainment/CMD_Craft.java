package sperias.essential.command.cmd_entertainment;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sperias.essential.command.controller.EntertainmentController;

public class CMD_Craft implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

            if(args.length == 0 && sender instanceof Player)
            {
                Player player = (Player) sender;
                EntertainmentController entertainmentController = new EntertainmentController(player);
                if(!entertainmentController.canCraft()) return false;
                player.openWorkbench(player.getLocation(), true);
            }
        return false;
    }
}
