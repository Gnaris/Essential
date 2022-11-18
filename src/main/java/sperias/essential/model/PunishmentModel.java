package sperias.essential.model;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import sperias.essential.entity.Ban;
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

    public Map<UUID, Ban> getPlayerBanned() throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = database.getConnection().prepareStatement("SELECT uuid, reason, case when expiration = '0000-00-00 00:00:00' then null else expiration end as expiration FROM ban_list");
        ResultSet result = stmt.executeQuery();
        Map<UUID, Ban> playerBanned = new HashMap<>();
        while(result.next())
        {
            playerBanned.put(UUID.fromString(result.getString("uuid")), new Ban(result.getTimestamp("expiration"), result.getString("reason")));
        }
        return playerBanned;
    }
    public void insertPlayerBanned(CommandSender sender, String uuid, Timestamp date, String reason){
        new Thread(() -> {
            try {
                PreparedStatement stmt = database.getConnection().prepareStatement("INSERT INTO ban_list VALUES (?, ?, ?)");
                stmt.setString(1, uuid);
                stmt.setTimestamp(2, date);
                stmt.setString(3, reason);
                stmt.executeUpdate();
            } catch (SQLException | ClassNotFoundException e) {
                sender.sendMessage("§cVérifiez si le pseudo est correct et que le joueur a bien déjà joué sur le serveur");
            }
        }).start();
    }

    public void deletePlayerBanned(String uuid){
        new Thread(() ->  {
            try{
                PreparedStatement stmt = database.getConnection().prepareStatement("DELETE FROM ban_list WHERE uuid = ?");
                stmt.setString(1, uuid);
                stmt.executeUpdate();
            }catch (SQLException | ClassNotFoundException e)
            {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
