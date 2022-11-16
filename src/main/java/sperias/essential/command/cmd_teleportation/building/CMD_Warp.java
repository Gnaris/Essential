package sperias.essential.command.cmd_teleportation.building;

import sperias.essential.command.CommandFactory;
import sperias.essential.command.controller.TeleportationController;
import sperias.essential.entity.Warp;
import SPEssential.SPEssential;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class CMD_Warp extends CommandFactory implements CommandExecutor {

    public CMD_Warp(SPEssential plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        if(args.length == 1)
        {
            TeleportationController teleportationController = new TeleportationController(player, plugin);
            if(!teleportationController.canGoToWarp(args[0])) return false;
            Warp warp = plugin.getWarpLocation().get(args[0]);
            player.teleport(warp.getLocation());
            player.sendMessage("§aVous vous êtes téléporté au warp " + warp.getName());
            return true;
        }

        if(args.length == 0)
        {
            if(plugin.getWarpLocation().size() > 0)
            {
                StringBuilder warpList = new StringBuilder();
                for(Map.Entry<String, Warp> warp : plugin.getWarpLocation().entrySet())
                {
                    if(warp.getValue().getPermission() != null && (player.hasPermission(warp.getValue().getPermission()) || player.isOp()))
                    {
                        warpList.append("§6" + warp.getValue().getName()).append(" ");
                    }

                    if(warp.getValue().getPermission() == null)
                    {
                        warpList.append("§6" + warp.getValue().getName()).append(" ");
                    }
                }
                player.sendMessage("§aVoici la liste des warps qui vous sont disponibles :");
                player.sendMessage(warpList.toString());
            }
            else
            {
                player.sendMessage("§cAucun warp est disponible actuellement");
            }
            return true;
        }

        return false;
    }
}
