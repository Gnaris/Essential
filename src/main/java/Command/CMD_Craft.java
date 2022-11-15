package Command;

import Command.Controller.EssentialController;
import Command.Parent.CommandFactory;
import SPEssential.SPEssential;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Craft implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

            if(args.length == 0 && sender instanceof Player)
            {
                Player player = (Player) sender;
                EssentialController essentialController = new EssentialController(player);
                if(!essentialController.canCraft()) return false;
                player.openWorkbench(player.getLocation(), true);
            }
        return false;
    }
}
