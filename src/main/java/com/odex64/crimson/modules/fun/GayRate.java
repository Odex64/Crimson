package com.odex64.crimson.modules.fun;

import com.odex64.crimson.commands.CommandContext;
import com.odex64.crimson.commands.ICommand;
import com.odex64.crimson.commands.IReaction;
import com.odex64.crimson.utils.Config;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.util.Arrays;
import java.util.List;

public class GayRate implements ICommand
{
    @Override
    public void handle(CommandContext event)
    {
        List<Member> member = event.getMessage().getMentionedMembers();

        if (!member.isEmpty())
        {
            event.getChannel()
                    .sendMessage(
                            new MessageEmbed(null, "Gay'o'meter", member.get(0).getAsMention() + " is " + (Config.rand.nextInt(101)) + "% gay", null, null, 16758465, null, null, null, null, null, null, null))
                    .queue();
        }
    }

    @Override
    public List<String> getName()
    {
        return Arrays.asList("gay", "gayrate");
    }

    @Override
    public boolean isActive()
    {
        return true;
    }

    @Override
    public List<IReaction> getReactions()
    {
        return null;
    }

    @Override
    public List<Permission> getPermissions()
    {
        // TODO Auto-generated method stub
        return null;
    }
}
