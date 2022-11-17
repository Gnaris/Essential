package sperias.essential.command.cmd_teleportation;

import SPEssential.SPEssential;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sperias.essential.command.Command;
import sperias.essential.command.controller.TeleportationController;

public class Back extends Command implements CommandExecutor {
    public Back(SPEssential plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String s, String[] args) {

        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        TeleportationController teleportationController = new TeleportationController(player, plugin);
        if(!teleportationController.canBack()) return false;
        player.teleport(plugin.getPlayerLastTeleportationLocation().get(player));
        player.sendMessage("§cVous vous êtes téléporté à votre dernière position");
        return false;
    }
}
