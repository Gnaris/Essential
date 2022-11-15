package Command;

import Command.Controller.EssentialController;
import Command.Parent.CommandFactory;
import SPEssential.SPEssential;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Hat extends CommandFactory implements CommandExecutor {
    public CMD_Hat(SPEssential plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

            if(args.length == 0 && sender instanceof Player)
            {
                Player player = (Player) sender;
                EssentialController essentialController = new EssentialController(player);
                if(!essentialController.canHat()) return false;
                player.getInventory().setHelmet(player.getInventory().getItemInMainHand());
                player.sendMessage("§aVoila un magnifique chapeau et tout propre !");
            }
        return false;
    }
}
