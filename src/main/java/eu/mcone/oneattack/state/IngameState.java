package eu.mcone.oneattack.state;

import eu.mcone.coresystem.api.bukkit.util.Messenger;
import eu.mcone.gameapi.api.event.gamestate.GameStateStartEvent;
import eu.mcone.gameapi.api.event.gamestate.GameStateStopEvent;
import eu.mcone.gameapi.api.gamestate.common.InGameState;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.gameapi.api.team.Team;
import eu.mcone.oneattack.OneAttack;
import eu.mcone.oneattack.gadgets.Items;
import eu.mcone.oneattack.inventorys.AttackKitInventory;
import eu.mcone.oneattack.inventorys.AttackSpawnLocationInventory;
import eu.mcone.oneattack.inventorys.DefendKitInventory;
import eu.mcone.oneattack.inventorys.DefendSpawnLocationInventory;
import eu.mcone.oneattack.kit.DefenderRole;
import eu.mcone.oneattack.kit.Role;
import eu.mcone.oneattack.listener.InventoryTriggerListener;
import eu.mcone.oneattack.objectives.InGameObjective;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.swing.*;
import java.awt.*;

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


                                            System.out.println("§aTeleport to vote spawnlocations..");
                                            if (gameplayers.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getAttackTeam().getName())) {
                                                //ATTACK
                                                if (AttackSpawnLocationInventory.gerage > AttackSpawnLocationInventory.main_entrance) {
                                                    gameplayers.bukkit().teleport(OneAttack.getInstance().getGameWorld().getLocation("attacker.spawn.1"));
                                                    System.out.println("§aTeleport attacker to attacker.spawn.1");
                                                } else if (AttackSpawnLocationInventory.gerage < AttackSpawnLocationInventory.main_entrance) {
                                                    gameplayers.bukkit().teleport(OneAttack.getInstance().getGameWorld().getLocation("attacker.spawn.2"));
                                                    System.out.println("§aTeleport attacker to attacker.spawn.2");
                                                } else {
                                                    System.out.println("§aTeleport attacker to attacker.spawn.1 because he dont choose");
                                                    gameplayers.bukkit().teleport(OneAttack.getInstance().getGameWorld().getLocation("attacker.spawn.1"));
                                                }
                                            } else if (gameplayers.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getDefenderTeam().getName())) {
                                                //DEFEND
                                                if (DefendSpawnLocationInventory.gerage > 0 && DefendSpawnLocationInventory.kitchen > 0 && DefendSpawnLocationInventory.thirdfloor > 0) {
                                                    if (DefendSpawnLocationInventory.gerage < DefendSpawnLocationInventory.kitchen && DefendSpawnLocationInventory.gerage < DefendSpawnLocationInventory.thirdfloor) {
                                                        System.out.println("§aTeleport defender to defender.spawn.gerage");
                                                        gameplayers.bukkit().teleport(OneAttack.getInstance().getGameWorld().getLocation("defender.spawn.gerage"));
                                                    } else if (DefendSpawnLocationInventory.kitchen < DefendSpawnLocationInventory.thirdfloor) {
                                                        System.out.println("§aTeleport defender to defender.spawn.kitchen");
                                                        gameplayers.bukkit().teleport(OneAttack.getInstance().getGameWorld().getLocation("defender.spawn.kitchen"));
                                                    } else {
                                                        System.out.println("§aTeleport defender to defender.spawn.thirdfloor");
                                                        gameplayers.bukkit().teleport(OneAttack.getInstance().getGameWorld().getLocation("defender.spawn.thirdfloor"));
                                                    }
                                                } else {
                                                    System.out.println("§aTeleport defender to defender.spawn.gerage");
                                                    gameplayers.bukkit().teleport(OneAttack.getInstance().getGameWorld().getLocation("defender.spawn.gerage"));
                                                }
                                            }


                                            gameplayers.bukkit().closeInventory();

                                            if (gameplayers.getCurrentKit().equals(Role.DEFAULT_ATTACKER) || gameplayers.getCurrentKit().equals(Role.DEFAULT_DEFENDS)) {
                                                if (gameplayers.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getAttackTeam().getName())) {
                                                    gameplayers.setKit(Role.DEFAULT_ATTACKER);
                                                } else if (gameplayers.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getDefenderTeam().getName())) {
                                                    gameplayers.setKit(Role.DEFAULT_DEFENDS);
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
