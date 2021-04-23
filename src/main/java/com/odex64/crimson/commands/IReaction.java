package com.odex64.crimson.commands;

import net.dv8tion.jda.api.events.message.guild.react.GenericGuildMessageReactionEvent;

public interface IReaction
{
    void handleReaction(GenericGuildMessageReactionEvent event);

    String getMessageId();
}
