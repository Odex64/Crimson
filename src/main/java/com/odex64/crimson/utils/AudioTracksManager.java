package com.odex64.crimson.utils;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.ArrayList;
import java.util.List;

public class AudioTracksManager
{
    private List<AudioTrack> queue = new ArrayList<>();
    private List<Member> skips = new ArrayList<>();
    private AudioManager audioManager;

    public AudioManager getAudioManager()
    {
        return audioManager;
    }

    public void setAudioManager(AudioManager audioManager)
    {
        this.audioManager = audioManager;
    }

    public void addSkip(Member member)
    {
        skips.add(member);
    }

    public void clearSkips()
    {
        skips.clear();
    }

    public void skipQueue()
    {
        if (queue.size() > 0)
            queue.remove(0);
    }

    public int skipsCount()
    {
        return skips.size();
    }

    public boolean findSkip(Member member)
    {
        return skips.contains(member) ? true : false;
    }

    public boolean findQueue(AudioTrack track)
    {
        return (queue.contains(track) ? true : false);
    }

    public void clearQueue()
    {
        queue.clear();
    }

    public int queueSize()
    {
        return queue.size();
    }

    public void addQueue(AudioTrack track)
    {
        queue.add(track);
    }

    public void removeQueue(AudioTrack track)
    {
        queue.remove(track);
    }

    public void removeQueue(int index)
    {
        if (index < queue.size() && index >= 0)
            queue.remove(index);
    }

    public String getTrackName(int index)
    {
        return queue.get(index).getInfo().title;
    }

    public boolean queueIsEmpty()
    {
        return queue.isEmpty();
    }

    public AudioTrack getQueue(int index)
    {
        if (index < queue.size() && index >= 0)
            return queue.get(index);
        return null;
    }
}
