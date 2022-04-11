package src.main.java.tools;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import src.main.java.command.TemplateCom;

import java.util.List;

public class ToolsSlash {
    public static void initSlash(JDA jda, List<TemplateCom> listCom)
    {
        if (jda == null)
        {
            return;
        }
        CommandListUpdateAction list = jda.updateCommands();
        for (TemplateCom elt : listCom) {
            list = elt.addSlashCom(list);
        }
        list.queue();
        System.out.print("Slashes has been init.\n");
    }
}
