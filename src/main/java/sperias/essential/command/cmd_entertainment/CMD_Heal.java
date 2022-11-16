package sperias.essential.command.cmd_entertainment;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sperias.essential.command.controller.EntertainmentController;

public class CMD_Heal implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        EntertainmentController entertainmentController = new EntertainmentController(player);
        if(args.length == 0 && entertainmentController.canHeal())
        {
            player.setHealth(20);
            player.sendMessage("§aVous êtes soigné");
            return true;
        }

        if(args.length == 1 && entertainmentController.canHealPlayer())
        {
            Player target = Bukkit.getPlayer(args[0]);
            if(!entertainmentController.existingTarget(target)) return false;
            target.setHealth(20);
            player.sendMessage("§a" + target.getName() + " est soigné");
            target.sendMessage("§aVous êtes soigné par " + player.getName());
            return true;
        }
        return false;
    }
}
