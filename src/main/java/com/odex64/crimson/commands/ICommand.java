package com.odex64.crimson.commands;

import net.dv8tion.jda.api.Permission;

import java.util.List;

public interface ICommand
{
    void handle(CommandContext event);

    List<String> getName(); // commands alias

    List<IReaction> getReactions(); // messages' id for reaction commands

    List<Permission> getPermissions();

    boolean isActive();
}
