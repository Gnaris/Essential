package sperias.essential.event.Controller;

import SPEssential.SPEssential;
import sperias.essential.event.model.PlayerJoinModel;

public abstract class EventController {

    protected SPEssential plugin;
    protected PlayerJoinModel playerJoinModel = new PlayerJoinModel();

    public EventController(SPEssential plugin) {
        this.plugin = plugin;
    }
}
