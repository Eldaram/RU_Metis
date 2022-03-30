package src.main.java.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface TemplateCom {
    void slash(SlashCommandInteractionEvent event);
    void msg(MessageReceivedEvent event);
    String getName();
}
