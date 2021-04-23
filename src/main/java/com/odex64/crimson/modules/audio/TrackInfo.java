package com.odex64.crimson.modules.audio;

import com.odex64.crimson.commands.CommandContext;
import com.odex64.crimson.commands.ICommand;
import com.odex64.crimson.commands.IReaction;
import com.odex64.crimson.utils.AudioTracksManager;
import com.odex64.crimson.utils.Config;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.VoiceChannel;

import java.util.Arrays;
import java.util.List;

public class TrackInfo implements ICommand
{
    private AudioTracksManager manager;

    public TrackInfo(AudioTracksManager manager)
    {
        this.manager = manager;
    }

    @Override
    public void handle(CommandContext event)
    {
        VoiceChannel channel = event.getMember().getVoiceState().getChannel();
        if (channel == null || !channel.getMembers().stream().anyMatch(i -> i.getId().equals(Config.botId)))
            return;

        String names = "";
        if (!manager.queueIsEmpty())
        {
            for (int i = 0; i < manager.queueSize(); i++)
                names += (i + 1) + ". " + manager.getTrackName(i) + "\n";
            event.getChannel().sendMessage(names).queue();
        }
    }

    @Override
    public List<String> getName()
    {
        return Arrays.asList("queue", "list");
    }

    @Override
    public List<IReaction> getReactions()
    {
        return null;
    }

    @Override
    public boolean isActive()
    {
        return true;
    }

    @Override
    public List<Permission> getPermissions()
    {
        // TODO Auto-generated method stub
        return null;
    }
}
