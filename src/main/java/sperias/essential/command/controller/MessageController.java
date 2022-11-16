package sperias.essential.command.controller;

import SPEssential.SPEssential;
import org.bukkit.entity.Player;

public class MessageController extends ControllerFactory{
    public MessageController(Player player) {
        super(player);
    }

    public MessageController(Player player, SPEssential plugin) {
        super(player, plugin);
    }

    public boolean canSpyMp(){return this.havePermission("sperias.essential.command.spymessage");}
}
