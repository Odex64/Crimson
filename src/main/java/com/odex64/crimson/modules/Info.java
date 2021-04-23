package com.odex64.crimson.modules;

import com.odex64.crimson.commands.ICommand;
import com.odex64.crimson.modules.info.Dictionary;
import com.odex64.crimson.modules.info.Ping;
import com.odex64.crimson.modules.info.User;

import java.util.ArrayList;
import java.util.List;

public class Info implements IModule
{
    private List<ICommand> commands = new ArrayList<>();

    public Info()
    {
        commands.add(new Ping());
        commands.add(new User());
        commands.add(new Dictionary());
    }

    @Override
    public String getName()
    {
        return "Info";
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
