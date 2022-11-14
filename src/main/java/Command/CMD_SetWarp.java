package Command;

import Command.Controller.EssentialController;
import Command.Parent.CommandFactory;
import SPEssential.SPEssential;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_SetWarp extends CommandFactory implements CommandExecutor {
    public CMD_SetWarp(SPEssential plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length > 0 && args.length <= 2 && sender instanceof Player)
        {
            Player player = (Player) sender;
            EssentialController essentialController = new EssentialController(player, plugin);
            if(!essentialController.canSetWarp(args[0])) return false;
            String permission = null;
            if(args.length == 2) permission = args[1];
            plugin.addWarpLocation(args[0], player.getLocation(), permission);
            player.sendMessage("§aLe warp " + args[0] + " a bien été crée");
            return true;
        }
        return false;
    }
}
