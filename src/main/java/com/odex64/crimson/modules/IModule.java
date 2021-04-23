package com.odex64.crimson.modules;

import com.odex64.crimson.commands.ICommand;

import java.util.List;

public interface IModule
{
    String getName();

    boolean isActive();

    List<ICommand> getCommands();
}
