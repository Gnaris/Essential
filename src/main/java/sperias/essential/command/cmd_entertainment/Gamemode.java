package sperias.essential.command.cmd_entertainment;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sperias.essential.command.controller.EntertainmentController;

public class Gamemode implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        EntertainmentController entertainmentController = new EntertainmentController(player);
        if(args.length == 1 && entertainmentController.canSetGamemode())
        {
            if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival"))
            {
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage("§aVous êtes maintenant en mode survie");
                return true;
            }

            if(args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative"))
            {
                player.setGameMode(GameMode.CREATIVE);
                player.sendMessage("§aVous êtes maintenant en mode creative");
                return true;
            }

            if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("spectator"))
            {
                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage("§aVous êtes maintenant en mode spectateur");
                return true;
            }

            if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("adventure"))
            {
                player.setGameMode(GameMode.ADVENTURE);
                player.sendMessage("§aVous êtes maintenant en mode aventure");
                return true;
            }
        }

        if(args.length == 2 && entertainmentController.canSetGamemode())
        {
            Player target = Bukkit.getPlayer(args[1]);
            if(!entertainmentController.existingTarget(target)) return false;

            if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival"))
            {
                target.setGameMode(GameMode.SURVIVAL);
                target.sendMessage("§aVous êtes maintenant en mode survie");
                player.sendMessage("§a" + target.getName() + " est maintenant en mode survie");
                return true;
            }

            if(args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative"))
            {
                target.setGameMode(GameMode.CREATIVE);
                target.sendMessage("§aVous êtes maintenant en mode creative");
                player.sendMessage("§a" + target.getName() + " est maintenant en mode creative");
                return true;
            }

            if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("spectator"))
            {
                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage("§aVous êtes maintenant en mode spectateur");
                player.sendMessage("§a" + target.getName() + " est maintenant en mode spectateur");
                return true;
            }

            if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("adventure"))
            {
                player.setGameMode(GameMode.ADVENTURE);
                player.sendMessage("§aVous êtes maintenant en mode aventure");
                player.sendMessage("§a" + target.getName() + " est maintenant en mode aventure");
                return true;
            }
        }

        return false;
    }
}
