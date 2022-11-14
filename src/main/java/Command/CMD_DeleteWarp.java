package Command;

import Command.Controller.EssentialController;
import Command.Parent.CommandFactory;
import SPEssential.SPEssential;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_DeleteWarp extends CommandFactory implements CommandExecutor {
    public CMD_DeleteWarp(SPEssential plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 1 && sender instanceof Player)
        {
            Player player = (Player) sender;
            EssentialController essentialController = new EssentialController(player, plugin);
            if(!essentialController.canDeleteWarp(args[0])) return false;
            plugin.deleteWarp(args[0]);
            player.sendMessage("§aLe warp " + args[0] + " a bien été supprimé");
            return true;
        }
        return false;
    }
}
