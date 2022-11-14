package Command;

import Command.Controller.EssentialController;
import SPEssential.SPEssential;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.A;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.util.*;

public class CMD_Message implements CommandExecutor {

    private SPEssential plugin;

    public CMD_Message(SPEssential plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length > 1)
        {
            if(!(sender instanceof Player)) return false;
            Player player = (Player) sender;
            EssentialController essentialController = new EssentialController(player);
            Player target = Bukkit.getPlayer(args[0]);
            if(!essentialController.existingTarget(target)) return false;
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
