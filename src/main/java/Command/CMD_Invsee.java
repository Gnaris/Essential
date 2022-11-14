package Command;

import Command.Controller.EssentialController;
import Command.Parent.CommandFactory;
import SPEssential.SPEssential;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Invsee extends CommandFactory implements CommandExecutor {


    public CMD_Invsee(SPEssential plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(args.length == 1 && sender instanceof Player)
        {
            Player player = (Player) sender;
            EssentialController essentialController = new EssentialController(player);
            if(!essentialController.canInvsee()) return false;
            Player target = Bukkit.getPlayer(args[0]);
            if(!essentialController.existingTarget(target)) return false;
            player.openInventory(target.getInventory());
            plugin.getPlayerInvsee().add(player);
        }
        return false;
    }
}
