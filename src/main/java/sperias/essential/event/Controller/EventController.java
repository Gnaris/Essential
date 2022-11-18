package sperias.essential.event.Controller;

import SPEssential.SPEssential;
import sperias.essential.model.PlayerModel;

public abstract class EventController {

    protected SPEssential plugin;
    protected PlayerModel playerModel = new PlayerModel();

    public EventController(SPEssential plugin) {
        this.plugin = plugin;
    }
}
