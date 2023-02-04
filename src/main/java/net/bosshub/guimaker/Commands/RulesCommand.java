package net.bosshub.guimaker.Commands;

import net.bosshub.guimaker.GuiMaker;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class RulesCommand implements CommandExecutor {

    private final GuiMaker plugin;

    public RulesCommand(GuiMaker plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player) {

            Player player = (Player) sender;

            UUID uuid = player.getUniqueId();

            plugin.getInventorySetup().initializeRulesItems(false);

            plugin.getInventorySetup().openInventory(player, plugin.getInventorySetup().rulesInv);

        }

        return true;

    }

}
