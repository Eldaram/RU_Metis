package src.main.java.listener;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.internal.entities.TextChannelImpl;
import org.jetbrains.annotations.NotNull;
import src.main.java.BotInfo;

public class MetisGuildListener extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        event.getMember().getUser().openPrivateChannel().queue(channel -> {
            channel.sendMessage(BotInfo.welcomeMessage).queue();
        });
    }

    @Override
    public void onGuildMemberRoleAdd(@NotNull GuildMemberRoleAddEvent event) {
        if (BotInfo.mainId == null)
            return;
        for (int i = 0; i < event.getRoles().size(); i++) {
            for (int j = 0; j < BotInfo.roleInteractionId.size(); j++) {
                if (BotInfo.roleInteractionId.get(j) == event.getRoles().get(i).getId()) {
                    event.getGuild().getChannelById(TextChannel.class, BotInfo.mainId)
                            .sendMessage(BotInfo.roleInteractionMsg.get(j)).queue();
                }
            }
        }
    }
}
