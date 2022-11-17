package sperias.essential.command.cmd_message;

import SPEssential.SPEssential;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sperias.essential.command.Command;
import sperias.essential.command.controller.MessageController;

import java.util.*;

public class Message extends Command implements CommandExecutor {

    public Message(SPEssential plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        if(args.length > 1 && sender instanceof Player)
        {
            Player player = (Player) sender;
            MessageController messageController = new MessageController(player);
            Player target = Bukkit.getPlayer(args[0]);
            if(!messageController.existingTarget(target)) return false;
            List<String> arguments = new ArrayList<>(Arrays.asList(args));
            arguments.remove(args[0]);
            StringBuilder message = new StringBuilder();
            arguments.forEach(word -> message.append(word).append(" "));
            player.sendMessage("§c§l(!) §r§7[ §aVous §3→ §2" + target.getName() + "§7] §8" + message);
            target.sendMessage("§c§l(!) §r§7[ §a" + target.getName() + " §3→ §2Vous §7] §8" + message);
            plugin.getPlayerSpyMessage().forEach(p -> p.sendMessage("§b§l(!) §r§7[ §a" + player.getName() + " §3→ §2" + target.getName() + "§7] §8" + message));
            return true;
        }
        return false;
    }
}
