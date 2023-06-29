package com.github.gpaddons.gppublicraids;

// Java Imports
import org.jetbrains.annotations.NotNull;
import java.util.Objects;

// GP Imports
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.ClaimPermission;
import me.ryanhamshire.GriefPrevention.Messages;
import me.ryanhamshire.GriefPrevention.PlayerData;
import me.ryanhamshire.GriefPrevention.TextMode;
import me.ryanhamshire.GriefPrevention.DataStore;

// Bukkit config imports
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

// Bukkit event imports
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;
import org.bukkit.event.raid.RaidTriggerEvent;

// Bukkit command imports
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class GPPublicRaids extends JavaPlugin {

    private GriefPrevention instance;

    @Override
    public void onEnable() {
        instance = GriefPrevention.instance;
        getLogger().info("Plugin carregado");
        if (instance != null) {
            getLogger().info("Acesso a instancia");
        } else {
            getLogger().info("Sem acesso a instancia");
        }
        if (instance.dataStore != null) {
            getLogger().info("Acesso a DataStore");
        } else {
            getLogger().info("Sem acesso a DataStore");
        }
    }

    public void onPlayerTriggerRaid(RaidTriggerEvent event) {
        getLogger().info("Um player tentou ativar uma raid");
        event.setCancelled(true);
    }

    @Override
    public boolean onCommand(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            @NotNull String[] args) {

        Player player = null;
        if (sender instanceof Player) {
            player = (Player) sender;
        }

        if (command.getName().equalsIgnoreCase("claimallowpublicraids") && player != null) {
            getLogger().info("Commando Rodou");
            Claim claim = instance.dataStore.getClaimAt(player.getLocation(), true, null);
            if (claim.hasExplicitPermission(player, ClaimPermission.Build)) {
                getLogger().info("É o dono da claim");
            } else {
                getLogger().info("Não é o dono da claim");
            }
            // determine which claim the player is standing in
            /* Claim claim = this.dataStore.getClaimAt(player.getLocation(), true, null);

            if (claim == null) {
                GriefPrevention.sendMessage(player, TextMode.Err, Messages.DeleteClaimMissing);
            } else {
                Supplier<String> noBuildReason = claim.checkPermission(player, ClaimPermission.Build, null);
                if (noBuildReason != null) {
                    GriefPrevention.sendMessage(player, TextMode.Err, noBuildReason.get());
                    return true;
                }

                if (claim.arePublicRaidsAllowed) {
                    claim.arePublicRaidsAllowed = false;
                    GriefPrevention.sendMessage(player, TextMode.Success, Messages.RaidDisabled);
                } else {
                    claim.arePublicRaidsAllowed = true;
                    GriefPrevention.sendMessage(player, TextMode.Success, Messages.RaidEnabled);
                }
            }

            return true; */
            return true;
        }
        return false;
    }

    @Override
    public void onDisable() {
    }

}
