package Command;

import Command.Controller.EssentialController;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Feed implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        EssentialController essentialController = new EssentialController(player);
        if(args.length == 0 && essentialController.canFeed())
        {
            player.setFoodLevel(20);
            player.sendMessage("§aVous êtes rassasié");
            return true;
        }

        if(args.length == 1 && essentialController.canFeedPlayer())
        {
            Player target = Bukkit.getPlayer(args[0]);
            if(!essentialController.existingTarget(target)) return false;
            target.setFoodLevel(20);
            player.sendMessage("§a" + target.getName() + " est rassasié");
            target.sendMessage("§aVous êtes rassasié");
            return true;
        }
        return false;
    }
}
