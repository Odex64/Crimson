package com.odex64.crimson.modules.info;

import com.odex64.crimson.commands.IReaction;
import net.dv8tion.jda.api.events.message.guild.react.GenericGuildMessageReactionEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;

import java.util.List;

public class DictionaryVote implements IReaction
{
    private String id;
    private List<String> definitions;
    private int index = 0;

    public DictionaryVote(String id, List<String> definitions)
    {
        this.id = id;
        this.definitions = definitions;
    }

    @Override
    public void handleReaction(GenericGuildMessageReactionEvent event)
    {
        if (!(event instanceof GuildMessageReactionAddEvent))
            return;

        if (event.getReactionEmote().getAsCodepoints().equalsIgnoreCase("U+2B05")) // left
        {
            if (index > 0)
                event.getChannel().editMessageById(id, definitions.get(--index)).queue();

        } else if (event.getReactionEmote().getAsCodepoints().equalsIgnoreCase("U+27A1")) // right
        {
            if (index < definitions.size() - 1)
                event.getChannel().editMessageById(id, definitions.get(++index)).queue();
        }
        event.getReaction().removeReaction(event.getUser()).queue();
    }

    @Override
    public String getMessageId()
    {
        return id;
    }
}
