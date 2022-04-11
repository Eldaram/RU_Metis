package src.main.java.command;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import java.util.List;

public class PingCom implements TemplateCom{
    private static final String name = "ping";

    @Override
    public void slash(SlashCommandInteractionEvent event)
    {
        Long time = System.currentTimeMillis();
        event.reply("Pong!").setEphemeral(true) // reply or acknowledge
                .flatMap(v ->
                        event.getHook().editOriginalFormat("Pong: %d ms", System.currentTimeMillis() - time) // then edit original
                ).queue(); // Queue both reply and edit
    }

    @Override
    public void msg(MessageReceivedEvent event, List<String> parsed)
    {
        MessageChannel channel = event.getChannel();
        long time = System.currentTimeMillis();
        channel.sendMessage("Pong!") /* => RestAction<Message> */
                .queue(response /* => Message */ -> {
                    response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue();
                });
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public CommandListUpdateAction addSlashCom(CommandListUpdateAction list) {
        list.addCommands(Commands.slash(name, "Renvoie le ping du bot"));
        return list;
    }
}
