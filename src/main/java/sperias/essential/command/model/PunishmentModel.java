package sperias.essential.command.model;

import org.bukkit.Bukkit;
import sperias.gnaris.SPDatabase.SPDatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class PunishmentModel {

    private final SPDatabase database = (SPDatabase) Bukkit.getServer().getPluginManager().getPlugin("SP_Database");


    public void insertPlayerBanned(String uuid, Timestamp date, String reason) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = database.getConnection().prepareStatement("INSERT INTO ban_list VALUES (?, ?, ?)");
        stmt.setString(1, uuid);
        stmt.setTimestamp(2, date);
        stmt.setString(3, reason);
        stmt.executeUpdate();
    }
}
