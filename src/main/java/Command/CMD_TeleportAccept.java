package Command;

import Command.Controller.EssentialController;
import Command.Parent.CommandFactory;
import SPEssential.SPEssential;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_TeleportAccept extends CommandFactory implements CommandExecutor {

    public CMD_TeleportAccept(SPEssential plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 0 && !(sender instanceof Player))
        {
            Player player = (Player) sender;
            EssentialController essentialController = new EssentialController(player, plugin);
            if(!essentialController.haveTpRequest()) return false;
            Player target = plugin.getPlayerTpRequest().get(player);
            target.teleport(player);
            target.sendMessage("§a" + player.getName() + " a accepté votre requête de téléportation");
            player.sendMessage("§a" + target.getName() + " s'est téléporté à vous");
        }

        return true;
    }
}
