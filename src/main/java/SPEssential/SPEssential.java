package SPEssential;

import Command.*;
import Event.PlayerDeath;
import Event.PlayerInvsee;
import Event.PlayerManagement;
import Event.PlayerTeleport;
import Model.EssentialModel;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import Entity.Warp;

import java.sql.SQLException;
import java.util.*;

public final class SPEssential extends JavaPlugin {

    private Location spawnLocation;
    private final Map<String, Warp> warpLocation = new HashMap<>();

    private final List<Player> playerSpyMessage = new ArrayList<>();
    private final Map<Player, Player> playerTpRequest = new HashMap<>();
    private final List<Player> playerInvsee = new ArrayList<>();
    private final Map<Player, Location> playerLastTeleportationLocation = new HashMap<>();

    private final Map<UUID, Date> playerBanned = new HashMap<>();

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        // TODO BAN A FAIRE
        try {
            new EssentialModel().getPlayerBanned();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

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


        Objects.requireNonNull(getCommand("gamemode")).setExecutor(new CMD_Gamemode());
        Objects.requireNonNull(getCommand("enderchest")).setExecutor(new CMD_Enderchest());
        Objects.requireNonNull(getCommand("fly")).setExecutor(new CMD_Fly());
        Objects.requireNonNull(getCommand("heal")).setExecutor(new CMD_Heal());
        Objects.requireNonNull(getCommand("feed")).setExecutor(new CMD_Feed());
        Objects.requireNonNull(getCommand("kill")).setExecutor(new CMD_Kill());
        Objects.requireNonNull(getCommand("vanish")).setExecutor(new CMD_Vanish());
        Objects.requireNonNull(getCommand("message")).setExecutor(new CMD_Message(this));
        Objects.requireNonNull(getCommand("spymessage")).setExecutor(new CMD_SpyMessage(this));
        Objects.requireNonNull(getCommand("teleport")).setExecutor(new CMD_Teleport());
        Objects.requireNonNull(getCommand("teleportto")).setExecutor(new CMD_TeleportTo(this));
        Objects.requireNonNull(getCommand("teleportaccept")).setExecutor(new CMD_TeleportAccept(this));
        Objects.requireNonNull(getCommand("teleportdeny")).setExecutor(new CMD_TeleportDeny(this));
        Objects.requireNonNull(getCommand("invsee")).setExecutor(new CMD_Invsee(this));
        Objects.requireNonNull(getCommand("spawn")).setExecutor(new CMD_Spawn(this));
        Objects.requireNonNull(getCommand("setspawn")).setExecutor(new CMD_SetSpawn(this));
        Objects.requireNonNull(getCommand("warp")).setExecutor(new CMD_Warp(this));
        Objects.requireNonNull(getCommand("setwarp")).setExecutor(new CMD_SetWarp(this));
        Objects.requireNonNull(getCommand("deletewarp")).setExecutor(new CMD_DeleteWarp(this));
        Objects.requireNonNull(getCommand("back")).setExecutor(new CMD_Back(this));
        Objects.requireNonNull(getCommand("hat")).setExecutor(new CMD_Hat(this));
        Objects.requireNonNull(getCommand("condense")).setExecutor(new CMD_Condense());
        Objects.requireNonNull(getCommand("craft")).setExecutor(new CMD_Craft());

        getServer().getPluginManager().registerEvents(new PlayerManagement(), this);
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
}
