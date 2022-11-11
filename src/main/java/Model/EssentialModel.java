package Model;

import SPEssential.SPEssential;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EssentialModel {

    public String getPlayer(String uuid) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = SPEssential.getDatabase().prepareStatement("SELECT uuid FROM player WHERE uuid = ?");
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
        PreparedStatement stmt = SPEssential.getDatabase().prepareStatement("INSERT INTO player VALUES (NULL, ?, ?, DEFAULT, DEFAULT)");
        stmt.setString(1, uuid);
        stmt.setString(2, name);
        stmt.executeUpdate();
    }
}
