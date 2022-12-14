package sperias.essential.command.cmd_message;

import SPEssential.SPEssential;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sperias.essential.command.Command;
import sperias.essential.command.controller.MessageController;

public class SpyMessage extends Command implements CommandExecutor {

    public SpyMessage(SPEssential plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

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
