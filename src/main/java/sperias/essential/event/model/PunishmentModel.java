package sperias.essential.event.model;

import org.bukkit.Bukkit;
import sperias.gnaris.SPDatabase.SPDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PunishmentModel {

    private final SPDatabase database = (SPDatabase) Bukkit.getServer().getPluginManager().getPlugin("SP_Database");

    public Map<UUID, Timestamp> getPlayerBanned() throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = database.getConnection().prepareStatement("SELECT uuid, case when expiration = '0000-00-00 00:00:00' then null else expiration end as expiration FROM ban_list");
        ResultSet result = stmt.executeQuery();
        Map<UUID, Timestamp> playerBanned = new HashMap<>();
        while(result.next())
        {
            playerBanned.put(UUID.fromString(result.getString("uuid")), result.getTimestamp("expiration"));
        }
        return playerBanned;
    }
}
