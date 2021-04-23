package com.odex64.crimson;

import com.odex64.crimson.commands.CommandDispatcher;
import com.odex64.crimson.utils.Config;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Bot
{
    public static void main(String[] args) throws LoginException
    {
        if (args.length >= 1)
        {
            if (args.length == 2)
                Config.ownerId = args[1];
            JDABuilder.createDefault(args[0]).setActivity(Activity.playing(Config.prefix + "help")).addEventListeners(new CommandDispatcher()).build();
        } else
            JDABuilder.createDefault(System.getenv().get("TOKEN")).setActivity(Activity.playing(Config.prefix + "help")).addEventListeners(new CommandDispatcher()).build();
    }
}
