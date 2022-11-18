package sperias.essential.command.controller;

import SPEssential.SPEssential;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EntertainmentController extends Controller {

    public EntertainmentController(Player player) {
        super(player);
    }
    public EntertainmentController(Player player, SPEssential plugin) {
        super(player, plugin);
    }

    public boolean canCondense(ItemStack block, int nbOreInventory)
    {
        if(!this.havePermission("sperias.essential.command.condense")) return false;
        if(block == null)
        {
            sender.sendMessage("§cCette item n'est pas condensable. Voici la liste : ");
            sender.sendMessage("§7Charbon§r, §7L'ingot de cuivre§r, §7L'ingot de fer§r, §7Lapis Lazulis§r, §7Redstone§r, §7Pépite d'or§r, §7L'ingot d'or§r, §7Diamant§r,  §7Émeraude§r, §7Quartz§r,");
            return false;
        }
        if(nbOreInventory < 9 && block.getType() != Material.QUARTZ_BLOCK)
        {
            sender.sendMessage("§cVous en avez pas assez pour condenser. Minimum : 9");
            return false;
        }
        if(nbOreInventory < 4 && block.getType() == Material.QUARTZ_BLOCK)
        {
            sender.sendMessage("§cVous en avez pas assez pour condenser. Minimum : 4");
            return false;
        }
        return true;
    }
    public boolean canHat(){return this.havePermission("sperias.essential.command.hat");}
    public boolean canCraft(){return this.havePermission("sperias.essential.command.craft");}
    public boolean canSetGamemode()
    {
        return this.havePermission("sperias.essential.command.gamemode");
    }
    public boolean canWatchEnderchest()
    {
        return this.havePermission("sperias.essential.command.enderchest.me");
    }
    public boolean canWatchPlayerEnderchest() {return this.havePermission("sperias.essential.command.enderchest");}
    public boolean canFly() {return this.havePermission("sperias.essential.command.fly");}
    public boolean canHeal() {return this.havePermission("sperias.essential.command.heal.me");}
    public boolean canHealPlayer() {return this.havePermission("sperias.essential.command.heal");}
    public boolean canFeed() {return this.havePermission("sperias.essential.command.feed.me");}
    public boolean canFeedPlayer() {return this.havePermission("sperias.essential.command.feed");}
    public boolean canKill(){return this.havePermission("sperias.essential.command.kill.me");}
    public boolean canKillPlayer(){return this.havePermission("sperias.essential.command.kill");}
    public boolean canBeVanished(){return this.havePermission("sperias.essential.command.vanish");}
    public boolean canInvsee(){return this.havePermission("sperias.essential.command.invsee");}
}
