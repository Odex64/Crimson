package com.odex64.crimson.commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;
import net.dv8tion.jda.api.events.message.guild.GenericGuildMessageEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class CommandContext
{
    private final GenericGuildEvent event;
    private final List<String> args;

    public CommandContext(GenericGuildEvent event, List<String> args)
    {
        this.event = event;
        this.args = args;
    }

    public Guild getGuild()
    {
        return getEvent().getGuild();
    }

    public GenericGuildEvent getEvent()
    {
        return event;
    }

    public List<String> getArgs()
    {
        return args;
    }

    public TextChannel getChannel()
    {
        return ((GenericGuildMessageEvent) event).getChannel();
    }

    public Member getMember()
    {
        return ((GuildMessageReceivedEvent) event).getMember();
    }

    public Message getMessage()
    {
        return ((GuildMessageReceivedEvent) event).getMessage();
    }

    public String getArg(int index)
    {
        if (index < args.size())
            return args.get(index);
        return null;
    }
}
