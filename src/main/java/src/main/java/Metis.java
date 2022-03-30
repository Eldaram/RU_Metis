package src.main.java;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import src.main.java.listener.Bot;
import src.main.java.listener.ToolsSlash;
import src.main.java.ready.ReadyListener;

import javax.security.auth.login.LoginException;

public class Metis {
    public static void main(String[] args) {
        JDABuilder builder = JDABuilder.createDefault("NTUxMzEzNDMzMjYyNTU1MTM3.XHo4pQ.HwHhWFeGKHRvRHQ_T_dYPz1fgzk");
        builder.addEventListeners(new ReadyListener())
                .addEventListeners(new Bot())
                .setActivity(Activity.playing("Type /ping"));
        JDA jda = null;
        try {
            jda = builder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
        //ToolsSlash.initSlash(jda);
        System.out.print("This is a pure test.\n");
    }
}
