package com.dominion.castlesiege;

import cloud.commandframework.CommandTree;
import cloud.commandframework.bukkit.BukkitCommandManager;
import cloud.commandframework.execution.CommandExecutionCoordinator;
import cloud.commandframework.paper.PaperCommandManager;
import com.dominion.castlesiege.commands.Commands;
import com.dominion.castlesiege.text.Messages;
import com.dominion.castlesiege.text.TextColors;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Function;

public final class CastleSiege extends JavaPlugin {

    private BukkitCommandManager<CommandSender> manager;

    @Override
    public void onEnable() {
        // Detect and Setup Hex Support
        TextColors.setup(this);
        // Setup Main Config File
        setupConfig();
        // Setup Messages
        Messages.setup();
        // Setup Commands
        setupCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void setupCommands() {
        final Function<CommandTree<CommandSender>, CommandExecutionCoordinator<CommandSender>> executionCoordinatorFunction =
                CommandExecutionCoordinator.simpleCoordinator();
        final Function<CommandSender, CommandSender> mapperFunction = Function.identity();
        try {
            this.manager = new PaperCommandManager<>(
                    this,
                    executionCoordinatorFunction,
                    mapperFunction,
                    mapperFunction
            );
        } catch (final Exception e) {
            this.getLogger().severe("Failed to initialize the command manager.");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        new Commands(manager).initCommands();
    }

    private void setupConfig() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        MainConfig.config = getConfig();
    }

    public static class MainConfig {

        private static FileConfiguration config;

        public static String getPrefix() {
            return TextColors.translate(config.getString("prefix"));
        }

    }
}
