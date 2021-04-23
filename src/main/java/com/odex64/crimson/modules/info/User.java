package com.odex64.crimson.modules.info;

import com.odex64.crimson.commands.CommandContext;
import com.odex64.crimson.commands.ICommand;
import com.odex64.crimson.commands.IReaction;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageEmbed.Thumbnail;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public class User implements ICommand
{
    @Override
    public void handle(CommandContext event)
    {
        List<Member> member = event.getMessage().getMentionedMembers();
        if (!member.isEmpty())
        {
            event.getChannel().sendMessage(new MessageEmbed(null, member.get(0).getEffectiveName() + " info", getDescription(member.get(0)), null, null, event.getGuild().getSelfMember().getColorRaw(),
                    new Thumbnail(member.get(0).getUser().getEffectiveAvatarUrl(), null, 32, 32), null, null, null, null, null, null)).queue();
        }
    }

    public String getDescription(Member member)
    {
        return ("\nAccount Created: " + member.getTimeCreated().format(DateTimeFormatter.ISO_LOCAL_DATE) + "\nJoined Server: " + member.getTimeJoined().format(DateTimeFormatter.ISO_LOCAL_DATE) + "\nID: "
                + member.getId() + isMod(member));
    }

    private String isMod(Member member)
    {
        EnumSet<Permission> perms = member.getPermissions();
        if (perms.contains(Permission.ADMINISTRATOR))
            return "\nAdmin";
        else if (checkPerms(member, Permission.KICK_MEMBERS, Permission.BAN_MEMBERS))
            return "\nMod";
        return "";
    }

    private boolean checkPerms(Member member, Permission... perms)
    {
        for (Permission perm : perms)
        {
            if (member.getPermissions().contains(perm))
                return true;
        }
        return false;
    }

    @Override
    public List<String> getName()
    {
        return Arrays.asList("user", "userinfo");
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
