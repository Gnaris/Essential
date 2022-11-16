package sperias.essential.event.Controller;

import sperias.essential.event.model.PlayerJoinModel;

import java.sql.SQLException;

public class PlayerJoinController {

    public boolean isNewPlayer(String uuid) throws SQLException, ClassNotFoundException {
        return new PlayerJoinModel().getPlayer(uuid) == null;
    }
}
