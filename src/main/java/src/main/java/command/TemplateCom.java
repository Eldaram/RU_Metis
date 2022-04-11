package src.main.java.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import java.util.List;

public interface TemplateCom {
    void slash(SlashCommandInteractionEvent event);
    void msg(MessageReceivedEvent event, List<String> parsed);
    String getName();
    CommandListUpdateAction addSlashCom(CommandListUpdateAction list);
}
