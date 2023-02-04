package net.bosshub.guimaker;

import net.bosshub.guimaker.Commands.HelpCommand;
import net.bosshub.guimaker.Commands.LeaderboardCommand;
import net.bosshub.guimaker.Commands.RulesCommand;
import net.bosshub.guimaker.Commands.StatCommand;
import net.bosshub.guimaker.Listeners.InventorySetup;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class GuiMaker extends JavaPlugin {

    private InventorySetup inventorySetup;


    @Override
    public void onEnable() {
        this.inventorySetup = new InventorySetup(this);

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.getPluginManager().registerEvents(new InventorySetup(this), this);
        } else {
            Bukkit.getLogger().warning("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        // Set up commands
        getCommand("help").setExecutor(new HelpCommand(this));
        getCommand("leaderboard").setExecutor(new LeaderboardCommand(this));
        getCommand("stats").setExecutor(new StatCommand(this));
        getCommand("rules").setExecutor(new RulesCommand(this));

        // Set up listeners
        // getServer().getPluginManager().registerEvents(new InventorySetup(this), this);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public InventorySetup getInventorySetup() {
        return inventorySetup;
    }
}
