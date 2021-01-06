package eu.mcone.oneattack;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.world.CoreWorld;
import eu.mcone.gameapi.api.GamePlugin;
import eu.mcone.gameapi.api.Option;
import eu.mcone.gameapi.api.backpack.defaults.DefaultItem;
import eu.mcone.gameapi.api.team.Team;
import eu.mcone.oneattack.commands.OneAttackCMD;
import eu.mcone.oneattack.handler.GadgetHandler;
import eu.mcone.oneattack.handler.VoteHandler;
import eu.mcone.oneattack.kit.Role;
import eu.mcone.oneattack.listener.*;
import eu.mcone.oneattack.state.EndState;
import eu.mcone.oneattack.state.IngameState;
import eu.mcone.oneattack.state.LobbyState;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import javax.swing.plaf.basic.BasicBorders;

public class OneAttack extends GamePlugin {

    public OneAttack() {
        super("OneAttack", ChatColor.AQUA, "oneattack.prefix",
                Option.BACKPACK_MANAGER_REGISTER_GADGET_CATEGORY,
                Option.BACKPACK_MANAGER_REGISTER_OUTFIT_CATEGORY,
                Option.BACKPACK_MANAGER_REGISTER_HAT_CATEGORY,
                Option.BACKPACK_MANAGER_REGISTER_TRAIL_CATEGORY,
                Option.BACKPACK_MANAGER_REGISTER_EXCLUSIVE_CATEGORY,
                Option.HOTBAR_SET_ITEMS,
                Option.TEAM_MANAGER_DISABLE_RESPAWN);
    }

    @Getter
    private Team attackTeam, defenderTeam;

    @Getter
    public static OneAttack instance;
    @Getter
    private CoreWorld gameWorld;
    @Getter
    private VoteHandler voteHandler;
    @Getter
    private GadgetHandler gadgetHandler;

    @Override
    public void onGameEnable() {
        instance = this;
        CoreSystem.getInstance().getTranslationManager().loadAdditionalCategories("oneattack");
        sendConsoleMessage("§aInitializing new GameState Handler...");
        getGameStateManager()
                .addGameState(new LobbyState())
                .addGameState(new IngameState())
                .addGameState(new EndState())
                .startGame();
        getPlayerManager();

        System.out.println("aInitializing VoteHandler & GadgetHandler");
        voteHandler = new VoteHandler();
        gadgetHandler = new GadgetHandler();

        attackTeam = getTeamManager().registerNewTeam("Angreifer", "§cAngreifer", 1, ChatColor.RED, new ItemBuilder(Material.IRON_SWORD).create());
        defenderTeam = getTeamManager().registerNewTeam("Verteidiger", "§9Verteidiger", 2, ChatColor.BLUE, new ItemBuilder(Material.IRON_HELMET).create());
        getTeamManager();
        System.out.println("teams loaded: " + getTeamManager().getTeams());
        getDamageLogger();

        registerEvents(
                new GeneralPlayerListener(),
                new PlayerJoinListener(),
                new BlockEvent(),
                new InventoryTriggerListener(),
                new GeneralPlayerListener(),
                new EntityDamageByEntityListener(),
                new PlayerDeathListener(),
                new GameEndListener(),
                new PlayerMoveListener()
        );

        registerCommands(
                new OneAttackCMD()
        );


        getKitManager().registerKits(Role.DEFAULT_DEFENDS, Role.DEFAULT_ATTACKER, Role.PUSHER, Role.TRAPPER, Role.BARRICADER, Role.SAVER);
        getBackpackManager();
        getBackpackManager().disableItem(DefaultItem.COINBOMB);

        gameWorld = CoreSystem.getInstance().getWorldManager().getWorld(getGameConfig().parseConfig().getGameWorld());

    }

    @Override
    public void onGameDisable() {

    }
}
