package sperias.essential.command.cmd_entertainment;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sperias.essential.command.controller.EntertainmentController;

public class CMD_Enderchest implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        EntertainmentController entertainmentController = new EntertainmentController(player);

        if(args.length == 0 && entertainmentController.canWatchEnderchest())
        {
            player.openInventory(player.getEnderChest());
            return true;
        }

        if(args.length == 1 && entertainmentController.canWatchPlayerEnderchest())
        {
            Player target = Bukkit.getPlayer(args[0]);
            if(!entertainmentController.existingTarget(target)) return false;
            player.openInventory(target.getEnderChest());
            return true;
        }

        return false;
    }
}
