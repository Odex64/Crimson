package com.odex64.crimson.modules.audio;

import com.odex64.crimson.commands.CommandContext;
import com.odex64.crimson.commands.ICommand;
import com.odex64.crimson.commands.IReaction;
import com.odex64.crimson.utils.AudioTracksManager;
import com.odex64.crimson.utils.Config;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.VoiceChannel;

import java.util.Arrays;
import java.util.List;

public class Skip implements ICommand
{
    private AudioTracksManager manager;
    private AudioPlayer player;

    public Skip(AudioTracksManager manager, AudioPlayer player)
    {
        this.manager = manager;
        this.player = player;
    }

    @Override
    public void handle(CommandContext event)
    {
        VoiceChannel channel = event.getMember().getVoiceState().getChannel();
        if (channel == null || !channel.getMembers().stream().anyMatch(i -> i.getId().equals(Config.botId)))
            return;

        if (!manager.findSkip(event.getMember()))
            manager.addSkip(event.getMember());

        if (!manager.queueIsEmpty() && manager.skipsCount() >= channel.getMembers().size() / 2)
            player.stopTrack();
        else
            event.getChannel().sendMessage(((channel.getMembers().size() / 2) - manager.skipsCount()) + " skips remaining").queue();
    }

    @Override
    public List<String> getName()
    {
        return Arrays.asList("skip");
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
