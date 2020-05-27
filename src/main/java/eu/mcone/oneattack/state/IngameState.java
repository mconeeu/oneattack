package eu.mcone.oneattack.state;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.gameapi.api.event.gamestate.GameStateStartEvent;
import eu.mcone.gameapi.api.event.gamestate.GameStateStopEvent;
import eu.mcone.gameapi.api.gamestate.common.InGameState;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.oneattack.OneAttack;
import eu.mcone.oneattack.gadgets.Items;
import eu.mcone.oneattack.inventorys.AttackKitInventory;
import eu.mcone.oneattack.inventorys.AttackSpawnLocationInventory;
import eu.mcone.oneattack.inventorys.DefendKitInventory;
import eu.mcone.oneattack.inventorys.DefendSpawnLocationInventory;
import eu.mcone.oneattack.kit.Role;
import eu.mcone.oneattack.listener.PlayerMoveListener;
import eu.mcone.oneattack.objectives.InGameObjective;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.DisplaySlot;

public class IngameState extends InGameState {

    static {
        setObjective(InGameObjective.class);
    }

    public IngameState() {
        super(60 * 45);
    }

    @Override
    public void onStart(GameStateStartEvent event) {
        for (Player all : Bukkit.getOnlinePlayers()) {
            GamePlayer gameplayers = OneAttack.getInstance().getGamePlayer(all);
            openKitInv(gameplayers);
            all.playSound(all.getLocation(), Sound.CHEST_OPEN, 1, 1);
            Bukkit.getScheduler().runTaskLater(OneAttack.getInstance(), () -> {
                openKitInv(gameplayers);
                Bukkit.getScheduler().runTaskLater(OneAttack.getInstance(), () -> {
                    openKitInv(gameplayers);
                    Bukkit.getScheduler().runTaskLater(OneAttack.getInstance(), () -> {
                        openKitInv(gameplayers);
                        Bukkit.getScheduler().runTaskLater(OneAttack.getInstance(), () -> {
                            openSpawnInv(gameplayers);
                            Bukkit.getScheduler().runTaskLater(OneAttack.getInstance(), () -> {
                                openSpawnInv(gameplayers);
                                Bukkit.getScheduler().runTaskLater(OneAttack.getInstance(), () -> {
                                    OneAttack.getInstance().getMessenger().send(gameplayers.bukkit(), "§fDie Vorbereitsphase endet in 3 Sekunden");
                                    openSpawnInv(gameplayers);
                                    Bukkit.getScheduler().runTaskLater(OneAttack.getInstance(), () -> {
                                        OneAttack.getInstance().getMessenger().send(gameplayers.bukkit(), "§fDie Vorbereitsphase endet in 2 Sekunden");
                                        openSpawnInv(gameplayers);
                                        Bukkit.getScheduler().runTaskLater(OneAttack.getInstance(), () -> {
                                            OneAttack.getInstance().getMessenger().send(gameplayers.bukkit(), "§fDie Vorbereitsphase endet in einer Sekunden");


                                            teleportPlayerToLocation(gameplayers);
                                            gameplayers.bukkit().closeInventory();
                                            PlayerMoveListener.isPreparing.clear();

                                            if (gameplayers.getCurrentKit() != null) {
                                                if (gameplayers.getCurrentKit().equals(Role.DEFAULT_ATTACKER) || gameplayers.getCurrentKit().equals(Role.DEFAULT_DEFENDS)) {
                                                    if (gameplayers.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getAttackTeam().getName())) {
                                                        gameplayers.setKit(Role.DEFAULT_ATTACKER);
                                                    } else if (gameplayers.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getDefenderTeam().getName())) {
                                                        gameplayers.setKit(Role.DEFAULT_DEFENDS);
                                                    }
                                                }
                                            }


                                            gameplayers.bukkit().getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS));
                                            gameplayers.bukkit().getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
                                            gameplayers.bukkit().getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));


                                            if (gameplayers.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getAttackTeam().getName())) {
                                                OneAttack.getInstance().getMessenger().send(gameplayers.bukkit(), "§cTöte alle Verteidiger oder entschärfe die Bombe");
                                                if (Items.hasDefuser.isEmpty()) {
                                                    gameplayers.bukkit().getInventory().addItem(Items.DEFUSER.getItem());
                                                    Items.hasDefuser.add(gameplayers.bukkit());
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

    private String mathDefenderSpawnLocation() {
        if (DefendSpawnLocationInventory.gerage < DefendSpawnLocationInventory.kitchen && DefendSpawnLocationInventory.gerage < DefendSpawnLocationInventory.thirdfloor) {
            System.out.println("§aTeleport defender to defender.spawn.gerage");
            return "defender.spawn.gerage";
        } else if (DefendSpawnLocationInventory.kitchen > DefendSpawnLocationInventory.thirdfloor) {
            System.out.println("§aTeleport defender to defender.spawn.kitchen");
            return "defender.spawn.kitchen";
        } else if (DefendSpawnLocationInventory.kitchen < DefendSpawnLocationInventory.thirdfloor) {
            System.out.println("§aTeleport defender to defender.spawn.thirdfloor");
            return "defender.spawn.thirdfloor";
        } else {
            System.out.println("§aTeleport defender to defender.spawn.gerage because nobody he not choosed!");
            return "defender.spawn.gerage";
        }
    }

    private String mathAttackerSpawnLocation() {
        if (AttackSpawnLocationInventory.gerage > AttackSpawnLocationInventory.main_entrance) {
            if (mathDefenderSpawnLocation().equals("defender.spawn.gerage")) {
                return "attacker.spawn.bomb1.1";
            } else if (mathDefenderSpawnLocation().equals("defender.spawn.kitchen")) {
                return "attacker.spawn.bomb2.1";
            } else {
                return "attacker.spawn.bomb3.1";
            }
        } else if (AttackSpawnLocationInventory.gerage < AttackSpawnLocationInventory.main_entrance) {
            if (mathDefenderSpawnLocation().equals("defender.spawn.gerage")) {
                return "attacker.spawn.bomb1.2";
            } else if (mathDefenderSpawnLocation().equals("defender.spawn.kitchen")) {
                return "attacker.spawn.bomb2.2";
            } else {
                return "attacker.spawn.bomb3.2";
            }
        } else {
            if (mathDefenderSpawnLocation().equals("defender.spawn.gerage")) {
                return "attacker.spawn.bomb1.1";
            } else if (mathDefenderSpawnLocation().equals("defender.spawn.kitchen")) {
                return "attacker.spawn.bomb2.1";
            } else {
                return "attacker.spawn.bomb3.1";
            }
        }
    }

    private void teleportPlayerToLocation(GamePlayer gamePlayer) {
        System.out.println("§aTeleport to vote spawnlocations..");
        if (gamePlayer.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getAttackTeam().getName())) {
            gamePlayer.bukkit().teleport(OneAttack.getInstance().getGameWorld().getLocation(mathAttackerSpawnLocation()));
        } else if (gamePlayer.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getDefenderTeam().getName())) {
            gamePlayer.bukkit().teleport(OneAttack.getInstance().getGameWorld().getLocation(mathDefenderSpawnLocation()));
        }
    }

    private void openSpawnInv(GamePlayer gamePlayer) {
        if (gamePlayer.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getDefenderTeam().getName())) {
            new DefendSpawnLocationInventory(gamePlayer.bukkit());
        } else if (gamePlayer.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getAttackTeam().getName())) {
            new AttackSpawnLocationInventory(gamePlayer.bukkit());
        }
    }

    private void openKitInv(GamePlayer gamePlayer) {
        if (gamePlayer.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getDefenderTeam().getName())) {
            new DefendKitInventory(gamePlayer.bukkit());
        } else if (gamePlayer.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getAttackTeam().getName())) {
            new AttackKitInventory(gamePlayer.bukkit());
        }
    }

    @Override
    public void onStop(GameStateStopEvent event) {
    }
}
