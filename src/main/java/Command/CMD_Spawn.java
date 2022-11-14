package Command;

import Command.Controller.EssentialController;
import Command.Parent.CommandFactory;
import SPEssential.SPEssential;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Spawn extends CommandFactory implements CommandExecutor {

    public CMD_Spawn(SPEssential plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 0 && sender instanceof Player)
        {
            Player player = (Player) sender;
            EssentialController essentialController = new EssentialController(player, plugin);
            if(!essentialController.haveSpawnLocation()) return false;
            player.teleport(plugin.getSpawnLocation());
            player.sendMessage("§aVous vous êtes téléporté au spawn");
            return true;
        }

        return false;
    }
}
