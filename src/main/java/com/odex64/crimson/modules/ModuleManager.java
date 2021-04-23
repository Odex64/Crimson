package com.odex64.crimson.modules;

import com.odex64.crimson.commands.CommandContext;
import com.odex64.crimson.commands.ICommand;
import com.odex64.crimson.commands.IReaction;
import com.odex64.crimson.utils.Config;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.react.GenericGuildMessageReactionEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class ModuleManager
{
    // List of Modules
    private final List<IModule> modules = new ArrayList<>();

    // Register modules
    public ModuleManager()
    {
        modules.add(new Info());
        modules.add(new Fun());
        // modules.add(new Music());
        modules.add(new Moderation());
    }

    // Searches the through the different modules for a command.
    private ICommand getCommand(String search, Member member)
    {
        for (IModule module : modules)
        {
            if (module.isActive() || member.getId().equals(Config.ownerId))
            {
                for (ICommand cmd : module.getCommands())
                {
                    if ((cmd.isActive() || member.getId().equals(Config.ownerId)) && cmd.getPermissions() == null || member.hasPermission(cmd.getPermissions()))
                    {
                        if (cmd.getName().contains(search))
                            return cmd;
                    }
                }
            }
        }
        return null;
    }

    // This will handle command executing for all commands
    public void handleMessage(GuildMessageReceivedEvent event)
    {
        String[] split = event.getMessage().getContentRaw().replaceFirst("(?i)" + Pattern.quote(Config.prefix), "").split("\\s+");

        ICommand cmd = getCommand(split[0], event.getMember());

        if (cmd != null)
        {
            List<String> args = Arrays.asList(split).subList(1, split.length);
            CommandContext ctx = new CommandContext(event, args);
            cmd.handle(ctx);
        }
    }

    public IReaction getReaction(String id)
    {
        for (IModule module : modules)
        {
            if (module.isActive())
            {
                for (ICommand cmd : module.getCommands())
                {
                    if (cmd.isActive() && cmd.getReactions() != null)
                    {
                        for (IReaction react : cmd.getReactions())
                        {
                            if (react.getMessageId().equals(id))
                                return react;
                        }
                    }
                }
            }
        }
        return null;
    }

    public void handleReaction(GenericGuildMessageReactionEvent event)
    {
        IReaction react = getReaction(event.getMessageId());
        if (react != null)
        {
            react.handleReaction(event);
        }
    }

    private ICommand getChannel()
    {
        for (IModule module : modules)
        {
            for (ICommand cmd : module.getCommands())
            {
                if (cmd.getName().contains("play"))
                    return cmd;
            }
        }
        return null;
    }

    public void handleBotLeft(GuildVoiceLeaveEvent event)
    {
        if (event.getEntity().equals(event.getGuild().getSelfMember()))
        {
            //getChannel().handle(new CommandContext(event, Arrays.asList("stop")));
        }
    }

    public List<IModule> getModules()
    {
        return modules;
    }
}
