package eu.mcone.oneattack.state;

import eu.mcone.gameapi.api.event.gamestate.GameStateStartEvent;
import eu.mcone.gameapi.api.event.gamestate.GameStateStopEvent;
import eu.mcone.gameapi.api.gamestate.common.InGameState;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.oneattack.OneAttack;
import eu.mcone.oneattack.gadgets.Items;
import eu.mcone.oneattack.handler.VoteHandler;
import eu.mcone.oneattack.inventorys.AttackKitInventory;
import eu.mcone.oneattack.inventorys.DefendKitInventory;
import eu.mcone.oneattack.kit.Role;
import eu.mcone.oneattack.objectives.InGameObjective;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class IngameState extends InGameState {

    static {
        setObjective(InGameObjective.class);
    }

    public IngameState() {
        super(60 * 6);
    }

    @Override
    public void onStart(GameStateStartEvent event) {
        VoteHandler voteHandler = OneAttack.getInstance().getVoteHandler();

        for (Player all : Bukkit.getOnlinePlayers()) {
            GamePlayer gameplayers = OneAttack.getInstance().getGamePlayer(all);
            voteHandler.openKitInv(gameplayers);
            all.playSound(all.getLocation(), Sound.CHEST_OPEN, 1, 1);
            Bukkit.getScheduler().runTaskLater(OneAttack.getInstance(), () -> {
                voteHandler.openKitInv(gameplayers);
                Bukkit.getScheduler().runTaskLater(OneAttack.getInstance(), () -> {
                    voteHandler.openKitInv(gameplayers);
                    Bukkit.getScheduler().runTaskLater(OneAttack.getInstance(), () -> {
                        voteHandler.openKitInv(gameplayers);
                        Bukkit.getScheduler().runTaskLater(OneAttack.getInstance(), () -> {
                            voteHandler.openSpawnInv(gameplayers);
                            Bukkit.getScheduler().runTaskLater(OneAttack.getInstance(), () -> {
                                voteHandler.openSpawnInv(gameplayers);
                                Bukkit.getScheduler().runTaskLater(OneAttack.getInstance(), () -> {
                                    OneAttack.getInstance().getMessenger().send(gameplayers.bukkit(), "§fDie Vorbereitsphase endet in 3 Sekunden");
                                    voteHandler.openSpawnInv(gameplayers);
                                    Bukkit.getScheduler().runTaskLater(OneAttack.getInstance(), () -> {
                                        OneAttack.getInstance().getMessenger().send(gameplayers.bukkit(), "§fDie Vorbereitsphase endet in 2 Sekunden");
                                        voteHandler.openSpawnInv(gameplayers);
                                        Bukkit.getScheduler().runTaskLater(OneAttack.getInstance(), () -> {
                                            OneAttack.getInstance().getMessenger().send(gameplayers.bukkit(), "§fDie Vorbereitsphase endet in einer Sekunden");

                                            voteHandler.teleportPlayerToLocation(gameplayers);
                                            gameplayers.bukkit().closeInventory();

                                            /*  REMOVE USER FROM THE LIST BECAUSE HE GO INSIDE || FOR NO DEATH IN THE WAITING HALL */
                                            OneAttack.getInstance().getGadgetHandler().getIsPreparing().clear();

                                            /*  ADD DEFAULT KITS TO PLAYER THAT HAVE NO ONE PICK */
                                            if (gameplayers.getCurrentKit() != null) {
                                                if (gameplayers.getCurrentKit().equals(Role.DEFAULT_ATTACKER) || gameplayers.getCurrentKit().equals(Role.DEFAULT_DEFENDS)) {
                                                    if (gameplayers.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getAttackTeam().getName())) {
                                                        gameplayers.setKit(Role.DEFAULT_ATTACKER);
                                                    } else if (gameplayers.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getDefenderTeam().getName())) {
                                                        gameplayers.setKit(Role.DEFAULT_DEFENDS);
                                                    }
                                                }
                                            }

                                            /*  ADD ARMOR */
                                            gameplayers.bukkit().getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS));
                                            gameplayers.bukkit().getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
                                            gameplayers.bukkit().getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));


                                            if (gameplayers.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getAttackTeam().getName())) {
                                                OneAttack.getInstance().getMessenger().send(gameplayers.bukkit(), "§cTöte alle Verteidiger oder entschärfe die Bombe");

                                                /*  ADD DEFUSER */
                                                if (OneAttack.getInstance().getGadgetHandler().getHasDefuser().isEmpty()) {
                                                    gameplayers.bukkit().getInventory().addItem(Items.DEFUSER.getItem());
                                                    OneAttack.getInstance().getGadgetHandler().getHasDefuser().add(gameplayers.bukkit());
                                                    OneAttack.getInstance().getMessenger().send(gameplayers.bukkit(), "§aDu hast den Entschärfer aufgenommen!");
                                                }

                                            } else if (gameplayers.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getDefenderTeam().getName())) {
                                                OneAttack.getInstance().getMessenger().send(gameplayers.bukkit(), "§cTöte alle Angreifer oder beschütze die Bombe");
                                            }
                                        }, 20L);
                                    }, 20L);
                                }, 20L);
                            }, 40L);
                        }, 20L);
                    }, 20L);
                }, 20L);
            }, 50L);
        }

        super.onStart(event);
    }

    /*
     * Gerage -> 1 defender.spawn.gerage
     * kitchen -> 2 defender.spawn.kitchen
     * thirdfloor -> 3 defender.spawn.thirdfloor
     */


    @Override
    public void onStop(GameStateStopEvent event) {
    }
}
