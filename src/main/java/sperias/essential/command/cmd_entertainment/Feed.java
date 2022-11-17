package sperias.essential.command.cmd_entertainment;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sperias.essential.command.controller.EntertainmentController;

public class Feed implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        EntertainmentController entertainmentController = new EntertainmentController(player);
        if(args.length == 0 && entertainmentController.canFeed())
        {
            player.setFoodLevel(20);
            player.sendMessage("§aVous êtes rassasié");
            return true;
        }

        if(args.length == 1 && entertainmentController.canFeedPlayer())
        {
            Player target = Bukkit.getPlayer(args[0]);
            if(!entertainmentController.existingTarget(target)) return false;
            target.setFoodLevel(20);
            player.sendMessage("§a" + target.getName() + " est rassasié");
            target.sendMessage("§aVous êtes rassasié");
            return true;
        }
        return false;
    }
}
