package com.odex64.crimson.utils;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

public class AudioTracksListener extends AudioEventAdapter
{
    private AudioTracksManager manager;

    public AudioTracksListener(AudioTracksManager manager)
    {
        this.manager = manager;
    }

    @Override
    public void onPlayerPause(AudioPlayer player)
    {

    }

    @Override
    public void onPlayerResume(AudioPlayer player)
    {

    }

    @Override
    public void onTrackStart(AudioPlayer player, AudioTrack track)
    {

    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason)
    {
        Config.LOGGER.info("stop");
        Config.LOGGER.info(String.valueOf(manager.queueSize()));
        manager.removeQueue(track);

        if (manager.queueSize() > 0)
        {
            manager.clearSkips();
            player.playTrack(manager.getQueue(0));
        } else
            manager.getAudioManager().closeAudioConnection();
    }

    @Override
    public void onTrackException(AudioPlayer player, AudioTrack track, FriendlyException exception)
    {
        // An already playing track threw an exception (track end event will still be received separately)
    }

    @Override
    public void onTrackStuck(AudioPlayer player, AudioTrack track, long thresholdMs)
    {
        // Audio track has been unable to provide us any audio, might want to just start a new track
    }
}
