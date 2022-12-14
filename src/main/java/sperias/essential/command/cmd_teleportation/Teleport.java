package sperias.essential.command.cmd_teleportation;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sperias.essential.command.controller.TeleportationController;

public class Teleport implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        TeleportationController teleportationController = new TeleportationController(player);
        if(!teleportationController.canTeleport()) return false;
        if(args.length == 1)
        {
            Player target = Bukkit.getPlayer(args[0]);
            if(!teleportationController.existingTarget(target)) return false;
            player.teleport(target.getLocation());
            player.sendMessage("§aVous vous êtes téléporté à " + target.getName());
            return true;
        }

        if(args.length == 2)
        {
            Player target1 = Bukkit.getPlayer(args[0]);
            Player target2 = Bukkit.getPlayer(args[1]);
            if(!teleportationController.existingTarget(target1)) return false;
            if(!teleportationController.existingTarget(target2)) return false;
            target1.teleport(target2.getLocation());
            player.sendMessage("§a" + target1.getName() + " s'est téléporté à " + target2.getName());
            target1.sendMessage("§aVous vous êtes téléporté à " + target2.getName());
            target2.sendMessage("§a" + target1.getName() + " s'est téléporté à vous");
            return true;
        }

        return false;
    }
}
