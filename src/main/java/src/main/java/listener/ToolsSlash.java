package src.main.java.listener;

import net.dv8tion.jda.api.JDA;

public class ToolsSlash {
    public static void initSlash(JDA jda)
    {
        if (jda == null)
        {
            return;
        }
        jda.upsertCommand("ping", "Calculate ping of the bot").queue();
        System.out.print("Ping has been init.\n");
    }
}
