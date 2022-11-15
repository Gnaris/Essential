package Command.Controller;

import Entity.Warp;
import SPEssential.SPEssential;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EssentialController {

    private Player player;
    private SPEssential plugin;

    public EssentialController(Player player) {
        this.player = player;
    }
    public EssentialController(Player player, SPEssential plugin) {
        this.player = player;
        this.plugin = plugin;
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
    public boolean canCondense(ItemStack block, int nbOreInventory)
    {
        if(!this.havePermission("sperias.essential.command.condense")) return false;
        if(block == null)
        {
            player.sendMessage("§cCette item n'est pas condensable. Voici la liste : ");
            player.sendMessage("§7Charbon§r, §7L'ingot de cuivre§r, §7L'ingot de fer§r, §7Lapis Lazulis§r, §7Redstone§r, §7Pépite d'or§r, §7L'ingot d'or§r, §7Diamant§r,  §7Émeraude§r, §7Quartz§r,");
            return false;
        }
        if(nbOreInventory < 9 && block.getType() != Material.QUARTZ_BLOCK)
        {
            player.sendMessage("§cVous en avez pas assez pour condenser. Minimum : 9");
            return false;
        }
        if(nbOreInventory < 4 && block.getType() == Material.QUARTZ_BLOCK)
        {
            player.sendMessage("§cVous en avez pas assez pour condenser. Minimum : 4");
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
    public boolean canSpyMp(){return this.havePermission("sperias.essential.command.spymessage");}
    public boolean canTeleport(){return this.havePermission("sperias.essential.command.tp");}
    public boolean canInvsee(){return this.havePermission("sperias.essential.command.invsee");}
    public boolean canSetSpawn(){return this.havePermission("sperias.essential.command.setspawn");}
    public boolean canHat(){return this.havePermission("sperias.essential.command.hat");}
    public boolean canCraft(){return this.havePermission("sperias.essential.command.craft");}


}
