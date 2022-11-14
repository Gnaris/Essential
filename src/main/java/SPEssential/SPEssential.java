package SPEssential;

import Command.*;
import Event.PlayerDeath;
import Event.PlayerManagement;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class SPEssential extends JavaPlugin {

    private List<Player> playerSpyMessage = new ArrayList<>();
    private Map<Player, Player> tpRequest = new HashMap<>();

    @Override
    public void onEnable() {
        getCommand("gamemode").setExecutor(new CMD_Gamemode());
        getCommand("enderchest").setExecutor(new CMD_Enderchest());
        getCommand("fly").setExecutor(new CMD_Fly());
        getCommand("heal").setExecutor(new CMD_Heal());
        getCommand("feed").setExecutor(new CMD_Feed());
        getCommand("kill").setExecutor(new CMD_Kill());
        getCommand("vanish").setExecutor(new CMD_Vanish());
        getCommand("message").setExecutor(new CMD_Message(this));
        getCommand("spymessage").setExecutor(new CMD_SpyMessage(this));
        getCommand("teleport").setExecutor(new CMD_Teleport());
        getCommand("tpa").setExecutor(new CMD_TeleportA(this));
        getCommand("tpaccept").setExecutor(new CMD_Teleport());
        getCommand("tpdeny").setExecutor(new CMD_Teleport());

        getServer().getPluginManager().registerEvents(new PlayerManagement(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
    }

    @Override
    public void onDisable() {

    }

    public List<Player> getPlayerSpyMessage() {
        return playerSpyMessage;
    }

    public Map<Player, Player> getTpRequest() {
        return tpRequest;
    }
}
