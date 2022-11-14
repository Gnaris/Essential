package Command;

import Command.Controller.EssentialController;
import Command.Parent.CommandFactory;
import SPEssential.SPEssential;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_SetSpawn extends CommandFactory implements CommandExecutor {

    public CMD_SetSpawn(SPEssential plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 0 && sender instanceof Player)
        {
            Player player = (Player) sender;
            EssentialController essentialController = new EssentialController(player, plugin);
            if(!essentialController.canSetSpawn()) return false;
            plugin.setSpawnLocation(player.getLocation());
            player.sendMessage("§aLe nouveau spawn a bien été défini");
            return true;
        }

        return false;
    }
}
