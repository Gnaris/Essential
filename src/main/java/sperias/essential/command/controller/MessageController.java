package sperias.essential.command.controller;

import SPEssential.SPEssential;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageController extends Controller {

    public MessageController(CommandSender sender) {
        super(sender);
    }

    public boolean canSpyMp(){return this.havePermission("sperias.essential.command.spymessage");}
}
