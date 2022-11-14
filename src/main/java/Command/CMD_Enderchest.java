package Command;

import Command.Controller.EssentialController;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Enderchest implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        EssentialController essentialController = new EssentialController(player);

        if(args.length == 0 && essentialController.canWatchEnderchest())
        {
            player.openInventory(player.getEnderChest());
            return true;
        }

        if(args.length == 1 && essentialController.canWatchPlayerEnderchest())
        {
            Player target = Bukkit.getPlayer(args[0]);
            if(!essentialController.existingTarget(target)) return false;
            player.openInventory(target.getEnderChest());
            return true;
        }

        return false;
    }
}
