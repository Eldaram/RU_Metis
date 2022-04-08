package src.main.java.command;

import net.dv8tion.jda.api.entities.Webhook;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import src.main.java.webhooks.Tools;

import java.util.List;

public class sendFromWebhooksCom implements TemplateCom{
    @Override
    public void slash(SlashCommandInteractionEvent event) {
        List<Webhook> web = event.getTextChannel().retrieveWebhooks().complete();
        for (Webhook e:
                web) {
            Tools.sendMessage(e, "Hello world!");
        }
    }

    @Override
    public void msg(MessageReceivedEvent event, List<String> parsed) {
        List<Webhook> web = event.getTextChannel().retrieveWebhooks().complete();
        for (Webhook e:
             web) {
            Tools.sendMessage(e, "Hello world!");
        }
    }

    @Override
    public String getName() {
        return "sendFrom";
    }
}
