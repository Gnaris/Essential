package sperias.essential.command.controller;

import SPEssential.SPEssential;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sperias.essential.entity.Warp;

public class TeleportationController extends Controller {


    public TeleportationController(CommandSender sender) {
        super(sender);
    }

    public TeleportationController(CommandSender sender, SPEssential plugin) {
        super(sender, plugin);
    }

    public boolean haveTpRequest()
    {
        if(plugin.getPlayerTpRequest().get((Player) sender) == null)
        {
            sender.sendMessage("§cPersonne ne vous a demandé de se téléporter à vous");
            return false;
        }

        return true;
    }

    public boolean haveSpawnLocation()
    {
        if(plugin.getSpawnLocation() == null)
        {
            sender.sendMessage("§cIl n'y a actuellement aucun spawn de disponible");
            return false;
        }

        return true;
    }

    public boolean canGoToWarp(String name)
    {
        if(plugin.getWarpLocation().get(name) == null)
        {
            sender.sendMessage("§cCe warp n'existe pas");
            return false;
        }
        Warp warp = plugin.getWarpLocation().get(name);
        if(warp.getPermission() != null && !sender.hasPermission(warp.getPermission()))
        {
            sender.sendMessage("§cVous n'avez pas la permission d'y aller");
            return false;
        }

        return true;
    }

    public boolean canSetWarp(String name)
    {
        if(plugin.getWarpLocation().get(name) != null)
        {
            sender.sendMessage("§cCe warp existe déjà");
            return false;
        }
        return this.havePermission("sperias.essential.command.setwarp");
    }

    public boolean canDeleteWarp(String name)
    {
        if(plugin.getWarpLocation().get(name) == null)
        {
            sender.sendMessage("§cCe warp n'existe pas");
            return false;
        }
        return this.havePermission("sperias.essential.command.deletewarp");
    }

    public boolean canBack()
    {
        if(!this.havePermission("sperias.essential.command.back")) return false;
        if(plugin.getPlayerLastTeleportationLocation().get((Player) sender) == null)
        {
            sender.sendMessage("§cVous n'avez pas de dernière position");
            return false;
        }

        return true;
    }

    public boolean canTeleport(){return this.havePermission("sperias.essential.command.tp");}
    public boolean canSetSpawn(){return this.havePermission("sperias.essential.command.setspawn");}
}
