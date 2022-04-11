package src.main.java.command;

import net.dv8tion.jda.api.entities.Webhook;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import src.main.java.webhooks.Tools;

import java.util.List;

public class sendFromWebhooksCom implements TemplateCom{
    private final String name = "sendFrom";

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
        return name;
    }

    @Override
    public CommandListUpdateAction addSlashCom(CommandListUpdateAction list) {
        list.addCommands(Commands.slash(name, "Envoie un message depuis un PnJ")
                .addOption(OptionType.STRING, "Nom", "Le nom du PnJ")
                .addOption(OptionType.STRING, "Texte", "Le texte a faire dire au PnJ"));
        return list;
    }
}
