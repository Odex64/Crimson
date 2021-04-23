package com.odex64.crimson.commands;

import com.odex64.crimson.modules.IModule;
import com.odex64.crimson.modules.ModuleManager;
import com.odex64.crimson.utils.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.react.GenericGuildMessageReactionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandDispatcher extends ListenerAdapter
{
    private ModuleManager moduleManager;

    public CommandDispatcher()
    {
        moduleManager = new ModuleManager();
    }

    @Override
    public void onReady(ReadyEvent event)
    {
        Config.botId = event.getJDA().getSelfUser().getId();

        Config.LOGGER.info("{} is ready", event.getJDA().getSelfUser().getAsTag());
        // Displays the status of each module/command
        Config.LOGGER.info("-------------------------------------");
        for (IModule module : moduleManager.getModules())
        {
            Config.LOGGER.info(module.getName() + " : " + module.isActive());
            for (ICommand cmd : module.getCommands())
            {
                Config.LOGGER.info("   " + cmd.getName() + " : " + cmd.isActive());
            }
        }
        Config.LOGGER.info("-------------------------------------");
    }

    // Main Message Event
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event)
    {
        User user = event.getAuthor();

        if (user.isBot() || event.isWebhookMessage())
            return;

        String prefix = Config.prefix;
        String raw = event.getMessage().getContentRaw();

        // Auto Generated Help Command
        if (raw.equalsIgnoreCase(prefix + "help"))
        {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("Help");

            for (IModule module : moduleManager.getModules())
            {
                if (module.isActive())
                {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (ICommand cmd : module.getCommands())
                    {
                        if (cmd.isActive())
                        {
                            stringBuilder.append(String.join(" / ", cmd.getName()) + "\n");
                        }
                    }
                    builder.addField(module.getName(), stringBuilder.toString(), true);
                }
            }

            builder.setColor(event.getGuild().getMemberById(Config.botId).getColorRaw());
            event.getChannel().sendMessage(builder.build()).queue();
        }

        // Sends out requests to all the modules
        if (raw.startsWith(prefix))
            moduleManager.handleMessage(event);
    }

    @Override
    public void onGenericGuildMessageReaction(GenericGuildMessageReactionEvent event)
    {
        if (event.getUser().isBot())
            return;
        moduleManager.handleReaction(event);
    }

    @Override
    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event)
    {
        if (event.getMember().getId().equals(Config.botId))
        {
            moduleManager.handleBotLeft(event);
        }
    }
}
