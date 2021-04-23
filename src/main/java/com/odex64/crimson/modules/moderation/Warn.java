package com.odex64.crimson.modules.moderation;

import com.odex64.crimson.commands.CommandContext;
import com.odex64.crimson.commands.ICommand;
import com.odex64.crimson.commands.IReaction;
import net.dv8tion.jda.api.Permission;

import java.util.Arrays;
import java.util.List;

public class Warn implements ICommand
{
    @Override
    public void handle(CommandContext event)
    {
        event.getChannel().sendMessage("warn test").queue();
    }

    @Override
    public List<String> getName()
    {
        return Arrays.asList("warn", "punish");
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
        return Arrays.asList(Permission.KICK_MEMBERS);
    }
}
