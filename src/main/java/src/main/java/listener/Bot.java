package src.main.java.listener;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import src.main.java.command.PingCom;
import src.main.java.command.TemplateCom;

import java.util.Arrays;
import java.util.List;

public class Bot extends ListenerAdapter {
    //add here every commands
    static final List<TemplateCom> listCom = Arrays.asList(new PingCom());

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        Message msg = event.getMessage();
        for (TemplateCom com: listCom) {
            if (msg.equals(com.getName())) com.msg(event);
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
