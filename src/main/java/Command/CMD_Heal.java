package Command;

import Command.Controller.EssentialController;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Heal implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        EssentialController essentialController = new EssentialController(player);
        if(args.length == 0 && essentialController.canHeal())
        {
            player.setHealth(20);
            player.sendMessage("§aVous êtes soigné");
            return true;
        }

        if(args.length == 1 && essentialController.canHealPlayer())
        {
            Player target = Bukkit.getPlayer(args[0]);
            if(!essentialController.existingTarget(target)) return false;
            target.setHealth(20);
            player.sendMessage("§a" + target.getName() + " est soigné");
            target.sendMessage("§aVous êtes soigné par " + player.getName());
            return true;
        }
        return false;
    }
}
