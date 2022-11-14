package Command;

import Command.Controller.EssentialController;
import SPEssential.SPEssential;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_TeleportA implements CommandExecutor {

    private SPEssential plugin;

    public CMD_TeleportA(SPEssential plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        EssentialController essentialController = new EssentialController(player);
        if(args.length == 1)
        {
            Player target = Bukkit.getPlayer(args[0]);
            if(!essentialController.existingTarget(target)) return false;
            plugin.getTpRequest().put(target, player);
            player.sendMessage("§aVous avez fait une demande de téléportation sur " + target.getName());
            target.sendMessage("§a" + player.getName() + " vous demande de se téléporter à vous. /tpyes pour acceptée ou /tpno pour refusée");
            return true;
        }
        return false;
    }
}
