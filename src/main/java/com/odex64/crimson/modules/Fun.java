package com.odex64.crimson.modules;

import com.odex64.crimson.commands.ICommand;
import com.odex64.crimson.modules.fun.GayRate;
import com.odex64.crimson.modules.fun.Say;

import java.util.ArrayList;
import java.util.List;

public class Fun implements IModule
{
    private List<ICommand> commands = new ArrayList<>();

    public Fun()
    {
        commands.add(new Say());
        commands.add(new GayRate());
    }

    @Override
    public String getName()
    {
        return "Fun";
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
