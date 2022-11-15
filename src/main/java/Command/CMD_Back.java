package Command;

import Command.Controller.EssentialController;
import Command.Parent.CommandFactory;
import SPEssential.SPEssential;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Back extends CommandFactory implements CommandExecutor {
    public CMD_Back(SPEssential plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        EssentialController essentialController = new EssentialController(player, plugin);
        if(!essentialController.canBack()) return false;
        player.teleport(plugin.getPlayerLastTeleportationLocation().get(player));
        player.sendMessage("§cVous vous êtes téléporté à votre dernière position");
        return false;
    }
}
