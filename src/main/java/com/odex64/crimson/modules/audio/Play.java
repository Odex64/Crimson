package com.odex64.crimson.modules.audio;

import com.odex64.crimson.commands.CommandContext;
import com.odex64.crimson.commands.ICommand;
import com.odex64.crimson.commands.IReaction;
import com.odex64.crimson.utils.AudioPlayerSendHandler;
import com.odex64.crimson.utils.AudioTracksListener;
import com.odex64.crimson.utils.AudioTracksManager;
import com.odex64.crimson.utils.Config;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.Arrays;
import java.util.List;

public class Play implements ICommand
{
    private AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
    private AudioPlayer player = playerManager.createPlayer();
    private AudioTracksManager manager;
    private AudioTracksListener listener;

    public Play(AudioTracksManager manager)
    {
        this.manager = manager;
    }

    public AudioPlayer getPlayer()
    {
        return player;
    }

    @Override
    public void handle(CommandContext event)
    {
        if (event.getArg(0).equals("stop"))
        {
            stop();
            return;
        }
        VoiceChannel channel = event.getMember().getVoiceState().getChannel();
        if (channel == null || (!channel.getMembers().stream().anyMatch(i -> i.getId().equals(Config.botId)) && manager.getAudioManager() != null))
            return;

        AudioSourceManagers.registerRemoteSources(playerManager);
        playerManager.loadItem(event.getArg(0), new AudioLoadResultHandler()
        {
            @Override
            public void trackLoaded(AudioTrack track)
            {
                if (manager.queueSize() == 0)
                {
                    join(event.getGuild().getAudioManager(), channel);
                    listener = new AudioTracksListener(manager);
                    player.addListener(listener);
                    player.playTrack(track);
                }
                manager.addQueue(track);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist)
            {

            }

            @Override
            public void noMatches()
            {
                event.getChannel().sendMessage("Can't find audio track!").queue();
            }

            @Override
            public void loadFailed(FriendlyException exception)
            {
                event.getChannel().sendMessage("Congrats! You just broke the bot.").queue();
            }
        });
    }

    public void join(AudioManager guildAudio, VoiceChannel channel)
    {
        manager.setAudioManager(guildAudio);
        manager.getAudioManager().setSendingHandler(new AudioPlayerSendHandler(player));
        manager.getAudioManager().openAudioConnection(channel);
    }

    public void stop()
    {
        manager.clearQueue();
        manager.clearSkips();
        player.removeListener(listener);
        player.stopTrack();
        manager.setAudioManager(null);
    }

    @Override
    public List<String> getName()
    {
        return Arrays.asList("play");
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
        return null;
    }
}
