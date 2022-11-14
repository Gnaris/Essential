package Command;

import Command.Controller.EssentialController;
import Command.Parent.CommandFactory;
import SPEssential.SPEssential;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_SpyMessage extends CommandFactory implements CommandExecutor {

    public CMD_SpyMessage(SPEssential plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        EssentialController essentialController = new EssentialController(player);
        if(args.length == 0 && essentialController.canSpyMp())
        {
            if(plugin.getPlayerSpyMessage().contains(player))
            {
                plugin.getPlayerSpyMessage().remove(player);
                player.sendMessage("§aVous n'espionner plus les messages");
            }
            else
            {
                plugin.getPlayerSpyMessage().add(player);
                player.sendMessage("§aVous pouvez à présent espionner tout les messages privés");
            }
            return true;
        }
        return false;
    }
}
