package Event.Controller;

import Model.EssentialModel;

import java.sql.SQLException;

public class PlayerManagementController {

    public boolean isNewPlayer(String uuid) throws SQLException, ClassNotFoundException {
        return new EssentialModel().getPlayer(uuid) == null;
    }
}
