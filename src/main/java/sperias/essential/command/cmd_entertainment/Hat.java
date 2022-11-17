package sperias.essential.command.cmd_entertainment;

import SPEssential.SPEssential;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sperias.essential.command.Command;
import sperias.essential.command.controller.EntertainmentController;

public class Hat extends Command implements CommandExecutor {
    public Hat(SPEssential plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String s, String[] args) {

            if(args.length == 0 && sender instanceof Player)
            {
                Player player = (Player) sender;
                EntertainmentController entertainmentController = new EntertainmentController(player);
                if(!entertainmentController.canHat()) return false;
                player.getInventory().setHelmet(player.getInventory().getItemInMainHand());
                player.getInventory().setItemInMainHand(null);
                player.sendMessage("§aVoila un magnifique chapeau et tout propre !");
                return true;
            }
        return false;
    }
}
