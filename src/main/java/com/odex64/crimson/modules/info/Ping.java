package com.odex64.crimson.modules.info;

import com.odex64.crimson.commands.CommandContext;
import com.odex64.crimson.commands.ICommand;
import com.odex64.crimson.commands.IReaction;
import net.dv8tion.jda.api.Permission;

import java.util.Arrays;
import java.util.List;

public class Ping implements ICommand
{
    @Override
    public void handle(CommandContext event)
    {
        if (event.getArgs().size() == 0)
        {
            event.getEvent().getJDA().getRestPing().queue((time) -> event.getChannel().sendMessageFormat("Ping: %d ms", time).queue());
        }
    }

    @Override
    public List<String> getName()
    {
        return Arrays.asList("ping", "ms");
    }

    @Override
    public boolean isActive()
    {
        return true;
    }

    @Override
    public List<IReaction> getReactions()
    {
        return null;
    }

    @Override
    public List<Permission> getPermissions()
    {
        // TODO Auto-generated method stub
        return null;
    }
}
