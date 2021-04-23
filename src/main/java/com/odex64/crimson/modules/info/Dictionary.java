package com.odex64.crimson.modules.info;

import com.odex64.crimson.commands.CommandContext;
import com.odex64.crimson.commands.ICommand;
import com.odex64.crimson.commands.IReaction;
import net.dv8tion.jda.api.Permission;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dictionary implements ICommand
{
    private List<IReaction> messages = new ArrayList<>();

    @Override
    public void handle(CommandContext event)
    {
        String message = String.join(" ", event.getArgs());
        if (!message.trim().isEmpty())
        {
            String temp = "";
            try
            {
                URL url = new URL("http://api.urbandictionary.com/v0/define?term=" + URLEncoder.encode(message, "UTF-8"));
                BufferedReader read = new BufferedReader(new InputStreamReader(url.openStream()));
                String i;
                while ((i = read.readLine()) != null)
                    temp += i;
                read.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }

            JSONObject result = new JSONObject(temp);

            if (result.getJSONArray("list").isNull(0))
            {
                event.getChannel().sendMessage("no results!").queue();
            } else
            {
                List<String> definitions = fixBrackets(result.getJSONArray("list"));
                event.getChannel().sendMessage(definitions.get(0)).queue(i -> {
                    i.addReaction("U+2B05").queue();
                    i.addReaction("U+27A1").queue();
                    messages.add(new DictionaryVote(i.getId(), definitions));
                });
            }
        }
    }

    public List<String> fixBrackets(JSONArray json)
    {
        List<String> definitions = new ArrayList<>();
        for (int i = 0; !json.isNull(i); i++)
            definitions.add(((JSONObject) json.get(i)).getString("definition").replaceAll("[\\[\\]]", ""));
        return definitions;
    }

    @Override
    public List<String> getName()
    {
        return Arrays.asList("urban", "meaning", "dictionary");
    }

    @Override
    public boolean isActive()
    {
        return true;
    }

    @Override
    public List<IReaction> getReactions()
    {
        return messages;
    }

    @Override
    public List<Permission> getPermissions()
    {
        // TODO Auto-generated method stub
        return null;
    }
}
