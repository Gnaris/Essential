package Command.Controller;

import org.bukkit.entity.Player;

public class EssentialController {

    private Player player;

    public EssentialController(Player player) {
        this.player = player;
    }

    private boolean havePermission(String permission)
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

    public boolean canSetGamemode()
    {
        return this.havePermission("sperias.essential.command.gamemode");
    }

    public boolean canWatchEnderchest()
    {
        return this.havePermission("sperias.essential.command.enderchest");
    }
    public boolean canWatchPlayerEnderchest() {return this.havePermission("sperias.essential.command.enderchest.player");}

    public boolean canFly() {return this.havePermission("sperias.essential.command.fly");}

    public boolean canHeal() {return this.havePermission("sperias.essential.command.heal");}
    public boolean canHealPlayer() {return this.havePermission("sperias.essential.command.heal.player");}

    public boolean canFeed() {return this.havePermission("sperias.essential.command.feed");}
    public boolean canFeedPlayer() {return this.havePermission("sperias.essential.command.feed.player");}

    public boolean canKill(){return this.havePermission("sperias.essential.command.kill");}
    public boolean canKillPlayer(){return this.havePermission("sperias.essential.command.kill.player");}

    public boolean canBeVanished(){return this.havePermission("sperias.essential.command.vanish");}

    public boolean canSpyMp(){return this.havePermission("sperias.essential.command.spymessage");}

    public boolean canTeleport(){return this.havePermission("sperias.essential.command.tp");}
}
