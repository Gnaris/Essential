package sperias.essential.command.controller;

import SPEssential.SPEssential;
import org.bukkit.entity.Player;

public abstract class ControllerFactory{

    protected Player player;
    protected SPEssential plugin;

    public ControllerFactory(Player player) {
        this.player = player;
    }

    public ControllerFactory(Player player, SPEssential plugin) {
        this.player = player;
        this.plugin = plugin;
    }

    protected boolean havePermission(String permission)
    {
        if(!player.hasPermission(permission) && !player.isOp())
        {
            player.sendMessage("§cVous n'avez pas les permission");
            return false;
        }
        return true;
    }

    public boolean existingTarget(Player target)
    {
        if(target == null)
        {
            player.sendMessage("§cCe joueur n'existe pas");
            return false;
        }
        return true;
    }
}
