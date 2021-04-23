package com.odex64.crimson.modules;

import com.odex64.crimson.commands.ICommand;
import com.odex64.crimson.modules.audio.Play;
import com.odex64.crimson.modules.audio.Skip;
import com.odex64.crimson.modules.audio.TrackInfo;
import com.odex64.crimson.utils.AudioTracksManager;

import java.util.ArrayList;
import java.util.List;

public class Music implements IModule
{
    private List<ICommand> commands = new ArrayList<>();

    public Music()
    {
        AudioTracksManager manager = new AudioTracksManager();
        Play player = new Play(manager);
        Skip skip = new Skip(manager, player.getPlayer());
        commands.add(player);
        commands.add(skip);
        commands.add(new TrackInfo(manager));
    }

    @Override
    public String getName()
    {
        return "Music";
    }

    @Override
    public boolean isActive()
    {
        return false;
    }

    @Override
    public List<ICommand> getCommands()
    {
        return commands;
    }
}
