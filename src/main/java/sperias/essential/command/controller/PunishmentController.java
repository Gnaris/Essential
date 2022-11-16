package sperias.essential.command.controller;

import SPEssential.SPEssential;
import org.bukkit.entity.Player;

public class PunishmentController extends ControllerFactory{

    public PunishmentController(Player player) {
        super(player);
    }

    public PunishmentController(Player player, SPEssential plugin) {
        super(player, plugin);
    }
}
