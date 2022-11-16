package sperias.essential.command.cmd_message;

import SPEssential.SPEssential;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sperias.essential.command.CommandFactory;
import sperias.essential.command.controller.MessageController;

public class CMD_SpyMessage extends CommandFactory implements CommandExecutor {

    public CMD_SpyMessage(SPEssential plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        MessageController messageController = new MessageController(player);
        if(args.length == 0 && messageController.canSpyMp())
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
