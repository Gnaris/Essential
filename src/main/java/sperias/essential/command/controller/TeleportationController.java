package sperias.essential.command.controller;

import SPEssential.SPEssential;
import org.bukkit.entity.Player;
import sperias.essential.entity.Warp;

public class TeleportationController extends ControllerFactory{

    public TeleportationController(Player player) {
        super(player);
    }

    public TeleportationController(Player player, SPEssential plugin) {
        super(player, plugin);
    }

    public boolean haveTpRequest()
    {
        if(plugin.getPlayerTpRequest().get(player) == null)
        {
            player.sendMessage("§cPersonne ne vous a demandé de se téléporter à vous");
            return false;
        }

        return true;
    }

    public boolean haveSpawnLocation()
    {
        if(plugin.getSpawnLocation() == null)
        {
            player.sendMessage("§cIl n'y a actuellement aucun spawn de disponible");
            return false;
        }

        return true;
    }

    public boolean canGoToWarp(String name)
    {
        if(plugin.getWarpLocation().get(name) == null)
        {
            player.sendMessage("§cCe warp n'existe pas");
            return false;
        }
        Warp warp = plugin.getWarpLocation().get(name);
        if(warp.getPermission() != null && !player.hasPermission(warp.getPermission()))
        {
            player.sendMessage("§cVous n'avez pas la permission d'y aller");
            return false;
        }

        return true;
    }

    public boolean canSetWarp(String name)
    {
        if(plugin.getWarpLocation().get(name) != null)
        {
            player.sendMessage("§cCe warp existe déjà");
            return false;
        }
        return this.havePermission("sperias.essential.command.setwarp");
    }

    public boolean canDeleteWarp(String name)
    {
        if(plugin.getWarpLocation().get(name) == null)
        {
            player.sendMessage("§cCe warp n'existe pas");
            return false;
        }
        return this.havePermission("sperias.essential.command.deletewarp");
    }

    public boolean canBack()
    {
        if(!this.havePermission("sperias.essential.command.back")) return false;
        if(plugin.getPlayerLastTeleportationLocation().get(player) == null)
        {
            player.sendMessage("§cVous n'avez pas de dernière position");
            return false;
        }

        return true;
    }

    public boolean canTeleport(){return this.havePermission("sperias.essential.command.tp");}
    public boolean canSetSpawn(){return this.havePermission("sperias.essential.command.setspawn");}
}
