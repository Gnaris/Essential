package SPEssential;

import Event.PlayerManagement;
import org.bukkit.plugin.java.JavaPlugin;
import sperias.gnaris.SPDatabase.SPDatabase;
import sperias.gnaris.database.Database;

import java.sql.Connection;
import java.sql.SQLException;

public final class SPEssential extends JavaPlugin {

    private final SPDatabase database = (SPDatabase) getServer().getPluginManager().getPlugin("SP_Database");
    private static SPEssential INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        getServer().getPluginManager().registerEvents(new PlayerManagement(), this);
    }

    @Override
    public void onDisable() {

    }

    public static SPEssential getInstance()
    {
        return INSTANCE;
    }
    public static Connection getDatabase() throws SQLException, ClassNotFoundException {
        return getInstance().database.getSPDatabase().getDatabase();
    }
}
