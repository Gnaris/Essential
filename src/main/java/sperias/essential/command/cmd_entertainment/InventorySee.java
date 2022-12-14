package sperias.essential.command.cmd_entertainment;

import SPEssential.SPEssential;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sperias.essential.command.Command;
import sperias.essential.command.controller.EntertainmentController;

public class InventorySee extends Command implements CommandExecutor {


    public InventorySee(SPEssential plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String s, String[] args) {

        if(args.length == 1 && sender instanceof Player)
        {
            Player player = (Player) sender;
            EntertainmentController entertainmentController = new EntertainmentController(player);
            if(!entertainmentController.canInvsee()) return false;
            Player target = Bukkit.getPlayer(args[0]);
            if(!entertainmentController.existingTarget(target)) return false;
            player.openInventory(target.getInventory());
            plugin.getPlayerInvsee().add(player);
        }
        return false;
    }
}
