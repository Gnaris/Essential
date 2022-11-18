package sperias.essential.command.controller;

import SPEssential.SPEssential;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class Controller {

    protected CommandSender sender;
    protected SPEssential plugin;

    public Controller(CommandSender sender) {
        this.sender = sender;
    }

    public Controller(CommandSender sender, SPEssential plugin) {
        this.sender = sender;
        this.plugin = plugin;
    }

    protected boolean havePermission(String permission)
    {
        if(!sender.hasPermission(permission) && !sender.isOp())
        {
            sender.sendMessage("§cVous n'avez pas les permission");
            return false;
        }
        return true;
    }

    public boolean existingTarget(Player target)
    {
        if(target == null)
        {
            sender.sendMessage("§cCe joueur n'existe pas");
            return false;
        }
        return true;
    }
}
