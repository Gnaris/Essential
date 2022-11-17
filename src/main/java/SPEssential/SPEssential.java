package SPEssential;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import sperias.essential.command.cmd_entertainment.*;
import sperias.essential.command.cmd_message.Message;
import sperias.essential.command.cmd_message.SpyMessage;
import sperias.essential.command.cmd_punishment.TempBan;
import sperias.essential.command.cmd_teleportation.*;
import sperias.essential.command.cmd_teleportation.building.*;
import sperias.essential.entity.Warp;
import sperias.essential.event.PlayerDeath;
import sperias.essential.event.PlayerInvsee;
import sperias.essential.event.PlayerJoin;
import sperias.essential.event.PlayerTeleport;
import sperias.essential.event.model.PunishmentModel;

import java.sql.SQLException;
import java.util.*;

public final class SPEssential extends JavaPlugin {

    private Location spawnLocation;
    private final Map<String, Warp> warpLocation = new HashMap<>();

    private final List<Player> playerSpyMessage = new ArrayList<>();
    private final Map<Player, Player> playerTpRequest = new HashMap<>();
    private final List<Player> playerInvsee = new ArrayList<>();
    private final Map<Player, Location> playerLastTeleportationLocation = new HashMap<>();

    private Map<UUID, Date> playerBanned;


    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        if(getConfig().contains("spawn"))
        {
            spawnLocation = new Location(
                    Bukkit.getWorld(Objects.requireNonNull(getConfig().getString("spawn.world"))),
                    getConfig().getDouble("spawn.x"),
                    getConfig().getDouble("spawn.y"),
                    getConfig().getDouble("spawn.z"));
        }
        if(getConfig().contains("warps"))
        {
            for(String key : Objects.requireNonNull(getConfig().getConfigurationSection("warps")).getKeys(false))
            {
                World world = Bukkit.getWorld(Objects.requireNonNull(getConfig().getString("warps." + key + ".world")));
                double x = getConfig().getDouble("warps." + key + ".x");
                double y = getConfig().getDouble("warps." + key + ".y");
                double z = getConfig().getDouble("warps." + key + ".z");
                float yaw = (float) getConfig().getDouble("warps." + key + ".yaw");
                float pitch = (float) getConfig().getDouble("warps." + key + ".pitch");
                warpLocation.put(key, new Warp(key, new Location(world, x, y, z, yaw, pitch), getConfig().getString("warps." + key + ".permission")));
            }
        }

        try { playerBanned = new PunishmentModel().getPlayerBanned();} catch (SQLException | ClassNotFoundException e) {throw new RuntimeException(e);}


        Objects.requireNonNull(getCommand("gamemode")).setExecutor(new Gamemode());
        Objects.requireNonNull(getCommand("enderchest")).setExecutor(new Enderchest());
        Objects.requireNonNull(getCommand("fly")).setExecutor(new Fly());
        Objects.requireNonNull(getCommand("heal")).setExecutor(new Heal());
        Objects.requireNonNull(getCommand("feed")).setExecutor(new Feed());
        Objects.requireNonNull(getCommand("kill")).setExecutor(new Kill());
        Objects.requireNonNull(getCommand("vanish")).setExecutor(new Vanish());
        Objects.requireNonNull(getCommand("message")).setExecutor(new Message(this));
        Objects.requireNonNull(getCommand("spymessage")).setExecutor(new SpyMessage(this));
        Objects.requireNonNull(getCommand("teleport")).setExecutor(new Teleport());
        Objects.requireNonNull(getCommand("teleportto")).setExecutor(new TeleportTo(this));
        Objects.requireNonNull(getCommand("teleportaccept")).setExecutor(new TeleportAccept(this));
        Objects.requireNonNull(getCommand("teleportdeny")).setExecutor(new TeleportDeny(this));
        Objects.requireNonNull(getCommand("invsee")).setExecutor(new InventorySee(this));
        Objects.requireNonNull(getCommand("spawn")).setExecutor(new CMD_Spawn(this));
        Objects.requireNonNull(getCommand("setspawn")).setExecutor(new CMD_SetSpawn(this));
        Objects.requireNonNull(getCommand("warp")).setExecutor(new CMD_Warp(this));
        Objects.requireNonNull(getCommand("setwarp")).setExecutor(new CMD_SetWarp(this));
        Objects.requireNonNull(getCommand("deletewarp")).setExecutor(new CMD_DeleteWarp(this));
        Objects.requireNonNull(getCommand("back")).setExecutor(new Back(this));
        Objects.requireNonNull(getCommand("hat")).setExecutor(new Hat(this));
        Objects.requireNonNull(getCommand("condense")).setExecutor(new Condense());
        Objects.requireNonNull(getCommand("craft")).setExecutor(new Craft());
        Objects.requireNonNull(getCommand("tempban")).setExecutor(new TempBan(this));

        getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        getServer().getPluginManager().registerEvents(new PlayerDeath(this), this);
        getServer().getPluginManager().registerEvents(new PlayerInvsee(this), this);
        getServer().getPluginManager().registerEvents(new PlayerTeleport(this), this);
    }

    @Override
    public void onDisable() {

    }



    public Location getSpawnLocation() {
        return spawnLocation;
    }
    public Map<String, Warp> getWarpLocation() {
        return warpLocation;
    }
    public void setSpawnLocation(Location location)
    {
        getConfig().set("spawn.world", location.getWorld().getName());
        getConfig().set("spawn.x", location.getX());
        getConfig().set("spawn.y", location.getY());
        getConfig().set("spawn.z", location.getZ());
        getConfig().set("spawn.yaw", location.getYaw());
        getConfig().set("spawn.pitch", location.getPitch());
        saveConfig();
        this.spawnLocation = location;
    }
    public void addWarpLocation(String name, Location location, String permission)
    {
        getConfig().set("warps." + name + ".world", location.getWorld().getName());
        getConfig().set("warps." + name + ".x", location.getX());
        getConfig().set("warps." + name + ".y", location.getY());
        getConfig().set("warps." + name + ".z", location.getZ());
        getConfig().set("warps." + name + ".yaw", location.getYaw());
        getConfig().set("warps." + name + ".pitch", location.getPitch());
        if(permission != null)
        {
            getConfig().set("warps." + name + ".permission", permission);
        }
        saveConfig();
        warpLocation.put(name, new Warp(name, location, permission));
    }
    public void deleteWarp(String name)
    {
        warpLocation.remove(name);
        getConfig().set("warps." + name, null);
        saveConfig();
    }


    public List<Player> getPlayerSpyMessage() {
        return playerSpyMessage;
    }
    public Map<Player, Player> getPlayerTpRequest() {
        return playerTpRequest;
    }
    public List<Player> getPlayerInvsee() {
        return playerInvsee;
    }
    public Map<Player, Location> getPlayerLastTeleportationLocation() {
        return playerLastTeleportationLocation;
    }
    public Map<UUID, Date> getPlayerBanned() {
        return playerBanned;
    }
}
