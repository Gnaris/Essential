package sperias.essential.command;

import SPEssential.SPEssential;

public abstract class Command {

    protected SPEssential plugin;

    public Command(SPEssential plugin) {
        this.plugin = plugin;
    }
}
