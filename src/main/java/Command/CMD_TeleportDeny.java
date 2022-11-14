package Command;

import Command.Controller.EssentialController;
import Command.Parent.CommandFactory;
import SPEssential.SPEssential;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_TeleportDeny extends CommandFactory implements CommandExecutor {


    public CMD_TeleportDeny(SPEssential plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 0 && !(sender instanceof Player))
        {
            Player player = (Player) sender;
            EssentialController essentialController = new EssentialController(player, plugin);
            if(!essentialController.haveTpRequest()) return false;
            Player target =  plugin.getPlayerTpRequest().get(player);
            target.sendMessage("§a" + player.getName() + " a nier votre demande");
            player.sendMessage("§a Vous avez refusé la demande");
            plugin.getPlayerTpRequest().remove(player);
        }
        return false;
    }
}
