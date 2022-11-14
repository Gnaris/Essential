package Command;

import Command.Controller.EssentialController;
import SPEssential.SPEssential;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.annotation.Target;

public class CMD_TpAccept implements CommandExecutor {

    private SPEssential plugin;

    public CMD_TpAccept(SPEssential plugin) {
        this.plugin = plugin;
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
