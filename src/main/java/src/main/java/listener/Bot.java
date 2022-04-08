package src.main.java.listener;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import src.main.java.command.*;
import src.main.java.tools.Parsing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bot extends ListenerAdapter {
    //add here every commands
    static final List<TemplateCom> listCom = Arrays.asList(new PingCom(), new ComputeCom(), new sendFromWebhooksCom(),
            new createWebhooksCom());

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        Message msg = event.getMessage();
        List<String> parse = Parsing.parse(msg.getContentRaw());
        if (parse.size() == 0)
            return;
        for (TemplateCom com: listCom) {
            if (parse.get(0).equals("!" + com.getName())) com.msg(event, parse);
        }
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event)
    {
        for (TemplateCom com: listCom) {
            if (event.getName().equals(com.getName())) com.slash(event);
        }
    }
}
