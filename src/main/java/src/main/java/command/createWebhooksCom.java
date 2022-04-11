package src.main.java.command;

import net.dv8tion.jda.api.entities.Icon;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class createWebhooksCom implements TemplateCom{

    private static final String name = "createWebhooks";

    @Override
    public void slash(SlashCommandInteractionEvent event) {

    }

    @Override
    public void msg(MessageReceivedEvent event, List<String> parsed) {
        try {
            File a = new File("C:\\Users\\draga\\Pictures\\tbomonfrere.jpg");
            event.getTextChannel().createWebhook("Hello bot").setAvatar(Icon.from(a)).queue();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public CommandListUpdateAction addSlashCom(CommandListUpdateAction list)
    {
        list.addCommands(Commands.slash(name, "Créer un Webhook")
                .addOption(OptionType.STRING, "Nom", "Le nom du webooks")
                .addOption(OptionType.ATTACHMENT, "Image", "L'image du Webhooks"));
        return list;
    }
}
