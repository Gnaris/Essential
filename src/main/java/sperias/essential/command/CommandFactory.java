package sperias.essential.command;

import SPEssential.SPEssential;

public abstract class CommandFactory {

    protected SPEssential plugin;

    public CommandFactory(SPEssential plugin) {
        this.plugin = plugin;
    }
}
