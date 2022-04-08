package src.main.java.webhooks;

import club.minnced.discord.webhook.external.JDAWebhookClient;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Webhook;
import net.dv8tion.jda.api.entities.WebhookClient;
import net.dv8tion.jda.internal.entities.WebhookImpl;

public class Tools {
    static public void sendMessage(Webhook webhook, String txt)
    {
        MessageBuilder message = new MessageBuilder();
        message.append(txt);
        try (JDAWebhookClient client = JDAWebhookClient.from(webhook)) {
            client.send(message.build());
        }
    }
}
