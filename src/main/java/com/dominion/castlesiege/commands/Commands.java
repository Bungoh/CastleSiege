package com.dominion.castlesiege.commands;

import cloud.commandframework.bukkit.BukkitCommandManager;
import org.bukkit.command.CommandSender;

public class Commands {

    private final BukkitCommandManager<CommandSender> manager;

    public Commands(BukkitCommandManager<CommandSender> manager) {
        this.manager = manager;
    }

    public void initCommands() {
        //TODO add command initializiation here
    }

}
