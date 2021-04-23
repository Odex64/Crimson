package com.odex64.crimson.modules;

import com.odex64.crimson.commands.ICommand;
import com.odex64.crimson.modules.moderation.Warn;

import java.util.ArrayList;
import java.util.List;

public class Moderation implements IModule
{
    private List<ICommand> commands = new ArrayList<>();

    public Moderation()
    {
        commands.add(new Warn());
    }

    @Override
    public String getName()
    {
        return "Moderation";
    }

    @Override
    public boolean isActive()
    {
        return true;
    }

    @Override
    public List<ICommand> getCommands()
    {
        return commands;
    }
}
