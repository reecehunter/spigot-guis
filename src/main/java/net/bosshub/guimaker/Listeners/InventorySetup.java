package net.bosshub.guimaker.Listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import net.bosshub.guimaker.GuiMaker;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.UUID;

public class InventorySetup implements Listener {

    private final GuiMaker plugin;

    public InventorySetup(GuiMaker plugin) {
        this.plugin = plugin;
    }

    public Inventory inv;
    public Inventory leaderboardInv;
    public Inventory statsInv;
    public Inventory rulesInv;

    private final String leaderboardInvName = "           Leaderboards";
    private final String statsInvName = "              Your Stats";
    private final String helpInvName = "            Starter Guide";
    private final String rulesInvName = "                 Rules";


    public void initializeHelpItems() {

        inv = Bukkit.createInventory(null, 27, helpInvName);

        inv.clear();

        inv.setItem(10, createGuiItem(
                Material.KNOWLEDGE_BOOK,
                ChatColor.of("#ff0000")+"§lRULES",
                ChatColor.of("#ff0000")+"→ §fClick to view the rules"
        ));

        inv.setItem(11, createGuiItem(
                Material.IRON_SWORD,
                "§e§lJOIN A TEAM",
                "§e→ /team §fto join a team",
                "§e→ §fTeam members can't attack each other",
                "§e→ §fBecome the most powerful team"
        ));

        inv.setItem(12, createGuiItem(
                Material.GOLD_INGOT,
                "§a§lEARN MONEY",
                "§a→ §fBuild farms and grinders",
                "§a→ §fSell items with §a/sell §fand §a/ah",
                "§a→ §fBecome the richest player"
        ));

        inv.setItem(13, createGuiItem(
                Material.GRASS_BLOCK,
                "§6§lBUILD YOUR BASE",
                "§6→ §fGo as far out as you can",
                "§6→ §fBuild a hidden base",
                "§6→ §fDon't let anyone find it"
        ));

        inv.setItem(14, createGuiItem(
                Material.EMERALD,
                ChatColor.of("#b400ff")+"§lEARN GEMS",
                ChatColor.of("#b400ff")+"→ /afk §fto get teleported",
                ChatColor.of("#b400ff")+"→ §fEarn " + ChatColor.of("#b400ff") + "1 Gem" + " §fevery minute",
                ChatColor.of("#b400ff")+"→ /afkshop §fto spend Gems"
        ));

        inv.setItem(15, createGuiItem(
                Material.REDSTONE,
                "§3§lLEARN COMMANDS",
                "§3→ /sethome §fto set a home",
                "§3→ /homes §fto view your homes",
                "§3→ /afk §fto earn Gems",
                "§3→ /sell §fto sell items",
                "§3→ /ah §fto auction items"
        ));

        inv.setItem(16, createGuiItem(
                Material.IRON_PICKAXE,
                ChatColor.of("#00a4ff")+"§lGET STARTED",
                ChatColor.of("#00a4ff")+"→ /rtp §fto teleport into the world",
                ChatColor.of("#00a4ff")+"→ §fBe careful who you trust"
        ));

    }

    public void initializeRulesItems(boolean hasBack) {

        rulesInv = Bukkit.createInventory(null, 27, rulesInvName);

        rulesInv.clear();

        rulesInv.setItem(11, createGuiItem(
                Material.KNOWLEDGE_BOOK,
                "§a§lGENERAL RULES",
                "§a→ §fNo bug abuse/exploits",
                "§a→ §fNo attempting exploits/duping",
                "§a→ §fNo impersonation of staff",
                "§a→ §fNo punishment evasion",
                "§a→ §fNo doxxing or DDOSing",
                "§a→ §fNo lying to staff",
                "§a→ §fReport rule-breakers to staff in",
                "§four Discord (§adiscord.gg/bosshub§f)"
        ));

        rulesInv.setItem(13, createGuiItem(
                Material.KNOWLEDGE_BOOK,
                "§c§lCLIENT/MOD RULES",
                "§c→ §fNo hacked clients",
                "§c→ §fNo macros",
                "§c→ §fNo movement mods",
                "§c→ §fNo inventory mods",
                "§c→ §fNo freecam",
                "§c→ §fNo esp/xray/radar",
                "§c→ §fNo autoplace",
                "§c→ §fNo automation of any kind",
                "§c→ §fReport rule-breakers to staff in",
                "§four Discord (§cdiscord.gg/bosshub§f)"
        ));

        rulesInv.setItem(15, createGuiItem(
                Material.KNOWLEDGE_BOOK,
                "§e§lCHAT RULES",
                "§e→ §fNo spamming",
                "§e→ §fNo harassing/bullying others",
                "§e→ §fNo derogatory language",
                "§e→ §fNo sharing personal information",
                "§e→ §fNo advertising or promoting",
                "§e→ §fNo threats or encouragement of self harm",
                "§e→ §fReport rule-breakers to staff in",
                "§four Discord (§ediscord.gg/bosshub§f)"
        ));

        if(hasBack) rulesInv.setItem(18, createGuiItem(Material.BARRIER, ChatColor.of("#ff0000")+"§lBACK", "§fClick to go back"));
    }

    public void initializeLeaderboardItems(UUID uuid) {

        inv = Bukkit.createInventory(null, 27, leaderboardInvName);

        inv.clear();

        inv.setItem(10, createGuiItem(
                Material.DIAMOND_SWORD,
                ChatColor.of("#ff0000")+""+ChatColor.BOLD+"PLAYER KILLS",
                "§fClick to open"
        ));

        inv.setItem(11, createGuiItem(
                Material.SKELETON_SKULL,
                ChatColor.GOLD+""+ChatColor.BOLD+"DEATHS",
                "§fClick to open"
        ));

        inv.setItem(12, createGuiItem(
                Material.ROTTEN_FLESH,
                ChatColor.DARK_GREEN+""+ChatColor.BOLD+"MOB KILLS",
                "§fClick to open"
        ));

        inv.setItem(13, createGuiItem(
                Material.CLOCK,
                ChatColor.YELLOW+""+ChatColor.BOLD+"PLAYTIME",
                "§fClick to open"
        ));

        inv.setItem(14, createGuiItem(
                Material.GOLD_INGOT,
                ChatColor.GREEN+""+ChatColor.BOLD+"MONEY",
                "§fClick to open"
        ));

        inv.setItem(15, createGuiItem(
                Material.EMERALD,
                ChatColor.of("#b400ff")+""+ChatColor.BOLD+"GEMS",
                "§fClick to open"
        ));

        inv.setItem(16, createGuiItem(
                Material.DIAMOND_PICKAXE,
                ChatColor.of("#7CF1E6")+""+ChatColor.BOLD+"BLOCKS BROKEN",
                "§fClick to open"
        ));

    }

    public void initializeStatItems(UUID uuid) {

        statsInv = Bukkit.createInventory(null, 27, statsInvName);

        statsInv.clear();

        statsInv.setItem(10, createGuiItem(
                Material.DIAMOND_SWORD,
                ChatColor.of("#ff0000")+""+ChatColor.BOLD+"PLAYER KILLS",
                PlaceholderAPI.setPlaceholders(Bukkit.getPlayer(uuid),"§f%statistic_player_kills%")
        ));

        statsInv.setItem(11, createGuiItem(
                Material.SKELETON_SKULL,
                ChatColor.GOLD+""+ChatColor.BOLD+"DEATHS",
                PlaceholderAPI.setPlaceholders(Bukkit.getPlayer(uuid),"§f%statistic_deaths%")
        ));

        statsInv.setItem(12, createGuiItem(
                Material.ROTTEN_FLESH,
                ChatColor.DARK_GREEN+""+ChatColor.BOLD+"MOB KILLS",
                PlaceholderAPI.setPlaceholders(Bukkit.getPlayer(uuid),"§f%statistic_mob_kills%")
        ));

        statsInv.setItem(13, createGuiItem(
                Material.CLOCK,
                ChatColor.YELLOW+""+ChatColor.BOLD+"PLAYTIME",
                PlaceholderAPI.setPlaceholders(Bukkit.getPlayer(uuid),"§f%statistic_time_played%")
        ));

        statsInv.setItem(14, createGuiItem(
                Material.GOLD_INGOT,
                ChatColor.GREEN+""+ChatColor.BOLD+"MONEY",
                PlaceholderAPI.setPlaceholders(Bukkit.getPlayer(uuid),"§f%vault_eco_balance_formatted%")
        ));

        statsInv.setItem(15, createGuiItem(
                Material.EMERALD,
                ChatColor.of("#b400ff")+""+ChatColor.BOLD+"GEMS",
                PlaceholderAPI.setPlaceholders(Bukkit.getPlayer(uuid),"§f%gems_balance%")
        ));

        statsInv.setItem(16, createGuiItem(
                Material.DIAMOND_PICKAXE,
                ChatColor.of("#7CF1E6")+""+ChatColor.BOLD+"BLOCKS BROKEN",
                PlaceholderAPI.setPlaceholders(Bukkit.getPlayer(uuid),"§f%statistic_mine_block%")
        ));

    }

    public void initializeLeaderboard(UUID uuid, String placeholder, String type) {

        leaderboardInv = Bukkit.createInventory(null, 54, leaderboardInvName);

        leaderboardInv.clear();

        for(int i = 1; i < 46; i ++) {
            leaderboardInv.addItem(createSkull(
                    PlaceholderAPI.setPlaceholders(Bukkit.getPlayer(uuid),"%ajlb_lb_" + placeholder + "_" + i + "_alltime_name%"),
                    PlaceholderAPI.setPlaceholders(Bukkit.getPlayer(uuid),ChatColor.of("#ff0000")+"§l%ajlb_lb_" + placeholder + "_" + i + "_alltime_name%"),
                    PlaceholderAPI.setPlaceholders(Bukkit.getPlayer(uuid),"§f%ajlb_lb_" + placeholder + "_" + i + "_alltime_value% " + type)
            ));
        }

        leaderboardInv.setItem(45, createGuiItem(Material.BARRIER, ChatColor.of("#ff0000")+"§lBACK", "§fClick to go back"));

    }

    protected ItemStack createSkull(final String ownerName, final String name, final String... lore) {
            final ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);

            // Set the skull meta
            SkullMeta meta = (SkullMeta) item.getItemMeta();
            meta.setDisplayName(name);
            meta.setOwner(ownerName);
            meta.setLore(Arrays.asList(lore));

            item.setItemMeta(meta);

            return item;
        }

    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
            final ItemStack item = new ItemStack(material, 1);
            final ItemMeta meta = item.getItemMeta();

            // Set the name of the item
            meta.setDisplayName(name);

            // Set the lore of the item
            meta.setLore(Arrays.asList(lore));
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            item.setItemMeta(meta);

            return item;
        }

    public void openInventory(final HumanEntity e, Inventory type) {
        e.openInventory(type);
    }

    // Check for clicks on items
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if(!e.getView().getTitle().equals(helpInvName) &&
                !e.getView().getTitle().equals(statsInvName) &&
                !e.getView().getTitle().equals(leaderboardInvName) &&
                !e.getView().getTitle().equals(rulesInvName)) { return; }

        e.setCancelled(true);

        if (e.getClick() == ClickType.SWAP_OFFHAND) return;

        final Player player = (Player) e.getWhoClicked();

        if (e.getClickedInventory() instanceof PlayerInventory) return;

        UUID uuid = e.getWhoClicked().getUniqueId();
        String clickedName = e.getCurrentItem().getItemMeta().getDisplayName();

        if(e.getView().getTitle().equals(helpInvName) || e.getView().getTitle().equals(rulesInvName)) {
            if(clickedName.equals("§x§F§F§0§0§0§0§lRULES")) {
                initializeRulesItems(true);
                openInventory(player, rulesInv);
            } else if(clickedName.equals("§x§F§F§0§0§0§0§lBACK")) {
                initializeHelpItems();
                openInventory(player, inv);
            }
        } else if(e.getView().getTitle().equals(leaderboardInvName)) {
            if(clickedName.equals("§x§F§F§0§0§0§0§lBACK")) {
                initializeLeaderboardItems(uuid);
                openInventory(player, inv);
            } else if (clickedName.equals("§x§F§F§0§0§0§0§lPLAYER KILLS")) {
                initializeLeaderboard(uuid, "statistic_player_kills", "kills");
                openInventory(player, leaderboardInv);
            } else if (clickedName.equals("§e§lPLAYTIME")) {
                initializeLeaderboard(uuid, "statistic_time_played", "");
                openInventory(player, leaderboardInv);
            } else if (clickedName.equals("§6§lDEATHS")) {
                initializeLeaderboard(uuid, "statistic_deaths", "deaths");
                openInventory(player, leaderboardInv);
            } else if (clickedName.equals("§2§lMOB KILLS")) {
                initializeLeaderboard(uuid, "statistic_mob_kills", "kills");
                openInventory(player, leaderboardInv);
            } else if (clickedName.equals("§a§lMONEY")) {
                initializeLeaderboard(uuid, "vault_eco_balance", "");
                openInventory(player, leaderboardInv);
            } else if (clickedName.equals("§x§B§4§0§0§F§F§lGEMS")) {
                initializeLeaderboard(uuid, "gems_balance", "Gems");
                openInventory(player, leaderboardInv);
            } else if (clickedName.equals("§x§7§C§F§1§E§6§lBLOCKS BROKEN")) {
                initializeLeaderboard(uuid, "statistic_mine_block", "blocks");
                openInventory(player, leaderboardInv);
            }
        }
    }

    // Cancel dragging in our inventory
    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (e.getInventory().equals(inv)) {
            e.setCancelled(true);
        }
    }

}
