package src.main.java.listener;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

public class ToolsSlash {
    public static void initSlash(JDA jda)
    {
        if (jda == null)
        {
            return;
        }
        jda.upsertCommand("ping", "Calculate ping of the bot").queue();
        CommandDataImpl calc = new CommandDataImpl("roll", "Effectue un tirage");
        calc.addOption(OptionType.STRING, "content", "Le calcule");
        jda.upsertCommand(calc).queue();
        System.out.print("Slashes has been init.\n");
    }
}
