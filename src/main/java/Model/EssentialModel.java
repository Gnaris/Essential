package Model;

import org.bukkit.Bukkit;
import sperias.gnaris.SPDatabase.SPDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class EssentialModel {

    private final SPDatabase database = (SPDatabase) Bukkit.getServer().getPluginManager().getPlugin("SP_Database");

    public String getPlayer(String uuid) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = database.getConnection().prepareStatement("SELECT uuid FROM player WHERE uuid = ?");
        stmt.setString(1, uuid);
        ResultSet result = stmt.executeQuery();
        String player = null;
        while (result.next())
        {
            player = result.getString("uuid");
        }
        return player;
    }

    public void insertNewPlayer(String uuid, String name) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = database.getConnection().prepareStatement("INSERT INTO player VALUES (NULL, ?, ?, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT)");
        stmt.setString(1, uuid);
        stmt.setString(2, name);
        stmt.executeUpdate();
    }

    public void getPlayerBanned() throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = database.getConnection().prepareStatement("SELECT name, banned, banExpiration FROM ban_list");
        ResultSet result = stmt.executeQuery();
        while(result.next())
        {
            System.out.println(result.getString("n"));
        }
    }
}
