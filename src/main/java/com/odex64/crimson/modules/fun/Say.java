package com.odex64.crimson.modules.fun;

import com.odex64.crimson.commands.CommandContext;
import com.odex64.crimson.commands.ICommand;
import com.odex64.crimson.commands.IReaction;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.Arrays;
import java.util.List;

public class Say implements ICommand
{
    @Override
    public void handle(CommandContext event)
    {
        String message = String.join(" ", event.getArgs());
        if (!message.trim().isEmpty())
        {
            TextChannel channel = event.getMessage().getTextChannel();
            event.getMessage().delete().queue(i -> channel.sendMessage(message).queue());
        }
    }

    @Override
    public List<String> getName()
    {
        return Arrays.asList("say");
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
