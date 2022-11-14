package SPEssential;

import Command.*;
import Event.PlayerDeath;
import Event.PlayerManagement;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class SPEssential extends JavaPlugin {

    private Location spawnLocation;
    private Map<String, Location> warpLocation = new HashMap<>();

    private final List<Player> playerSpyMessage = new ArrayList<>();
    private final Map<Player, Player> playerTpRequest = new HashMap<>();
    private final List<Player> playerInvsee = new ArrayList<>();

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        // ! ICIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
        spawnLocation = new Location(
                Bukkit.getWorld(getConfig().getString("spawn.world")),
                getConfig().getDouble("spawn.x"),
                getConfig().getDouble("spawn.y"),
                getConfig().getDouble("spawn.z"));



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
        Objects.requireNonNull(getCommand("teleportaccept")).setExecutor(new CMD_Teleport());
        Objects.requireNonNull(getCommand("teleportdeny")).setExecutor(new CMD_Teleport());
        Objects.requireNonNull(getCommand("invsee")).setExecutor(new CMD_Teleport());

        Objects.requireNonNull(getCommand("spawn")).setExecutor(new CMD_Teleport());
        Objects.requireNonNull(getCommand("warp")).setExecutor(new CMD_Teleport());
        Objects.requireNonNull(getCommand("setspawn")).setExecutor(new CMD_Teleport());
        Objects.requireNonNull(getCommand("setwarp")).setExecutor(new CMD_Teleport());


        getServer().getPluginManager().registerEvents(new PlayerManagement(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
    }

    @Override
    public void onDisable() {

    }

    public void setSpawnLocation(String world, double x, double y, double z, double yaw, double pitch)
    {
        getConfig().set("spawn.world", world);
        getConfig().set("spawn.x", x);
        getConfig().set("spawn.y", y);
        getConfig().set("spawn.z", z);
        getConfig().set("spawn.yaw", yaw);
        getConfig().set("spawn.pitch", pitch);
    }

    public void setWarpLocation(String name, String world, double x, double y, double z, double yaw, double pitch)
    {
        getConfig().set("warps." + name + ".world", world);
        getConfig().set("warps." + name + ".x", x);
        getConfig().set("warps." + name + ".y", y);
        getConfig().set("warps." + name + ".z", z);
        getConfig().set("warps." + name + ".yaw", yaw);
        getConfig().set("warps." + name + ".pitch", pitch);
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
}
